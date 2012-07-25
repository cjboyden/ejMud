package com.ewerp.mud.plugins;

import java.util.ArrayList;
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

public class MockPluginManager implements IPluginManager {
    public static String DEFAULT_NAMESPACE = "mock-namespace";
    public Map<String, List<IPlugin>> pluginMap = new HashMap<String, List<IPlugin>>();

    @Override
    public IPlugin getPlugin(String namespace, Class<?> clazz) throws IllegalArgumentException {
        if(null == clazz) {
            throw new IllegalArgumentException();
        }

        IPlugin plugin = null;
        List<IPlugin> pluginList = pluginMap.get(namespace);
        if(null != pluginList) {
            for(IPlugin p : pluginList) {
                if((null != p.getInterfaces()) && (p.getInterfaces().contains(clazz))) {
                    plugin = p;
                    break;
                }
            }
        }
        return plugin;
    }

    @Override
    public IPlugin getPlugin(Class<?> clazz) throws IllegalArgumentException {
        return getPlugin(MockPluginManager.DEFAULT_NAMESPACE, clazz);
    }

    @Override
    public void addPlugin(String namespace, IPlugin plugin) throws IllegalArgumentException {
        if(null == plugin) {
            throw new IllegalArgumentException();
        }

        List<IPlugin> pluginList = pluginMap.get(namespace);
        if(null == pluginList) {
            pluginList = new ArrayList<IPlugin>();
            pluginMap.put(namespace, pluginList);
        }

        pluginList.add(plugin);
    }

    @Override
    public void addPlugin(IPlugin plugin) throws IllegalArgumentException {
        addPlugin(MockPluginManager.DEFAULT_NAMESPACE, plugin);
    }
}
