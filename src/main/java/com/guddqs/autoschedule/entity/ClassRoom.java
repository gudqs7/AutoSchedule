package com.guddqs.autoschedule.entity;

import lombok.Data;

/**
 * @author wq
 * @date 2018/9/5
 */
@Data
public class ClassRoom {
    private String classRoomId;
    private String classRoomName;

    public ClassRoom(String classRoomId, String classRoomName) {
        this.classRoomId = classRoomId;
        this.classRoomName = classRoomName;
    }
}
