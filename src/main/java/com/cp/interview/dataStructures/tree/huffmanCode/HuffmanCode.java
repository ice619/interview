package com.cp.interview.dataStructures.tree.huffmanCode;
import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码 (数据压缩 解压)
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);//40
//
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));


       /*
       List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        //测试生成对应赫夫曼编码
        Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码表："+ huffmanCodes);

        //
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));//17

        */

        //发送huffmanCodeBytes

        //测试解码 解压缩
        //测试byteToBitString
//        System.out.println(byteToBitString((byte)1));
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
//        System.out.println("解码后原来字符串=" + new String(sourceBytes));

        //测试压缩文件
//        String srcFile = "C:\\Users\\cp\\Desktop\\a.jpg";
//        String dstFile = "C:\\Users\\cp\\Desktop\\dst.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件成功");

        //测试解压文件
        String zipFile = "C:\\Users\\cp\\Desktop\\dst.zip";
        String dstFile1 = "C:\\Users\\cp\\Desktop\\b1.jpg";
        unZipFile(zipFile, dstFile1);
        System.out.println("解压成功");

    }

    /**
     * 解压文件
     * @param zipFile
     * @param dstFile
     */
    public static void unZipFile(String zipFile, String dstFile){
        //定义文件输入流
        InputStream is = null;
        //输入流关联的对象流
        ObjectInputStream ois = null;
        //输出流
        FileOutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对文件进行压缩
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile){
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件输入流
        FileInputStream is = null;

        try {
             is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);

            //这里以对象流的方式写入赫夫曼编码，为了以后恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件,以后解压的时候用
            oos.writeObject(huffmanCodes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //完成数据解压
    /**
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
        //1.先得到huffmanBytes对应的二进制字符串 “100010101001010”
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for(int i = 0; i < huffmanBytes.length; i ++){
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        System.out.println("赫夫曼字节数组对应二进制字符串=" + stringBuilder.toString());

        //把字符串按照指定赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100  100->a
        Map<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        System.out.println("map=" + map);

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for(int i = 0; i < stringBuilder.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while(flag){
                //取出key
                String key = stringBuilder.substring(i, i + count);//i不动，count移动，直到匹配到一个字符
                b = map.get(key);
                if(b == null){//说明没有匹配到
                    count++;
                }else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count
        }
        //for循环结束后，list就存放了所有字符
        //把list中数据放入到byte[] 返回
        byte[] b = new byte[list.size()];
        for(int i = 0; i < b.length; i ++){
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte 转成一个二进制字符串
     * @param b
     * @param flag 标志是否需要补高位，如果是最后一个字节，无需补高位
     * @return 返回对应二进制字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b){
        //使用一个变量保存b
        int temp = b;//将b转成int
        //如果是正数，还需要补高位
        if(flag){
            temp |= 256;//按位或 256 1 0000 0000 \ 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//这里返回的是temp对应的二进制补码
        System.out.println("str=" + str);
        if(flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }

    }
    /**
     * 封装
     * @param bytes 原始字符串对应的字节数组
     * @return 返回经过赫夫曼编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //生成对应赫夫曼编码
        Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    /**
     * 将字符串对应的byte[]数组，通过生成对应的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     * @param bytes
     * @param huffmanCodes
     * @return
     * 举例 str="", => str.getBytes
     * 返回对应byte[] 8位对应一个byte
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){

        //1.利用huffmanCodes 将 bytes 转成赫夫曼编码对应的字符串
        for(byte b: bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println("测试stringBuilder=" + stringBuilder);
        //将stringBuilder 转成byte[]
        //统计返回byte[] huffmanCodeBytes长度
        //如果用一句话 int len = (stringBuilder.length() + 7) / 8
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录第几个byte
        for(int i = 0; i < stringBuilder.length(); i += 8){//步长为8
            String strByte;
            if(i + 8 > stringBuilder.length()){//不够8位
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte 放入huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index ++;
        }
        return huffmanCodeBytes;
    }

     //生成赫夫曼树对应的赫夫曼编码
    //1.将赫夫曼编码表存放在Map<Byte,String> 形式
     static Map<Byte,String> huffmanCodes = new HashMap<>();
     //2.生成赫夫曼编码表时，要拼接路径，用StringBuilder存储路径
    static StringBuilder stringBuilder = new StringBuilder();

    //重载
    private static Map<Byte,String> getCodes(Node root){
        if(root == null){
            return null;
        }
        //处理root左子树
        getCodes(root.left, "0",stringBuilder);
        //处理root右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的node节点所在叶子节点赫夫曼编码得到，并放入huffmanCodes
     * @param node
     * @param code 路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node != null){
            //判断当前node是叶子节点还是非叶子节点
            if(node.data == null){ //非叶子节点
                //递归处理
                //向左
                getCodes(node.left,"0",stringBuilder2);
                //向右
                getCodes(node.right,"1",stringBuilder2);
            }else {//说明是叶子节点
                //表示找到了某个叶子节点最后
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    //前序遍历
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     *
     * @param bytes
     * @return 返回list形式 [Node[date=97, weight = 5], Node[..], ...]
     */
    private static List<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历bytes 统计存储每个byte出现的此时 -》 map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for(byte b: bytes){
            Integer count = counts.get(b);
            if(count == null){//map中还没有这个字符数据
                counts.put(b,1);
            }else {
                counts.put(b,count + 1);
            }
        }
        //把每个键值对，转成一个node对象，并加入nodes集合
        for(Map.Entry<Byte,Integer> entry: counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size() > 1){
            //排序，从小到大
            Collections.sort(nodes);
            //取出前俩个最小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一个新的二叉树,它的根节点，没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已处理的俩颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(parent);
        }
        //最后的节点就是赫夫曼树的根节点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    Byte data;//存放数据（字符）本身，比如'a' => 97
    int weight;//权值
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
