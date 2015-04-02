package lobbyServer;
import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	ArrayList<String> ArrayListUsername = new ArrayList<String>();
	ArrayList<String> ArrayListPassword = new ArrayList<String>();
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf message = (ByteBuf) msg;

        byte[] bytes = new byte[message.readableBytes()];
        message.readBytes(bytes);
        String UP = new String(bytes);
        
        
        String[] UsernamePassword = UP.split(" ");
        String type = UsernamePassword[0]; // 004
        String Username = UsernamePassword[1]; // 004
        String Password = UsernamePassword[2]; // 034556
        
        if(type.equals("register")){
        	
        ArrayListUsername.add(Username);
        ArrayListPassword.add(Password);
        
        for(int i=0;i<ArrayListUsername.size();i++){
        	System.out.println("array key "+i+" "+ArrayListUsername.get(i));
        	System.out.println("array key "+i+" "+ArrayListPassword.get(i));
        }
        
        }else if(type.equals("login")){
        	
        	
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
