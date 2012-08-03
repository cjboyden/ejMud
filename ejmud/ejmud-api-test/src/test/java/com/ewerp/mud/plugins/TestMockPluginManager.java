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
public class TestMockPluginManager {
    public static IPluginManager generatePluginManager() {
        return new MockPluginManager();
    }

    public static String NONDEFAULT_NAMESPACE = "non-default-namespace";

    @Test
    public void testMockPluginManager() {
        IPluginManager pluginManager = generatePluginManager();

        IPlugin plugin1 = new MockPlugin();
        plugin1.registerPluginManager(pluginManager);
        IPlugin plugin2 = new MockPlugin();
        plugin2.registerPluginManager(pluginManager);
        IPlugin plugin3 = new MockPlugin();
        plugin3.registerPluginManager(pluginManager);
        IPlugin plugin4 = new MockPlugin();
        plugin4.registerPluginManager(pluginManager);

        pluginManager.addPlugin(plugin1);
        pluginManager.addPlugin(NONDEFAULT_NAMESPACE, plugin2);

        Assert.assertNull(pluginManager.getPlugin(TestMockPluginManager.class));
        Assert.assertNull(pluginManager.getPlugin(NONDEFAULT_NAMESPACE, TestMockPluginManager.class));

        Assert.assertNull(pluginManager.getPlugins(TestMockPluginManager.class));
        Assert.assertNull(pluginManager.getPlugins(NONDEFAULT_NAMESPACE, TestMockPluginManager.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMockPluginManagerNoNamespaceNullPlugin() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.addPlugin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMockPluginManagerWithNamespaceNullPlugin() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.addPlugin(NONDEFAULT_NAMESPACE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMockPluginManagerNoNamespaceNullClazz() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.getPlugin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMockPluginManagerWithNamespaceNullClazz() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.getPlugin(NONDEFAULT_NAMESPACE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMockPluginManagerMultipleNoNamespaceNullClazz() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.getPlugins(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMockPluginManagerMultipleWithNamespaceNullClazz() {
        IPluginManager pluginManager = generatePluginManager();

        pluginManager.getPlugins(NONDEFAULT_NAMESPACE, null);
    }
}
