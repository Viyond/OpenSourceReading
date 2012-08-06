/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.channel;

import io.netty.buffer.MessageBuf;
import io.netty.buffer.Unpooled;

import java.util.Queue;

public abstract class ChannelInboundMessageHandlerAdapter<I>
        extends ChannelInboundHandlerAdapter implements ChannelInboundMessageHandler<I> {

    @Override
    public MessageBuf<I> newInboundBuffer(ChannelHandlerContext ctx) throws Exception {
        return Unpooled.messageBuffer();
    }

    @Override
    public final void inboundBufferUpdated(ChannelHandlerContext ctx) throws Exception {
        MessageBuf<I> in = ctx.inboundMessageBuffer();
        for (;;) {
            I msg = in.poll();
            if (msg == null) {
                break;
            }
            try {
                messageReceived(ctx, msg);
            } catch (Throwable t) {
                ctx.fireExceptionCaught(t);
            }
        }
    }

    public abstract void messageReceived(ChannelHandlerContext ctx, I msg) throws Exception;
}
