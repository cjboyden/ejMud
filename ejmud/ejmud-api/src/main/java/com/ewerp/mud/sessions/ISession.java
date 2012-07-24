package com.ewerp.mud.sessions;

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

import com.ewerp.mud.commands.IMessage;
import com.ewerp.mud.plugins.IPluginManager;

/**
 * An {@link ISession} encapsulates an entry point through which commands can
 * enter the ejMud engine.
 * <p/>
 * Allows the {@link ISessionManager} to control (send notifications, shutdown,
 * etc...) the {@link ISession}s as a whole or individually.
 *
 * @author cboyden
 */
public interface ISession {
    /**
     * Give the {@link ISession} access to the {@link IPluginManager}
     *
     * @param pluginManager The {@link IPluginManager} to associate with this
     *                      {@link ISession} <br />
     *                      <ul>
     *                      <li>{@link IPluginManager} : A valid plugin manager
     *                      implementing {@link IPluginManager}</li>
     *                      <li>null : Null is permitted. This will remove any existing
     *                      association to an {@link IPluginManager}</li>
     *                      </ul>
     */
    public void setPluginManager(IPluginManager pluginManager);

    /**
     * Used by the {@link ISessionManager} to shutdown an {@link ISession}.
     * Typically this will notify the connected client, terminate the connection and
     * free any session resources.
     */
    public void shutdown();

    public void processMessage(IMessage message);
}
