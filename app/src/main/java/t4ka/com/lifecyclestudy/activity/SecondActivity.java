package t4ka.com.lifecyclestudy.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import t4ka.com.lifecyclestudy.R;
import t4ka.com.lifecyclestudy.Service.MyService;

public class SecondActivity extends ActionBarActivity implements View.OnClickListener{
    //from reference
    MyService myService;
    boolean bound = false;

    //Interface with Service
    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyServiceLocalBinder localBinder = (MyService.MyServiceLocalBinder)service;
            myService = localBinder.getService();
            Toast.makeText(SecondActivity.this,"onServiceConnected()",Toast.LENGTH_SHORT).show();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(SecondActivity.this,"onServiceDisconnect()",Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {
        if(v.getId() == R.id.StartBtn){
            startService(new Intent(SecondActivity.this,MyService.class));
        } else if(v.getId() == R.id.StopBtn){
            stopService(new Intent(SecondActivity.this,MyService.class));
        } else if(v.getId() == R.id.BindBtn){
            Intent intent = new Intent(SecondActivity.this,MyService.class);
            bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE );
        } else if(v.getId() == R.id.UnBindBtn){
            if(bound){
                unbindService(serviceConnection);
                bound = false;
            }
        }

    }

    @Override
    public void onDestroy(){
        stopService(new Intent(SecondActivity.this,MyService.class));
        if(bound){
            unbindService(serviceConnection);
            bound = false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
