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
public class TestLog {
    public static ILog generateLog() {
        return new Log();
    }

    @Test
    public void testLog() {
        ILog log = generateLog();

        String message = "This is a message";
        Exception e = new EjMudException("This is the exception message");

        log.logDebug(message);
        log.logDebug(message, e);
        log.logDetail(message);
        log.logDetail(message, e);
        log.logInfo(message);
        log.logInfo(message, e);
        log.logWarn(message);
        log.logWarn(message, e);
        log.logError(message);
        log.logError(message, e);
    }
}
