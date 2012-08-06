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
package io.netty.channel.socket.nio;

import io.netty.channel.EventExecutor;

import io.netty.channel.MultithreadEventLoop;

import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;


// 对应了一个Nio Single TreadEventLoop
public class NioEventLoop extends MultithreadEventLoop {

    public NioEventLoop() {
        this(0);// 默认创建0个线程，使用默认线程工厂
    }

    public NioEventLoop(int nThreads) {
        this(nThreads, null);
    }

    public NioEventLoop(int nThreads, ThreadFactory threadFactory) {
        super(nThreads, threadFactory);
    }

    public NioEventLoop(int nThreads, ThreadFactory threadFactory, final SelectorProvider selectorProvider) {
        super(nThreads, threadFactory, selectorProvider);
    }

    @Override
    // 重写newChild方法，来定义单线程的Executor的创建方式
    protected EventExecutor newChild(ThreadFactory threadFactory, Object... args) throws Exception {
        SelectorProvider selectorProvider;
        if (args == null || args.length == 0 || args[0] == null) {
            selectorProvider = SelectorProvider.provider();
        } else {
            selectorProvider = (SelectorProvider) args[0];// 或者指定
        }
        return new NioChildEventLoop(threadFactory, selectorProvider);
    }
}
