package t4ka.com.lifecyclestudy.commons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by taka-dhu on 2015/11/13.
 */
public class SQLProcesser {
    /**
     * mType = parameter
     */
    private String mType = "create";
    private Context mContext;
    private String mDbname = "DataBase";
    private String mId = "0";
    private String mName = "";
    private String mComment = "";
    private String mSql = "";
    private SQLiteDatabase db;

    public void setContext(Context context){
        mContext = context;
    }

    public void setType(String parameter){
        mType = parameter;
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

    public String showDatas(){
        db = mContext.openOrCreateDatabase(mDbname,Context.MODE_PRIVATE,null);
        Cursor c = db.rawQuery("select * from user;",null);
        c.moveToFirst();
        int count = c.getCount();
        String data = new String();
        for(int i = 0;i < count;i++ ){
            for(int j = 0;j < 3;j++) {
                data += c.getString(j);
                if((j+1) < 3){
                    data += ":";
                }
            }
            if((i+1) < count){
                data += "\n";
            }
            c.moveToNext();
        }

        try{
            db.close();
            Toast.makeText(mContext,"Close at showing",Toast.LENGTH_SHORT).show();
        } catch(NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(mContext,"NPE at close",Toast.LENGTH_SHORT).show();
        }
        return data;
    }

    public List<DBDatas> showDatas(List<DBDatas> objects){
        db = mContext.openOrCreateDatabase(mDbname,Context.MODE_PRIVATE,null);
        Cursor c = db.rawQuery("select * from user;",null);
        c.moveToFirst();
        int count = c.getCount();
        for(int i = 0;i < count; i++){
            DBDatas item = new DBDatas();
            item.setId(c.getString(0));
            item.setName(c.getString(1));
            item.setComment(c.getString(2));
            objects.add(item);
            c.moveToNext();
        }

        try{
            db.close();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public void Process(String TABLE_NAME) {
        try {
            db = mContext.openOrCreateDatabase(mDbname,Context.MODE_PRIVATE,null);
        } catch (NullPointerException e) {
            db = null;
            e.printStackTrace();
        }

        if(mType.equals("create")){
            Toast.makeText(mContext, "D.B.Created",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "D.B.opened",Toast.LENGTH_SHORT).show();
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
                        + "name='" + mName + "', comment='" + mComment + "' where id=" + mId  + ";";
            } else if(mType.equals("deldata")){
                mSql = "delete from " + TABLE_NAME + " where (id=" + mId  + ");";
            }
            try{
                db.execSQL(mSql);
                if (mType.equals("createtable")) {
                    Toast.makeText(mContext, "table created",Toast.LENGTH_SHORT).show();
                } else if (mType.equals("deletetable")) {
                    Toast.makeText(mContext, "table deleted",Toast.LENGTH_SHORT).show();
                } else if (mType.equals("insert")) {
                    Toast.makeText(mContext, "data inserted",Toast.LENGTH_SHORT).show();
                } else if(mType.equals("update")){
                    Toast.makeText(mContext, "data updated",Toast.LENGTH_SHORT).show();
                } else if(mType.equals("deldata")){
                    Toast.makeText(mContext, "data deleted",Toast.LENGTH_SHORT).show();
                }
            } catch(NullPointerException e) {
                Log.e("NPE-ERROR",e.toString());
                e.printStackTrace();
                Toast.makeText(mContext,"NPE at exec",Toast.LENGTH_SHORT).show();
            }
            catch(SQLiteException e){
                Log.e("SQLite-ERROR",e.toString());
                e.printStackTrace();
                Toast.makeText(mContext,"SQLite error at exec",Toast.LENGTH_SHORT).show();
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
