package com.ewerp.mud.properties;

import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.IPluginManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

public class Properties implements IPlugin, IProperties {
    private static Properties INSTANCE = new Properties();
    private static String DEFAULT_NAMESPACE = "EWERP_DEFAULT-";
    protected IPluginManager pluginManager;
    protected Map<String, String> properties = new HashMap<String, String>();

    public static Properties getInstance() {
        return INSTANCE;
    }

    private Properties() {
    }

    @Override
    public void registerPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public List<Class<?>> getInterfaces() {
        return Arrays.asList(new Class<?>[] {IProperties.class});
    }

    @Override
    public String getProperty(String key) {
        return getNamespaceProperty(null, key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return getNamespaceProperty(null, key, defaultValue);
    }

    @Override
    public String getNamespaceProperty(String namespace, String key) {
        return properties.get(generateKey(generateNamespace(namespace), key));
    }

    @Override
    public String getNamespaceProperty(String namespace, String key, String defaultValue) {
        String result = properties.get(generateKey(generateNamespace(namespace), key));

        if(null == result) {
            result = defaultValue;
        }

        return result;
    }

    protected String generateNamespace(String inNamespace) {
        String result = null;
        if(null == inNamespace) {
            result = Properties.DEFAULT_NAMESPACE;
        } else {
            result = "[" + inNamespace + "]-";
        }

        return result;
    }

    protected String generateKey(String namespace, String key) {
        return namespace + key;
    }
}
