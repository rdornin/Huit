package edu.hackathon.wear.wear;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;


import java.util.Calendar;

import edu.hackathon.wear.service.AlarmService;


public class MainActivity extends Activity {

    private TextView mTextView;
    public static AlarmManager am;
    private static PendingIntent pi;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);

                //TextView text = (TextView) findViewById(R.id.this_is_the_id_of_textview);
                mTextView.setText("Take Your Pills App");
                /*context = getActivity();

                alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(context,MainActivity.class);
                alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                // get the alarm interval and display


                alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() +
                                10 * 1000, alarmIntent);*/
                int alarmInterval = AlarmService.getAlarmService().getSecondsInterval();
                //mTextView.setText("Interval is: " + alarmInterval);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                pi = PendingIntent.getActivity(getApplicationContext(),3333,i,
                        PendingIntent.FLAG_CANCEL_CURRENT);

                //getting current time and add 5 seconds in it
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,  alarmInterval);
                //registering our pending intent with alarmmanager
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), pi);
                //am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                       // 1000 * alarmInterval, pi);

            }
        });


    }

    public static  void cancelAlarm(){
        MainActivity.am.cancel(pi);
    }
}
