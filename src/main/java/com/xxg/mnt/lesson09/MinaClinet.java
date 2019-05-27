package com.xxg.mnt.lesson09;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @program: mnt
 * @description:
 * @author: liangzr
 * @create: 2019-05-27 11:36
 */
public class MinaClinet {
    public static void main(String[] args) throws IOException {
        BufferedReader in = null;
        Socket socket = null;
        OutputStream out = null;
        try {
            socket = new Socket("localhost", 8080);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = socket.getOutputStream();
            // 第一次请求服务器
            String lines1 = "Hello\r\n";
            String lines2=lines1+"world\r\n";
            byte[] outputBytes1 = lines2.getBytes("UTF-8");
            out.write(outputBytes1);
            out.flush();
            while (true){
               String temp = in.readLine();
               System.out.println(temp);
            }
        } finally {
            // 关闭连接
            out.close();
            socket.close();
        }
    }
}
