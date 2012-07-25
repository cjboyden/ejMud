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

import java.util.List;

/**
 * A mechanism for describing an event, commands result, observable interaction. etc...
 * Each consumer of the {@link IMessage} is responsible for interpreting what the event
 * means to it.
 *
 * An {@link IMessage} is described by its constituent {@link IMessageMeta} parts. Each {@link IMessageMeta}
 * describes an aspect of the message that can be interpreted by the messages consumer to determine
 * what the message means to it.
 */
public interface IMessage {
    /**
     * Add a single {@link IMessageMeta} to this {@link IMessage}
     * @param meta {@link IMessageMeta} to describe an aspect of this message.
     * If it is null, nothing will happen.
     */
    public void addMeta(IMessageMeta meta);
    /**
     * Remove a single {@link IMessageMeta} from this {@link IMessage}
     * @param meta {@link IMessageMeta} to be removed
     * If it is null, nothing will happen
     * If it cannot be found, nothing will happen
     */
    public void removeMeta(IMessageMeta meta);
    /**
     * Append a group of {@link IMessageMeta} to this {@link IMessage}
     * @param metaList {@link List<IMessageMeta>} describing aspects of this message.
     * If the list is null, nothing will happen.
     * If the list is empty, nothing will happen
     */
    public void addMeta(List<IMessageMeta> metaList);
    /**
     * Remove a group of {@link IMessageMeta} from this {@link IMessage}
     * @param metaList {@link List<IMessageMeta>} to be removed
     * If the list is null, nothing will happen
     * If the list is empty, nothing will happen
     * If an {@link IMessageMeta} cannot be found, it will be skipped and the others will be removed
     */
    public void removeMeta(List<IMessageMeta> metaList);

    /**
     * Get the current list of {@link IMessageMeta} describing this {@link IMessage}
     * @return The list of {@link IMessageMeta} describing this {@link IMessage}
     * The list will be empty if no {@link IMessageMeta} are contained
     * The list returned will never be null
     */
    public List<IMessageMeta> getMeta();
    /**
     * Replace the current list of {@link IMessageMeta} describing this {@link IMessage}
     * @param metaList {@link List<IMessageMeta>} to replace the current list of {@link IMessageMeta}
     * If this list is empty or null the current list will be cleared
     */
    public void setMeta(List<IMessageMeta> metaList);
}
