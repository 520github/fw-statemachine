package org.sunso.statemachine.transfer;

import org.sunso.statemachine.action.Action;
import org.sunso.statemachine.check.Check;
import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.prepare.Prepare;
import org.sunso.statemachine.response.Response;
import org.sunso.statemachine.state.State;
import org.sunso.statemachine.state.holder.StateHolder;

import java.util.List;

/**
 * 连接处理器,连接state、event、check、prepare、action
 */
public interface Transfer {
    void setSource(StateHolder source);

    void setTarget(State target);

    State getTarget();

    void setEvent(Event event);

    void setCheck(Check check);

    void setPrepare(List<Prepare> prepareList);

    void setAction(Action action);

    Response transfer(Context context);
}
