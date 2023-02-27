package org.sunso.statemachine.action;

import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

/**
 * 空动作，没有做任何事情
 */
public class EmptyAction implements Action<Context> {
    @Override
    public void execute(State source, State target, Event event, Context context) {

    }
}
