package t4ka.com.lifecyclestudy.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.ConditionVariable;
import android.os.IBinder;
import android.widget.Toast;

import t4ka.com.lifecyclestudy.R;
import t4ka.com.lifecyclestudy.activity.SecondActivity;

/**
 * Created by taka-dhu on 2016/04/13.
 */
public class MyService extends Service {

    static final String TAG = "LocalService";

    /**
     * Binder to access this Service
     */
    public class MyServiceLocalBinder extends Binder {
        //getService
        public MyService getService() {
            return MyService.this;
        }
    }
    //Create Binder
    public final IBinder iBinder = new MyServiceLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this,"MyService's onBind:" + intent,Toast.LENGTH_SHORT).show();
        return iBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this,"MyService's onRebind:" + intent,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"MyService's onUnbind:" + intent,Toast.LENGTH_SHORT).show();
        //if this return value = true when override -> Next time will call onRebind()
        return true;
    }

    private NotificationManager notificationManager;
    private ConditionVariable condition;

    @Override
    public void onCreate() {
        Toast.makeText(this,"Service's on Create",Toast.LENGTH_SHORT).show();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //make another thread not to stop the process
        Thread thread = new Thread(null,task,"NotifyingService");
        condition = new ConditionVariable(false);
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MyService's onStartCommand",Toast.LENGTH_SHORT).show();
        //return when need to return Service Starting or Stopping Expressly
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"MyService's onDestroy",Toast.LENGTH_SHORT).show();
    }

    private int WAIT_TIME = 4000;
    private Runnable task = new Runnable() {
        public void run() {
            for(int i = 0;i < 4;++i) {
                showNotification(R.mipmap.ic_launcher,"Showing Circle");
                if(condition.block(WAIT_TIME)){
                    break;
                }
                showNotification(R.mipmap.ic_launcher,"Showing Triangle");
                if(condition.block(WAIT_TIME)){
                    break;
                }
                showNotification(R.mipmap.ic_launcher,"Showing Note");
                if(condition.block(WAIT_TIME)){
                    break;
                }
            }
            //End Animation & Service
            MyService.this.stopSelf();
        }
    };

    //get Layout file's ID to get unique ID
    private static int NOTIFICATION_ID = R.layout.activity_second;

    private void showNotification(int drawableId, CharSequence text) {
        //decide notification content
        Notification notification = new Notification(drawableId,null,System.currentTimeMillis());

        //PendingIntent is Intent triggered in settle timing
        //if Notification tapped, then, StartActivity
        PendingIntent contentIntent = PendingIntent.getActivity(
                this,0,new Intent(this, SecondActivity.class),0 );

        //Setting Notification
        notification.setLatestEventInfo(this,"MyService",text,contentIntent);

        //update info of notification -> circle -> triangle -> note
        notificationManager.notify(NOTIFICATION_ID,notification);
    }

}
