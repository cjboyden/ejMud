package com.ewerp.mud.commands;

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

import com.ewerp.mud.content.IEntity;
import com.ewerp.mud.plugins.IPluginManager;

/**
 * An {@link ICommand} is a procedure to be executed. Most commands will be
 * executed by a user with parameters.
 *
 * @author cboyden
 */
public interface ICommand {

    /**
     * Invoke the command logic. Typically operating on the target
     * {@link IEntity} using the source {@link IEntity}.
     *
     * @throws IllegalStateException Must be thrown if the configuration of the {@link ICommand}
     *                               is invalid
     */
    public void execute() throws IllegalStateException;

    /**
     * Set the {@link IPluginManager} for the command to use to access
     * resources.
     *
     * @param pluginManager The {@link IPluginManager} the command will use to gain access
     *                      to required resources. <br />
     *                      <ul>
     *                      <li>{@link IPluginManager} : A valid {@link IPluginManager}</li>
     *                      <li>null : Must not be null. An
     *                      {@link IllegalArgumentException} will be thrown</li>
     *                      <ul>
     * @throws IllegalArgumentException Must be thrown if the pluginManager is null.
     */
    public void setPluginManager(IPluginManager pluginManager) throws IllegalArgumentException;
}
