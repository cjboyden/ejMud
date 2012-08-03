package com.ewerp.mud.content;

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
public class TestContentSessionFactory {
    public static IContentSessionFactory generateContentSessionFactory() {
        return new ContentSessionFactory();
    }

    @Test
    public void testContentSessionFactory() {
        IContentSessionFactory contentSession = generateContentSessionFactory();
        MockPluginManager pluginManager = new MockPluginManager();

        IPlugin plugin = (IPlugin) contentSession;
        plugin.registerPluginManager(null);
        plugin.registerPluginManager(pluginManager);

        Assert.assertNotNull(plugin.getInterfaces());
        Assert.assertEquals(1, plugin.getInterfaces().size());
        Assert.assertEquals(IContentSessionFactory.class, plugin.getInterfaces().get(0));

        Assert.assertNotNull(contentSession);
    }
}
