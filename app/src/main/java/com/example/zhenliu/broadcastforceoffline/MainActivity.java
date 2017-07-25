package com.example.zhenliu.broadcastforceoffline;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setResource(R.layout.activity_main);
        Button forceOfflinBtn = (Button) findViewById(R.id.force_offline);
        forceOfflinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                Intent intent = new Intent("com.example.zhenliu.broadcastforceoffline.FORCE_OFFLINE");
                localBroadcastManager.sendBroadcast(intent);

            }
        });
    }

    @Override
    public void setResource(int id) {
        setContentView(id);
    }
}
