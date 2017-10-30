package team2.calendarapp.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by iqbalkhan on 10/29/17.
 */

public class App extends Application {

    private static Context sContext;

    public static Context getAppContext(){

        return sContext;

    }

    @Override
    public void onCreate(){

        super.onCreate();
        sContext = getApplicationContext();

    }

}
