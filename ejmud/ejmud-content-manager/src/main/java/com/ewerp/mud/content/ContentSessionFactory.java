package com.ewerp.mud.content;

import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.IPluginManager;

import java.util.Arrays;
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
public class ContentSessionFactory implements IPlugin, IContentSessionFactory {
    private IPluginManager pluginManager;

    @Override
    public IContentSession openContentSession() {
        return ContentSession.getInstance();
    }

    @Override
    public void registerPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public List<Class<?>> getInterfaces() {
        return Arrays.asList(new Class<?>[] { IContentSessionFactory.class });
    }
}
