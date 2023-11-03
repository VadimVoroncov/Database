package com.example.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String MY_TABLE = "MY_TABLE";
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String COLUMN_SURNAME = "COLUMN_SURNAME";
    public static final String COLUMN_AGE = "COLUMN_AGE";

    public DBHelper(@Nullable Context context) {
        super(context, "example.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MY_TABLE
                + " (" + COLUMN_NAME + " TEXT, " + COLUMN_SURNAME + " TEXT, " + COLUMN_AGE + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MY_TABLE, null, null);
        db.close();
    }

    public void deleteOne(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        // удаляем рядок в котором колонка с именем совпадает с значениеm name
        db.delete(MY_TABLE, COLUMN_NAME + " = ?", new  String[]{name});
        db.close();
    }

    public void updateOne(String oldName, String newName, String newSurname, String newAge){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, newName);
        cv.put(COLUMN_SURNAME, newSurname);
        cv.put(COLUMN_AGE, newAge);

        // обновляем рядок в котором колонка с именем совпадает с значениеm name
        db.update(MY_TABLE, cv, COLUMN_NAME + " = ?", new  String[]{oldName});
        db.close();
    }

    public void addOne(Data data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, data.name);
        cv.put(COLUMN_SURNAME, data.surame);
        cv.put(COLUMN_AGE, data.age);

        db.insert(MY_TABLE, null, cv);

        db.close();
    }

    public LinkedList<Data> GetAll(){
        LinkedList<Data> list = new LinkedList<>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(MY_TABLE, null, null, null,
                                           null, null, null);
        // проверяем есть ли в таблице хоть каие-то данные
        if(cursor.moveToFirst()){
            // цикл выполняется если курсору есть куда двигаться по данным
            do{
                int id_n = cursor.getColumnIndex(COLUMN_NAME);
                int id_s = cursor.getColumnIndex(COLUMN_SURNAME);
                int id_y = cursor.getColumnIndex(COLUMN_AGE);

                Data data = new Data(cursor.getString(id_n), cursor.getString(id_s), cursor.getInt(id_y));
                list.add(data);
            }while(cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public LinkedList<Data> search(String encuiry){
        LinkedList<Data> list = new LinkedList<>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(encuiry,null);
        // проверяем есть ли в таблице хоть каие-то данные
        if(cursor.moveToFirst()){
            // цикл выполняется если курсору есть куда двигаться по данным
            do{
                int id_n = cursor.getColumnIndex(COLUMN_NAME);
                int id_s = cursor.getColumnIndex(COLUMN_SURNAME);
                int id_y = cursor.getColumnIndex(COLUMN_AGE);

                Data data = new Data(cursor.getString(id_n), cursor.getString(id_s), cursor.getInt(id_y));
                list.add(data);
            }while(cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public long Insert1000(){
        long t1 = System.currentTimeMillis();

        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        try {
            for(int i = 0; i != 1000; i++){
                ContentValues cv = new ContentValues();

                cv.put(COLUMN_NAME, "Vasiliy");
                cv.put(COLUMN_SURNAME, "Pupkin");
                cv.put(COLUMN_AGE, 2008);

                db.insert(MY_TABLE, null, cv);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
        db.close();

        long t2 = System.currentTimeMillis();
        return t2 - t1;
    }
}
