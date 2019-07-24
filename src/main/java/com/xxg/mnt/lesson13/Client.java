package com.xxg.mnt.lesson13;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * @program: mnt
 * @description:
 * @author: liangzr
 * @create: 2019-07-23 14:11
 */
public class Client {
    public static void main(String[] args) throws Exception{
        SSLContext sslContext=getSSLContext();
    }
    public static SSLContext getSSLContext() throws Exception{
        SSLContext ctx = SSLContext.getInstance("SSL");

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");

        KeyStore ks = KeyStore.getInstance("JKS");
        KeyStore tks = KeyStore.getInstance("JKS");

        ks.load(new FileInputStream("F:\\code+ziliao\\��MINA��Netty��Twistedһ��ѧ��ϵ�н̳�Դ��\\mina-netty-twisted\\src\\main\\resources\\lesson13\\client\\kclient.keystore"), "123456".toCharArray());
        tks.load(new FileInputStream("F:\\code+ziliao\\��MINA��Netty��Twistedһ��ѧ��ϵ�н̳�Դ��\\mina-netty-twisted\\src\\main\\resources\\lesson13\\client\\tclient.keystore"), "123456".toCharArray());
        kmf.init(ks, "123456".toCharArray());
        tmf.init(tks);
        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        return ctx;
    }
}