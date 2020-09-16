package nettystartup.h1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.junit.Test;

public class ByteBufTest {
    public ByteBuf a(ByteBuf input) {
        System.out.println("a(): " + input.refCnt());
        return input;
    }

    public ByteBuf b(ByteBuf input) {
        try {
            System.out.println("b(): " + input.refCnt());
            System.out.println(input);
            System.out.println(input.toString(CharsetUtil.UTF_8));
            ByteBuf output = input.alloc().directBuffer(input.readableBytes() + 1);
            System.out.println("input readableBytes: " + input.readableBytes());
            System.out.println(output);
            return output;
        } finally {
            input.release();
            System.out.println("finally b " + input.refCnt());
        }
    }

    public void c(ByteBuf input) {
        System.out.println("c(): " + input.refCnt());
        System.out.println(input);
        System.out.println("["+input.toString(CharsetUtil.UTF_8)+"]");
        input.release();
        System.out.println("finally c " + input.refCnt());
    }

    @Test
    public void test() {
        ByteBuf buf = Unpooled.copiedBuffer("testString", CharsetUtil.UTF_8);
        System.out.println(buf.refCnt());
        c(b(a(buf)));
        System.out.println(buf.refCnt());
//        System.out.println(buf.toString(CharsetUtil.UTF_8));
    }
}
