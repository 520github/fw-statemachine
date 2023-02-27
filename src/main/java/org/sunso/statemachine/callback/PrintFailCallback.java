package org.sunso.statemachine.callback;

import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.response.BizCode;
import org.sunso.statemachine.state.State;

/**
 * 控制台打印回调处理类
 */
public class PrintFailCallback extends AbstractCallback<Context> {
    @Override
    public void onFail(State source, State target, Event event, Context context, BizCode bizCode) {
        System.out.println(getMessage(source, target, event, context));
        System.out.println("bizCode:" + bizCode);
    }

    @Override
    public void onException(State source, State target, Event event, Context context, Exception e) {
        System.out.println(getMessage(source, target, event, context));
        e.printStackTrace();
    }
}
