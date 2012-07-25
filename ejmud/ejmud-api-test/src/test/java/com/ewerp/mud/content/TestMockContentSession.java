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
public class TestMockContentSession {
    public static IContentSession generateContentSession() {
        return new MockContentSession();
    }

    @Test
    public void testMockContentSession() {
        IContentSession contentSession = generateContentSession();

        MockEntity mockEntity1 = new MockEntity();
        mockEntity1.setObjectId(1);
        MockEntity mockEntity2 = new MockEntity();
        mockEntity2.setObjectId(2);
        MockEntity mockEntity3 = new MockEntity();
        mockEntity3.setObjectId(3);
        MockEntity mockEntity4 = new MockEntity();
        mockEntity4.setObjectId(4);
        MockEntity mockEntity5 = new MockEntity();
        mockEntity5.setObjectId(5);

        for(int i = -5; i <= 5; i++) {
            Assert.assertEquals("Empty session returned a value", null, contentSession.fetch(i));
        }

        contentSession.store(null);
        contentSession.delete(null);

        contentSession.store(mockEntity1);
        contentSession.store(mockEntity3);
        contentSession.store(mockEntity5);

        Assert.assertEquals(mockEntity1, contentSession.fetch(1));
        Assert.assertEquals(null, contentSession.fetch(2));
        Assert.assertEquals(mockEntity3, contentSession.fetch(3));
        Assert.assertEquals(null, contentSession.fetch(4));
        Assert.assertEquals(mockEntity5, contentSession.fetch(5));

        contentSession.delete(mockEntity1);
        contentSession.delete(mockEntity2);
        contentSession.delete(mockEntity3);

        Assert.assertEquals(null, contentSession.fetch(1));
        Assert.assertEquals(null, contentSession.fetch(2));
        Assert.assertEquals(null, contentSession.fetch(3));
        Assert.assertEquals(null, contentSession.fetch(4));
        Assert.assertEquals(mockEntity5, contentSession.fetch(5));
    }
}
