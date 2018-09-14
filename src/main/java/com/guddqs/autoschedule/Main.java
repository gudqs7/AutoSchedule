package com.guddqs.autoschedule;

import com.guddqs.autoschedule.entity.Schedule;
import com.guddqs.autoschedule.entity.Student;
import com.guddqs.autoschedule.service.AutoScheduleService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        //TODO start auto schedule
        List<Schedule> schedules = new AutoScheduleService().start(-1);
        for (Schedule schedule : schedules) {
            System.out.println(schedule);
        }
    }
}
