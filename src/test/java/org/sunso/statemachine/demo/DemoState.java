package org.sunso.statemachine.demo;

import org.sunso.statemachine.state.State;

public enum DemoState implements State {
    INIT("init", "init"),
    CREATE("create", "create"),
    DESTROY("destroy", "destroy"),;

    String key;
    String name;

    DemoState(String key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }
}
