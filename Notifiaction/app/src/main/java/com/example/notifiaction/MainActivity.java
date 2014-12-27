package com.example.notifiaction;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    private Button button, button2, button3;
    private Notification.Builder builder;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        builder = new Notification.Builder(this);
        builder.setTicker("you have new notifiaction!");
        builder.setContentTitle("nOTIFIACTION!");
        builder.setContentText("content notifiaction");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                        1, intent, PendingIntent.FLAG_ONE_SHOT);
                builder.setContentIntent(pendingIntent);
                manager.notify((int) (System.currentTimeMillis()), builder.build());
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder1 = new
                        NotificationCompat.Builder(MainActivity.this);
                builder1.setSmallIcon(R.drawable.ic_launcher).
                        setContentTitle("BIG NOTIFIACTION").
                        setContentText("content");
                NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                builder1.setStyle(style);
                //在使用大通知时通知的总体高度有限值 想显示更多需要重写！如优酷的图片！
                String event[] = {"sdfgsdfgsdf", "sdfgsdfg", "sdfgsdf"};
                style.setBigContentTitle("event ：");
                for (int i = 0; i < event.length; i++) {
                    style.addLine(event[i]);
                }


                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
                builder1.setContentIntent(pendingIntent);
                builder1.setAutoCancel(true);//点击一次通知就消失
                manager.notify(1001, builder1.build());


            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancelAll();//清除所有
//                manager.cancel(id); 清除ID = id 的通知
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
