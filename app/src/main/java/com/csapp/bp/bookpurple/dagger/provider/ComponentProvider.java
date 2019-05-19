package com.csapp.bp.bookpurple.dagger.provider;

import com.csapp.bp.bookpurple.dagger.component.Graph;

/*
 * Provider for parent graph component
 * Created by Gaurav Sharma on 2019-05-19.
 */
public class ComponentProvider {

    private static Graph component;

    private static void initializeGraph() {
        component = Graph.Initializer.initialize();
    }

    public static Graph getComponent() {
        if (component == null) {
            initializeGraph();
        }
        return component;
    }
}
