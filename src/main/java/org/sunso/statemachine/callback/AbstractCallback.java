package org.sunso.statemachine.callback;

import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

/**
 * 回调处理抽象类
 * @param <C>
 */
public abstract class AbstractCallback<C extends Context> implements Callback<C> {
    protected String getMessage(State source, State target, Event event, Context context) {
        return String.format("Cannot fire event [%s] on current state [%s] to target state [%s] with context [%s]",
                event, source, target, context);
    }
}
