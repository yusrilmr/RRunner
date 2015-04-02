package lobbyServer;
import java.util.ArrayList;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(10);
        
        //String coba = "";

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                        	//ArrayList<String> coba = new ArrayList<String>();
                        	String Var = "";
                            ChannelPipeline p = ch.pipeline();
                            
                            
                            //Var = p.addLast(new ServerHandler()).toString();
                            p.addLast(new ServerHandler());
                            
                            //System.out.println("Try Variable: "+Var);
                           // System.out.println("nyoba "+coba.get(0));
                        //    Var = p.addLast(new ServerHandler());
                           
                        }
                    });
            ChannelFuture f = b.bind(11111).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
