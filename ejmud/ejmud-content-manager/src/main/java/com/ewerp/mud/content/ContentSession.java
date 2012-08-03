package com.ewerp.mud.content;

import java.util.LinkedList;
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
public class ContentSession implements IContentSession {
    private static ContentSession INSTANCE = new ContentSession();

    private List<IEntity> entities = new LinkedList<IEntity>();

    public static IContentSession getInstance() {
        return INSTANCE;
    }

    protected ContentSession() {
    }

    @Override
    public void rollback() {
    }

    @Override
    public void commit() {
    }

    @Override
    public IEntity fetch(long id) {
        IEntity result = null;

        for(IEntity entity : entities) {
            if(entity.getObjectId() == id) {
                result = entity;
                break;
            }
        }

        return result;
    }

    @Override
    public void store(IEntity object) {
        if(null != object) {
            entities.add(object);
        }
    }

    @Override
    public void delete(IEntity object) {
        if(null != object) {
            IEntity removeEntity = fetch(object.getObjectId());
            if(null != removeEntity) {
                entities.remove(removeEntity);
            }
        }
    }
}
