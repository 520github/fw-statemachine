package org.sunso.statemachine.demo.action;

import org.sunso.statemachine.action.Action;
import org.sunso.statemachine.demo.DemoContext;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

public class DemoDestroyAction implements Action<DemoContext> {
    @Override
    public void execute(State source, State target, Event event, DemoContext context) {
        System.out.println("destroy action execute source:" + source + ",target:" + target + ",event:" + event
                + ",context:" + context);
    }
}
