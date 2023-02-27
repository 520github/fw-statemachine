package org.sunso.statemachine.demo;

import org.sunso.statemachine.response.BizCode;

public enum DemoBizCode implements BizCode {
    tagEmpty("tagEmpty", "tagEmpty"),;

    String code;
    String message;

    DemoBizCode(String code, String message) {
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
