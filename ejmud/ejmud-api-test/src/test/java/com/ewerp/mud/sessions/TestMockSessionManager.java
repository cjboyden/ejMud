package com.ewerp.mud.sessions;

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
public class TestMockSessionManager {
    public static ISessionManager generateSessionManager() {
        return new MockSessionManager();
    }

    @Test
    public void testMockSessionManager() {
        ISessionManager sessionManager = generateSessionManager();

        MockSession session = new MockSession();

        sessionManager.removeSession(null);
        sessionManager.removeSession(session);

        sessionManager.addSession(session);
        sessionManager.addSession(session);

        sessionManager.removeSession(session);
        sessionManager.removeSession(session);
        sessionManager.removeSession(session);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMockSessionManagerWithNull() {
        ISessionManager sessionManager = generateSessionManager();

        sessionManager.addSession(null);
    }
}
