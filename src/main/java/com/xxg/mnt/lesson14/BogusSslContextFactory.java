///*
// *  Licensed to the Apache Software Foundation (ASF) under one
// *  or more contributor license agreements.  See the NOTICE file
// *  distributed with this work for additional information
// *  regarding copyright ownership.  The ASF licenses this file
// *  to you under the Apache License, Version 2.0 (the
// *  "License"); you may not use this file except in compliance
// *  with the License.  You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing,
// *  software distributed under the License is distributed on an
// *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// *  KIND, either express or implied.  See the License for the
// *  specific language governing permissions and limitations
// *  under the License.
// *
// */
//package com.xxg.mnt.lesson14;
//
//import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.SSLContext;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.GeneralSecurityException;
//import java.security.KeyStore;
//
///**
// * Factory to create a bogus SSLContext.
// *
// * @author The Apache MINA Project (dev@mina.apache.org)
// */
//public class BogusSslContextFactory {
//
//    //private static final String KEY_MANAGER_FACTORY_ALGORITHM;
//
////    static {
////        String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
////        if (algorithm == null) {
////            algorithm = KeyManagerFactory.getDefaultAlgorithm();
////        }
////
////        KEY_MANAGER_FACTORY_ALGORITHM = algorithm;
////    }
//
//
//
//    // NOTE: The keystore was generated using keytool:
//    //   keytool -genkey -alias bogus -keysize 512 -validity 3650
//    //           -keyalg RSA -dname "CN=bogus.com, OU=XXX CA,
//    //               O=Bogus Inc, L=Stockholm, S=Stockholm, C=SE"
//    //           -keypass boguspw -storepass boguspw -keystore bogus.cert
//
//    /**
//     * Bougus keystore password.
//     */
//    //private static final char[] BOGUS_PW = {'b', 'o', 'g', 'u', 's', 'p', 'w'};
//
//    private static SSLContext serverInstance = null;
//
//    private static SSLContext clientInstance = null;
//
//    /**
//     * Get SSLContext singleton.
//     *
//     * @return SSLContext
//     * @throws java.security.GeneralSecurityException
//     */
//    public static SSLContext getInstance(boolean server) throws GeneralSecurityException {
//        SSLContext retInstance = null;
//        if (server) {
//            synchronized (BogusSslContextFactory.class) {
//                if (serverInstance == null) {
//                    try {
//                        serverInstance = createBougusServerSslContext();
//                    } catch (Exception ioe) {
//                        throw new GeneralSecurityException(
//                                "Can't create Server SSLContext:" + ioe);
//                    }
//                }
//            }
//            retInstance = serverInstance;
//        } else {
//            synchronized (BogusSslContextFactory.class) {
//                if (clientInstance == null) {
//                    clientInstance = createBougusClientSslContext();
//                }
//            }
//            retInstance = clientInstance;
//        }
//        return retInstance;
//    }
//
//    private static SSLContext createBougusServerSslContext() throws GeneralSecurityException, IOException {
//        // Create keystore
//        KeyStore ks = KeyStore.getInstance("JKS");
//        InputStream in = null;
//        try {
//            in = BogusSslContextFactory.class.getResourceAsStream("F:\\code+ziliao\\《MINA、Netty、Twisted一起学》系列教程源码\\mina-netty-twisted\\src\\main\\resources\\lesson14\\bogus.cert");
//            ks.load(in, "123456".toCharArray());
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException ignored) {
//                }
//            }
//        }
//
//        // Set up key manager factory to use our key store
//        KeyManagerFactory kmf = KeyManagerFactory
//                .getInstance("SunX509");
//        kmf.init(ks, "123456".toCharArray());
//
//        // Initialize the SSLContext to work with our key managers.
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(kmf.getKeyManagers(), BogusTrustManagerFactory1.X509_MANAGERS, null);
//
//        return sslContext;
//    }
//
//    private static SSLContext createBougusClientSslContext() throws GeneralSecurityException {
//        SSLContext context = SSLContext.getInstance("TLS");
//        context.init(null, BogusTrustManagerFactory1.X509_MANAGERS, null);
//        return context;
//    }
//
//}
