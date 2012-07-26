package com.ewerp.mud.commands;

import com.ewerp.mud.EjMudException;
import com.ewerp.mud.plugins.IPluginManager;

import java.util.ArrayList;
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

public class MockCommandEngine implements ICommandEngine {
    public List<ICommand> commandList = new ArrayList<ICommand>();
    public IPluginManager pluginManager;

    @Override
    public void pushCommand(ICommand command) throws IllegalArgumentException, EjMudException {
        if(null == command) {
            throw new IllegalArgumentException();
        }
        commandList.add(command);
    }

    @Override
    public void setPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }
}
