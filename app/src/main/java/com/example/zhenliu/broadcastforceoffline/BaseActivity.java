package com.example.zhenliu.broadcastforceoffline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by zhenliu on 2017/7/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    LocalBroadcastManager localBroadcastManager;
    private ForceOfflineReceiver forceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityColector.addActivity(this);
        localBroadcastManager = LocalBroadcastManager.getInstance(BaseActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("BaseActivity", "onResume: ");
        IntentFilter intentFilter = new IntentFilter("com.example.zhenliu.broadcastforceoffline.FORCE_OFFLINE");
        forceOfflineReceiver = new ForceOfflineReceiver();
        localBroadcastManager.registerReceiver(forceOfflineReceiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("BaseActivity", "onPause: ");
        if (forceOfflineReceiver != null) {
            localBroadcastManager.unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityColector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            Log.d("BaseActivity", "onReceive: ");
            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
            builder.setTitle("Warning");
            builder.setMessage("You are force to be offline ,please try to login again");
            builder.setCancelable(false);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityColector.finishAll();
                    Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            });

            builder.show();
        }
    }

    public abstract void setResource(int id);
}

