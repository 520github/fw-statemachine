package org.sunso.statemachine.demo;

import org.junit.Assert;
import org.junit.Test;
import org.sunso.statemachine.AbstractTest;
import org.sunso.statemachine.StateMachine;
import org.sunso.statemachine.bootstrap.StateMachineBootstrap;
import org.sunso.statemachine.demo.action.DemoCreateAction;
import org.sunso.statemachine.demo.action.DemoDestroyAction;
import org.sunso.statemachine.demo.prepare.DemoMysqlPrepare;
import org.sunso.statemachine.demo.prepare.DemoRedisPrepare;
import org.sunso.statemachine.response.Response;
import java.util.Arrays;

public class DemoStateMachineTest extends AbstractTest {

    @Test
    public void demoStateMachineTest() {
        StateMachineBootstrap bootstrap = StateMachineBootstrap.create();
        // bootstrap.setCallback(new ThrowExceptionCallback());

        bootstrap.newTransfer()
                .source(DemoState.INIT)
                .target(DemoState.CREATE)
                .event(DemoEvent.CREATE)
                .check(new DemoCheck())
                .prepare(Arrays.asList(new DemoMysqlPrepare(), new DemoRedisPrepare()))
                .action(new DemoCreateAction()).builder();

        bootstrap.newTransfer()
                .source(DemoState.CREATE)
                .target(DemoState.DESTROY)
                .event(DemoEvent.DESTROY)
                .check(new DemoCheck())
                .prepare(Arrays.asList(new DemoMysqlPrepare(), new DemoRedisPrepare()))
                .action(new DemoDestroyAction()).builder();

        StateMachine stateMachine = bootstrap.builder(DemoStateMachineId.DEMO);
        DemoContext context = new DemoContext();

        // check fail
        Response response = stateMachine.fireEvent(DemoState.INIT, DemoEvent.CREATE, context);
        Assert.assertEquals(null, response.getState());
        Assert.assertEquals(false, response.isSuccess());

        // check success, INIT to CREATE
        context.setTag("1");
        response = stateMachine.fireEvent(DemoState.INIT, DemoEvent.CREATE, context);
        Assert.assertEquals(DemoState.CREATE, response.getState());
        Assert.assertEquals(true, response.isSuccess());

        // check success, CREATE to DESTROY
        response = stateMachine.fireEvent(DemoState.CREATE, DemoEvent.DESTROY, context);
        Assert.assertEquals(DemoState.DESTROY, response.getState());
        Assert.assertEquals(true, response.isSuccess());
    }
}
