package com.ewerp.mud.sessions.terminal;

import com.ewerp.mud.plugins.ILifecycleListener;
import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.IPluginManager;
import com.ewerp.mud.properties.IProperties;
import com.ewerp.mud.sessions.ISessionFactory;
import com.ewerp.mud.sessions.ISessionManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
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
public abstract class SocketSessionFactory implements ISessionFactory, IPlugin, ILifecycleListener, Runnable {
    private ISessionManager sessionManager;

    protected ServerSocket serverSocket;
    protected Thread serverThread;

    private IPluginManager pluginManager;
    private IProperties properties;

    public static String FIELD_SERVER_PORT = "terminal-port";
    private static String SERVER_PORT = "37209";

    protected AtomicBoolean shutdown = new AtomicBoolean(false);

    protected String getProperty(String key, String defaultValue) {
        String result = null;

        if(null != properties) {
            result = properties.getProperty(key);
        }

        if(null == result) {
            result = defaultValue;
        }

        return result;
    }

    @Override
    public void registerSessionManager(ISessionManager sessionManager) throws IllegalArgumentException {
        if(null == sessionManager) {
            throw new IllegalArgumentException();
        }

        this.sessionManager = sessionManager;
    }

    /**
     * Configure the server (Get configuration information from an {@link com.ewerp.mud.properties.IProperties} instance)
     * -- Server URL / port
     * -- Attach ICommand generator(s) / IMessage interpreter(s)
     */
    @Override
    public void init() {
        if(null != pluginManager) {
            properties = (IProperties)pluginManager.getPlugin(IProperties.class);
        }

        try {
            String serverPort = getProperty(SocketSessionFactory.FIELD_SERVER_PORT, SERVER_PORT);
            serverSocket = new ServerSocket(Integer.parseInt(serverPort));

            serverThread = new Thread(this);

        } catch (IOException e) {
            // Todo: Connect to logger
            e.printStackTrace();
        }
    }

    /**
     * Run the server / start the listener thread to begin accepting connections
     */
    @Override
    public void start() {
        if(null != serverThread && !serverThread.isAlive()) {
            serverThread.start();
        }
    }

    /**
     * Stop listening for new connections
     * -- Current connections will be terminated by their handling ISessions and will
     *    be notified by the ISessionManager with which they are registered
     */
    @Override
    public void stop() {
        shutdown.set(true);
        serverThread.interrupt();
    }

    /**
     * Listen for new socket connections and spin up a new SocketSession for each
     */
    @Override
    public void run() {
        try {
            if(null != serverSocket) {
                Socket client = null;

                while (!shutdown.get()) {
                    try {
                        client = serverSocket.accept();
                        // TODO: Add a hook here that returns true if client should be accepted
                        sessionManager.addSession(createSocketSession(client));
                    } catch (IOException e) {
                        //TODO: Log
                        e.printStackTrace();
                    }
                }
            }

            // Shutdown server
            serverSocket.close();

        } catch (IOException e) {
            //TODO: log
            e.printStackTrace();
        }
    }

    protected IPluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public void registerPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public List<Class<?>> getInterfaces() {
        return Arrays.asList(new Class<?>[]{ISessionFactory.class});
    }

    protected abstract ITerminalSession createSocketSession(Socket socket);
}
