package org.sunso.statemachine.callback;

import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.response.BizCode;
import org.sunso.statemachine.state.State;

/**
 * 失败或异常的回调处理类
 * @param <C>
 */
public interface Callback<C extends Context> {

    void onFail(State source, State target, Event event, C context, BizCode bizCode);

    void onException(State source, State target, Event event, C context, Exception e);
}
