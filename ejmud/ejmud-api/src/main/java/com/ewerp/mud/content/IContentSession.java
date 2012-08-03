package com.ewerp.mud.content;

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
 * The {@link IContentSession} is responsible for storing and exposing all {@link IEntity} objects as
 * well as coordinating access between threads.
 *
 * @author cboyden
 */
public interface IContentSession {
    /**
     * Rollback the content session. No changes will be committed and all object references
     * should no longer be considered valid.
     * //TODO: Further define this - Is the Session closed at this point? How can it be further used?
     */
    public void rollback();

    /**
     * Commit the content session. All changes will be saved and all object references
     * remain valid for further actions.
     * //TODO: Further define this - Is the Session closed at this point? How can it be further used?
     */
    public void commit();

    /**
     * Fetch an {@link IEntity} with a matching id
     *
     * The fetched entity may not be the exact same object but will have the same properties as the
     * original at the time it was fetched
     * @param id
     * @return {@link IEntity} with a matching id, or null if none are found
     */
    public IEntity fetch(long id);

    /**
     * Store a new or existing {@link IEntity}. Once stored the {@link IEntity} can
     * still be used and changes stored again in the future.
     * New {@link IEntity} are created and existing {@link IEntity} are updated.
     * @param object {@link IEntity} to create or update
     * If object is null, nothing will happen
     */
    public void store(IEntity object);

    /**
     * Remove an existing {@link IEntity}. If the object does not exist nothing happens.
     * If {@link IEntity} is null, nothing will happen
     * @param object {@link IEntity} to remove
     */
    public void delete(IEntity object);
}
