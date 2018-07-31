package com.zyy.helloworld;

import android.app.Notification;
import android.content.Context;
import android.util.Log;

public class NotificationConditions extends Notification{

    private boolean isSuspend;
    private boolean isShake;
    private int sound;
    private boolean isLight;
    private boolean isOnGoing;
    private boolean isReply;
    private boolean isGroup;

    private int style = 0;
    private Context mContext;
    private Builder mNotificationBuilder;


    public NotificationConditions(Context context){
        mContext = context;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
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

    //普通小通知模板
    public Builder nomalNotificationBuilder(){
        mNotificationBuilder = new Notification.Builder(mContext)
                .setContentTitle("普通小通知")
                .setContentText("普通小通知的内容")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(getOnGoing())
                .setTicker("普通小通知");
        Log.d("test", "builder  普通通知  成功 " );
        return mNotificationBuilder;
    }

    //文本通知模板
    public Builder bigTextNotificationBuilder(){
        mNotificationBuilder = new Notification.Builder(mContext)
                .setContentTitle("大文本通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("大文本通知")
                .setOngoing(getOnGoing())
                .setStyle(new Notification.BigTextStyle())
                .setContentText("大文本通知大文本通知大文本通知大文本通知大文本通知大文本通知\n"
                        +"大文本通知大文本通知大文本通知大文本通知大文本通知大文本通知\n"
                        +"大文本通知大文本通知大文本通知大文本通知大文本通知大文本通知");
        return mNotificationBuilder;
    }

    //大图通知模板
    public Builder bigPictureNotificationBuilder(){
        mNotificationBuilder = new Notification.Builder(mContext)
                .setContentTitle("大图通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("大图通知")
                .setOngoing(getOnGoing())
                .setStyle(new Notification.BigPictureStyle())
                .setContentText("大图通知内容");
        return mNotificationBuilder;
    }

    //邮件通知模板
    public Builder emailNotificationBuilder(){
        mNotificationBuilder = new Notification.Builder(mContext)
                .setContentTitle("邮件通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("邮件通知")
                .setOngoing(getOnGoing())
                .setStyle(new Notification.InboxStyle());
        return mNotificationBuilder;
    }

    //下载通知模板
    public Builder progressNotificationBuilder(){
        mNotificationBuilder = new Notification.Builder(mContext)
                .setTicker("下载通知")
                .setContentTitle("下载通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(getOnGoing())
                .setProgress(0, 0, true);
        return mNotificationBuilder;
    }

    //消息通知模板
    public Builder messageNotificationBuilder(){
        mNotificationBuilder = new Notification.Builder(mContext)
                .setTicker("消息通知")
                .setContentTitle("消息通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(getOnGoing())
                .setContentText("消息通知内容");
//                .setStyle(new Notification.MessagingStyle.Message())
        return mNotificationBuilder;
    }

}
