package org.sunso.statemachine.exception;

public class StateMachineFailException extends RuntimeException {

    public StateMachineFailException(String msg) {
        super(msg);
    }

    public StateMachineFailException(String msg, Throwable t) {
        super(msg, t);
    }

}
