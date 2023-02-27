package org.sunso.statemachine.response;

import org.sunso.statemachine.state.State;

public class DefaultResponse implements Response {
    private BizCode bizCode;
    private State state;
    private boolean isSuccess;

    public static DefaultResponse defaultSuccess(State state) {
        return new DefaultResponse(SystemBizCode.success, state, true);
    }

    public static DefaultResponse defaultFail() {
        return new DefaultResponse(SystemBizCode.fail, null, false);
    }

    public static DefaultResponse success(BizCode bizCode, State state) {
        return new DefaultResponse(bizCode, state, true);
    }

    public static DefaultResponse fail(BizCode bizCode) {
        return new DefaultResponse(bizCode, null, false);
    }

    private DefaultResponse(BizCode bizCode, State state, boolean isSuccess) {
        this.bizCode = bizCode;
        this.state = state;
        this.isSuccess = isSuccess;
    }

    @Override
    public BizCode getBizCode() {
        return bizCode;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public boolean isFail() {
        return !isSuccess();
    }

    @Override
    public String toString() {
        return "DefaultResponse{" +
                "bizCode=" + bizCode +
                ", state=" + state +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
