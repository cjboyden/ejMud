package com.ewerp.mud.properties;

import org.junit.Assert;
import org.junit.Test;

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
public class TestMockProperties {
    public static IProperties generateProperties() {
        return new MockProperties();
    }

    public static String NONDEFAULT_NAMESPACE = "nondefault-namespace";
    public static String NONEXISTENT_KEY = "nonexistent-key";

    @Test
    public void testMockProperties() {
        IProperties properties = generateProperties();

        Assert.assertNull("A property that does not exist must return null", properties.getProperty(TestMockProperties.NONEXISTENT_KEY));
        Assert.assertNull("A null property should return null", properties.getProperty(null));
        Assert.assertEquals("Default value must be returned if the property does not exist", "default", properties.getProperty(TestMockProperties.NONEXISTENT_KEY, "default"));
        Assert.assertEquals("Default value must be returned if the property does not exist", null, properties.getProperty(TestMockProperties.NONEXISTENT_KEY, null));

        Assert.assertNull("A property that does not exist must return null", properties.getNamespaceProperty(null, TestMockProperties.NONEXISTENT_KEY));
        Assert.assertNull("A null property should return null", properties.getNamespaceProperty(null, null));
        Assert.assertEquals("Default value must be returned if the property does not exist", "default", properties.getNamespaceProperty(null, TestMockProperties.NONEXISTENT_KEY, "default"));
        Assert.assertEquals("Default value must be returned if the property does not exist", null, properties.getNamespaceProperty(null, TestMockProperties.NONEXISTENT_KEY, null));

        Assert.assertNull("A property that does not exist must return null", properties.getNamespaceProperty(TestMockProperties.NONDEFAULT_NAMESPACE, TestMockProperties.NONEXISTENT_KEY));
        Assert.assertNull("A null property should return null", properties.getNamespaceProperty(TestMockProperties.NONDEFAULT_NAMESPACE, null));
        Assert.assertEquals("Default value must be returned if the property does not exist", "default", properties.getNamespaceProperty(TestMockProperties.NONDEFAULT_NAMESPACE, TestMockProperties.NONEXISTENT_KEY, "default"));
        Assert.assertEquals("Default value must be returned if the property does not exist", null, properties.getNamespaceProperty(TestMockProperties.NONDEFAULT_NAMESPACE, TestMockProperties.NONEXISTENT_KEY, null));
    }

    @Test
    public void testMockPropertiesWithProperties() {
        IProperties properties = generateProperties();

        Map<String, String> defaultProperties = new HashMap<String, String>();
        defaultProperties.put("PropertyOne", "ValueOne");
        defaultProperties.put("PropertyTwo", "ValueTwo");
        ((MockProperties) properties).namespaces.put(MockProperties.DEFAULT_NAMESPACE, defaultProperties);

        Map<String, String> nondefaultProperties = new HashMap<String, String>();
        nondefaultProperties.put("PropertyThree", "ValueThree");
        ((MockProperties) properties).namespaces.put(TestMockProperties.NONDEFAULT_NAMESPACE, nondefaultProperties);


        Assert.assertEquals("ValueOne", properties.getProperty("PropertyOne"));
        Assert.assertEquals("ValueTwo", properties.getProperty("PropertyTwo"));
        Assert.assertNull(properties.getProperty("PropertyThree"));

        Assert.assertEquals("ValueOne", properties.getNamespaceProperty(null, "PropertyOne"));
        Assert.assertEquals("ValueTwo", properties.getNamespaceProperty(null, "PropertyTwo"));
        Assert.assertNull(properties.getNamespaceProperty(null, "PropertyThree"));

        Assert.assertNull(properties.getNamespaceProperty(NONDEFAULT_NAMESPACE, "PropertyOne"));
        Assert.assertNull(properties.getNamespaceProperty(NONDEFAULT_NAMESPACE, "PropertyTwo"));
        Assert.assertEquals("ValueThree", properties.getNamespaceProperty(NONDEFAULT_NAMESPACE, "PropertyThree"));


    }
}
