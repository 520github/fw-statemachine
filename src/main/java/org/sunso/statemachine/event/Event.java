package org.sunso.statemachine.event;

import org.sunso.statemachine.state.State;

import java.util.List;

/**
 * 事件
 */
public interface Event {

    String getKey();

    String getName();

    List<State> allowState();
}
