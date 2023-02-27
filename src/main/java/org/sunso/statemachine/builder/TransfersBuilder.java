package org.sunso.statemachine.builder;

import org.sunso.statemachine.action.Action;
import org.sunso.statemachine.check.Check;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.helper.DefaultStateHolderHelper;
import org.sunso.statemachine.prepare.Prepare;
import org.sunso.statemachine.state.State;
import org.sunso.statemachine.state.holder.StateHolder;
import org.sunso.statemachine.transfer.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 根据多个sourceState和event创建多个Transfer的建造者
 */
public class TransfersBuilder {
    final Map<State, StateHolder> stateHolderMap;
    private List<StateHolder> sourceStateHolderList = new ArrayList<>();
    private State target;
    private Event event;
    private Check check;
    private List<Prepare> prepareList;
    private Action action;

    public TransfersBuilder(Map<State, StateHolder> stateHolderMap) {
        this.stateHolderMap = stateHolderMap;
    }

    public TransfersBuilder source(State... sources) {
        for(State source: sources) {
            sourceStateHolderList.add(DefaultStateHolderHelper.getDefaultStateHolder(stateHolderMap, source));
        }
        return this;
    }

    public TransfersBuilder source(List<State> sources) {
        for(State source: sources) {
            sourceStateHolderList.add(DefaultStateHolderHelper.getDefaultStateHolder(stateHolderMap, source));
        }
        return this;
    }

    public TransfersBuilder target(State target) {
        this.target = target;
        return this;
    }

    public TransfersBuilder event(Event event) {
        this.event = event;
        return this;
    }

    public TransfersBuilder check(Check check) {
        this.check = check;
        return this;
    }

    public TransfersBuilder prepare(List<Prepare> prepareList) {
        this.prepareList = prepareList;
        return this;
    }

    public TransfersBuilder action(Action action) {
        this.action = action;
        return this;
    }

    public void builder() {
        for(StateHolder sourceStateHolder: sourceStateHolderList) {
            Transfer transfer = sourceStateHolder.addTransfer(target, event);
            transfer.setCheck(check);
            transfer.setPrepare(prepareList);
            transfer.setAction(action);
        }
    }
}
