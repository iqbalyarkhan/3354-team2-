package team2.calendarapp.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by iqbalkhan on 10/29/17.
 */

public class Utils {

    public static int getScreenWidth(Context context){

        return getPointSize(context).x;

    }

    public static int getScreenHeight(Context context){

        return getPointSize(context).y;

    }

    private static Point getPointSize(Context context){

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;

    }

}
