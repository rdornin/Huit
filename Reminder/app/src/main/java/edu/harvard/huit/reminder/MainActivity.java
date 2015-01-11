package edu.harvard.huit.reminder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends Activity {

    private TextView mTextView;
    private EditText mEnter;
    private Button   mButton;
    private Integer counter = 0;
    private Integer timeLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Timer t = new Timer();
        final String[] msgs = new String[]{"Take Your Pills","Register for classes","Turn in homework","Study!!"};
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                mEnter = (EditText) stub.findViewById(R.id.enter);
                mButton = (Button)findViewById(R.id.btn);
                mButton.setOnClickListener(
                        new View.OnClickListener()
                        {
                            public void onClick(View view)
                            {
                                Log.v("EditText", mEnter.getText().toString());

                                timeLimit = Integer.parseInt(mEnter.getText().toString());

                                Context context = getApplicationContext();
                                CharSequence text = "Must be a number less 6";
                                int duration = Toast.LENGTH_SHORT;



                                if(timeLimit > 5){
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                } else {
                                    mButton.setVisibility(View.GONE);
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            try {
                                                while (true) {
                                                    sleep(5000);
                                                    counter++;
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            String text = counter.toString();
                                                            if (counter > msgs.length - 1) {
                                                                counter = 0;
                                                            }
                                                            mTextView.setText(msgs[counter]);
                                                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                            // Vibrate for 500 milliseconds
                                                            v.vibrate(500);
                                                        }
                                                    });

                                                }
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    thread.start();
                                }


                            }
                        });
            }
        });
    }
}
