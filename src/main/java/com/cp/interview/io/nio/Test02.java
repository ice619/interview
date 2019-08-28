package com.cp.interview.io.nio;
import	java.nio.ByteBuffer;

/**
 * Description: mark与rest用法
 *
 * @author chenpeng
 * @date 2019/8/28 16:33
 */
public class Test02 {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("abcde".getBytes());
        buf.flip();
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes,0,2);
        buf.mark();
        System.out.println(new String(bytes, 0, 2));
        System.out.println(buf.position());
        buf.get(bytes, 2, 2);
        System.out.println(new String(bytes, 2, 2));
        System.out.println(buf.position());
        buf.reset();
        System.out.println("重置恢复到mark位置..");
        System.out.println(buf.position());

    }
}