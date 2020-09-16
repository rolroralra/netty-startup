// modified from http://netty.io/wiki/user-guide-for-4.x.html
package nettystartup.h1.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        try {
            // 1. Discard
            // if do nothing, it means discard



            // 2. Received Data
//            while (buf.isReadable()) { // (1)
//                System.out.print((char) buf.readByte());
//                System.out.flush();
//            }

            // 3. Writing Echo Server
//            ctx.writeAndFlush(msg);


        } finally {
            buf.release(); // 이 부분은 두번째 시간에 설명합니다.
//            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
