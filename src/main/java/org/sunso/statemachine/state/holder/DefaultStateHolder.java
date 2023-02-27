package org.sunso.statemachine.state.holder;

import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;
import org.sunso.statemachine.transfer.DefaultTransfer;
import org.sunso.statemachine.transfer.Transfer;

import java.util.HashMap;
import java.util.Map;

public class DefaultStateHolder implements StateHolder {
    private State source;
    private Map<Event, Transfer> eventTransferMap = new HashMap<>();

    public DefaultStateHolder(State source) {
        this.source = source;
    }

    @Override
    public State getSource() {
        return source;
    }

    @Override
    public Transfer addTransfer(State target, Event event) {
        Transfer transfer = new DefaultTransfer();
        transfer.setSource(this);
        transfer.setEvent(event);
        transfer.setTarget(target);
        eventTransferMap.put(event, transfer);
        return transfer;
    }

    @Override
    public Transfer getTransfer(Event event) {
        return eventTransferMap.get(event);
    }
}
