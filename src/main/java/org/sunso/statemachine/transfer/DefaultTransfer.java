package org.sunso.statemachine.transfer;

import org.sunso.statemachine.action.Action;
import org.sunso.statemachine.check.Check;
import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.prepare.Prepare;
import org.sunso.statemachine.response.DefaultResponse;
import org.sunso.statemachine.response.Response;
import org.sunso.statemachine.state.State;
import org.sunso.statemachine.state.holder.StateHolder;

import java.util.List;

/**
 * 默认连接处理器实现
 */
public class DefaultTransfer implements Transfer {

    private StateHolder source;
    private State target;
    private Event event;
    private Check check;
    private List<Prepare> prepareList;
    private Action action;

    @Override
    public void setSource(StateHolder source) {
        this.source = source;
    }

    @Override
    public void setTarget(State target) {
        this.target = target;
    }

    @Override
    public State getTarget() {
        return target;
    }

    @Override
    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public void setCheck(Check check) {
        this.check = check;
    }

    @Override
    public void setPrepare(List<Prepare> prepareList) {
        this.prepareList = prepareList;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Response transfer(Context context) {
        Response response = doCheck(context);
        if (!response.isSuccess()) {
            return response;
        }
        doPrepareList(context);
        action.execute(source.getSource(), target, event, context);
        return DefaultResponse.defaultSuccess(target);
    }

    private void doPrepareList(Context context) {
        if (prepareList == null) {
            return;
        }
        prepareList.forEach(prepare -> {
            prepare.prepare(source.getSource(), target, event, context);
        });
    }

    private Response doCheck(Context context) {
        if (check == null) {
            return DefaultResponse.defaultSuccess(null);
        }
        return check.check(source.getSource(), target, event, context);
    }
}
