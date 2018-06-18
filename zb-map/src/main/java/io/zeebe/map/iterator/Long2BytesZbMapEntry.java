/*
 * Copyright © 2017 camunda services GmbH (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.zeebe.map.iterator;

import io.zeebe.map.types.ByteArrayValueHandler;
import io.zeebe.map.types.LongKeyHandler;
import org.agrona.DirectBuffer;

public class Long2BytesZbMapEntry implements ZbMapEntry<LongKeyHandler, ByteArrayValueHandler> {
  private long key;
  private DirectBuffer value;

  @Override
  public void read(LongKeyHandler keyHander, ByteArrayValueHandler valueHandler) {
    key = keyHander.theKey;
    value = valueHandler.valueBuffer;
  }

  public long getKey() {
    return key;
  }

  public DirectBuffer getValue() {
    return value;
  }
}
