package com.ewerp.mud.content;

import junit.framework.Assert;
import org.junit.Test;

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
public class TestContentSession {
    public static IContentSession generateContentSession() {
        return ContentSession.getInstance();
    }

    @Test
    public void testContentSession() {
        IContentSession contentSession = generateContentSession();

        IEntity entity;

        IEntity entityOne = new MockEntity();
        entityOne.setObjectId(1);

        IEntity entityTwo = new MockEntity();
        entityTwo.setObjectId(2);

        contentSession.store(null);

        contentSession.store(entityOne);

        entity = contentSession.fetch(0);
        Assert.assertNull(entity);

        entity = contentSession.fetch(1);
        Assert.assertNotNull(entity);
        Assert.assertEquals(1, entity.getObjectId());

        entity = contentSession.fetch(2);
        Assert.assertNull(entity);

        contentSession.store(entityTwo);
        entity = contentSession.fetch(2);
        Assert.assertNotNull(entity);
        Assert.assertEquals(2, entity.getObjectId());

        contentSession.delete(entityOne);
        entity = contentSession.fetch(1);
        Assert.assertNull(entity);
        entity = contentSession.fetch(2);
        Assert.assertNotNull(entity);

        contentSession.delete(entityTwo);
        entity = contentSession.fetch(1);
        Assert.assertNull(entity);
        entity = contentSession.fetch(2);
        Assert.assertNull(entity);

        contentSession.delete(null);
        contentSession.delete(entityOne);
    }
}
