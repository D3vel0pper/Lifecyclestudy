package t4ka.com.lifecyclestudy.commons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by taka-dhu on 2015/11/13.
 */
public class SQLProcesser {
    //mType = parameter
    private String mType = "create";
    private Context mContext;
    private String mDbname;
    private String mId;
    private String mName;
    private String mComment;
    private String mSql;

    public void setContext(Context context){
        mContext = context;
    }

    public void setType(String parameter){

    }

    public void setDbname(String dbname){
        mDbname = dbname;
    }

    public void setId(String id){
        mId = id;
    }

    public void setName(String name){
        mName = name;
    }

    public void setComment(String comment){
        mComment = comment;
    }

    public void Process(String TABLE_NAME) {
        SQLiteDatabase db;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(mDbname, null);
        } catch (NullPointerException e) {
            db = null;
            e.printStackTrace();
        }

        if(mType.equals("create")){
        }
        else {
            if (mType.equals("createtable")) {
                mSql = "create table user (id integer primary key autoincrement, "
                        + "name text not null, comment text not null);";
            } else if (mType.equals("deletetable")) {
                mSql = "drop table user";
            } else if (mType.equals("insert")) {
                mSql = "insert into " + TABLE_NAME + "(name, comment)"
                        + "values ('" + mName + "','" + mComment + "');";
            } else if(mType.equals("update")){
                mSql = "update " + TABLE_NAME + " set "
                        + "name='" + mName + "', comment='" + mComment + " where id=" + mId  + ";";
            } else if(mType.equals("deldata")){
                mSql = "delete from " + TABLE_NAME + " where (id=" + mId  + ");";
            }
            try{
                db.execSQL(mSql);
            } catch(NullPointerException e) {
                Log.e("NPE-ERROR",e.toString());
                e.printStackTrace();
                Toast.makeText(mContext,"NPE at create",Toast.LENGTH_SHORT).show();
            }
            catch(SQLiteException e){
                Log.e("SQLite-ERROR",e.toString());
                e.printStackTrace();
                Toast.makeText(mContext,"SQLite error at create",Toast.LENGTH_SHORT).show();
            }
        }
        try{
            db.close();
        } catch(NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(mContext,"NPE at close",Toast.LENGTH_SHORT).show();
        }


    }

}
