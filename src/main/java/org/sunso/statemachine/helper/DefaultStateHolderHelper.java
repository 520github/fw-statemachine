package org.sunso.statemachine.helper;

import org.sunso.statemachine.state.State;
import org.sunso.statemachine.state.holder.DefaultStateHolder;
import org.sunso.statemachine.state.holder.StateHolder;

import java.util.Map;

public class DefaultStateHolderHelper {

    public static StateHolder getDefaultStateHolder(Map<State, StateHolder> stateHolderMap, State source) {
        StateHolder stateHolder = stateHolderMap.get(source);
        if (stateHolder == null) {
            stateHolder = new DefaultStateHolder(source);
            stateHolderMap.put(source, stateHolder);
        }
        return stateHolder;
    }
}
