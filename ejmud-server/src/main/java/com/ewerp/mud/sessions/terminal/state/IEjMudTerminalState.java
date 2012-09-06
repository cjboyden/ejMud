package com.ewerp.mud.sessions.terminal.state;

import com.ewerp.engine.plugins.IPluginManager;
import com.ewerp.mud.sessions.terminal.IEjMudTerminalSession;

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

/**
 * The TerminalState classes will handle short term "chatter" with a terminal
 * client to generate meaningful actions within ejMud.
 *
 * Exacmples:
 *
 *  I) Login:
 *      1) Upon creation send a welcome screen with a login prompt to the user
 *      2) processLine("username")
 *      3) Send a password prompt
 *      4) processLine("password")
 *      5) Generate a login command with this state registered as a listener
 *      6) Upon failure to login, send a message and return to step 2
 *      7) Upon successful login, register information and change state accordingly
 *
 *  II) Create room:
 *      1) Create Room terminal state is entered by another state
 *      2) Prompt user for room name
 *      3) processLine("Woods room 1")
 *      4) Prompt user for room description
 *      5) processLine("You are in an empty room")
 *      6) Generate a room command
 *      7) Return to previous state
 *
 */
public interface IEjMudTerminalState {
    /**
     * Session for output & data access
     * @param session
     */
    void setSession(IEjMudTerminalSession session);

    /**
     * PluginManager for access to application facilities
     * @param pluginManager
     */
    void setPluginManager(IPluginManager pluginManager);

    /**
     * Line of input to process
     * @param input
     */
    void processLine(String input);
}
