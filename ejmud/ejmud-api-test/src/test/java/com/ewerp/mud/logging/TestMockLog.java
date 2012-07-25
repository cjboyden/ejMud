package com.ewerp.mud.logging;

import com.ewerp.mud.EjMudException;
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
public class TestMockLog {

    public static ILog generateLog() {
        return new MockLog();
    }

    @Test
    public void testMockLog() {
        ILog log = generateLog();

        Exception e = new EjMudException();

        log.logDebug("debug message");
        log.logDebug("debug message", e);

        log.logDetail("detail message");
        log.logDetail("detail message", e);

        log.logInfo("info message");
        log.logInfo("info message", e);

        log.logWarn("warn message");
        log.logWarn("warn message", e);

        log.logError("error message");
        log.logError("error message", e);
    }
}
