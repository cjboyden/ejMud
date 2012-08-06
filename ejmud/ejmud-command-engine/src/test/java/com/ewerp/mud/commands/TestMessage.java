package com.ewerp.mud.commands;

import junit.framework.Assert;
import org.junit.Test;

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
public class TestMessage {
    public static IMessage generateMessage() {
        return new Message();
    }

    @Test
    public void testMessage() {
        IMessage message = generateMessage();

        MockMessageMeta mockMessageMetaOne = new MockMessageMeta();
        MockMessageMeta mockMessageMetaTwo = new MockMessageMeta();
        MockMessageMeta mockMessageMetaThree = new MockMessageMeta();
        MockMessageMeta mockMessageMetaFour = new MockMessageMeta();

        Assert.assertNotNull(message.getMeta());
        Assert.assertEquals(0, message.getMeta().size());

        message.addMeta(mockMessageMetaOne);
        Assert.assertEquals(1, message.getMeta().size());
        Assert.assertSame(mockMessageMetaOne, message.getMeta().get(0));

        message.addMeta(mockMessageMetaTwo);
        Assert.assertEquals(2, message.getMeta().size());

        message.removeMeta(mockMessageMetaOne);
        Assert.assertEquals(1, message.getMeta().size());
        Assert.assertSame(mockMessageMetaTwo, message.getMeta().get(0));

        message.removeMeta(mockMessageMetaOne);
        message.removeMeta(mockMessageMetaTwo);
        message.removeMeta(mockMessageMetaThree);
        message.removeMeta((IMessageMeta)null);
        message.removeMeta((List<IMessageMeta>)null);

        List<IMessageMeta> mockMessageMetaList = new ArrayList<IMessageMeta>();
        mockMessageMetaList.add(mockMessageMetaOne);
        mockMessageMetaList.add(mockMessageMetaTwo);
        mockMessageMetaList.add(mockMessageMetaThree);
        mockMessageMetaList.add(mockMessageMetaFour);

        message.setMeta(mockMessageMetaList);
        Assert.assertEquals(4, message.getMeta().size());

        mockMessageMetaList.clear();
        mockMessageMetaList.add(mockMessageMetaOne);
        mockMessageMetaList.add(mockMessageMetaTwo);
        mockMessageMetaList.add(mockMessageMetaFour);

        message.removeMeta(mockMessageMetaList);
        Assert.assertEquals(1, message.getMeta().size());
        Assert.assertSame(mockMessageMetaThree, message.getMeta().get(0));

        message.removeMeta(new ArrayList<IMessageMeta>());
        Assert.assertEquals(1, message.getMeta().size());
        Assert.assertSame(mockMessageMetaThree, message.getMeta().get(0));

        message.setMeta(null);
        Assert.assertNotNull(message.getMeta());
        Assert.assertEquals(0, message.getMeta().size());
    }
}
