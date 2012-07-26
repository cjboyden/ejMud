package com.ewerp.mud.properties;

import org.junit.Assert;
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
public class TestMockProperties {
    public static IProperties generateProperties() {
        return new MockProperties();
    }

    @Test
    public void testMockProperties() {
        IProperties properties = generateProperties();

        Assert.assertNull("A property that does not exist must return null", properties.getProperty("nonexistentkey"));
        Assert.assertNull("A null property should return null", properties.getProperty(null));
        Assert.assertEquals("Default value must be returned if the property does not exist", "default", properties.getProperty("nonexistentkey", "default"));
        Assert.assertEquals("Default value must be returned if the property does not exist", null, properties.getProperty("nonexistentkey", null));

        Assert.assertNull("A property that does not exist must return null", properties.getNamespaceProperty(null, "nonexistentkey"));
        Assert.assertNull("A null property should return null", properties.getNamespaceProperty(null, null));
        Assert.assertEquals("Default value must be returned if the property does not exist", "default", properties.getNamespaceProperty(null, "nonexistentkey", "default"));
        Assert.assertEquals("Default value must be returned if the property does not exist", null, properties.getNamespaceProperty(null, "nonexistentkey", null));

        Assert.assertNull("A property that does not exist must return null", properties.getNamespaceProperty("nonexistentnamespace", "nonexistentkey"));
        Assert.assertNull("A null property should return null", properties.getNamespaceProperty("nonexistentnamespace", null));
        Assert.assertEquals("Default value must be returned if the property does not exist", "default", properties.getNamespaceProperty("nonexistentnamespace", "nonexistentkey", "default"));
        Assert.assertEquals("Default value must be returned if the property does not exist", null, properties.getNamespaceProperty("nonexistentnamespace", "nonexistentkey", null));
    }
}
