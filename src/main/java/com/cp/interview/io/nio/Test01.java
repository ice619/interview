package com.cp.interview.io.nio;
import	java.nio.ByteBuffer;

/**
 * Description:Buffer
 *
 * @author chenpeng
 * @date 2019/8/28 16:15
 */
public class Test01 {
    public static void main(String[] args) {
        // 1.指定缓冲区大小1024
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        // 2.向缓冲区存放5个数据
        System.out.println("--------------------");
        buf.put("abcde".getBytes());
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        // 3.开启读模式
        System.out.println("----------开启读模式...----------");
        buf.flip();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));
        // 4.开启重复读模式
        System.out.println("----------重复读模式...----------");
        buf.rewind();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        byte[] bytes2 = new byte[buf.limit()];
        buf.get(bytes2);
        System.out.println(new String(bytes2, 0, bytes2.length));
        // 5.clean 清空缓冲区  数据依然存在,只不过数据被遗忘
        System.out.println("----------清空缓冲区...----------");
        buf.clear();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println((char)buf.get());

        byte[] bytes3 = new byte[buf.limit() - 1];
        buf.get(bytes3);
        System.out.println(new String(bytes3, 0, bytes3.length - 1));

    }
}