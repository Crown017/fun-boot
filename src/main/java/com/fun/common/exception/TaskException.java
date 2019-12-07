package com.fun.common.exception;

/**
 * 计划策略异常
 *
 * @author cqjava
 */
public class TaskException extends Exception {
    private static final long serialVersionUID = 1L;

    private Code code;

    public TaskException(String msg, Code code) {
        this(msg, code, null);
    }

    public TaskException(String msg, Code code, Exception nestedEx) {
        super(msg, nestedEx);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public enum Code {
        /** 任务存在 */
        TASK_EXISTS,
        /** 没有任务存在 */
        NO_TASK_EXISTS,
        /** 任务已经开始 */
        TASK_ALREADY_STARTED,
        /** 未知错误 */
        UNKNOWN,
        /** 配置错误：如CRON表达式错误 */
        CONFIG_ERROR,
        /** 任务节点没有变量 */
        TASK_NODE_NOT_AVAILABLE
    }
}