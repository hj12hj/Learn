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
package com.flydean56.dnsdot;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.dns.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.util.NetUtil;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class DoTClient {

    public static void main(String[] args) throws Exception {
        DoTClient client = new DoTClient();
        final String dnsServer = "223.5.5.5";
        final int dnsPort = 853;
        final String queryDomain = "www.flydean.com";
        client.startClient(dnsServer,dnsPort,queryDomain);
    }

    private  void startClient(String dnsServer, int dnsPort, String queryDomain) throws SSLException, InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            SslProvider provider =
                    SslProvider.isAlpnSupported(SslProvider.OPENSSL)? SslProvider.OPENSSL : SslProvider.JDK;
            final SslContext sslContext = SslContextBuilder.forClient()
                    .sslProvider(provider)
                    .protocols("TLSv1.3", "TLSv1.2")
                    .build();

            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new DotChannelInitializer(sslContext, dnsServer, dnsPort));
            final Channel ch = b.connect(dnsServer, dnsPort).sync().channel();

            int randomID = (int) (System.currentTimeMillis() / 1000);
            DnsQuery query = new DefaultDnsQuery(randomID, DnsOpCode.QUERY)
                    .setRecord(DnsSection.QUESTION, new DefaultDnsQuestion(queryDomain, DnsRecordType.A));
            ch.writeAndFlush(query).sync();
            boolean result = ch.closeFuture().await(10, TimeUnit.SECONDS);
            if (!result) {
                log.error("DNS查询失败");
                ch.close().sync();
            }
        } finally {
            group.shutdownGracefully();
        }
    }

}
