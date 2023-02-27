package org.sunso.statemachine.builder;

import org.sunso.statemachine.action.Action;
import org.sunso.statemachine.check.Check;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.helper.DefaultStateHolderHelper;
import org.sunso.statemachine.prepare.Prepare;
import org.sunso.statemachine.state.State;
import org.sunso.statemachine.state.holder.StateHolder;
import org.sunso.statemachine.transfer.Transfer;

import java.util.List;
import java.util.Map;

/**
 * 根据sourceState和event创建Transfer的建造者
 */
public class TransferBuilder {
    final Map<State, StateHolder> stateHolderMap;
    private StateHolder sourceStateHolder;
    private State target;
    private Event event;
    private Check check;
    private List<Prepare> prepareList;
    private Action action;

    public TransferBuilder(Map<State, StateHolder> stateHolderMap) {
        this.stateHolderMap = stateHolderMap;
    }

    public TransferBuilder source(State source) {
        this.sourceStateHolder = DefaultStateHolderHelper.getDefaultStateHolder(stateHolderMap, source);
        return this;
    }

    public TransferBuilder target(State target) {
        this.target = target;
        return this;
    }

    public TransferBuilder event(Event event) {
        this.event = event;
        return this;
    }

    public TransferBuilder check(Check check) {
        this.check = check;
        return this;
    }

    public TransferBuilder prepare(List<Prepare> prepareList) {
        this.prepareList = prepareList;
        return this;
    }

    public TransferBuilder action(Action action) {
        this.action = action;
        return this;
    }

    public void builder() {
        Transfer transfer = sourceStateHolder.addTransfer(target, event);
        transfer.setCheck(check);
        transfer.setPrepare(prepareList);
        transfer.setAction(action);
    }
}
