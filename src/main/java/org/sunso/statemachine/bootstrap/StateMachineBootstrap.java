package org.sunso.statemachine.bootstrap;

import org.sunso.statemachine.DefaultStateMachine;
import org.sunso.statemachine.StateMachine;
import org.sunso.statemachine.StateMachineID;
import org.sunso.statemachine.builder.TransferBuilder;
import org.sunso.statemachine.builder.TransfersBuilder;
import org.sunso.statemachine.callback.Callback;
import org.sunso.statemachine.callback.PrintFailCallback;
import org.sunso.statemachine.repository.StateMachineRepositoryFactory;
import org.sunso.statemachine.state.State;
import org.sunso.statemachine.state.holder.StateHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建状态机的引导类
 */
public class StateMachineBootstrap {
    private final Map<State, StateHolder> stateHolderMap = new ConcurrentHashMap<>();
    private final StateMachine stateMachine = new DefaultStateMachine(stateHolderMap);
    private Callback callback = new PrintFailCallback();

    public static StateMachineBootstrap create() {
        return new StateMachineBootstrap();
    }

    public TransferBuilder newTransfer() {
        return new TransferBuilder(stateHolderMap);
    }

    public TransfersBuilder newTransfers() {
        return new TransfersBuilder(stateHolderMap);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public StateMachine builder(StateMachineID stateMachineId) {
        stateMachine.setStateMachineId(stateMachineId);
        stateMachine.setCallback(callback);
        StateMachineRepositoryFactory.register(stateMachineId, stateMachine);
        return stateMachine;
    }
}
