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
 * The {@link IEntity} is the most basic representation of an object in the system
 *
 * @author cboyden
 */
public interface IEntity {

    /**
     * Get the id of the {@link IEntity} object
     * @return id of the {@link IEntity} object
     */
    public long getObjectId();

    /**
     * Set the id of the {@link IEntity} object
     * @param id of the {@link IEntity} object
     *
     * //TODO: What happens if an id already exists? Is this a new object? Can this be a negative number or 0?
     */
    public void setObjectId(long id);
}
