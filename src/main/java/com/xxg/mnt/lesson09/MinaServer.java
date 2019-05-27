package com.xxg.mnt.lesson09;

import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @program: mnt
 * @description:
 * @author: liangzr
 * @create: 2019-05-27 11:30
 */
public class MinaServer {
    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), "\r\n", "\r\n")));
        acceptor.setHandler(new ServerHandle());
        acceptor.bind(new InetSocketAddress(8080));
    }
}

class ServerHandle extends IoHandlerAdapter {
    public ServerHandle() {
        super();
    }
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
          WriteFuture writeFuture = session.write("输出返回"+message);
          //String out="输出返回"+(String)message;
          writeFuture.addListener(new IoFutureListener<IoFuture>() {
              @Override
              public void operationComplete(IoFuture ioFuture) {
                  if(ioFuture.isDone()){
                      System.out.println("write操作成功");
                  }else {
                      System.out.println("write操作失败");
                  }
              }
          });
    }
}
