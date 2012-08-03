package com.ewerp.mud.plugins;

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
public class TestPluginManager {

    public static IPluginManager generatePluginManager() {
        return PluginManager.getInstance();
    }

    public static String NONDEFAULT_NAMESPACE = "non-default-namespace";

    @Test
    public void testPluginManager() {
        IPluginManager pluginManager = generatePluginManager();

        MockPlugin plugin1 = new MockPluginOne();
        MockPlugin plugin2 = new MockPlugin();
        MockPlugin plugin4 = new MockPluginTwo();
        MockPlugin plugin5 = new MockPluginTwo();
        MockPlugin plugin6 = new MockPluginOne();

        Assert.assertNull(plugin1.pluginManager);
        Assert.assertNull(plugin2.pluginManager);
        Assert.assertNull(plugin4.pluginManager);
        Assert.assertNull(plugin5.pluginManager);
        Assert.assertNull(plugin6.pluginManager);

        pluginManager.addPlugin(plugin1); // Good
        pluginManager.addPlugin(NONDEFAULT_NAMESPACE, plugin2); // Good, cannot be fetched - no supported interfaces
        pluginManager.addPlugin(plugin1); // Nothing should happen, it already exists
        pluginManager.addPlugin(NONDEFAULT_NAMESPACE, plugin4); // Good
        pluginManager.addPlugin(plugin5); // Good
        pluginManager.addPlugin(NONDEFAULT_NAMESPACE, plugin5); // Good
        pluginManager.addPlugin(plugin6); // Good, should not be fetchable because plugin1 supports the same interfaces and was injected first
                                          // however it should still be in the system and executed accordingly

        // Make sure the plugin manager was associated with the plugins after adding them
        Assert.assertEquals(pluginManager, plugin1.pluginManager);
        Assert.assertEquals(pluginManager, plugin2.pluginManager);
        Assert.assertEquals(pluginManager, plugin4.pluginManager);
        Assert.assertEquals(pluginManager, plugin5.pluginManager);

        Assert.assertNull(pluginManager.getPlugin(TestPluginManager.class));
        Assert.assertNull(pluginManager.getPlugin(NONDEFAULT_NAMESPACE, TestPluginManager.class));

        // Plugins 2 & 3 cannot be fetched. Plugin 2 does not expose any interfaces, plugin 3 is the same as plugin1

        Assert.assertSame(plugin1, pluginManager.getPlugin(MockPluginOne.class));
        Assert.assertNull(pluginManager.getPlugin(NONDEFAULT_NAMESPACE, MockPluginOne.class));

        Assert.assertNotNull(pluginManager.getPlugins(MockPluginOne.class));
        Assert.assertEquals(2, pluginManager.getPlugins(MockPluginOne.class).size());

        Assert.assertNotNull(pluginManager.getPlugins(NONDEFAULT_NAMESPACE, MockPluginTwo.class));
        Assert.assertEquals(2, pluginManager.getPlugins(NONDEFAULT_NAMESPACE, MockPluginTwo.class).size());

        Assert.assertNull(pluginManager.getPlugins(NONDEFAULT_NAMESPACE, MockPluginOne.class));

        Assert.assertSame(plugin4, pluginManager.getPlugin(NONDEFAULT_NAMESPACE, MockPluginTwo.class));

        Assert.assertSame(plugin5, pluginManager.getPlugin(MockPluginTwo.class));
        Assert.assertNotSame(plugin5, pluginManager.getPlugin(NONDEFAULT_NAMESPACE, MockPluginTwo.class));
    }

    @Test
    public void testLifecycle() {
        IPluginManager pluginManager = generatePluginManager();

        // Lifecycle listeners
        MockPluginOne plugin1a = new MockPluginOne();
        MockPluginOne plugin1b = new MockPluginOne();

        // Not a lifecycle listener
        MockPluginTwo plugin2a = new MockPluginTwo();
        MockPluginTwo plugin2b = new MockPluginTwo();

        pluginManager.addPlugin(plugin1a);
        pluginManager.addPlugin(NONDEFAULT_NAMESPACE, plugin1a);
        pluginManager.addPlugin(plugin1a);
        pluginManager.addPlugin(plugin1b);
        pluginManager.addPlugin(plugin2a);
        pluginManager.addPlugin(NONDEFAULT_NAMESPACE, plugin2b);

        ((ILifecycleListener)pluginManager).start();
        ((ILifecycleListener)pluginManager).init();
        ((ILifecycleListener)pluginManager).stop();


        Assert.assertEquals(2, plugin1a.initCount);
        Assert.assertEquals(2, plugin1a.startCount);
        Assert.assertEquals(2, plugin1a.stopCount);

        Assert.assertEquals(1, plugin1b.initCount);
        Assert.assertEquals(1, plugin1b.startCount);
        Assert.assertEquals(1, plugin1b.stopCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPluginManagerNoNamespaceNullPlugin() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.addPlugin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPluginManagerWithNamespaceNullPlugin() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.addPlugin(NONDEFAULT_NAMESPACE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPluginManagerNoNamespaceNullClazz() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.getPlugin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPluginManagerWithNamespaceNullClazz() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.getPlugin(NONDEFAULT_NAMESPACE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPluginManagerMultipleNoNamespaceNullClazz() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.getPlugins(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPluginManagerMultipleWithNamespaceNullClazz() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.getPlugins(NONDEFAULT_NAMESPACE, null);
    }

}
