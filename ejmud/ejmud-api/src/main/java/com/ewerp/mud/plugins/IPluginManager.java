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


/**
 * The {@link IPluginManager} is the heart of the ejMud. All services register
 * themselves as plugins with the {@link IPluginManager} and query the
 * {@link IPluginManager} for services they require themselves.
 *
 * @author cboyden
 */
public interface IPluginManager {

    /**
     * Request an {@link IPlugin} that implements the {@link Class} for a given
     * namespace. The {@link IPlugin} provides functionality to the other
     * {@link IPlugin}s.
     *
     * The first plugin to support a given interface that was added is the one that will be returned.
     *
     * @param namespace May be specified to prevent collision <br />
     *                  <ul>
     *                  <li>String : Any valid string can be used to represent a
     *                  namespace</li>
     *                  <li>null : Use the default namespace</li>
     *                  </ul>
     * @param clazz     The interface implemented by the {@link IPlugin} that is being
     *                  requested <br />
     *                  <ul>
     *                  <li>{@link Class} : A valid {@link Class} representing the
     *                  desired functionality to retrieve</li>
     *                  <li>null : A null value will result in an
     *                  {@link IllegalArgumentException}</li>
     *                  </ul>
     * @return An instance of {@link IPlugin} that implements the interface
     *         requested by the clazz parameter <br />
     *         Null if no matching {@link IPlugin} can be found
     * @throws IllegalArgumentException Must be thrown if clazz is null
     */
    public IPlugin getPlugin(String namespace, Class<?> clazz) throws IllegalArgumentException;

    /**
     * Request an {@link IPlugin} that implements the {@link Class} using a
     * default namespace. The {@link IPlugin} provides functionality to the
     * other {@link IPlugin}s.
     *
     * The first plugin to support a given interface that was added is the one that will be returned.
     *
     * @param clazz The interface implemented by the {@link IPlugin} that is being
     *              requested <br />
     *              <ul>
     *              <li>{@link Class} : A valid {@link Class} representing the
     *              desired functionality to retrieve</li>
     *              <li>null : A null value will result in an
     *              {@link IllegalArgumentException}</li>
     *              </ul>
     * @return An instance of {@link IPlugin} that implements the interface
     *         requested by the clazz parameter <br />
     *         Null if no matching {@link IPlugin} can be found
     * @throws IllegalArgumentException Must be thrown if clazz is null
     */
    public IPlugin getPlugin(Class<?> clazz) throws IllegalArgumentException;

    /**
     * Register an {@link IPlugin} associated with a given namespace. The
     * {@link IPlugin} provides functionality to the other {@link IPlugin}s.
     * This method will register this {@link IPluginManager} as the plugin manager for the incoming {@link IPlugin}
     *
     * @param namespace May be specified to prevent collision <br />
     *                  <ul>
     *                  <li>String : Any valid string can be used to represent a
     *                  namespace</li>
     *                  <li>null : Use the default namespace</li>
     *                  </ul>
     * @param plugin    An instance of {@link IPlugin} that provides functionality to
     *                  the ejMud <br />
     *                  <ul>
     *                  <li>{@link IPlugin} : A valid plugin extending {@link IPlugin}
     *                  <li>If the {@link IPlugin} has already been added, nothing will happen</li>
     *                  </li>
     *                  <li>null : A null value will result in an
     *                  {@link IllegalArgumentException}</li>
     *                  </ul>
     * @throws IllegalArgumentException Must be thrown if {@link IPlugin} is null
     */
    public void addPlugin(String namespace, IPlugin plugin) throws IllegalArgumentException;

    /**
     * Register an {@link IPlugin} associated with a given namespace. The
     * {@link IPlugin} provides functionality to the other {@link IPlugin}s.
     * This method will register this {@link IPluginManager} as the plugin manager for the incoming {@link IPlugin}
     *
     * @param plugin An instance of {@link IPlugin} that provides functionality to
     *               the ejMud <br />
     *               <ul>
     *               <li>{@link IPlugin} : A valid plugin extending {@link IPlugin}
     *               </li>
     *               <li>If the {@link IPlugin} has already been added, nothing will happen</li>
     *               <li>null : A null value will result in an
     *               {@link IllegalArgumentException}</li>
     *               </ul>
     * @throws IllegalArgumentException Must be thrown if {@link IPlugin} is null
     */
    public void addPlugin(IPlugin plugin) throws IllegalArgumentException;
}
