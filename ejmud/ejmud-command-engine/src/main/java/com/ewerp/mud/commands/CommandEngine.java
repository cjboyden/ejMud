package com.ewerp.mud.commands;

import com.ewerp.mud.EjMudException;
import com.ewerp.mud.plugins.ILifecycleListener;
import com.ewerp.mud.plugins.IPluginManager;

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
public class CommandEngine implements ICommandEngine, ILifecycleListener {
    @Override
    public void pushCommand(ICommand command) throws IllegalArgumentException, EjMudException {
    }

    @Override
    public void setPluginManager(IPluginManager pluginManager) {
    }

    @Override
    public void init() {
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    // Null does nothing
    // If executionListener is already listening, do NOT add it again
    public void addExecutionListener(IExecutionListener executionListener) {
    }

    // Null does nothing
    // Do nothing if the executionListener cannot be found to be removed
    public void removeExecutionListener(IExecutionListener executionListener) {
    }

}
