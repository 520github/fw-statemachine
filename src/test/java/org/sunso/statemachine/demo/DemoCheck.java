package org.sunso.statemachine.demo;

import org.sunso.statemachine.check.Check;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.response.DefaultResponse;
import org.sunso.statemachine.response.Response;
import org.sunso.statemachine.state.State;

public class DemoCheck implements Check<DemoContext> {
    @Override
    public Response check(State source, State target, Event event, DemoContext context) {
        if (context.isEmptyTag()) {
            return DefaultResponse.fail(DemoBizCode.tagEmpty);
        }
        return DefaultResponse.defaultSuccess(target);
    }
}
