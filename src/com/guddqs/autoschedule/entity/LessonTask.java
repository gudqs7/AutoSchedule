package com.guddqs.autoschedule.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wq
 * @date 2018/9/5
 */
@Data
public class LessonTask {

    private String lessonTaskId;
    private String lessonTaskName;

    private String lessonId;
    private Lesson lesson;

    private String adminClassId;

    private String teacherId;

    private List<Student> students;

}
