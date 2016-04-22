package t4ka.com.lifecyclestudy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import t4ka.com.lifecyclestudy.R;
import t4ka.com.lifecyclestudy.Service.MyService;

/**
 * At first, You Must Check the AndroidManifest
 * Then, check is it contained service which you want to use
 * If there isn't, you must add the tag <service></service>
 */

public class MainActivity extends Activity implements OnClickListener{

    /**
     * myService -> holder for holding MyService
     * boolean bound -> flag of that is bound or not
     */
    MyService myService;
    boolean bound = false;

    /**
     * call when connect to or disconnect from the service. This is passed to bindService()
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {

        /**
         * Called when connect to service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyServiceLocalBinder localBinder = (MyService.MyServiceLocalBinder)service;
            myService = localBinder.getService();
            Toast.makeText(MainActivity.this,"onServiceConnected()",Toast.LENGTH_SHORT).show();
            bound = true;
        }

        /**
         * Called when disconnect from service
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this,"onServiceDisconnect()",Toast.LENGTH_SHORT).show();
            bound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button StartBtn = (Button)findViewById(R.id.StartBtn);
        StartBtn.setOnClickListener(this);
        Button StopBtn = (Button)findViewById(R.id.StopBtn);
        StopBtn.setOnClickListener(this);
        Button BindBtn = (Button)findViewById(R.id.BindBtn);
        BindBtn.setOnClickListener(this);
        Button UnBindBtn = (Button)findViewById(R.id.UnBindBtn);
        UnBindBtn.setOnClickListener(this);

    }

    @Override
    public void onStart(){
        super.onStart();
        //Called After App created or Restarted
        Toast.makeText(this,"onStart called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        //Called After App Started
        Toast.makeText(this,"onResume called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause(){
        super.onPause();
        //Called when App move to BackGround
        Toast.makeText(this,"onPause called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop(){
        super.onStop();
        //Called After Pause called
        Toast.makeText(this,"onStop called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        //Called When Background App Move to Forward
        Toast.makeText(this,"onRestart called",Toast.LENGTH_SHORT).show();
    }

    /**
     * without stopping description, Resource will be leak and throw Exception
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
        stopService(new Intent(MainActivity.this,MyService.class));
        if(bound){
            unbindService(serviceConnection);
            bound = false;
        }
        Toast.makeText(this,"onDestroy called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.StartBtn){
            startService(new Intent(MainActivity.this,MyService.class));
        } else if(v.getId() == R.id.StopBtn){
            stopService(new Intent(MainActivity.this,MyService.class));
        } else if(v.getId() == R.id.BindBtn){
            Intent intent = new Intent(MainActivity.this,MyService.class);
            bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE );
        } else if(v.getId() == R.id.UnBindBtn){
            if(bound){
                unbindService(serviceConnection);
                bound = false;
            }
        }

    }


/*
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
    */
}
