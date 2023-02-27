package org.sunso.statemachine.response;

public enum SystemBizCode implements BizCode {
    success("success", "success"),
    fail("fail", "fail"),
    exception("exception", "异常"),
    transferNull("transferNull", "transfer为空"),
    currentStateNotAllow("currentStateNotAllow", "当前状态不允许")
    ;

    String code;
    String message;

    SystemBizCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
