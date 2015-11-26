package t4ka.com.lifecyclestudy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import t4ka.com.lifecyclestudy.adapter.*;
import t4ka.com.lifecyclestudy.commons.*;


public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Called When App created(started)
        Toast.makeText(this, "onCreate called",Toast.LENGTH_SHORT ).show();
        setContentView(R.layout.activity_main);

        Button createBtn = (Button)findViewById(R.id.createBtn);
        createBtn.setOnClickListener((OnClickListener)this);


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

    @Override
    public void onDestroy(){
        super.onDestroy();
        //Call when App killed
        Toast.makeText(this,"onDestroy called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.createBtn){
            Toast.makeText(this, "createBtn Clicked",Toast.LENGTH_SHORT).show();
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
