package lobbyServer;
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

        try {
        	System.out.println("1");
            ServerBootstrap b = new ServerBootstrap();
            System.out.println("2");
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                        	System.out.println("3");
                            ChannelPipeline p = ch.pipeline();
                            System.out.println("4");
                            p.addLast(new ServerHandler());
                            System.out.println("5");
                        }
                    });
            System.out.println("6");
            ChannelFuture f = b.bind(11111).sync();
            System.out.println("7");
            f.channel().closeFuture().sync();
            System.out.println("8");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
