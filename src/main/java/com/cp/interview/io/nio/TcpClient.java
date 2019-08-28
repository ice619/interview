package com.cp.interview.io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description: 使用多线程支持多个请求
 *
 * @author chenpeng
 * @date 2019/8/28 17:56
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        System.out.println("socket tcp 客户端启动....");
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我是陈鹏".getBytes());
        outputStream.close();
        socket.close();
    }
}

class TcpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("socket tcp服务器端启动....");
        ServerSocket serverSocket = new ServerSocket(8080);
        // 等待客户端请求
        try {
            while (true){
                Socket acceptSocket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = acceptSocket.getInputStream();
                            byte[] bytes = new byte[1024];
                            int len = inputStream.read(bytes);
                            String str = new String(bytes, 0, len);
                            System.out.println("服务器接受客户端内容:" + str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}