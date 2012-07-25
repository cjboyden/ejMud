package com.ewerp.mud.logging;

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

public class MockLog implements ILog {
    public class MockLogEntry {
        public String logLevel;
        public String message;
        public Exception exception;

        public MockLogEntry(String logLevel, String message, Exception exception) {
            this.logLevel = logLevel;
            this.message = message;
            this.exception = exception;
        }
    }

    public List<MockLogEntry> mockLogEntryList = new ArrayList<MockLogEntry>();

    @Override
    public void logDebug(String message) {
        mockLogEntryList.add(new MockLogEntry("debug", message, null));
    }

    @Override
    public void logDebug(String message, Exception e) {
        mockLogEntryList.add(new MockLogEntry("debug", message, e));
    }

    @Override
    public void logDetail(String message) {
        mockLogEntryList.add(new MockLogEntry("detail", message, null));
    }

    @Override
    public void logDetail(String message, Exception e) {
        mockLogEntryList.add(new MockLogEntry("detail", message, e));
    }

    @Override
    public void logInfo(String message) {
        mockLogEntryList.add(new MockLogEntry("info", message, null));
    }

    @Override
    public void logInfo(String message, Exception e) {
        mockLogEntryList.add(new MockLogEntry("info", message, e));
    }

    @Override
    public void logWarn(String message) {
        mockLogEntryList.add(new MockLogEntry("warn", message, null));
    }

    @Override
    public void logWarn(String message, Exception e) {
        mockLogEntryList.add(new MockLogEntry("warn", message, e));
    }

    @Override
    public void logError(String message) {
        mockLogEntryList.add(new MockLogEntry("error", message, null));
    }

    @Override
    public void logError(String message, Exception e) {
        mockLogEntryList.add(new MockLogEntry("error", message, e));
    }
}
