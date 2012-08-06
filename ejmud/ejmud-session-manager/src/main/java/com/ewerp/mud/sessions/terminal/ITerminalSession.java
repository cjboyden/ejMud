package com.ewerp.mud.sessions.terminal;

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

import com.ewerp.mud.sessions.ISession;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for extending the terminal type sessions.
 */
public interface ITerminalSession extends ISession {

    /**
     * Get the InputStream for a terminal (Console, Socket, etc...)
     * @return Valid InputStream or null if not connected
     */
    InputStream getInputStream();

    /**
     * Get the OutputStream for a terminal (Console, Socket, etc...)
     * @return Valid OutputStream or null if not connected
     */
    OutputStream getOutputStream();

    /**
     * Is the inputstream still valid for writing to? (Is the socket open and the input stream available?)
     * @return
     */
    boolean isInputShutdown();

    /**
     * Is the outputstream still valid for writing to? (Is the socket open and the output stream available?)
     * @return
     */
    boolean isOutputShutdown();
}
