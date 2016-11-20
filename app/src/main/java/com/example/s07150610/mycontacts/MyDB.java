package com.example.s07150610.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * Created by Yosing on 2016/10/18.
 */
public class MyDB extends SQLiteOpenHelper{
    private static String DB_NAME="My_DB.db";
    private static int DB_VERSION=2;
    private SQLiteDatabase db;

    public MyDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isTableExits(String tablename) {
        try{
            openConnection();
            String str="select count(*)xcount from"+tablename;
            db.rawQuery(str,null).close();
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }finally {
            closeConnection();
        }
        return true;
    }

    public void closeConnection()
    {
        try {
            if (db!=null && db.isOpen())
            {
                db.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private SQLiteDatabase openConnection() {
        if (!db.isOpen()){
            db=getWritableDatabase();
        }
        return db;
    }

    public boolean creareTable(String createTablesSql)
    {
        try {
            openConnection();
            db.execSQL(createTablesSql);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }

    public boolean save(String tableName, ContentValues values)
    {
        try {
            openConnection();
            db.insert(tableName,null,values);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    public  boolean update(String table,ContentValues values,String whereClause,String[] whereArgs)
    {
        try {
            openConnection();
                    db.update(table,values,whereClause,whereArgs);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    public  boolean delete(String table, String deleteSql,String obj[])
    {
        try {
            openConnection();
            db.delete(table,deleteSql,obj);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();

        }
        return true;
    }
    public Cursor find(String findSql,String obj[]) {
        try {
            openConnection();
            Cursor cursor=db.rawQuery(findSql,obj);
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean isTableExiss(String tablename)
    {
        try {
            openConnection();
            String str="select count(*)xcount from "+tablename;
            db.rawQuery(str,null).close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();

        }
        return true;
    }

}
