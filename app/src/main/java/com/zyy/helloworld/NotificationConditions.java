package com.zyy.helloworld;

import android.app.Notification;

public class NotificationConditions extends Notification{

    private boolean isSuspend;
    private boolean isShake;
    private int sound;
    private boolean isLight;
    private boolean isOnGoing;
    private boolean isReply;
    private boolean isGroup;

    public NotificationConditions(){

    }

    public boolean getSuspend() {
        return isSuspend;
    }

    public void setSuspend(boolean suspend) {
        isSuspend = suspend;
    }

    public boolean getShake() {
        return isShake;
    }

    public void setShake(boolean shake) {
        isShake = shake;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public boolean getLight() {
        return isLight;
    }

    public void setLight(boolean light) {
        isLight = light;
    }

    public boolean getOnGoing() {
        return isOnGoing;
    }

    public void setOnGoing(boolean onGoing) {
        isOnGoing = onGoing;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }


}
