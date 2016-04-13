package t4ka.com.lifecyclestudy.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

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

    @Override
    public void onCreate() {
        Toast.makeText(this,"Service's on Create",Toast.LENGTH_SHORT).show();
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



}
