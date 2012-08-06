package com.ewerp.mud.sessions.terminal.interpreter;

import com.ewerp.mud.commands.ICommand;
import com.ewerp.mud.sessions.ISession;

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
public interface IEjMudCommandInterpreter {
    ICommand convertToCommand(String command);

    /**
     *
     * @param command
     * @param session Session to send IMessages to if there is an error processing the command string or
     *                if some other immediate communication is required
     * @return
     */
    ICommand convertToCommand(String command, ISession session);
}
