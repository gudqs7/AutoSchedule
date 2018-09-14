package com.guddqs.autoschedule.entity;

import lombok.Data;

/**
 * @author wq
 * @date 2018/9/5
 */
@Data
public class Schedule {

    private String scheduleId;
    private String scheduleName;

    private Integer week;
    private Integer dayOfWeek;
    private Integer section;
    private String startTime;
    private String endTime;

    private String lessonTaskId;
    private LessonTask lessonTask;
    private String lessonId;

    private String classRoomId;
    private String teacherId;

    /**
     * 表记录唯一性:
     * 学生-时间-教室 ( 学生角度: 在同一时间只出现在一个教室上课  )
     * 教师-时间-教室 ( 老师角度: 同上  )
     * 教室-时间-课程 ( 教室角度: 同一时间 教室 里只会上一门课  )
     */

    /**
     * 排课核心指导
     * 以将课程课时均匀分布到每个学生的每一周的时间上为目的
     */

}
