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
package io.netty.buffer;

/**
 * 对ByteBuf的通用包装接口
 * The common interface for buffer wrappers and derived buffers.  Most users won't
 * need to use this interface.  It is used internally in most cases.
 */
public interface WrappedByteBuf extends ByteBuf {
    /**
     * 返回本buffer包装的ByteBuf
     * Returns this buffer's parent that this buffer is wrapping.
     */
    ByteBuf unwrap();
}
