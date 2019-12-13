package com.xxg.mnt.lesson01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wucao on 17/2/27.
 */
public class TcpClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = null;
        OutputStream out = null;
        InputStream in = null;

        try{

            socket = new Socket("localhost", 8080);
            out = socket.getOutputStream();
            in = socket.getInputStream();

            // ���������
            out.write("��һ������".getBytes("UTF-8"));
            out.flush();

            // ��ȡ��������Ӧ�����
            byte[] byteArray = new byte[1024];
            int length = in.read(byteArray);
            System.out.println(new String(byteArray, 0, length, "UTF-8"));

            Thread.sleep(5000);

            // �ٴ����������
            out.write("�ڶ�������".getBytes("UTF-8"));
            out.flush();

            // �ٴλ�ȡ��������Ӧ�����
            byteArray = new byte[1024];
            length = in.read(byteArray);
            System.out.println(new String(byteArray, 0, length, "UTF-8"));


        } finally {
            // �ر�����
            in.close();
            out.close();
            socket.close();
        }

    }
}
