package com.ewerp.mud.content;

import com.ewerp.engine.content.IContentSession;
import com.ewerp.engine.content.IEntity;
import org.hibernate.Session;

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
public class EjMudContentSession implements IContentSession {
    private Session hibernateSession;

    public EjMudContentSession(Session hibernateSession) {
        this.hibernateSession = hibernateSession;
        if(null != hibernateSession) {
            hibernateSession.beginTransaction();
        }
    }

    @Override
    public void rollback() {
        if(null != hibernateSession) {
            hibernateSession.getTransaction().rollback();
            hibernateSession.close();
        }
    }

    @Override
    public void commit() {
        if(null != hibernateSession) {
            hibernateSession.getTransaction().commit();
            hibernateSession.close();
        }
    }

    @Override
    public IEntity fetch(long id) {
        return null;
    }

    @Override
    public void store(IEntity entity) {
        if(null != hibernateSession) {
            hibernateSession.saveOrUpdate(entity);
        }
    }

    @Override
    public void delete(IEntity entity) {
        if(null != hibernateSession) {
            hibernateSession.delete(entity);
        }
    }
}
