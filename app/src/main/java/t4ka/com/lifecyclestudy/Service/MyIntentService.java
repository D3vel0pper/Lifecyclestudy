package t4ka.com.lifecyclestudy.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by taka-dhu on 2016/04/16.
 *
 * !! ATTENTION !!
 * If you want to show progress bar, or process responsibly at each time of background,
 * you MUST use AsyncTask instead of IntentService
 *
 * IntentService lasts process even if Activity is not foreground.
 */
public class MyIntentService extends IntentService {
    public MyIntentService(String name){
        super(name);
    }

    /**
     * when call this from Activity, this constracter will be called
     */
    public MyIntentService(){
        super("MyIntentService");
    }

    /**
     * Put here and run method which you want to run in async timing
     */
    @Override
    protected void onHandleIntent(Intent intent){
//        Toast.makeText(this,"onHandleIntent",Toast.LENGTH_SHORT).show();
//        Toast.makeText(this,"Intent msg:" + intent.getStringExtra("IntentServiceCommand"),Toast.LENGTH_SHORT).show();
        Log.d("IntentService","onHandleIntent");
        Log.d("IntentService","intent msg:" + intent.getStringExtra("IntentServiceCommand"));
    }

}
