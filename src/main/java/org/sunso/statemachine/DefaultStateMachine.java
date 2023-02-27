package org.sunso.statemachine;

import org.sunso.statemachine.callback.Callback;
import org.sunso.statemachine.context.Context;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.helper.DefaultStateHolderHelper;
import org.sunso.statemachine.response.BizCode;
import org.sunso.statemachine.response.DefaultResponse;
import org.sunso.statemachine.response.Response;
import org.sunso.statemachine.response.SystemBizCode;
import org.sunso.statemachine.state.State;
import org.sunso.statemachine.state.holder.StateHolder;
import org.sunso.statemachine.transfer.Transfer;

import java.util.Map;

/**
 * 默认状态机实现
 */
public class DefaultStateMachine implements StateMachine {
    private StateMachineID stateMachineId;

    private final Map<State, StateHolder> stateHolderMap;
    private Callback callback;

    public DefaultStateMachine(Map<State, StateHolder> stateHolderMap) {
        this.stateHolderMap = stateHolderMap;
    }

    @Override
    public void setStateMachineId(StateMachineID stateMachineId) {
        this.stateMachineId = stateMachineId;
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public Response fireEvent(State sourceState, Event event, Context context) {
        Transfer transfer = getTransferByEvent(sourceState, event);
        if (transfer == null) {
            doCallback(sourceState, null, event, context, SystemBizCode.transferNull, null);
            return DefaultResponse.fail(SystemBizCode.transferNull);
        }
        try {
            Response response = transfer.transfer(context);
            if (!response.isSuccess()) {
                doCallback(sourceState, transfer.getTarget(), event, context, response.getBizCode(), null);
            }
            return response;
        } catch (Exception e) {
            doCallback(sourceState, transfer.getTarget(), event, context, SystemBizCode.exception, e);
            return DefaultResponse.fail(SystemBizCode.exception);
        }

    }

    private Transfer getTransferByEvent(State sourceState, Event event) {
        return geStateHolder(sourceState).getTransfer(event);
    }

    private StateHolder geStateHolder(State sourceState) {
        return DefaultStateHolderHelper.getDefaultStateHolder(stateHolderMap, sourceState);
    }

    private void doCallback(State source, State target, Event event, Context context, BizCode bizCode, Exception e) {
        if (callback == null) {
            return;
        }
        if (e != null) {
            callback.onException(source, target, event, context, e);
        } else {
            callback.onFail(source, target, event, context, bizCode);
        }
    }

}
