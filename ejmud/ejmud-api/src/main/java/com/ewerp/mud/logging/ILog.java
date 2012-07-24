package com.ewerp.mud.logging;

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

/**
 * Logging interface for the ejMud.
 *
 * @author cboyden
 */
public interface ILog {
    /**
     * Log a message at the Debug level
     * @param message Message to log; if null nothing is done
     */
    public void logDebug(String message);

    /**
     * logDebug(String message) followed by the stacktrace
     * @param message Message to log; if null then the stacktrace alone is printed
     * @param e if null this is the same as {@link public void logDebug(String message)}
     */
    public void logDebug(String message, Exception e);

    /**
     * Log a message at the Detail level
     * @param message Message to log; if null nothing is done
     */
    public void logDetail(String message);

    /**
     * logDetail(String message) followed by the stacktrace
     * @param message Message to log; if null then the stacktrace alone is printed
     * @param e if null this is the same as {@link public void logDetail(String message)}
     */
    public void logDetail(String message, Exception e);

    /**
     * Log a message at the Info level
     * @param message Message to log; if null nothing is done
     */
    public void logInfo(String message);

    /**
     * logInfo(String message) followed by the stacktrace
     * @param message Message to log; if null then the stacktrace alone is printed
     * @param e if null this is the same as {@link public void logInfo(String message)}
     */
    public void logInfo(String message, Exception e);

    /**
     * Log a message at the Warn level
     * @param message Message to log; if null nothing is done
     */
    public void logWarn(String message);

    /**
     * logWarn(String message) followed by the stacktrace
     * @param message Message to log; if null then the stacktrace alone is printed
     * @param e if null this is the same as {@link public void logWarn(String message)}
     */
    public void logWarn(String message, Exception e);

    /**
     * Log a message at the Error level
     * @param message Message to log; if null nothing is done
     */
    public void logError(String message);

    /**
     * logError(String message) followed by the stacktrace
     * @param message Message to log; if null then the stacktrace alone is printed
     * @param e if null this is the same as {@link public void logError(String message)}
     */
    public void logError(String message, Exception e);
}
