package com.guddqs.autoschedule.entity;

import lombok.Data;

import java.util.List;

/**
 * 教学任务, 由必修课(语数英)和选修课(6|7 选3)组成, 为具体 任务, 精确到 班级(虚拟班级)
 * @author wq
 * @date 2018/9/5
 */
@Data
public class LessonTask {

    private String lessonTaskId;
    private String lessonTaskName;

    /**
     * 课程信息
     */
    private String lessonId;
    private Lesson lesson;

    private String adminClassId;

    private String teacherId;

    private List<Student> students;

}
