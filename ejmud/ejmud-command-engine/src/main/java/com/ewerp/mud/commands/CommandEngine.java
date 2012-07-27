package com.ewerp.mud.commands;

import com.ewerp.mud.EjMudException;
import com.ewerp.mud.plugins.ILifecycleListener;
import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.IPluginManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

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
 * A simple, thread-safe implementation of the {@link ICommandEngine}.
 * Pass an {@link ICommand} object into the engine via {@link public void CommandEngine.pushCommand(ICommand)}
 * for processing.
 * If the {@link CommandEngine} has an {@link IPluginManager} it will associate that {@link IPluginManager}
 * with the {@link ICommand} if the {@link ICommand} is an instance of {@link IPlugin}.
 */
public class CommandEngine implements IPlugin, ICommandEngine, ILifecycleListener, Runnable {
    private IPluginManager pluginManager;

    protected final BlockingQueue<ICommand> commandQueue = new LinkedBlockingQueue<ICommand>();
    protected final List<IExecutionListener> executionListeners = Collections.synchronizedList(new ArrayList<IExecutionListener>());

    protected Thread thread;
    protected AtomicBoolean shutdown = new AtomicBoolean(false);

    @Override
    public void pushCommand(ICommand command) throws IllegalArgumentException, EjMudException {
        if (command == null) {
            throw new IllegalArgumentException("Parameter [command] MAY NOT be null");
        }

        try {
            commandQueue.put(command);
        } catch (Exception e) {
            throw new EjMudException("Unable to push command onto the stack", e);
        }
    }

    @Override
    public void registerPluginManager(IPluginManager pluginManager) throws IllegalArgumentException {
        if(null == pluginManager) {
            throw new IllegalArgumentException();
        }

        synchronized (this) {
            this.pluginManager = pluginManager;
        }
    }

    @Override
    public List<Class<?>> getInterfaces() {
        return Arrays.asList(new Class<?>[]{ICommandEngine.class});
    }

    @Override
    public void init() {
        thread = new Thread(this);
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        shutdown.set(true);
        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            boolean exec = true;

            while (!shutdown.get()) {
                try {
                    ICommand cmd = commandQueue.take();
                    if (cmd instanceof IPlugin) {
                        ((IPlugin) cmd).registerPluginManager(getPluginManager());
                    }

                    for(IExecutionListener executionListener : executionListeners) {
                        exec = executionListener.beforeExecute(cmd);

                        if(exec == false) {
                            break;
                        }
                    }

                    if(exec) {
                        cmd.execute();

                        for(IExecutionListener executionListener : executionListeners) {
                            executionListener.afterExecute(cmd);
                        }
                    }
                } catch (Exception e) {
                    if(e instanceof InterruptedException) {
                        throw e; // Throw exception outward to exit thread
                    }

                    // TODO: Log to logging facility if it exists, only print the stacktrace as a last ditch effort
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            if(!(e instanceof InterruptedException)) {
                // This is not an expected interrupt exception, a real error has occurred
                // TODO: Log to logging facility if it exists, only print the stacktrace as a last ditch effort
                e.printStackTrace();
            }
        }
    }

    protected IPluginManager getPluginManager() {
        synchronized (this) {
            return pluginManager;
        }
    }

    /**
     * Add an {@link IExecutionListener} to the chain of observers
     * @param executionListener If the executionListener is null or already exists in the list, nothing happens
     */
    public void addExecutionListener(IExecutionListener executionListener) {
        if(null != executionListener && !executionListeners.contains(executionListener)) {
            executionListeners.add(executionListener);
        }
    }

    /**
     * Remove an existing {@link IExecutionListener} from the chain of observers
     * @param executionListener If the executionListener is null or does not exist in the list, nothing happens
     */
    public void removeExecutionListener(IExecutionListener executionListener) {
        if(null != executionListener) {
            executionListeners.remove(executionListener);
        }
    }
}
