package com.ewerp.mud.sessions.terminal;

import java.io.*;
import java.net.Socket;

/**
 * Copyright 2012 Curtis Boyden
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class MockSocket extends Socket {
    public byte[] buffer = new byte[1024];

    public InputStream inputStream = new ByteArrayInputStream(buffer);
    public OutputStream outputStream = new ByteArrayOutputStream();

    public boolean closed = false;

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return outputStream;
    }

    @Override
    public synchronized void close() throws IOException {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public boolean isInputShutdown() {
        return closed;
    }

    @Override
    public boolean isOutputShutdown() {
        return closed;
    }
}
