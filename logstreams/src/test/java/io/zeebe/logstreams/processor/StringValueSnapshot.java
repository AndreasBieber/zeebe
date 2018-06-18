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
package io.zeebe.logstreams.processor;

import io.zeebe.logstreams.spi.SnapshotSupport;
import java.io.InputStream;
import java.io.OutputStream;

public class StringValueSnapshot implements SnapshotSupport {
  private String value = null;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public long writeSnapshot(OutputStream outputStream) throws Exception {
    if (value != null) {
      outputStream.write(value.getBytes());

      return value.length();
    } else {
      return 0;
    }
  }

  @Override
  public void reset() {
    value = null;
  }

  @Override
  public void recoverFromSnapshot(InputStream inputStream) throws Exception {
    final byte[] buffer = new byte[1024];
    final int length = inputStream.read(buffer);

    if (length > 0) {
      value = new String(buffer, 0, length);
    }
  }
}
