/*
 * Copyright 2022 learn-netty4 Project
 *
 * The learn-netty4 Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.flydean42.udtRendezvous;

import io.netty.util.internal.SocketUtils;

import java.net.InetSocketAddress;
import java.net.SocketAddress;


public class UDTByteRendezvousTwo extends UDTByteRendezvousBase {

    public UDTByteRendezvousTwo(int messageSize, SocketAddress myAddress, SocketAddress peerAddress) {
        super(messageSize, myAddress, peerAddress);
    }

    public static void main(String[] args) throws Exception {
        final int messageSize = 64 * 1024;
        final InetSocketAddress myAddress = SocketUtils.socketAddress("127.0.0.1", 8001);
        final InetSocketAddress peerAddress = SocketUtils.socketAddress("127.0.0.1", 8000);
        new UDTByteRendezvousTwo(messageSize, myAddress, peerAddress).run();
    }
}
