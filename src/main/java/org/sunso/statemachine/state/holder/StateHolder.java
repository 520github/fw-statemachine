package org.sunso.statemachine.state.holder;

import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;
import org.sunso.statemachine.transfer.Transfer;

/**
 * 状态
 */
public interface StateHolder {
    State getSource();

    Transfer addTransfer(State target, Event event);

    Transfer getTransfer(Event event);
}
