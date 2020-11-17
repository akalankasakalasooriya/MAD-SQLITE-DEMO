package com.example.sampleemptyproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    static final String DBNAME = "APPDB";
    static final String TABLENAME = "UserInfo";
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase()
;    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLENAME+" (" +
                UserProfile.Users.COL_1+ " integer PRIMARY KEY AUTOINCREMENT,"+
                UserProfile.Users.COL_2+" text ,"+
                UserProfile.Users.COL_3+" text ,"+
                UserProfile.Users.COL_4+" text "
                +
                ") ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public  boolean addInfo(String un, String dob , String gender)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserProfile.Users.COL_2,un);
        contentValues.put(UserProfile.Users.COL_3,dob);
        contentValues.put(UserProfile.Users.COL_4,gender);
        long result = db.insert(TABLENAME,null,contentValues);

        if(result== -1)
        {
            return false;
        }else{
            return true;
        }

    }

    public boolean updateInfor(int id, String un, String dob , String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UserProfile.Users.COL_2,un);
        contentValues.put(UserProfile.Users.COL_3,dob);
        contentValues.put(UserProfile.Users.COL_4,gender);

        String selection = UserProfile.Users.COL_1 + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};


        long isDone = db.update(TABLENAME,contentValues, selection,selectionArgs);
        if(isDone == -1){
            return  false;
        }else {
            return  true;
        }

    }

    public Cursor readAllInfor(){
        List userlist = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {UserProfile.Users.COL_1,UserProfile.Users.COL_2,UserProfile.Users.COL_3,UserProfile.Users.COL_4};
        String sortorder = UserProfile.Users.COL_1;
        Cursor result = db.query(TABLENAME,
                                projection,null,null,null,null,
                                sortorder
                );



        return result;
    }

    public Cursor readAllInfor(int id){
        List userlist = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {UserProfile.Users.COL_1,UserProfile.Users.COL_2,UserProfile.Users.COL_3,UserProfile.Users.COL_4};
        String sortorder = UserProfile.Users.COL_1;
        String selection =  UserProfile.Users.COL_1+" = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor result = db.query(TABLENAME,
                projection,selection,selectionArgs,null,null,
                sortorder
        );



        return result;
    }

    public void  deleteInfo(int id){
        List userlist = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selection =  UserProfile.Users.COL_1+" = ?";
        String[] selectionArgs = {String.valueOf(id)};

        db.delete(TABLENAME,selection,selectionArgs);

    }


}
