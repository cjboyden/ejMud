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
public class PluginManager implements IPluginManager, ILifecycleListener {
    private static final PluginManager INSTANCE = new PluginManager();
    protected static final String defaultNamespace = "EWERP_DEFAULT";

    protected Map<String, List<IPlugin>> namespaces = new HashMap<String, List<IPlugin>>();

    /**
     * Use the getInstance() method to gain access to the {@link PluginManager}
     * @return An instance of the {@link PluginManager}
     */
    public static PluginManager getInstance() {
        return INSTANCE;
    }

    protected PluginManager() {
    }

    @Override
    public IPlugin getPlugin(String namespace, Class<?> clazz) throws IllegalArgumentException {
        if(null == clazz) {
            throw new IllegalArgumentException();
        }

        IPlugin result = null;

        // Set working namespace
        String useNamespace = namespace;
        if(null == namespace) {
            useNamespace = PluginManager.defaultNamespace;
        }

        // If the namespace doesn't exist then the clazz cannot be found
        if(namespaces.containsKey(useNamespace)) {
            // Search each IPlugin in the namespace for clazz support
            for(IPlugin p : namespaces.get(useNamespace)) {
                if(null != p.getInterfaces() && p.getInterfaces().contains(clazz)) {
                    result = p;
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public List<IPlugin> getPlugins(String namespace, Class<?> clazz) throws IllegalArgumentException {
        if(null == clazz) {
            throw new IllegalArgumentException();
        }

        List<IPlugin> result = null;

        // Set working namespace
        String useNamespace = namespace;
        if(null == namespace) {
            useNamespace = PluginManager.defaultNamespace;
        }

        // If the namespace doesn't exist then the clazz cannot be found
        if(namespaces.containsKey(useNamespace)) {
            // Search each IPlugin in the namespace for clazz support
            for(IPlugin p : namespaces.get(useNamespace)) {
                if(null != p.getInterfaces() && p.getInterfaces().contains(clazz)) {
                    if(null == result) {
                        result = new ArrayList<IPlugin>();
                    }
                    result.add(p);
                }
            }
        }

        return result;
    }

    @Override
    public IPlugin getPlugin(Class<?> clazz) throws IllegalArgumentException {
        return getPlugin(null, clazz);
    }

    @Override
    public List<IPlugin> getPlugins(Class<?> clazz) throws IllegalArgumentException {
        return getPlugins(null, clazz);
    }

    @Override
    public void addPlugin(String namespace, IPlugin plugin) throws IllegalArgumentException {
        if(null == plugin) {
            throw new IllegalArgumentException();
        }

        String useNamespace = namespace;
        if(namespace == null) {
            useNamespace = defaultNamespace;
        }

        List<IPlugin> plugins = null;
        if(namespaces.containsKey(useNamespace)) {
            plugins = namespaces.get(useNamespace);
        } else {
            plugins = new ArrayList<IPlugin>();
            namespaces.put(useNamespace, plugins);
        }

        if(!plugins.contains(plugin)) {
            plugin.registerPluginManager(this);
            plugins.add(plugin);
        }
    }

    @Override
    public void addPlugin(IPlugin plugin) throws IllegalArgumentException {
        addPlugin(null, plugin);
    }

    @Override
    public void init() {
        for(List<IPlugin> plugins : namespaces.values()) {
            for(IPlugin plugin : plugins) {
                if(plugin instanceof ILifecycleListener) {
                    ((ILifecycleListener)plugin).init();
                }
            }
        }
    }

    @Override
    public void start() {
        for(List<IPlugin> plugins : namespaces.values()) {
            for(IPlugin plugin : plugins) {
                if(plugin instanceof ILifecycleListener) {
                    ((ILifecycleListener)plugin).start();
                }
            }
        }
    }

    @Override
    public void stop() {
        for(List<IPlugin> plugins : namespaces.values()) {
            for(IPlugin plugin : plugins) {
                if(plugin instanceof ILifecycleListener) {
                    ((ILifecycleListener)plugin).stop();
                }
            }
        }
    }
}
