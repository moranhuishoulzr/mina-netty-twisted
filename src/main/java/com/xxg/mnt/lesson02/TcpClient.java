package com.xxg.mnt.lesson02;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wucao on 17/2/27.
 */
public class TcpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        OutputStream out = null;
        try {

            socket = new Socket("localhost", 8080);
            out = socket.getOutputStream();

            String lines = "��ǰ";
            byte[] outputBytes = lines.getBytes("UTF-8");
            out.write(outputBytes);
            out.flush();

            Thread.sleep(1000);

            lines = "����";
            outputBytes = lines.getBytes("UTF-8");
            out.write(outputBytes);
            out.flush();

            Thread.sleep(1000);

            lines = "��\r\n���ǵ���˪\r\n��ͷ";
            outputBytes = lines.getBytes("UTF-8");
            out.write(outputBytes);
            out.flush();

            Thread.sleep(1000);

            lines = "������\r\n��ͷ˼����\r\n";
            outputBytes = lines.getBytes("UTF-8");
            out.write(outputBytes);
            out.flush();

        } finally {
            // �ر�����
            out.close();
            socket.close();
        }

    }
}
