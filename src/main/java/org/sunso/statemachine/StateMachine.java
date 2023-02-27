package org.sunso.statemachine;

import org.sunso.statemachine.callback.Callback;
import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.response.Response;
import org.sunso.statemachine.state.State;

/**
 * 状态机
 */
public interface StateMachine {
    void setStateMachineId(StateMachineID stateMachineId);

    void setCallback(Callback callback);

    Response fireEvent(State sourceState, Event event, Context context);
}
