package com.ewerp.mud.properties;

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

public class MockProperties implements IProperties {
    public Map<String, Map<String, String>> namespaces = new HashMap<String, Map<String, String>>();
    public static String DEFAULT_NAMESPACE = "EWERP_DEFAULT";

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
        String result = null;

        String useNamespace = namespace;
        if(null == useNamespace) {
            useNamespace = MockProperties.DEFAULT_NAMESPACE;
        }

        if(namespaces.containsKey(useNamespace)) {
            result = namespaces.get(useNamespace).get(key);
        }

        return result;
    }

    @Override
    public String getNamespaceProperty(String namespace, String key, String defaultValue) {
        String result = getNamespaceProperty(namespace, key);

        if(null == result) {
            result = defaultValue;
        }

        return result;
    }
}
