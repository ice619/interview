package com.cp.interview.io.nio;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import	java.nio.file.StandardOpenOption;

import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * Description: 直接缓冲区 非直接缓冲区
 *
 * @author chenpeng
 * @date 2019/8/28 16:45
 */
public class Test03 {
    public static void main(String[] args) throws IOException {
//        Test03.test1();
//        Test03.test2();
        Test03.test3();
    }

    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     */
    public static void test1() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("e://1.mp4"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("e://2.mp4"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
        //内存映射文件
        MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        System.out.println("缓冲区是否是直接缓冲区：" + inMappedByteBuffer.isDirect() +","+ outMappedByteBuffer.isDirect());
        //直接对缓冲区进行数据的读写操作
        byte[] bytes = new byte[inMappedByteBuffer.limit()];

        inMappedByteBuffer.get(bytes);
        outMappedByteBuffer.put(bytes);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    /**
     * 利用通道完成文件的复制(非直接缓冲区)
     * @throws IOException
     */
    public static void test2() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream("e://1.mp4");
        FileOutputStream fileOutputStream = new FileOutputStream("e://3.mp4");
        //获取通道
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();
        //分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("缓冲区是否是直接缓冲区：" + byteBuffer.isDirect());
        while (fileInputStreamChannel.read(byteBuffer) != -1){
            //开启读模式
            byteBuffer.flip();
            //将缓冲区中的数据写入通道中
            fileOutputStreamChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        fileOutputStreamChannel.close();
        fileInputStreamChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    /**
     * 分散读取与聚集写入
     * 分散读取(scattering Reads)：将通道中的数据分散到多个缓冲区中
     * 聚集写入(gathering Writes)：将多个缓冲区的数据聚集到通道中
     * @throws IOException
     */
    public static void test3() throws IOException {
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("e://1.txt", "rw");
        // 1.获取通道
        FileChannel channel = randomAccessFile1.getChannel();
        // 2.分配指定大小的指定缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(2);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        // 3.分散读取
        ByteBuffer[] bufs = {buffer1,buffer2};
        channel.read(bufs);
        for (ByteBuffer byteBuffer : bufs) {
            // 切换为读取模式
            byteBuffer.flip();
        }
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("------------------分散读取线分割--------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));
        // 聚集写入
        RandomAccessFile randomAccessFile2 = new RandomAccessFile("e://4.txt", "rw");
        FileChannel channel2 = randomAccessFile2.getChannel();
        channel2.write(bufs);

        channel2.close();
        channel.close();
        randomAccessFile2.close();
        randomAccessFile1.close();

    }
}