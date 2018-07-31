package com.zyy.helloworld;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

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
    private Random mNotificationId;
    private Button mSendNotifitionButton;
    private Button mClearNotifitionButton;
    private CheckBox mSuspendCheckBox;
    private CheckBox mSoundCheckBox;
    private CheckBox mShakeCheckBox;
    private CheckBox mLightCheckBox;
    private CheckBox mOnGoingCheckBox;
    private CheckBox mReplyCheckBox;
    private CheckBox mGroupCheckBox;
    Intent notificationClickIntent;
    PendingIntent notificationClickPendingIntent;

    //发送通知的附属条件
    private NotificationConditions mConditions ;
    private Notification.Builder mNotificationBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConditions = new NotificationConditions(this);
        mNotiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //获取页面元素
        mSuspendCheckBox = (CheckBox)findViewById(R.id.suspend);
        mShakeCheckBox = (CheckBox)findViewById(R.id.shake);
        mSoundCheckBox = (CheckBox)findViewById(R.id.dling);
        mLightCheckBox = (CheckBox)findViewById(R.id.light);
        mReplyCheckBox = (CheckBox)findViewById(R.id.reply);
        mGroupCheckBox = (CheckBox)findViewById(R.id.group);
        mOnGoingCheckBox = (CheckBox)findViewById(R.id.resident);
        mButtonNum = (Spinner)findViewById(R.id.buttomNumm);
        mMoType = (Spinner)findViewById(R.id.moType);
        mMoudleType = (Spinner)findViewById(R.id.moduleType);
        mSendNotifitionButton = (Button) findViewById(R.id.sendNotifition);
        mClearNotifitionButton = (Button) findViewById(R.id.clearAll);

        //模板类型，view数据绑定
        mMoudleAdpter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getMoudleTypeData());
        mMoudleType.setAdapter(mMoudleAdpter);

        //通知样式  view数据绑定
        mMoTypeAdpter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getMoType());
        mMoType.setAdapter(mMoTypeAdpter);

        //通知按钮数量  view数据绑定
        mButtonAdpter = new ArrayAdapter(this,  android.R.layout.simple_spinner_item, getButtonNum());
        mButtonNum.setAdapter(mButtonAdpter);

        //常驻CheckBox点击
        mOnGoingCheckBox.setChecked(false);
        mOnGoingCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("test","checkLinstemer：  "+b);
                mConditions.setOnGoing(b);
            }
        });
        //为通知模板下拉框设置监听
        mMoudleType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "mMoudleType click position  ---  "+ position);
                switch (position){
                    case 0 :
                        mConditions.setStyle(0);
                        break;
                    case 1 :
                        mConditions.setStyle(1);
                        break;
                    case 2 :
                        mConditions.setStyle(2);
                        break;
                    case 3 :
                        mConditions.setStyle(3);
                        break;
                    case 4 :
                        mConditions.setStyle(4);
                        break;
                    case 5 :
                        mConditions.setStyle(5);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mNotificationBuilder = mConditions.nomalNotificationBuilder();
            }
        });

        //长按删除所有通知
        mClearNotifitionButton.setOnLongClickListener(new Button.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                mNotiManager.cancelAll();
                return true;
            }
        });
        //通知点击跳转
        notificationClickIntent = new Intent(this, NotificationClickActivity.class);
        notificationClickPendingIntent = PendingIntent.getActivity(this,
                0, notificationClickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    //发送通知
    public void sendNotification(View view){
        switch (mConditions.getStyle()){
            case 0 :
                mNotificationBuilder = mConditions.nomalNotificationBuilder();
                break;
            case 1 :
                mNotificationBuilder = mConditions.bigTextNotificationBuilder();
                break;
            case 2 :
                mNotificationBuilder = mConditions.bigPictureNotificationBuilder();
                break;
            case 3 :
                mNotificationBuilder = mConditions.emailNotificationBuilder();
                break;
            case 4 :
                mNotificationBuilder = mConditions.progressNotificationBuilder();
                break;
            case 5 :
                mNotificationBuilder = mConditions.messageNotificationBuilder();
                break;
        }

        mConditions.getStyle();
        mNotification = mNotificationBuilder.build();
        mNotificationId = new Random(100);
        mNotiManager.notify(mNotificationId.nextInt(), mNotification);
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

    public List<String> getButtonNum(){
        mButtonNumData = new ArrayList<String>();
        mButtonNumData.add("0 个");
        mButtonNumData.add("1 个");
        mButtonNumData.add("2 个");
        mButtonNumData.add("3 个");
        return mButtonNumData;
    }

    public void clearNotification(View view) {
    }
}
