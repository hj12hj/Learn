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

package com.flydean62.redis;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class RedisClient {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 6379;
        startClient(host, port);
    }

    private static void startClient(String host, int port) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new RedisChannelInitializer());
            // 启动客户端
            Channel ch = b.connect(host, port).sync().channel();
            // 从stdin中读取命令
            System.out.println("输入redis命令(输入quit退出)");
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                final String input = in.readLine();
                final String line = input != null ? input.trim() : null;
                if (line == null || "quit".equalsIgnoreCase(line)) {
                    ch.close().sync();
                    break;
                } else if (line.isEmpty()) {
                    continue;
                }
                // 将命令发送到服务器端
                lastWriteFuture = ch.writeAndFlush(line);
                lastWriteFuture.addListener(future -> {
                    if (!future.isSuccess()) {
                        log.error("服务器端写入错误");
                    }
                });
            }
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            group.shutdownGracefully();
        }
    }

}
