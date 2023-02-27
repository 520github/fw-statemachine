package org.sunso.statemachine.demo;

import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

import java.util.Arrays;
import java.util.List;

public enum DemoEvent implements Event {
    INIT("initEvent", "init", Arrays.asList(DemoState.INIT)),
    CREATE("createEvent", "create", Arrays.asList(DemoState.INIT)),
    DESTROY("destroyEvent", "destroy", Arrays.asList(DemoState.CREATE)),;

    String key;
    String name;
    List<State> allowState;

    DemoEvent(String key, String name, List<State> allowState) {
        this.key = key;
        this.name = name;
        this.allowState = allowState;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<State> allowState() {
        return allowState;
    }
}
