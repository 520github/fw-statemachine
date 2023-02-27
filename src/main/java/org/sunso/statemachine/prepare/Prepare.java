package org.sunso.statemachine.prepare;

import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

/**
 * 数据准备
 * @param <C>
 */
public interface Prepare<C extends Context> {
    void prepare(State source, State target, Event event, C context);
}
