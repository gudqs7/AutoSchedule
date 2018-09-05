package com.guddqs.autoschedule.service;

import com.guddqs.autoschedule.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wq
 * @date 2018/9/5
 */
public class AutoScheduleService {

    private List<AdminClass> adminClasses;

    private List<Student> students;
    private List<Teacher> teachers;
    private List<ClassRoom> classRooms;

    private List<LessonTask> lessonTasks;

    private List<Integer> weeks;
    private List<Integer> dayOfWeeks;
    private List<Integer> sections;
    private Integer sectionHour = 2;

    public void generatorData() {

    }

    /**
     * 开始自动排课
     */
    public List<Schedule> start(Integer algorithm) {
        List<Schedule> backSchedules;
        generatorData();
        if (algorithm == 0) {
            backSchedules = annealing();
        } else if (algorithm == 1) {
            backSchedules = genetic();
        } else {
            throw new NullPointerException("algorithm not exists");
        }
        return backSchedules;
    }

    public List<Schedule> exhaustive() {
        List<Schedule> backSchedules = new ArrayList<>();
        for (LessonTask lessonTask : lessonTasks) {
            Integer hour = lessonTask.getLesson().getHour();
            List<Student> students = lessonTask.getStudents();
            for (Integer week : weeks) {
                for (Integer dayOfWeek : dayOfWeeks) {
                    for (Integer section : sections) {
                        Schedule schedule = new Schedule();
                        schedule.setWeek(week);
                        schedule.setDayOfWeek(dayOfWeek);
                        schedule.setSection(section);
                        schedule.setLessonTaskId(lessonTask.getLessonTaskId());
                    }
                }
            }

        }


        return backSchedules;
    }

    public List<Schedule> annealing() {
        List<Schedule> backSchedules = new ArrayList<>();

        return backSchedules;
    }

    public List<Schedule> genetic() {
        List<Schedule> backSchedules = new ArrayList<>();

        return backSchedules;
    }


}
