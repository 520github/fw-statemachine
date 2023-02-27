package org.sunso.statemachine.demo;

import org.sunso.statemachine.StateMachineID;

public enum DemoStateMachineId implements StateMachineID {
    DEMO("demo", "demo");

    String id;
    String name;

    DemoStateMachineId(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
