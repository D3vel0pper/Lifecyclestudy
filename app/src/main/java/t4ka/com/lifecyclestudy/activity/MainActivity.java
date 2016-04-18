package t4ka.com.lifecyclestudy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import t4ka.com.lifecyclestudy.R;
import t4ka.com.lifecyclestudy.Service.MyIntentService;
import t4ka.com.lifecyclestudy.adapter.*;
import t4ka.com.lifecyclestudy.commons.*;


public class MainActivity extends Activity implements OnClickListener{

    private TextView counter;
    private ImageView imageView;
    private Integer c=0;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button countBtn,startBtn;

        //prepare Image
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.american_flag);
        //show image before convert
        imageView = (ImageView)findViewById(R.id.flagImage);
        imageView.setImageBitmap(bitmap);

        startBtn = (Button)findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
        countBtn = (Button)findViewById(R.id.countBtn);
        countBtn.setOnClickListener(this);

        counter = (TextView)findViewById(R.id.counter);
        counter.setText(c.toString());

    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onRestart(){
        super.onRestart();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onClick(View v){

        if(v.getId() == R.id.startBtn){
            HttpResponsTask task = new HttpResponsTask(this);
            task.execute();
        } else if(v.getId() == R.id.countBtn){
            c++;
            ((Button)v).setText(c.toString());

        }

    }

}
