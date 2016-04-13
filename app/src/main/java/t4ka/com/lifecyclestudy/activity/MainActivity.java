package t4ka.com.lifecyclestudy.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import t4ka.com.lifecyclestudy.adapter.*;
import t4ka.com.lifecyclestudy.commons.*;


public class MainActivity extends Activity implements OnClickListener{

    private Context context = this;
    private String TABLE_NAME = "user";
    private String DB_NAME = "db01_01";
    private int DB_MODE = Context.MODE_PRIVATE;
    private Button createBtn,deletetableBtn,createtableBtn,insertBtn,updateBtn,deldataBtn,showBtn;
    private EditText idtext,nametext,commenttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide keyboard at first
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);
        //Called When App created(started)
        Toast.makeText(this, "this is StudyService branch",Toast.LENGTH_SHORT ).show();
        setContentView(R.layout.activity_main);

        createBtn = (Button)findViewById(R.id.createBtn);
        deletetableBtn = (Button)findViewById(R.id.deletetableBtn);
        createtableBtn = (Button)findViewById(R.id.createtableBtn);
        insertBtn = (Button)findViewById(R.id.insertBtn);
        updateBtn = (Button)findViewById(R.id.updateBtn);
        deldataBtn = (Button)findViewById(R.id.deldataBtn);
        showBtn = (Button)findViewById(R.id.showBtn);
        idtext = (EditText)findViewById(R.id.idText);
        nametext = (EditText)findViewById(R.id.nameText);
        commenttext = (EditText)findViewById(R.id.commentText);


        //Create Btn
        createBtn.setOnClickListener(this);
        //CreateTable Btn
        createtableBtn.setOnClickListener(this);
        //Delete Btn
        deletetableBtn.setOnClickListener(this);
        //insert
        insertBtn.setOnClickListener(this);
        //updateBtn
        updateBtn.setOnClickListener(this);
        //deldataBtn
        deldataBtn.setOnClickListener(this);
          //showBtn
        showBtn.setOnClickListener(this);

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
        SQLProcesser mPsql = new SQLProcesser();
        mPsql.setDbname(DB_NAME);
        mPsql.setContext(context);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.parentll);

        if(v.getId() == R.id.showBtn){
            AdjustDatas(mPsql);
        } else if(v.getId() == R.id.createBtn){
            mPsql.setType("create");
        } else if(v.getId() == R.id.deletetableBtn){
            mPsql.setType("deletetable");
        } else if(v.getId() == R.id.createtableBtn){
            mPsql.setType("createtable");
        } else if(v.getId() == R.id.insertBtn){
            mPsql.setType("insert");
            mPsql.setName(nametext.getText().toString());
            mPsql.setComment(commenttext.getText().toString());
        } else if(v.getId() == R.id.updateBtn){
            mPsql.setType("update");
            mPsql.setName(nametext.getText().toString());
            mPsql.setName(commenttext.getText().toString());
            mPsql.setId(idtext.getText().toString());
        } else if(v.getId() == R.id.deldataBtn){
            mPsql.setType("deldata");
            mPsql.setId(idtext.getText().toString());
        }
        mPsql.Process(TABLE_NAME);
        linearLayout.invalidate();
    }

    private void AdjustDatas(SQLProcesser prcssr){
        /**
         * Adjusting test data for custom listView
         */
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.dbdialog,(ListView)findViewById(R.id.List));
        //make a new list of DBDatas
        List<DBDatas> objects = new ArrayList<DBDatas>();
        //make data u need for
        objects = prcssr.showDatas(objects);

        //Give an adapter for setting Views
        DataAdapter dataAdapter = new DataAdapter(context,0,objects);
        //set container ListView
        ListView listView = (ListView)view.findViewById(R.id.List);
        //set adapter which contains data u want.
        listView.setAdapter(dataAdapter);

        //make Dialog
        new AlertDialog.Builder(MainActivity.this).setTitle("Data List")
                .setView(view).setPositiveButton("Close",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                    }
                }).show();

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
