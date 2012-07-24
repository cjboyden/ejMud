package com.ewerp.mud.plugins;

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

import java.util.List;

/**
 * ejMud plugins must implement this interface to gain access to the
 * {@link IPluginManager}. The {@link IPluginManager} gives plugins access to
 * other plugins for using their functionality.
 * <p/>
 * {@link IPlugin}s will typically support {@link ILifecycleListener}, or provide
 * functionality through implemented classes, or both.
 *
 * @author cboyden
 */
public interface IPlugin {

    /**
     * Associate an {@link IPluginManager} with this {@link IPlugin}.
     *
     * @param pluginManager The {@link IPluginManager} with which to associate this
     *                      {@link IPlugin} <br />
     *                      <ul>
     *                      <li>{@link IPluginManager} : A valid plugin manager
     *                      implementing {@link IPluginManager}</li>
     *                      <li>null : A null value will result in an
     *                      {@link IllegalArgumentException}</li>
     *                      </ul>
     * @throws IllegalArgumentException Must be thrown if {@link IPluginManager} is null
     */
    public void registerPluginManager(IPluginManager pluginManager) throws IllegalArgumentException;

    /**
     * The list of interfaces implemented by this class.
     *
     * @return The list of interfaces implemented by this class. This may be
     *         null if the {@link IPlugin} does not expose any functionality to
     *         the other ejMud {@link IPlugin}s.
     */
    public List<Class<?>> getInterfaces();
}
