package com.ewerp.mud.commands;

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

public class MockMessage implements IMessage {
    public List<IMessageMeta> messageMetaList = new ArrayList<IMessageMeta>();

    @Override
    public void addMeta(IMessageMeta meta) {
        messageMetaList.add(meta);
    }

    @Override
    public void removeMeta(IMessageMeta meta) {
        messageMetaList.remove(meta);
    }

    @Override
    public void addMeta(List<IMessageMeta> metaList) {
        messageMetaList.addAll(metaList);
    }

    @Override
    public void removeMeta(List<IMessageMeta> metaList) {
        messageMetaList.removeAll(metaList);
    }

    @Override
    public List<IMessageMeta> getMeta() {
        return messageMetaList;
    }

    @Override
    public void setMeta(List<IMessageMeta> metaList) {
        if(metaList == null) {
            messageMetaList.clear();
        } else {
            messageMetaList = metaList;
        }
    }
}
