package org.sunso.statemachine.demo.prepare;

import org.sunso.statemachine.demo.DemoContext;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.prepare.Prepare;
import org.sunso.statemachine.state.State;

public class DemoMysqlPrepare implements Prepare<DemoContext> {
    @Override
    public void prepare(State source, State target, Event event, DemoContext context) {
        context.setMysqlData("read mysql data");
    }
}
