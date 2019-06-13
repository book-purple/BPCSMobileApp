package com.csapp.bp.bookpurple.dagger.component;

import com.csapp.bp.bookpurple.dagger.module.MainModule;
import com.csapp.bp.bookpurple.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/*
 * Written by gauravsharma on 2019-05-19.
 */
@Singleton
@Component(modules = {MainModule.class, NetworkModule.class})
public interface Graph extends ModuleComponent {

    final class Initializer {
        public static Graph initialize() {
            return DaggerGraph.builder().build();
        }
    }
}
