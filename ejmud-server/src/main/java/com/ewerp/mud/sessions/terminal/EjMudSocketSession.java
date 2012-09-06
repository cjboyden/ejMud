package com.ewerp.mud.sessions.terminal;

import com.ewerp.engine.commands.IMessage;
import com.ewerp.engine.plugins.IPluginManager;
import com.ewerp.engine.sessions.terminal.SocketSession;
import com.ewerp.mud.sessions.terminal.state.EjMudLogin;
import com.ewerp.mud.sessions.terminal.state.IEjMudTerminalState;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
public class EjMudSocketSession extends SocketSession implements IEjMudTerminalSession {
    private Stack<IEjMudTerminalState> stateStack = new Stack<IEjMudTerminalState>();

    public EjMudSocketSession(Socket socket) {
        super(socket);
    }

    @Override
    public void processMessage(IMessage message) {
        EjMudTerminalSession.processMessage(this, message);
    }

    @Override
    public void run() {
        EjMudTerminalSession.run(this);
    }

    @Override
    public void setPluginManager(IPluginManager pluginManager) {
        super.setPluginManager(pluginManager);

        EjMudLogin loginState = new EjMudLogin();
        loginState.setSession(this);
        loginState.setPluginManager(pluginManager);
        stateStack.push(loginState);

        loginState.start();
    }

    @Override
    public IPluginManager getPluginManager() {
        return super.getPluginManager();
    }

    @Override
    public IEjMudTerminalState getCurrentState() {
        return stateStack.peek();
    }
}
