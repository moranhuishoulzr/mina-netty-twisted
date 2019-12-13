package com.xxg.mnt.lesson11;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * Created by wucao on 17/2/27.
 */
public class SslClient {

    public static void main(String args[]) throws Exception {

        // �ͻ������θ�֤�飬������У���������������֤��ĺϷ���
        String certPath = "F:\\code+ziliao\\��MINA��Netty��Twistedһ��ѧ��ϵ�н̳�Դ��\\mina-netty-twisted\\src\\main\\resources\\cert.crt";
        InputStream inStream = null;
        Certificate certificate = null;
        try {
            inStream = new FileInputStream(certPath);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            certificate = cf.generateCertificate(inStream);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
        ks.setCertificateEntry("cert", certificate);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(ks);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        SSLSocketFactory socketFactory = sslContext.getSocketFactory();


        Socket socket = null;
        OutputStream out = null;

        try {

            socket = socketFactory.createSocket("localhost", 8080);
            out = socket.getOutputStream();

            // ���������
            String lines = "��ǰ���¹�\r\n���ǵ���˪\r\n��ͷ������\r\n��ͷ˼����\r\n";
            byte[] outputBytes = lines.getBytes("UTF-8");
            out.write(outputBytes);
            out.flush();

        } finally {
            // �ر�����
            out.close();
            socket.close();
        }

    }
}
