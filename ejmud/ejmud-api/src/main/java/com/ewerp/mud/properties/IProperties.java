package com.ewerp.mud.properties;

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

/**
 * The {@link IProperties} provides a centralized repository of configuration information
 */
public interface IProperties {
    /**
     * Get a string property for a given key
     * @param key name of the property to fetch
     * @return String value of key, null if key not found
     */
    public String getProperty(String key);

    /**
     * Get a string property for a given key
     * @param key name of the property to fetch
     * @param defaultValue value to return if the key cannot be found
     * @return String value of key, defaultValue if key not found
     */
    public String getProperty(String key, String defaultValue);

    /**
     * Get a string property for a given key in a given namespace
     * @param namespace namespace corresponding to the key to fetch
     * @param key name of the property to fetch
     * @return String value of key, null if key not found
     */
    public String getNamespaceProperty(String namespace, String key);

    /**
     * Get a string property for a given key in a given namespace
     * @param namespace namespace corresponding to the key to fetch
     * @param key name of the property to fetch
     * @param defaultValue value to return if the key cannot be found
     * @return String value of key, defaultValue if key not found
     */
    public String getNamespaceProperty(String namespace, String key, String defaultValue);
}
