package com.ewerp.mud.plugins;

import com.ewerp.engine.commands.CommandEngine;
import com.ewerp.engine.content.ContentSessionFactory;
import com.ewerp.engine.logging.Log;
import com.ewerp.engine.plugins.PluginManager;
import com.ewerp.engine.properties.Properties;
import com.ewerp.engine.sessions.SessionManager;
import com.ewerp.mud.sessions.terminal.EjMudSocketSessionFactory;
import com.ewerp.mud.sessions.terminal.interpreter.EjMudCommandInterpreter;

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
public class EjMudPluginManager extends PluginManager {
    private static final EjMudPluginManager INSTANCE = new EjMudPluginManager();

    /**
     * Use the getInstance() method to gain access to the {@link PluginManager}
     * @return An instance of the {@link PluginManager}
     */
    public static PluginManager getInstance() {
        return INSTANCE;
    }

    protected EjMudPluginManager() {
        super();
    }

    @Override
    public void init() {
        //TODO: Load plugins from Configuration
        addPlugin(Properties.getInstance());
        addPlugin(new Log());
        addPlugin(new CommandEngine());
        addPlugin(new ContentSessionFactory());

        SessionManager sessionManager = new SessionManager();
        sessionManager.addSessionFactory(new EjMudSocketSessionFactory());

        addPlugin(sessionManager);

        addPlugin(new EjMudCommandInterpreter());

        super.init();
    }
}
