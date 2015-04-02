package lobbyServer;
import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
ArrayList<String> ArrayListUsername = new ArrayList<String>();
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf message = (ByteBuf) msg;

        byte[] bytes = new byte[message.readableBytes()];
        message.readBytes(bytes);
     
        ArrayListUsername.add(new String(bytes));
        
        for(int i=0;i<ArrayListUsername.size();i++){
        	System.out.println("array ke "+i+" "+ArrayListUsername.get(i));
        }
        
        //System.out.println(new String(bytes));
       // System.out.println(String.format("%s send %s.", ctx.channel().remoteAddress(), new String(bytes)));
 
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
