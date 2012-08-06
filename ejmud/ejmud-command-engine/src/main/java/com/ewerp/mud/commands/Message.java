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
public class Message implements IMessage {
    protected List<IMessageMeta> messageMetas = new ArrayList<IMessageMeta>();

    @Override
    public void addMeta(IMessageMeta meta) {
        if(null != meta && !messageMetas.contains(meta)) {
            messageMetas.add(meta);
        }
    }

    @Override
    public void removeMeta(IMessageMeta meta) {
        messageMetas.remove(meta);
    }

    @Override
    public void addMeta(List<IMessageMeta> metaList) {
        if(null != metaList) {
            for(IMessageMeta meta : metaList) {
                addMeta(meta);
            }
        }
    }

    @Override
    public void removeMeta(List<IMessageMeta> metaList) {
        if(null != metaList) {
            for(IMessageMeta meta : metaList) {
                removeMeta(meta);
            }
        }
    }

    @Override
    public List<IMessageMeta> getMeta() {
        return messageMetas;
    }

    @Override
    public void setMeta(List<IMessageMeta> metaList) {
        messageMetas.clear();

        if(null != metaList) {
            for(IMessageMeta meta : metaList) {
                messageMetas.add(meta);
            }
        }
    }
}
