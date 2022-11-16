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
package com.flydean65.mqttclient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
class MqttClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final String clientId;
    private final String userName;
    private final String password;

    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast( MqttEncoder.INSTANCE);
        ch.pipeline().addLast(new MqttDecoder());
        ch.pipeline().addLast(new IdleStateHandler(0, 30, 0, TimeUnit.SECONDS));
        ch.pipeline().addLast(new MqttClientHandler(clientId, userName, password));
    }
}
