package com.guddqs.autoschedule.entity;

import lombok.Data;

/**
 * @author wq
 * @date 2018/9/5
 */
@Data
public class Lesson {
    /**
     * LessonType: 0.行政班必修课(语数英) 1.选修课(6选3或7选三)
     */
    private Integer lessonType;
    private String lessonId;
    private String lessonName;
    private String subjectId;

    private Integer hour;


}
