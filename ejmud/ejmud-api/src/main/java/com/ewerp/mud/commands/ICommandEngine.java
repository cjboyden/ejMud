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

import com.ewerp.mud.EjMudException;

/**
 * The {@link ICommandEngine} executes {@link ICommand} from a {@link ICommand}
 * queue.
 *
 * @author cboyden
 */
public interface ICommandEngine {

    /**
     * Push an {@link ICommand} onto a queue for execution
     *
     * @param command The command to add to the execution queue <br />
     *                <ul>
     *                <li>{@link ICommand} : A valid command extending
     *                {@link ICommand}</li>
     *                <li>null : A null value will result in an
     *                {@link IllegalArgumentException}</li>
     *                </ul>
     * @throws IllegalArgumentException Must be thrown if {@link ICommand} is null
     * @throws EjMudException           Thrown if an error occurs pushing the {@link ICommand} into
     *                                  the queue
     */
    public void pushCommand(ICommand command) throws IllegalArgumentException, EjMudException;
}
