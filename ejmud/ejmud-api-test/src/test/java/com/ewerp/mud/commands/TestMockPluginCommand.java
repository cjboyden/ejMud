package com.ewerp.mud.commands;

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

import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.IPluginManager;
import com.ewerp.mud.plugins.MockPluginManager;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

public class TestMockPluginCommand {

    private static ICommand generateCommand() {
        return new MockPluginCommand();
    }

    @Test
    public void testVerifyPluginClass() {
        ICommand command = generateCommand();

        List<Class<?>> classList = ((IPlugin)command).getInterfaces();

        Assert.assertNotNull(classList);
        Assert.assertEquals(1, classList.size());
        Assert.assertTrue(classList.contains(ICommand.class));
    }

    @Test
    public void testExecuteGoodPluginManager() {
        ICommand command = generateCommand();

        MockPluginManager pluginManager = new MockPluginManager();
        ((IPlugin)command).registerPluginManager(pluginManager);
        command.execute();
    }

    @Test
    public void testExecuteNullPluginManager() {
        ICommand command = generateCommand();

        ((IPlugin)command).registerPluginManager(null);
        command.execute();
    }
}
