package com.example.win.trustinnojobagency00;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Win on 12/18/2016.
 */

public class JobSeekDatabaseHelper extends SQLiteOpenHelper {

    public static final String db_name="TrustinnoJobAgency.DB";
    public static final String tb_name="JobSeekers_table";
    public static final String col1="ID";
    public static final String col2="NAME";
    public static final String col3="TITLE";
    public static final String col4="DESCRIPTION";
    public static final String col5="DATE";

    public JobSeekDatabaseHelper(Context context){
        super(context,db_name,null,1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + tb_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,TITLE TEXT,DESCRIPTION TEXT, DATE TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db,int oldVer,int newVer){
        db.execSQL("DROP TABLE IF EXITS "+tb_name);
        onCreate(db);
    }

    public boolean insertData(String name,String title,String description,String date){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2, name);
        cv.put(col3,title);
        cv.put(col4, description);
        cv.put(col5, date);

        long res=db.insert(tb_name,null,cv);
        if(res==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+tb_name,null);
        return res;
    }

    public Cursor getDataByID(String id){
        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+tb_name+" WHERE ID="+id,null);
        return res;
    }

    public Cursor getDataByTitle(String title){
        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+tb_name+" WHERE TITLE="+title,null);
        return res;
    }

    public boolean updateData(String id,String name,String title,String description,String date){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col1,id);
        cv.put(col2,name);
        cv.put(col3,title);
        cv.put(col4,description);
        cv.put(col5,date);

        db.update(tb_name, cv, " ID=? " , new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=getWritableDatabase();

        return db.delete(tb_name, " ID=? ", new String[]{id});
    }
}


