package org.sunso.statemachine.response;

import org.sunso.statemachine.state.State;

/**
 * 事件执行结果
 */
public interface Response {
    BizCode getBizCode();

    State getState();

    boolean isSuccess();

    boolean isFail();
}
