package com.ewerp.mud.sessions.terminal;

import com.ewerp.mud.EjMudException;
import com.ewerp.mud.commands.ICommand;
import com.ewerp.mud.commands.ICommandEngine;
import com.ewerp.mud.commands.IMessage;
import com.ewerp.mud.commands.IMessageMeta;
import com.ewerp.mud.messages.metas.InformationMeta;
import com.ewerp.mud.plugins.IPluginManager;
import com.ewerp.mud.sessions.terminal.interpreter.IEjMudCommandInterpreter;

import java.io.*;
import java.net.Socket;

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
public class EjMudTerminalSession {

    public static void run(IEjMudTerminalSession session) {
        if(null != session) {
            BufferedReader reader = null;
            String command = null;
            IPluginManager pluginManager = session.getPluginManager();

            IEjMudCommandInterpreter commandInterpreter = null;
            ICommandEngine commandEngine = null;
            if(null != pluginManager) {
                commandInterpreter = (IEjMudCommandInterpreter)pluginManager.getPlugin(IEjMudCommandInterpreter.class);
                commandEngine = (ICommandEngine)pluginManager.getPlugin(ICommandEngine.class);
            }

            if(null != session.getInputStream()) {
                reader = new BufferedReader(new InputStreamReader(session.getInputStream()));
            }

            while(null != reader && !session.isInputShutdown()) {
                try {
                    command = reader.readLine();
                    System.out.println(command);

                    if(null != commandInterpreter && null != commandEngine) {
                        try {
                            commandEngine.pushCommand(commandInterpreter.convertToCommand(command, session));
                        } catch(EjMudException e) {
                            //TODO: Log
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    //TODO: Log
                    e.printStackTrace();
                }
            }
        }
    }

    public static void processMessage(IEjMudTerminalSession session, IMessage message) {
        try {
            if(null != session && null != session.getOutputStream() && null != message) {
                for(IMessageMeta messageMeta : message.getMeta()) {
                    if(messageMeta instanceof InformationMeta) {
                        String output = ((InformationMeta)messageMeta).getInformationString();
                        if(null != output) {
                            session.getOutputStream().write(output.getBytes());
                        }
                    }
                }
            }
        } catch (IOException e) {
            //TODO: Log
            e.printStackTrace();
        }
    }
}
