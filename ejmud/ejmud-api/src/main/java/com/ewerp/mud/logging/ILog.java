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
    public void logDebug(String message);

    public void logDebug(String message, Exception e);

    public void logDetailed(String message);

    public void logDetailed(String message, Exception e);

    public void logInfo(String message);

    public void logInfo(String message, Exception e);

    public void logError(String message);

    public void logError(String message, Exception e);
}
