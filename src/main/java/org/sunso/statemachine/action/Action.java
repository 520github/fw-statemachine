package org.sunso.statemachine.action;

import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

/**
 * 执行动作
 * @param <C>
 */
public interface Action<C extends Context> {
    void execute(State source, State target, Event event, C context);
}
