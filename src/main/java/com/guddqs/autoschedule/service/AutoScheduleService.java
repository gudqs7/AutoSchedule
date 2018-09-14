package com.guddqs.autoschedule.service;

import com.guddqs.autoschedule.entity.*;
import com.guddqs.autoschedule.util.FileHelper;
import com.guddqs.autoschedule.util.JsonUtil;

import java.io.IOException;
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
    private List<ClassRoom> classRooms = new ArrayList<>();

    private List<LessonTask> lessonTasks = new ArrayList<>();

    private List<Integer> weeks = new ArrayList<>();
    private List<Integer> dayOfWeeks = new ArrayList<>();
    private List<Integer> sections = new ArrayList<>();
    private static final Integer SECTION_HOUR = 2;
    private static final Integer CLASS_HOUR = 160;

    /**
     * 制造数据
     */
    public void generatorData() throws IOException {
        for (int i = 0; i < 20; i++) {
            weeks.add(i + 1);
        }
        for (int i = 0; i < 5; i++) {
            dayOfWeeks.add(i + 1);
        }
        for (int i = 0; i < 4; i++) {
            sections.add(i + 1);
        }
        for (int i = 0; i < 10; i++) {
            classRooms.add(new ClassRoom(i + "", (i + 1) + "号教室"));
        }
        lessonTasks = JsonUtil.jsonToBeanList(FileHelper.readFile(AutoScheduleService.class.getClassLoader().getResourceAsStream("lessonTask.json")), LessonTask.class);
        System.out.println(lessonTasks);
    }

    /**
     * 开始自动排课
     */
    public List<Schedule> start(Integer algorithm) throws IOException {
        List<Schedule> backSchedules;
        generatorData();
        if (algorithm == 0) {
            backSchedules = annealing();
        } else if (algorithm == 1) {
            backSchedules = genetic();
        } else if (algorithm == -1) {
            backSchedules = exhaustive();
        } else {
            throw new NullPointerException("algorithm not exists");
        }
        return backSchedules;
    }

    /**
     * 穷举法
     * 将每个教室 的教室时间 按每周每天每节次 排满课程(即教学任务, 教学任务按顺序轮排, 排满课时换下一个)
     *
     * @return 结果集
     */
    public List<Schedule> exhaustive() {
        Integer sumSection = 0;
        List<Schedule> backSchedules = new ArrayList<>();
        for (ClassRoom classRoom : classRooms) {
            boolean overClassRoom = false;
            for (Integer week : weeks) {
                if (overClassRoom) {
                    break;
                }
                for (Integer dayOfWeek : dayOfWeeks) {
                    if (overClassRoom) {
                        break;
                    }
                    for (Integer section : sections) {
                        sumSection++;
                        LessonTask lessonTask = getLessonTaskByHourIndex(sumSection);
                        if (lessonTask == null) {
                            System.out.println(" 教学班已排完 ");
                            return backSchedules;
                        }
                        if (isOverClassRoom(sumSection, classRoom)) {
                            System.out.println(classRoom.getClassRoomName() + "号教室已排完 ");
                            overClassRoom = true;
                            break;
                        }
                        Schedule schedule = new Schedule();
                        schedule.setScheduleName(classRoom.getClassRoomName() + "号教室第" + week + "周第" + dayOfWeek + "天第" + section + "节" + lessonTask.getLessonTaskName());
                        schedule.setWeek(week);
                        schedule.setDayOfWeek(dayOfWeek);
                        schedule.setSection(section);
                        schedule.setClassRoomId(classRoom.getClassRoomId());
                        schedule.setLessonTaskId(lessonTask.getLessonTaskId());
                        backSchedules.add(schedule);
                    }
                }

            }
        }

        return backSchedules;
    }

    /**
     * 教室时间是否使用完毕
     */
    private boolean isOverClassRoom(Integer nowSection, ClassRoom classRoom2) {
        Integer lessonSum = 0;
        for (ClassRoom classRoom : classRooms) {
            lessonSum += (CLASS_HOUR % SECTION_HOUR == 0 ? (CLASS_HOUR / SECTION_HOUR) : ((CLASS_HOUR / SECTION_HOUR) + 1));
            if (nowSection <= lessonSum) {
                return !classRoom.equals(classRoom2);
            }
        }
        return false;
    }

    /**
     * 根据累计课时(节次) 计算当前轮排教学任务
     */
    private LessonTask getLessonTaskByHourIndex(Integer nowSection) {
        Integer lessonSum = 0;
        for (LessonTask lessonTask : lessonTasks) {
            // 160 hour / 1 = 157 sections
            lessonSum += (lessonTask.getLesson().getHour() % SECTION_HOUR == 0 ? (lessonTask.getLesson().getHour() / SECTION_HOUR) : ((lessonTask.getLesson().getHour() / SECTION_HOUR) + 1));
            if (nowSection <= lessonSum) {
                return lessonTask;
            }
        }
        return null;
    }

    /**
     * 模拟退火算法
     *
     * @return
     */
    public List<Schedule> annealing() {
        List<Schedule> backSchedules = new ArrayList<>();

        return backSchedules;
    }

    /**
     * 遗传算法
     *
     * @return
     */
    public List<Schedule> genetic() {
        List<Schedule> backSchedules = new ArrayList<>();

        return backSchedules;
    }


}
