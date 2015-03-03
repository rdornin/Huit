package edu.harvard.huit.morsecode;

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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

public class MainActivity extends Activity {

    private TextView mTextView;
    private EditText mEnter;
    private Button mButton;
    private Integer counter = 0;
    private Integer timeLimit;

    private void pause(Integer timeLimit){
        try {
            Thread.sleep(timeLimit);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Timer t = new Timer();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
                                             @Override
                                             public void onLayoutInflated(WatchViewStub stub) {
                                                 mTextView = (TextView) stub.findViewById(R.id.text);
                                                 mEnter = (EditText) stub.findViewById(R.id.enter);
                                                 mButton = (Button) findViewById(R.id.btn);
                                                 mButton.setOnClickListener(
                                                         new View.OnClickListener() {
                                                             public void onClick(View view) {
                                                                 Log.v("EditText", mEnter.getText().toString());
                                                                 final Map<String, String> morseCodeMap = new HashMap<String, String>();
                                                                 morseCodeMap.put("a", ". _");
                                                                 morseCodeMap.put("b", "_ . . .");
                                                                 morseCodeMap.put("c", "_ . _ .");
                                                                 morseCodeMap.put("d", "_ . .");
                                                                 morseCodeMap.put("e", ".");
                                                                 morseCodeMap.put("f", ". . _ .");
                                                                 morseCodeMap.put("g", "_ _ .");
                                                                 morseCodeMap.put("h", ". . . .");
                                                                 morseCodeMap.put("i", ". .");
                                                                 morseCodeMap.put("j", ". _ _ _");
                                                                 morseCodeMap.put("k", "_ . _");
                                                                 morseCodeMap.put("l", ". _ . .");
                                                                 morseCodeMap.put("m", "_ _");
                                                                 morseCodeMap.put("n", "_ .");
                                                                 morseCodeMap.put("o", "_ _ _");
                                                                 morseCodeMap.put("p", ". _ _ .");
                                                                 morseCodeMap.put("q", "_ _ . _");
                                                                 morseCodeMap.put("r", ". _ .");
                                                                 morseCodeMap.put("s", ". . .");
                                                                 morseCodeMap.put("t", "_");
                                                                 morseCodeMap.put("u", ". . _");
                                                                 morseCodeMap.put("v", ". . . _");
                                                                 morseCodeMap.put("w", ". _ _");
                                                                 morseCodeMap.put("x", "_ . . _");
                                                                 morseCodeMap.put("y", "_ . _ _");
                                                                 morseCodeMap.put("z", "_ _ . .");
                                                                 morseCodeMap.put("1", ". _ _ _ _");
                                                                 morseCodeMap.put("2", ". . _ _ _");
                                                                 morseCodeMap.put("3", ". . . _ _");
                                                                 morseCodeMap.put("4", ". . . . _");
                                                                 morseCodeMap.put("5", ". . . . .");
                                                                 morseCodeMap.put("6", "_ . . . .");
                                                                 morseCodeMap.put("7", "_ _ . . .");
                                                                 morseCodeMap.put("8", "_ _ _ . .");
                                                                 morseCodeMap.put("9", "_ _ _ _ .");
                                                                 morseCodeMap.put("0", "_ _ _ _ _");
                                                                 morseCodeMap.put(" ", "x");

                                                                 String punctuations = ".,:;+)(^&%$#@!*{}[]";
                                                                 String str = mEnter.getText().toString();
                                                                 //str = str.replaceAll("\\s", ""); //stripping spaces for now
                                                                 String tmp;
                                                                 Context context = getApplicationContext();
                                                                 int duration = Toast.LENGTH_SHORT;

                                                                 Boolean punctuation = false;

                                                                 for (int i = 0; i < str.length(); i++) {
                                                                     tmp = Character.toString(str.charAt(i)).toLowerCase();
                                                                     System.out.println("Tmp Checker:" +tmp);
                                                                     if (punctuations.contains(tmp)) {
                                                                         punctuation = true;
                                                                     }
                                                                 }

                                                                 if (!punctuation) {
                                                                     for (int i = 0; i < str.length(); i++) {
                                                                         System.out.println(str.charAt(i));
                                                                         tmp = Character.toString(str.charAt(i)).toLowerCase();
                                                                         System.out.println("Val from Map: " + morseCodeMap.get(tmp));
                                                                         String code = morseCodeMap.get(tmp);
                                                                         final Integer dot = 200;
                                                                         final Integer dash = 500;
                                                                         final Integer space = 350;
                                                                         for (int j = 0; j < code.length(); j++) {
                                                                             String morseChar = Character.toString(code.charAt(j));
                                                                             System.out.println("Morse split:" + morseChar);
                                                                             if (morseChar.equals("_")) {
                                                                                 pause(space);
                                                                                 Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                                                 v.vibrate(dash);
                                                                                 pause(dash);
                                                                             } else if (morseChar.equals("x")) {
                                                                                 pause(space);
                                                                             } else if (morseChar.equals(".")) {
                                                                                 pause(space);
                                                                                 Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                                                 v.vibrate(dot);
                                                                                 pause(dot);
                                                                             }
                                                                         }
                                                                         pause(space);
                                                                     }
                                                                 } else {
                                                                     CharSequence text = "No punctuation please.";
                                                                     Toast toast = Toast.makeText(context, text, duration);
                                                                     toast.show();
                                                                 }
                                                             }
                                                         }

                                                 );
                                             }
                                         }

        );
    }
}
