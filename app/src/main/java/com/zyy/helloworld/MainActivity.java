package com.zyy.helloworld;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private Spinner mMoudleType;
    private Spinner mMoType;
    private Spinner mButtonNum;
    private List<String> mMoudleTypeData;
    private List<String> mMoTypeData;
    private List<String> mButtonNumData;
    private ArrayAdapter mMoudleAdpter;
    private ArrayAdapter mMoTypeAdpter;
    private ArrayAdapter mButtonAdpter;
    private NotificationManager mNotiManager;
    private Notification mNotification;
    private int mNotificationId;
    private Button mSendNotifitionButton;
    private Button mClearNotifitionButton;

    private CheckBox mSuspendCheckBox;
    private CheckBox mSoundCheckBox;
    private CheckBox mShakeCheckBox;
    private CheckBox mLightCheckBox;
    private CheckBox mOnGoingCheckBox;
    private CheckBox mReplyCheckBox;
    private CheckBox mGroupCheckBox;

    //发送通知附属条件
    private NotificationConditions mConditions = new NotificationConditions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //模板类型
        mMoudleType = findViewById(R.id.moduleType);
        mMoudleAdpter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getMoudleTypeData());
        mMoudleType.setAdapter(mMoudleAdpter);

        //通知样式
        mMoType = findViewById(R.id.moType);
        mMoTypeAdpter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getMoType());
        mMoType.setAdapter(mMoTypeAdpter);

        //通知按钮
        mButtonNum = findViewById(R.id.buttomNumm);
        mButtonAdpter = new ArrayAdapter(this,  android.R.layout.simple_spinner_item, getmButtonNum());
        mButtonNum.setAdapter(mButtonAdpter);

        //发送通知
        mSendNotifitionButton = findViewById(R.id.sendNotifition);
        mSendNotifitionButton.setOnClickListener(this);

        //获取CheckBox的选中情况
        mSuspendCheckBox = findViewById(R.id.suspend);
        mShakeCheckBox = findViewById(R.id.shake);
        mSoundCheckBox = findViewById(R.id.dling);
        mLightCheckBox = findViewById(R.id.light);
        mReplyCheckBox = findViewById(R.id.reply);
        mGroupCheckBox = findViewById(R.id.group);
        mOnGoingCheckBox = findViewById(R.id.resident);

        mSoundCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mConditions.setSound(Notification.DEFAULT_SOUND);
                }
                mConditions.setSound(0);
            }
        });
        mSoundCheckBox.setChecked(true);



        mOnGoingCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("test","checkLinstemer：  "+b);
                mConditions.setOnGoing(b);
            }
        });
        mOnGoingCheckBox.setChecked(true);

        mNotiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendNotifition :
                Log.d("test", "ongoing--"+mConditions.getOnGoing());
                sendMoTypeNotifition(mConditions);
                break;
        }
    }


    //发送通知
    public void sendMoTypeNotifition(NotificationConditions conditions){
        mNotification = new Notification.Builder(this)
                .setContentTitle("通知标题")
                .setContentText("普通小通知的通知内容")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("普通小通知")
                .setOngoing(conditions.getOnGoing())
                //.setSound(conditions.getSound())
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .build();
        mNotiManager.notify(mNotificationId, mNotification);
    }


    public List<String> getMoudleTypeData(){
        mMoudleTypeData = new ArrayList<String>();
        mMoudleTypeData.add("普通模板");
        mMoudleTypeData.add("文本模板");
        mMoudleTypeData.add("大图模板");
        mMoudleTypeData.add("邮件模板");
        mMoudleTypeData.add("下载模板");
        mMoudleTypeData.add("消息模板");
        return mMoudleTypeData;
    }

    public List<String> getMoType(){
        mMoTypeData = new ArrayList<String>();
        mMoTypeData.add("自定义小通知");
        mMoTypeData.add("自定义大通知");
        mMoTypeData.add("自定义浮动通知");
        return mMoTypeData;
    }

    public List<String> getmButtonNum(){
        mButtonNumData = new ArrayList<String>();
        mButtonNumData.add("0 个");
        mButtonNumData.add("1 个");
        mButtonNumData.add("2 个");
        mButtonNumData.add("3 个");
        return mButtonNumData;
    }

}
