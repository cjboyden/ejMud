package com.ewerp.mud.logging;

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
public class Log implements IPlugin, ILog {
    protected IPluginManager pluginManager;

    @Override
    public void logDebug(String message) {
        logDebug(message, null);
    }

    @Override
    public void logDebug(String message, Exception e) {
        print("Debug", message, e);
    }

    @Override
    public void logDetail(String message) {
        logDetail(message, null);
    }

    @Override
    public void logDetail(String message, Exception e) {
        print("Detail", message, e);
    }

    @Override
    public void logInfo(String message) {
        logInfo(message, null);
    }

    @Override
    public void logInfo(String message, Exception e) {
        print("Info", message, e);
    }

    @Override
    public void logWarn(String message) {
        logWarn(message, null);
    }

    @Override
    public void logWarn(String message, Exception e) {
        print("Warn", message, e);
    }

    @Override
    public void logError(String message) {
        logError(message, null);
    }

    @Override
    public void logError(String message, Exception e) {
        print("Error", message, e);
    }

    protected void print(String level, String message, Exception e) {
        if(null != message) {
            System.out.println("[" + level + "] " + message);
            if(null != e) {
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void registerPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public List<Class<?>> getInterfaces() {
        return Arrays.asList(new Class<?>[] {ILog.class});
    }
}
