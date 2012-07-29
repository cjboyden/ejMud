package com.ewerp.mud.properties;

import com.ewerp.mud.plugins.MockPluginManager;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

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

public class TestProperties {
    public static IProperties generateProperties() {
        return Properties.getInstance();
    }

    @Test
    public void testPropertiesPluginInterface() {
        IProperties properties = generateProperties();

        MockPluginManager pluginManager = new MockPluginManager();
        ((Properties)properties).registerPluginManager(null);
        ((Properties)properties).registerPluginManager(pluginManager);

        List<Class<?>> supportedInterfaces = ((Properties) properties).getInterfaces();

        Assert.assertNotNull(supportedInterfaces);
        Assert.assertEquals(1, supportedInterfaces.size());
        Assert.assertEquals(IProperties.class, supportedInterfaces.get(0));
    }

    @Test
    public void testProperties() {
        IProperties properties = generateProperties();

        Assert.assertNull(properties.getProperty(null));
        Assert.assertEquals("default", properties.getProperty(null, "default"));
        Assert.assertNull(properties.getNamespaceProperty(null, null));
        Assert.assertEquals("default", properties.getNamespaceProperty(null, null, "default"));
    }
}
