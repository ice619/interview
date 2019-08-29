package com.cp.interview.io.nio;
import	java.nio.ByteBuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Description: selector非阻塞
 *
 * @author chenpeng
 * @date 2019/8/29 10:05
 */
public class SelectorClient {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端已经启动....");
        // 1.创建通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        // 2.切换异步非阻塞
        socketChannel.configureBlocking(false);
        // 3.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            byteBuffer.put((new Date().toString()+": "+str).getBytes());
            // 4.切换读取模式
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();

        }

        socketChannel.close();

    }
}

class server {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器端已经启动....");
        // 1.创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2.切换异步非阻塞
        serverSocketChannel.configureBlocking(false);
        // 3.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8080));
        // 4.获取选择器
        Selector selector = Selector.open();
        // 5.将通道注册到选择器 "并且指定监听接受事件"
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6. 轮训式 获取选择 "已经准备就绪"的事件
        while (selector.select() > 0){
            // 7.获取当前选择器所有注册的"选择键(已经就绪的监听事件)"
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                // 8.获取准备就绪的事件
                SelectionKey selectionKey = it.next();
                // 9.判断具体是什么事件准备就绪
                if(selectionKey.isAcceptable()){
                    // 10.若"接受就绪",获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 11.设置非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 12.将该通道注册到服务器上
                    socketChannel.register(selector,SelectionKey.OP_READ);

                }else if (selectionKey.isReadable()){
                    // 13.获取当前选择器"就绪" 状态的通道
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    // 14.读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(byteBuffer)) > 0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }

                }

            }
            it.remove();

        }

    }
}