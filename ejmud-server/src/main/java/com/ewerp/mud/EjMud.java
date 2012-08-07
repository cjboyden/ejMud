package com.ewerp.mud;

import com.ewerp.mud.plugins.EjMudPluginManager;
import com.ewerp.engine.plugins.ILifecycleListener;
import com.ewerp.engine.plugins.IPluginManager;

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
public class EjMud {

    public static void main(String[] args) {
        IPluginManager pluginManager = EjMudPluginManager.getInstance();

        if(null != pluginManager && pluginManager instanceof ILifecycleListener) {
            ((ILifecycleListener)pluginManager).init();
            ((ILifecycleListener)pluginManager).start();
//            ((ILifecycleListener)pluginManager).stop();
        } else {
            //TODO: Log
            System.err.println("Unsupported Plugin Manager");
        }
    }
}
