package net.kathir.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MyApplication extends Application {


    public static MyApplication mInstance;

    SharedPreferences.Editor shared_edit;

    public MyApplication() {

        mInstance = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        shared_edit = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();


        //Initialize OneSignalSDK
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new NotificationHandler())
                .init();
    }


    class NotificationHandler implements OneSignal.NotificationOpenedHandler
    {

        @Override
        public void notificationOpened(OSNotificationOpenResult result) {

            JSONObject data = result.notification.payload.additionalData;

            if(data != null && data.has("name"))
            {

                shared_edit.putString("received_name",data.optString("name"));
                shared_edit.apply();
            }
        }
    }


    public static synchronized MyApplication getmInstance()
    {
        return mInstance;
    }
}
