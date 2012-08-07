package com.ewerp.mud.sessions.terminal.interpreter;

import com.ewerp.engine.commands.ICommand;
import com.ewerp.engine.commands.IMessage;
import com.ewerp.engine.commands.Message;
import com.ewerp.engine.plugins.IPlugin;
import com.ewerp.engine.plugins.IPluginManager;
import com.ewerp.engine.sessions.ISession;
import com.ewerp.mud.commands.NullCommand;
import com.ewerp.mud.messages.metas.InformationMeta;

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
public class EjMudCommandInterpreter implements IPlugin, IEjMudCommandInterpreter {
    protected IPluginManager pluginManager;

    @Override
    public ICommand convertToCommand(String command) {
        return convertToCommand(command, null);
    }

    @Override
    public ICommand convertToCommand(String command, ISession session) {
        if(null != session) {
            IMessage msg = new Message();
            msg.addMeta(new InformationMeta("Thank you\r\n"));
            session.processMessage(msg);
        }
        return new NullCommand();
    }


    @Override
    public void registerPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public List<Class<?>> getInterfaces() {
        return Arrays.asList(new Class<?>[] {IEjMudCommandInterpreter.class});
    }
}
