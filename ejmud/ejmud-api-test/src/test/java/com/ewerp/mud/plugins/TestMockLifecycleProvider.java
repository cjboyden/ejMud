package com.ewerp.mud.plugins;

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
public class TestMockLifecycleProvider {
    public static ILifecycleProvider generateLifecycleProvider() {
        return new MockLifecycleProvider();
    }

    @Test
    public void testLifecycleProvider() {
        ILifecycleProvider lifecycleProvider = generateLifecycleProvider();

        MockLifecycleListener lifecycleListener = new MockLifecycleListener();

        lifecycleProvider.unregisterListener(null);
        lifecycleProvider.unregisterListener(lifecycleListener);
        //Register same listener twice
        lifecycleProvider.registerListener(lifecycleListener);
        lifecycleProvider.registerListener(lifecycleListener);
        //Unregister same listener twice
        lifecycleProvider.unregisterListener(lifecycleListener);
        lifecycleProvider.unregisterListener(lifecycleListener);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLifecycleProviderWithNullRegister() {
        ILifecycleProvider lifecycleProvider = generateLifecycleProvider();

        lifecycleProvider.registerListener(null);
    }
}
