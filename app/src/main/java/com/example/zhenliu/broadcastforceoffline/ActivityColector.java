package com.example.zhenliu.broadcastforceoffline;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenliu on 2017/7/25.
 */

public class ActivityColector {
    public static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
