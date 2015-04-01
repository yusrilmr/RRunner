package client;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            final ClientHandler handler = new ClientHandler();

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(handler);
                        }
                    });

            ChannelFuture f = b.connect("127.0.0.1", 11111).sync();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String message = scanner.nextLine();

                if (message.equals("Exit")) {
                    break;
                } else {
                    handler.send(message);
                }
            }

            f.channel().close();
        } finally {
            group.shutdownGracefully();
        }
    }

}
