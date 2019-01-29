package com.geek.wheel.entity;

/**
 * @DESCRIPTION: 槽位
 * @CLASSNAME: Task
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 15:11
 */
public class Task {
    /**
     * 任务ID
     */
    private Integer taskId;

    /**
     * 任务名称
     */
    private String  taskName;

    /**
     * 任务内容
     */
    private String  taskBody;

    /**
     * 执行时间
     */
    private Integer cycleNum;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskBody() {
        return taskBody;
    }

    public void setTaskBody(String taskBody) {
        this.taskBody = taskBody;
    }

    public Integer getCycleNum() {
        return cycleNum;
    }

    public void setCycleNum(Integer cycleNum) {
        this.cycleNum = cycleNum;
    }
}
