package com.ewerp.mud.sessions;

import java.util.ArrayList;
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

public class MockSessionManager implements ISessionManager {
    public List<ISession> sessionList = new ArrayList<ISession>();
    public List<ISessionFactory> sessionFactoryList = new ArrayList<ISessionFactory>();

    @Override
    public void addSession(ISession session) throws IllegalArgumentException {
        if(null == session) {
            throw new IllegalArgumentException();
        }
        sessionList.add(session);
    }

    @Override
    public void removeSession(ISession session) {
        sessionList.remove(session);
    }

    @Override
    public void addSessionFactory(ISessionFactory sessionFactory) throws IllegalArgumentException {
        if(null == sessionFactory) {
            throw new IllegalArgumentException();
        }
        sessionFactoryList.add(sessionFactory);
    }

    @Override
    public void removeSessionFactory(ISessionFactory sessionFactory) {
        sessionFactoryList.remove(sessionFactory);
    }
}
