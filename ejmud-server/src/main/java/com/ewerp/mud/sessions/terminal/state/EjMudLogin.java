package com.ewerp.mud.sessions.terminal.state;

import com.ewerp.engine.plugins.IPluginManager;
import com.ewerp.mud.sessions.terminal.EjMudTerminalSession;
import com.ewerp.mud.sessions.terminal.IEjMudTerminalSession;

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
public class EjMudLogin implements IEjMudTerminalState {
    private IEjMudTerminalSession session;
    private IPluginManager pluginManager;

    @Override
    public void setSession(IEjMudTerminalSession session) {
        this.session = session;
    }

    @Override
    public void setPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public void processLine(String input) {
        EjMudTerminalSession.writeString(session, "You typed: " + input);
    }

    public void start() {
        out("Welcome to ejMud!\r\nLogin: ");
    }

    private void out(String message) {
        if(null != session) {
            EjMudTerminalSession.writeString(session, message);
        }
    }
}
