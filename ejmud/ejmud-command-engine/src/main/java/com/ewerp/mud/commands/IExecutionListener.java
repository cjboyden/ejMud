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

/**
 * The {@link CommandEngine} permits other objects to inspect {@link ICommand}s before
 * and after they have been executed.
 * There is no definition of what an {@link IExecutionListener} may do to a command either
 * before or after execution. However the execution of a specific {@link ICommand} will never happen
 * if the {@link boolean IExecutionListener.beforeExecute(ICommand)} does not return true.
 */
public interface IExecutionListener {
    /**
     * Called for every {@link ICommand} processed by {@link CommandEngine}.
     * @param command The {@link ICommand} to be executed. Null is possible and should be handled accordingly
     * @return true to continue execution of the {@link ICommand} within the {@link CommandEngine}, or false
     * to prevent execution.
     */
    public boolean beforeExecute(ICommand command);

    /**
     * Called after every {@link ICommand} processed by {@link CommandEngine}.
     * @param command The {@link ICommand} to be executed. Null is possible and should be handled accordingly
     */
    public void afterExecute(ICommand command);
}
