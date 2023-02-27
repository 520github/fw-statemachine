package org.sunso.statemachine.callback;

import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.exception.StateMachineFailException;
import org.sunso.statemachine.response.BizCode;
import org.sunso.statemachine.state.State;

/**
 * 直接抛出运行时异常的会调处理类
 */
public class ThrowExceptionCallback extends AbstractCallback<Context> {
    @Override
    public void onFail(State source, State target, Event event, Context context, BizCode bizCode) {
        throw new StateMachineFailException(getMessage(source, target, event, context));
    }

    @Override
    public void onException(State source, State target, Event event, Context context, Exception e) {
        throw new StateMachineFailException(getMessage(source, target, event, context), e);
    }

}
