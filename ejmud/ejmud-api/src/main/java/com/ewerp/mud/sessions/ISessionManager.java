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
 * An {@link ISessionManager} manages the {@link ISession}s for the ejMud. An
 * {@link ISession} is a single entry point into the ejMud for a given message
 * format over a given transport.
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
     *                <li>null : A null value will result in an
     *                {@link IllegalArgumentException}</li>
     *                </ul>
     * @throws IllegalArgumentException Must be thrown if {@link ISession} is null
     */
    public void removeSession(ISession session) throws IllegalArgumentException;
}

