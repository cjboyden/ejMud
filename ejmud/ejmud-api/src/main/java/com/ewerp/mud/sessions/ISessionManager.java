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

/**
 * The {@link ISessionManager} manages all {@link ISession}s for the ejMud. An
 * {@link ISession} is a single entry point into the ejMud for a given message
 * format over a given transport.
 *
 * The {@link ISessionManager} starts and stops all contained {@link ISessionFactory}s.
 * Each {@link ISessionFactory} is responsible for creating sessions and attaching them to the {@link ISessionManager}.
 * The {@link ISessionManager} will notify each contained {@link ISession} of {@link com.ewerp.mud.commands.IMessage}s
 * that need to be processed.
 *
 * This is the main interface that the other plugins should communicate with.
 *
 * @author cboyden
 */
public interface ISessionManager {

    /**
     * Add an {@link ISession} to be managed by this {@link ISessionManager}.
     *
     * @param session An {@link ISession} to be managed by this
     *                {@link ISessionManager} <br />
     *                <ul>
     *                <li>{@link ISession} : A valid session implementing
     *                {@link ISession}</li>
     *                <li>If {@link ISession} already exists, nothing will happen</li>
     *                <li>null : A null value will result in an
     *                {@link IllegalArgumentException}</li>
     *                </ul>
     * @throws IllegalArgumentException Must be thrown if {@link ISession} is null
     */
    public void addSession(ISession session) throws IllegalArgumentException;

    /**
     * Stop an {@link ISession} from being managed by this
     * {@link ISessionManager}.
     *
     * @param session An {@link ISession} to be removed from this
     *                {@link ISessionManager} <br />
     *                <ul>
     *                <li>{@link ISession} : A valid session implementing
     *                {@link ISession}</li>
     *                <li>If {@link ISession} does not exist in this {@link ISessionManager}, nothing happens</li>
     *                <li>null : A null value will do nothing</li>
     *                </ul>
     */
    public void removeSession(ISession session);

    /**
     * Add an {@link ISessionFactory} to this {@link ISessionManager}
     * @param sessionFactory An {@link ISessionFactory} to be managed by this {@link ISessionManager}
     *                <ul>
     *                <li>{@link ISessionFactory} : A valid session implementing
     *                {@link ISessionFactory}</li>
     *                <li>If {@link ISessionFactory} already exists, nothing will happen</li>
     *                <li>null : A null value will result in an
     *                {@link IllegalArgumentException}</li>
     *                </ul>
     * @throws IllegalArgumentException Must be thrown if {@link ISession} is null
     */
    public void addSessionFactory(ISessionFactory sessionFactory) throws IllegalArgumentException;

    /**
     * Remove and {@link ISessionFactory} from being managed by this {@link ISessionManager}
     * @param sessionFactory An {@link ISessionFactory} to be removed from this {@link ISessionManager}
     *                <ul>
     *                <li>{@link ISessionFactory} : A valid session implementing
     *                {@link ISessionFactory}</li>
     *                <li>If {@link ISessionFactory} does not exist in this {@link ISessionManager}, nothing happens</li>
     *                <li>null : A null value will do nothing</li>
     *                </ul>
     */
    public void removeSessionFactory(ISessionFactory sessionFactory);
}

