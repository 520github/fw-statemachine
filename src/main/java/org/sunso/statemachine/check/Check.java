package org.sunso.statemachine.check;

import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.response.Response;
import org.sunso.statemachine.state.State;

/**
 * 检查条件是否满足
 * @param <C>
 */
public interface Check<C extends Context> {
    Response check(State source, State target, Event event, C context);
}
