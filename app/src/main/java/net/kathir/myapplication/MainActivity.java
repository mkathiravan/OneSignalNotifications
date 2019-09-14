package net.kathir.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyApplication myApplication;
    TextView nameView;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        nameView = (TextView)findViewById(R.id.textView_title);
        myApplication = MyApplication.getmInstance();

        nameView.setText(preferences.getString("received_name","Welcome to OneSignal"));
    }
}
