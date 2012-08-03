package com.ewerp.mud.sessions;

import com.ewerp.mud.plugins.ILifecycleListener;
import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.MockPluginManager;
import junit.framework.Assert;
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
public class TestSessionManager {
    public static ISessionManager generateSessionManager() {
        return new SessionManager();
    }

    @Test
    public void testSessionManager() {
        ISessionManager sessionManager = generateSessionManager();

        MockSessionFactoryOne mockSessionFactory = new MockSessionFactoryOne();

        MockPluginManager mockPluginManager = new MockPluginManager();

        ((IPlugin)sessionManager).registerPluginManager(mockPluginManager);
        sessionManager.addSessionFactory(mockSessionFactory);
        sessionManager.addSessionFactory(mockSessionFactory);

        ((ILifecycleListener) sessionManager).init();
        ((ILifecycleListener)sessionManager).start();

        MockSession mockSessionOne = new MockSession();
        MockSession mockSessionTwo = new MockSession();
        MockSession mockSessionThree = new MockSession();

        sessionManager.addSession(mockSessionOne);
        sessionManager.addSession(mockSessionThree);
        sessionManager.addSession(mockSessionThree);

        sessionManager.removeSession(mockSessionOne);
        sessionManager.removeSession(mockSessionTwo);
        sessionManager.removeSession(mockSessionOne);

        ((ILifecycleListener)sessionManager).stop();

        sessionManager.removeSession(mockSessionThree);

        sessionManager.removeSession(null);
        sessionManager.removeSessionFactory(null);

        Assert.assertEquals(0, mockSessionOne.shutdownCount);
        Assert.assertEquals(0, mockSessionTwo.shutdownCount);
        Assert.assertEquals(1, mockSessionThree.shutdownCount);

        Assert.assertNull(mockSessionTwo.pluginManager);
        Assert.assertSame(mockPluginManager, mockSessionOne.pluginManager);
        Assert.assertSame(mockPluginManager, mockSessionThree.pluginManager);

        Assert.assertEquals(1, mockSessionFactory.initCount);
        Assert.assertEquals(1, mockSessionFactory.startCount);
        Assert.assertEquals(1, mockSessionFactory.stopCount);

        Assert.assertNotNull(((IPlugin)sessionManager).getInterfaces());
        Assert.assertEquals(1, ((IPlugin)sessionManager).getInterfaces().size());
        Assert.assertEquals(ISessionManager.class, ((IPlugin)sessionManager).getInterfaces().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSessionManagerAddNullSession() {
        ISessionManager sessionManager = generateSessionManager();

        sessionManager.addSession(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSessionManagerRemoveNullSessionFactory() {
        ISessionManager sessionManager = generateSessionManager();

        sessionManager.addSessionFactory(null);
    }

    @Test
    public void testSupportedPluginInterfaces() {
        ISessionManager sessionManager = generateSessionManager();
        IPlugin plugin = (IPlugin)sessionManager;

        Assert.assertNotNull(plugin.getInterfaces());
        Assert.assertEquals(1, plugin.getInterfaces().size());
        Assert.assertEquals(ISessionManager.class, plugin.getInterfaces().get(0));
    }
}
