状态机框架
======================

#### 说明
简单、轻量、性能极高的状态机框架，解决业务中状态流转的需求。

#### 概念说明

| 概念             | 说明                                         | 接口                                       |
|----------------|--------------------------------------------|------------------------------------------|
| State          | 状态                                         | org.sunso.statemachine.state.State       |
| Event          | 事件                                         | org.sunso.statemachine.event.Event       |
| Check          | 检查条件                                       | org.sunso.statemachine.check.Check       |
| Prepare        | 数据准备                                       | org.sunso.statemachine.prepare.Prepare   |
| Action         | 执行动作                                       | org.sunso.statemachine.action.Action     |
| Transfer       | 连接处理器,连接state、event、check、prepare、action   | org.sunso.statemachine.transfer.Transfer |
| StateMachine   | 状态机                                        | org.sunso.statemachine.StateMachine      |
| StateMachineID | 状态机id                                      | org.sunso.statemachine.StateMachineID    |
| BizCode        | 状态机执行结果的业务码                                | org.sunso.statemachine.response.BizCode  |
| Response       | 状态机执行结果                                    | org.sunso.statemachine.response.Response |
| Context        | 请求上下文                                      | org.sunso.statemachine.context.Context   |
| Callback       | 失败或异常的回调处理                                 | org.sunso.statemachine.callback.Callback         |
| builder        | 用于构建Transfer                               | org.sunso.statemachine.builder.TransferBuilder                                      |
| bootstrap      | 用于构建StateMachine                           | org.sunso.statemachine.bootstrap.StateMachineBootstrap                                    |
| repository     | 用于存储状态机的实例                                 | org.sunso.statemachine.repository.StateMachineRepositoryFactory                                     |



#### 使用例子
###### demo
demo中实现从init到create状态的流转,从create到destroy状态的流转。

* 定义state
~~~~
package org.sunso.statemachine.demo;

import org.sunso.statemachine.state.State;

public enum DemoState implements State {
    INIT("init", "init"), 
    CREATE("create", "create"), 
    DESTROY("destroy", "destroy"),;

    String key;
    String name;

    DemoState(String key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }
}
~~~~

* 定义event
~~~~
package org.sunso.statemachine.demo;

import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

import java.util.Arrays;
import java.util.List;

public enum DemoEvent implements Event {
    INIT("initEvent", "init", Arrays.asList(DemoState.INIT)),
    CREATE("createEvent", "create", Arrays.asList(DemoState.INIT)),
    DESTROY("destroyEvent", "destroy", Arrays.asList(DemoState.CREATE)),;

    String key;
    String name;
    List<State> allowState;

    DemoEvent(String key, String name, List<State> allowState) {
        this.key = key;
        this.name = name;
        this.allowState = allowState;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<State> allowState() {
        return allowState;
    }
}

~~~~
* 定义check
~~~~
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
~~~~

* 定义prepare
~~~~
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
~~~~
~~~~
package org.sunso.statemachine.demo.prepare;

import org.sunso.statemachine.demo.DemoContext;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.prepare.Prepare;
import org.sunso.statemachine.state.State;

public class DemoRedisPrepare implements Prepare<DemoContext> {
    @Override
    public void prepare(State source, State target, Event event, DemoContext context) {
        context.setRedisData("read redis data");
    }
}
~~~~

* 定义action
~~~~
package org.sunso.statemachine.demo.action;

import org.sunso.statemachine.action.Action;
import org.sunso.statemachine.demo.DemoContext;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

public class DemoCreateAction implements Action<DemoContext> {
    @Override
    public void execute(State source, State target, Event event, DemoContext context) {
        System.out.println("create action execute source:" + source + ",target:" + target + ",event:" + event
                + ",context:" + context);
    }
}
~~~~
~~~~
package org.sunso.statemachine.demo.action;

import org.sunso.statemachine.action.Action;
import org.sunso.statemachine.demo.DemoContext;
import org.sunso.statemachine.event.Event;
import org.sunso.statemachine.state.State;

public class DemoDestroyAction implements Action<DemoContext> {
    @Override
    public void execute(State source, State target, Event event, DemoContext context) {
        System.out.println("destroy action execute source:" + source + ",target:" + target + ",event:" + event
                + ",context:" + context);
    }
}
~~~~

* 定义DemoStateMachineId
~~~~
package org.sunso.statemachine.demo;

import org.sunso.statemachine.StateMachineID;

public enum DemoStateMachineId implements StateMachineID {
    DEMO("demo", "demo");

    String id;
    String name;

    DemoStateMachineId(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
~~~~

* 定义bizCode
~~~~
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
~~~~

* 定义Context
~~~~
package org.sunso.statemachine.demo;

import org.sunso.statemachine.context.Context;

public class DemoContext implements Context {
    private String tag;
    private String mysqlData;
    private String redisData;

    public String getTag() {
        return tag;
    }

    public DemoContext setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getMysqlData() {
        return mysqlData;
    }

    public DemoContext setMysqlData(String mysqlData) {
        this.mysqlData = mysqlData;
        return this;
    }

    public String getRedisData() {
        return redisData;
    }

    public DemoContext setRedisData(String redisData) {
        this.redisData = redisData;
        return this;
    }

    public boolean isEmptyTag() {
        if (tag == null) {
            return true;
        }
        if (tag.trim().length() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "DemoContext{" + "tag='" + tag + '\'' + ", mysqlData='" + mysqlData + '\'' + ", redisData='" + redisData
                + '\'' + '}';
    }
}
~~~~

*  设置状态机并触发事件执行
~~~~
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
~~~~

#### 具体参考单元测试类
~~~~
org.sunso.statemachine.demo.DemoStateMachineTest
~~~~

