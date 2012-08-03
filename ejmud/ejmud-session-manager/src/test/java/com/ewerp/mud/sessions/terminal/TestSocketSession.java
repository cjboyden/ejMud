package com.ewerp.mud.sessions.terminal;

import com.ewerp.mud.commands.IMessage;
import com.ewerp.mud.commands.MockMessage;
import com.ewerp.mud.plugins.MockPluginManager;
import com.ewerp.mud.sessions.ISession;
import com.ewerp.mud.sessions.MockSessionManager;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

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
public class TestSocketSession {
    @Test
    public void testTerminalSession() throws IOException {
        MockSocket socket = new MockSocket();
        MockSessionManager sessionManager = new MockSessionManager();
        MockPluginManager pluginManager = new MockPluginManager();

        MockMessage message = new MockMessage();

        ISession session = new SocketSession(socket) {

            @Override
            public void processMessage(IMessage message) {
            }

            @Override
            public void run() {
            }
        };

        sessionManager.addSession(session);
        session.setPluginManager(null);
        session.setPluginManager(pluginManager);

        session.processMessage(null);
        session.processMessage(message);

        Assert.assertEquals(socket.getInputStream(), ((ITerminalSession)session).getInputStream());
        Assert.assertEquals(socket.getOutputStream(), ((ITerminalSession)session).getOutputStream());

        session.shutdown();
    }
}
