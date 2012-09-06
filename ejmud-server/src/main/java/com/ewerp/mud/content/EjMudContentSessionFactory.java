package com.ewerp.mud.content;

import com.ewerp.engine.content.IContentSession;
import com.ewerp.engine.content.IContentSessionFactory;
import com.ewerp.engine.plugins.AbstractLifecyclePlugin;
import com.ewerp.engine.plugins.ILifecycleListener;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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
public class EjMudContentSessionFactory extends AbstractLifecyclePlugin implements IContentSessionFactory {
    SessionFactory sessionFactory;

    public EjMudContentSessionFactory() {
        appendInterfaces(new Class<?>[] {IContentSessionFactory.class});
    }

    @Override
    public IContentSession openContentSession() {
        EjMudContentSession contentSession = null;

        if(null != sessionFactory) {
            contentSession = new EjMudContentSession(sessionFactory.openSession());
        }

        return contentSession;
    }

    @Override
    public void init() {
        super.init();
        Configuration cfg = new Configuration();

        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:h2:/home/cboyden/db1;DB_CLOSE_DELAY=-1;MVCC=TRUE");
//        cfg.setProperty("hibernate.connection.url", "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1;MVCC=TRUE");
        cfg.setProperty("hibernate.connection.username", "sa");
        cfg.setProperty("hibernate.connection.password", "");
        cfg.setProperty("hibernate.connection.pool_size", "1");
        cfg.setProperty("hibernate.hbm2ddl.auto", "create");
        cfg.setProperty("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");

        cfg.addClass(com.ewerp.mud.content.Room.class);

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }
}
