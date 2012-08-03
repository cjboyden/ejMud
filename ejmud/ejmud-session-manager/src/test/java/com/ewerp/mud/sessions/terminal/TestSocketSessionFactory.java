package com.ewerp.mud.sessions.terminal;

import com.ewerp.mud.commands.IMessage;
import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.MockPluginManager;
import com.ewerp.mud.properties.MockProperties;
import com.ewerp.mud.sessions.ISessionFactory;
import com.ewerp.mud.sessions.MockPropertiesOne;
import com.ewerp.mud.sessions.MockSessionManagerOne;
import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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
public class TestSocketSessionFactory {
    public static ISessionFactory generateSessionFactory() {
        return new SocketSessionFactory() {

            @Override
            protected ITerminalSession createSocketSession(Socket socket) {
                return new SocketSession(socket) {
                    @Override
                    public void processMessage(IMessage message) {
                    }

                    @Override
                    public void run() {
                    }
                };
            }
        };
    }

    @Test
    public void testDefaultConfiguration() throws InterruptedException, UnknownHostException, IOException {
        ISessionFactory sessionFactory = generateSessionFactory();

        MockSessionManagerOne mockSessionManager = new MockSessionManagerOne();

        mockSessionManager.addSessionFactory(sessionFactory);
        sessionFactory.registerSessionManager(mockSessionManager);

        Assert.assertNotNull(mockSessionManager.sessionFactoryList);
        Assert.assertEquals(1, mockSessionManager.sessionFactoryList.size());

        mockSessionManager.init();
        mockSessionManager.start();

        Thread.sleep(500);

        Socket socket = new Socket("localhost", 37209);

        Thread.sleep(500);

        Assert.assertNotNull(mockSessionManager.sessionList);
        Assert.assertEquals(1, mockSessionManager.sessionList.size());
        Assert.assertTrue(mockSessionManager.sessionList.get(0) instanceof SocketSession);

        mockSessionManager.stop();
    }

    @Test
    public void testCustomConfiguration() throws InterruptedException, UnknownHostException, IOException {
        ISessionFactory sessionFactory = generateSessionFactory();

        MockPluginManager mockPluginManager = new MockPluginManager();
        MockPropertiesOne mockProperties = new MockPropertiesOne();

        Map<String, String> defaultProperties = new HashMap<String, String>();
        defaultProperties.put(SocketSessionFactory.FIELD_SERVER_PORT, "37208");
        mockProperties.namespaces.put(MockProperties.DEFAULT_NAMESPACE, defaultProperties);

        mockPluginManager.addPlugin(mockProperties);

        ((IPlugin)sessionFactory).registerPluginManager(mockPluginManager);

        MockSessionManagerOne mockSessionManager = new MockSessionManagerOne();

        mockSessionManager.addSessionFactory(sessionFactory);
        sessionFactory.registerSessionManager(mockSessionManager);

        Assert.assertNotNull(mockSessionManager.sessionFactoryList);
        Assert.assertEquals(1, mockSessionManager.sessionFactoryList.size());

        mockSessionManager.init();
        mockSessionManager.start();

        Thread.sleep(500);

        Socket socket = new Socket("localhost", 37208);

        Thread.sleep(500);

        Assert.assertNotNull(mockSessionManager.sessionList);
        Assert.assertEquals(1, mockSessionManager.sessionList.size());
        Assert.assertTrue(mockSessionManager.sessionList.get(0) instanceof SocketSession);

        mockSessionManager.stop();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSessionManager() {
        ISessionFactory sessionFactory = generateSessionFactory();
        sessionFactory.registerSessionManager(null);
    }
}
