package com.ewerp.mud.commands;

import com.ewerp.mud.plugins.ILifecycleListener;
import com.ewerp.mud.plugins.IPlugin;
import com.ewerp.mud.plugins.MockPluginManager;
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
public class TestCommandEngine {
    public static ICommandEngine generateCommandEngine() {
        return new CommandEngine();
    }

    @Test
    public void testCommandEngine() throws Exception {
        ICommandEngine commandEngine = generateCommandEngine();
        ((ILifecycleListener)commandEngine).init();
        ((ILifecycleListener)commandEngine).start();

        final MockPluginManager pluginManager = new MockPluginManager();

        ((IPlugin)commandEngine).registerPluginManager(pluginManager);

        MockPluginCommand command1 = new MockPluginCommand();
        MockPluginCommand command2 = new MockPluginCommand();

        AbstractMockExecutionListener executionListener = new AbstractMockExecutionListener() {
            @Override
            public boolean customBeforeExecute(ICommand command) {
                MockPluginCommand mc = (MockPluginCommand)command;
                Assert.assertEquals(pluginManager, mc.pluginManager);
                Assert.assertEquals(0, mc.executionCount);
                return true;
            }

            @Override
            public void customAfterExecute(ICommand command) {
                MockPluginCommand mc = (MockPluginCommand)command;
                Assert.assertEquals(pluginManager, mc.pluginManager);
                Assert.assertEquals(1, mc.executionCount);
            }
        };

        ((CommandEngine)commandEngine).addExecutionListener(null);
        ((CommandEngine)commandEngine).addExecutionListener(executionListener);
        ((CommandEngine)commandEngine).addExecutionListener(executionListener);

        commandEngine.pushCommand(command1);
        commandEngine.pushCommand(command2);

        Thread.sleep(1000);
        ((ILifecycleListener)commandEngine).stop();

        ((CommandEngine)commandEngine).removeExecutionListener(null);
        ((CommandEngine)commandEngine).removeExecutionListener(executionListener);

        Assert.assertEquals(1, command1.executionCount);
        Assert.assertEquals(1, command2.executionCount);
        Assert.assertEquals(2, executionListener.beforeExecuteCount);
        Assert.assertEquals(2, executionListener.afterExecuteCount);
    }

    @Test
    public void testCommandEngineAbortExecution() throws Exception {
        ICommandEngine commandEngine = generateCommandEngine();
        ((ILifecycleListener)commandEngine).init();
        ((ILifecycleListener)commandEngine).start();

        final MockPluginManager pluginManager = new MockPluginManager();

        ((IPlugin)commandEngine).registerPluginManager(pluginManager);

        MockPluginCommand command1 = new MockPluginCommand();
        MockPluginCommand command2 = new MockPluginCommand();

        AbstractMockExecutionListener executionListener =  new AbstractMockExecutionListener() {
            @Override
            public boolean customBeforeExecute(ICommand command) {
                MockPluginCommand mc = (MockPluginCommand) command;
                Assert.assertEquals(pluginManager, mc.pluginManager);
                Assert.assertEquals(0, mc.executionCount);
                return false;
            }

            @Override
            public void customAfterExecute(ICommand command) {
                Assert.fail("Should never get here, 'beforeExecute()' aborted execution");
            }
        };

        ((CommandEngine)commandEngine).addExecutionListener(executionListener);

        commandEngine.pushCommand(command1);
        commandEngine.pushCommand(command2);

        Thread.sleep(1000);
        ((ILifecycleListener)commandEngine).stop();

        Assert.assertEquals(0, command1.executionCount);
        Assert.assertEquals(0, command2.executionCount);
        Assert.assertEquals(2, executionListener.beforeExecuteCount);
        Assert.assertEquals(0, executionListener.afterExecuteCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCommandEngineSetPluginManagerNull() {
        ICommandEngine commandEngine = generateCommandEngine();
        ((IPlugin)commandEngine).registerPluginManager(null);
    }
}
