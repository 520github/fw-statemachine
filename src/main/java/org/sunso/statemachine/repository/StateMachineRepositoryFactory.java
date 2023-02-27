package org.sunso.statemachine.repository;

import org.sunso.statemachine.StateMachine;
import org.sunso.statemachine.StateMachineID;
import org.sunso.statemachine.exception.StateMachineFailException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储状态机实例化的仓库
 */
public class StateMachineRepositoryFactory {
    private static Map<StateMachineID, StateMachine> stateMachineMap = new ConcurrentHashMap<>();

    public static void register(StateMachineID id, StateMachine stateMachine) {
        if (stateMachineMap.containsKey(id)) {
            throw new StateMachineFailException(
                    "The stateMachine with id [" + id + "] is already build, no need to build again");
        }
        stateMachineMap.put(id, stateMachine);
    }

    public static StateMachine get(StateMachineID id) {
        if (!stateMachineMap.containsKey(id)) {
            throw new StateMachineFailException(
                    "There is no stateMachine instance for [" + id + "], please build it first");
        }
        return stateMachineMap.get(id);
    }
}
