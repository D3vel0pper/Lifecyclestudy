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


public class MainActivity extends Activity {

    private Context context = this;
    private String TABLE_NAME = "user";
    private String DB_NAME = "db01_01";
    private int DB_MODE = Context.MODE_PRIVATE;
    private Button createBtn,deletetableBtn,createtableBtn,insertBtn,updateBtn,deldataBtn,showBtn;
    private EditText idtext,nametext,commenttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Called When App created(started)
        Toast.makeText(this, "onCreate called",Toast.LENGTH_SHORT ).show();
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
        //final ViewGroup viewGroup = (ViewGroup)findViewById(R.id.layout1);


        //Create Btn
        createBtn.setOnClickListener((android.view.View.OnClickListener)this);
       /*
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating D.B.
                SQLiteDatabase db;
                //this method could be got java.io.FileNotFoundException.
                //So u have to write try-catch Exception
                try {
                    db = openOrCreateDatabase("db01_01", DB_MODE, null);
                } catch (NullPointerException e){
                    db = null;
                    e.printStackTrace();
                }
                Toast.makeText(context, "D.B.Created",Toast.LENGTH_SHORT).show();
            }
        });
        */

        //CreateTable Btn
        createtableBtn.setOnClickListener((View.OnClickListener)this);
        /*
        createtableBtn.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                SQLiteDatabase db;
                try{
                    db = openOrCreateDatabase("db01_01",DB_MODE,null);
                } catch(NullPointerException e){
                    db = null;
                    e.printStackTrace();

                }
                //Write SQL description
                String sql = "create table user (id integer primary key autoincrement, "
                        + "name text not null, comment text not null);";
                //try to process SQL
                try{
                    db.execSQL(sql);
                } catch(NullPointerException e) {
                    Log.e("NPE-ERROR",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at create",Toast.LENGTH_SHORT).show();
                }
                catch(SQLiteException e){
                    Log.e("SQLite-ERROR",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"SQLite error at create",Toast.LENGTH_SHORT).show();
                }
                try{
                    db.close();
                } catch(NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at close",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(context,"writing success",Toast.LENGTH_SHORT).show();
            }

        });
*/

        //Delete Btn
        deletetableBtn.setOnClickListener((View.OnClickListener)this);
/*
        deletetableBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db;
                try{
                    db = openOrCreateDatabase("db01_01",Context.MODE_PRIVATE,null);
                } catch(NullPointerException e){
                    db = null;
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at open",Toast.LENGTH_SHORT).show();
                }
                //Write SQL description
                String sql = "drop table user";
                //try to process SQL
                try{
                    db.execSQL(sql);
                } catch(NullPointerException e) {
                    Log.e("NPE-ERROR",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at delete",Toast.LENGTH_SHORT).show();
                }
                catch(SQLiteException e){
                    Log.e("SQLite-ERROR",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"SQLite error at delete",Toast.LENGTH_SHORT).show();
                }
                try{
                    db.close();
                } catch(NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at close",Toast.LENGTH_SHORT).show();
                }

            }

        });
*/

        //insert
        //memo -> these String has to be accessed from onClick, But it needs to be final
        final String id = idtext.getText().toString();
        final String name = nametext.getText().toString();
        final String comment = commenttext.getText().toString();

        insertBtn.setOnClickListener((View.OnClickListener)this);

/*
        //insertBtn
        insertBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db;
                String sql = "insert into " + TABLE_NAME + "(name, comment)"
                        + "values ('" + name + "','" + comment + "');";

                try{
                    db = openOrCreateDatabase(DB_NAME,DB_MODE,null);
                } catch(NullPointerException e){
                    db = null;
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context, "NPE at open",Toast.LENGTH_SHORT);
                }

                try{
                    db.execSQL(sql);
                } catch(NullPointerException e){
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context, "NPE at exec",Toast.LENGTH_SHORT);
                }catch(SQLiteException e){
                    Log.e("SQLite-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"SQLite error",Toast.LENGTH_SHORT);
                }

                try{
                    db.close();
                } catch(NullPointerException e){
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at close",Toast.LENGTH_SHORT);
                }

            }
        });
*/

        //updateBtn
        updateBtn.setOnClickListener((View.OnClickListener)this);
/*        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db;
                String sql = "update " + TABLE_NAME + " set "
                        + "name='" + name + "', comment='" + comment + " where id=" + id  + ";";
                try{
                    db = openOrCreateDatabase(DB_NAME,DB_MODE,null);
                } catch(NullPointerException e){
                    db = null;
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at open",Toast.LENGTH_SHORT);
                }

                try{
                    db.execSQL(sql);
                } catch(NullPointerException e){
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at exec",Toast.LENGTH_SHORT);
                } catch(SQLiteException e){
                    Log.e("SQLite-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"SQLite error",Toast.LENGTH_SHORT);
                }
                try{
                    db.close();
                } catch(NullPointerException e){
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at close",Toast.LENGTH_SHORT);
                }

            }
        });
*/
        //deldataBtn
        deldataBtn.setOnClickListener((View.OnClickListener)this);
/*
        deldataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db;
                String sql = "delete from " + TABLE_NAME + " where (id=" + id  + ");";
                try{
                    db = openOrCreateDatabase(DB_NAME,DB_MODE,null);
                } catch(NullPointerException e){
                    db = null;
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at open",Toast.LENGTH_SHORT);
                }

                try{
                    db.execSQL(sql);
                } catch(NullPointerException e){
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at exec",Toast.LENGTH_SHORT);
                } catch(SQLiteException e){
                    Log.e("SQLite-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"SQLite error",Toast.LENGTH_SHORT);
                }
                try{
                    db.close();
                } catch(NullPointerException e){
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at close",Toast.LENGTH_SHORT);
                }

            }
        });

        //-------------------------------------------------------------------------------------

        //making ListView
        final ListView lv = (ListView)findViewById(R.id.List);
        ListViewAdapter lvAdapter = new ListViewAdapter(context);
        ArrayList<DBDatas> list = new ArrayList<>();

*/

        //!!!!!!!Under Here, U Must Fix Descriptions!!!!!!
        /**
         * The Error is occurred in AdapterView
         * From Message, "addView() is not supported in AdapterView"
         * U MUST UNDERSTAND The work of Adapter.
         * */

          //showBtn
        showBtn.setOnClickListener((View.OnClickListener)this);
/*        showBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //LayoutInflater inflater = LayoutInflater.from(context);
                //View view = inflater.inflate(R.layout.dbdialog,viewGroup,true);
                //final TextView text = (TextView)view.findViewById(R.id.contents);


                //About SQLite
                SQLiteDatabase db;
                try{
                    db = openOrCreateDatabase(DB_NAME,DB_MODE,null);
                } catch(NullPointerException e){
                    db = null;
                    Log.e("NPE",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at open",Toast.LENGTH_SHORT);
                }
                String sql = "select * from " + TABLE_NAME + ";";

                try{
                    Cursor c = db.rawQuery(sql,null);
                    boolean isEof = c.moveToFirst();
                    while(isEof){
                        TextView tv = new TextView(context);
                        tv.setText(String.format("id=[%d], name=[%s], comment=[%s]",c.getInt(0),c.getString(1),c.getInt(2)));
                        isEof = c.moveToNext();

                    }
                } catch(NullPointerException e){

                    Log.e("NPE",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at Getting Data",Toast.LENGTH_SHORT);
                }


                new AlertDialog.Builder(context).setTitle("DialogTest").setView(lv)
                        .setPositiveButton("Close",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if u won't do anything, u have to keep here blank
                            }
                        }).show();

                try{
                    db.close();
                } catch(NullPointerException e){
                    Log.e("NPE-error",e.toString());
                    e.printStackTrace();
                    Toast.makeText(context,"NPE at close",Toast.LENGTH_SHORT);
                }

            }
        });

        lvAdapter.setDataList(list);
        lv.setAdapter(lvAdapter);

        idtext.setText("");
        nametext.setText("");
        commenttext.setText("");
*/
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

    public void onClick(View v){
        SQLProcesser mPsql = new SQLProcesser();

        if(v.getId() == R.id.createBtn){
            //mPsql.Process(DB_NAME,"show",context);
        } else if(v == createBtn){
            mPsql.setType("create");
            //mPsql.Process(DB_NAME,"create",context);
        } else if(v == deletetableBtn){
            mPsql.setType("deletetable");
            //mPsql.Process(DB_NAME,"deletetable",context);
        } else if(v == createtableBtn){
            mPsql.setType("createtable");
            //mPsql.Process(DB_NAME,"createtable",context);
        } else if(v == insertBtn){
            mPsql.setType("insert");
            mPsql.setName(nametext.getText().toString());
            //mPsql.Process(DB_NAME,"insert",context);
        } else if(v == updateBtn){
            mPsql.setName(nametext.getText().toString());
            mPsql.setComment(commenttext.getText().toString());
            mPsql.setId(idtext.getText().toString());
            //mPsql.Process(DB_NAME,"update",context);
        } else if(v == deldataBtn){
            mPsql.setId(idtext.getText().toString());
            //mPsql.Process(DB_NAME,"deldata",context);
        }
        mPsql.setDbname(DB_NAME);
        mPsql.setContext(context);
        mPsql.Process(TABLE_NAME);
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
