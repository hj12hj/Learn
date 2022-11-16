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
package com.flydean17.protobuf;

public interface StudentOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.flydean17.protobuf.Student)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 age = 1;</code>
   * @return Whether the age field is set.
   */
  boolean hasAge();
  /**
   * <code>int32 age = 1;</code>
   * @return The age.
   */
  int getAge();

  /**
   * <code>string name = 2;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
