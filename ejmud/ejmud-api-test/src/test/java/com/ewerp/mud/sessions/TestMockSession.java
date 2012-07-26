package com.ewerp.mud.sessions;

import com.ewerp.mud.commands.IMessage;
import com.ewerp.mud.commands.MockMessage;
import com.ewerp.mud.commands.MockMessageMeta;
import com.ewerp.mud.plugins.MockPluginManager;
import org.junit.Test;

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
public class TestMockSession {
    public static ISession generateSession() {
        return new MockSession();
    }

    @Test
    public void testMockSession() {
        ISession session = generateSession();

        MockPluginManager pluginManager = new MockPluginManager();
        session.setPluginManager(pluginManager);

        IMessage message = new MockMessage();
        message.addMeta(new MockMessageMeta());

        session.processMessage(message);

        session.shutdown();
    }
}
