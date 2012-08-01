package com.ewerp.mud.sessions.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    @Override
    public InputStream getInputStream() throws IOException {
        return super.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return super.getOutputStream();
    }

    @Override
    public synchronized void close() throws IOException {
        super.close();
    }

    @Override
    public boolean isClosed() {
        return super.isClosed();
    }

    @Override
    public boolean isInputShutdown() {
        return super.isInputShutdown();
    }

    @Override
    public boolean isOutputShutdown() {
        return super.isOutputShutdown();
    }
}
