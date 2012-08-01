package com.ewerp.mud.sessions;

import com.ewerp.mud.plugins.ILifecycleListener;
import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.IPluginManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
public class SessionManager implements ISessionManager, ILifecycleListener, IPlugin {
    protected IPluginManager pluginManager;

    protected List<ISessionFactory> sessionFactoryList = Collections.synchronizedList(new ArrayList<ISessionFactory>());

    protected List<ISession> sessionList = Collections.synchronizedList(new ArrayList<ISession>());

    /**
     * Session factories will add new sessions here
     */
    @Override
    public void addSession(ISession session) throws IllegalArgumentException {
        if (null == session) {
            throw new IllegalArgumentException();
        }

        if(!sessionList.contains(session)) {
            session.setPluginManager(getPluginManager());
            sessionList.add(session);
        }
    }

    /**
     * Sessions can be removed here, either they remove themselves when finished or something else can remove them
     */
    @Override
    public void removeSession(ISession session) {
        sessionList.remove(session);
    }

    @Override
    public void addSessionFactory(ISessionFactory sessionFactory) throws IllegalArgumentException {
        if(null == sessionFactory) {
            throw new IllegalArgumentException();
        }

        if(!sessionFactoryList.contains(sessionFactory)) {
            sessionFactoryList.add(sessionFactory);
        }
    }

    @Override
    public void removeSessionFactory(ISessionFactory sessionFactory) {
        sessionFactoryList.remove(sessionFactory);
    }

    /**
     * Load session factories
     */
    @Override
    public void init() {
        // TODO: Populate sessionFactoryList from IPluginManager -- Get all instance of ISessionFactory that are registered

        for (ISessionFactory sessionFactory : sessionFactoryList) {
            sessionFactory.registerSessionManager(this);
            if (sessionFactory instanceof ILifecycleListener) {
                ((ILifecycleListener) sessionFactory).init();
            }
        }
    }

    /**
     * Start all session factories
     */
    @Override
    public void start() {
        for (ISessionFactory sessionFactory : sessionFactoryList) {
            if (sessionFactory instanceof ILifecycleListener) {
                ((ILifecycleListener) sessionFactory).start();
            }
        }
    }

    /**
     * Stop all sessions and session factories
     */
    @Override
    public void stop() {
        // Send the shutdown signal to all active sessions
        for (ISession session : sessionList) {
            session.shutdown();
        }

        // Stop all session factories
        for (ISessionFactory sessionFactory : sessionFactoryList) {
            if (sessionFactory instanceof ILifecycleListener) {
                ((ILifecycleListener) sessionFactory).stop();
            }
        }
    }

    @Override
    public void registerPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public List<Class<?>> getInterfaces() {
        return Arrays.asList(new Class<?>[] {ISessionManager.class});
    }

    protected IPluginManager getPluginManager() {
        return pluginManager;
    }
}
