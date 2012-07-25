package com.ewerp.mud.commands;

import org.junit.Assert;
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

public class TestMockMessage {
    public static IMessage generateMessage() {
        return new MockMessage();
    }

    @Test
    public void testMockMessage() {
        IMessage message = generateMessage();

        MockMessageMeta messageMeta1 = new MockMessageMeta();
        MockMessageMeta messageMeta2 = new MockMessageMeta();
        MockMessageMeta messageMeta3 = new MockMessageMeta();
        MockMessageMeta messageMeta4 = new MockMessageMeta();

        message.addMeta(messageMeta1);
        message.addMeta(messageMeta2);
        message.addMeta(messageMeta3);

        List<IMessageMeta> mockMessageMetaList = message.getMeta();

        Assert.assertTrue(mockMessageMetaList.contains(messageMeta1));
        Assert.assertTrue(mockMessageMetaList.contains(messageMeta2));
        Assert.assertTrue(mockMessageMetaList.contains(messageMeta3));
        Assert.assertFalse(mockMessageMetaList.contains(messageMeta4));

        message.removeMeta(messageMeta1);
        mockMessageMetaList = message.getMeta();

        Assert.assertFalse(mockMessageMetaList.contains(messageMeta1));
        Assert.assertTrue(mockMessageMetaList.contains(messageMeta2));
        Assert.assertTrue(mockMessageMetaList.contains(messageMeta3));
        Assert.assertFalse(mockMessageMetaList.contains(messageMeta4));

        message.setMeta(null);
        mockMessageMetaList = message.getMeta();

        Assert.assertEquals(0, mockMessageMetaList.size());
    }

    @Test
    public void testMockMessageList() {
        IMessage message = generateMessage();

        List<IMessageMeta> mockMessageMetaList = new ArrayList<IMessageMeta>();

        MockMessageMeta messageMeta1 = new MockMessageMeta();
        MockMessageMeta messageMeta2 = new MockMessageMeta();
        MockMessageMeta messageMeta3 = new MockMessageMeta();
        MockMessageMeta messageMeta4 = new MockMessageMeta();

        mockMessageMetaList.add(messageMeta1);
        mockMessageMetaList.add(messageMeta2);
        mockMessageMetaList.add(messageMeta3);

        message.addMeta(mockMessageMetaList);

        List<IMessageMeta> mockMessageMetaListResult = message.getMeta();

        Assert.assertTrue(mockMessageMetaListResult.contains(messageMeta1));
        Assert.assertTrue(mockMessageMetaListResult.contains(messageMeta2));
        Assert.assertTrue(mockMessageMetaListResult.contains(messageMeta3));
        Assert.assertFalse(mockMessageMetaListResult.contains(messageMeta4));

        List<IMessageMeta> mockMessageMetaListRemove = new ArrayList<IMessageMeta>();
        mockMessageMetaListRemove.add(messageMeta1);
        mockMessageMetaListRemove.add(messageMeta2);

        message.removeMeta(mockMessageMetaListRemove);
        mockMessageMetaListResult = message.getMeta();

        Assert.assertFalse(mockMessageMetaListResult.contains(messageMeta1));
        Assert.assertFalse(mockMessageMetaListResult.contains(messageMeta2));
        Assert.assertTrue(mockMessageMetaListResult.contains(messageMeta3));
        Assert.assertFalse(mockMessageMetaListResult.contains(messageMeta4));

        message.setMeta(new ArrayList<IMessageMeta>());
        mockMessageMetaListResult = message.getMeta();

        Assert.assertEquals(0, mockMessageMetaListResult.size());
    }
}
