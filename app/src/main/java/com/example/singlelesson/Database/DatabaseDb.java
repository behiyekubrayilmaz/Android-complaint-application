package com.example.singlelesson.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DatabaseDb extends SQLiteOpenHelper {
    private Context context;
    private static final int database_VERSION = 1;
    private static final String database_NAME = "project";
    private static final String table_BRAND = "markalar";
    private static final String brand_ID = "id";
    private static final String brand_MARKA = "marka";
    private static final String brand_YORUM = "yorum";
    private static final String[] COLUMNS = {brand_ID, brand_MARKA, brand_YORUM};
    private static final String CREATE_BRAND_TABLE = "CREATE TABLE "
            + table_BRAND + "("
            + brand_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + brand_MARKA + " TEXT,"
            + brand_YORUM + " TEXT )";

    public DatabaseDb(@Nullable Context context) {
        super(context, database_NAME, null, database_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BRAND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + table_BRAND);   /*güncellemede var olan tablonun kalması için yeniden silmemek için*/
        onCreate(sqLiteDatabase);
    }

    public void addBrand(ModelDB modelDb)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues degerler =new ContentValues();
        degerler.put(brand_MARKA, modelDb.getMarka());
        degerler.put(brand_YORUM, modelDb.getYorum());
        long result = sqLiteDatabase.insert(table_BRAND,null,degerler);
        if (result == -1 ){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    public List<ModelDB> MarkaGetir(){
        List<ModelDB> markalar = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String selectQuery=" SELECT * FROM "+table_BRAND;
        Cursor cursor= sqLiteDatabase.rawQuery(selectQuery,null);
        ModelDB marka=null;
        if (cursor.moveToFirst()){
            do {
                marka=new ModelDB();
                marka.setId(Integer.parseInt(cursor.getString(0)));
                marka.setMarka(cursor.getString(1));
                marka.setYorum(cursor.getString(2));
                markalar.add(marka);
            }while (cursor.moveToNext());
        }
        return markalar;
    }

    public ModelDB markaOku(int id) {
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(table_BRAND,COLUMNS," id = ? ",new String[] {String.valueOf(id)},null,null,null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        ModelDB marka=new ModelDB();
        marka.setId(Integer.parseInt(cursor.getString(0)));
        marka.setMarka(cursor.getString(1));
        marka.setYorum(cursor.getString(2));
        return marka;
    }
    public void delete(ModelDB modelDB){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(table_BRAND,brand_ID + " = ? ",new String[] {String.valueOf(modelDB.getId())});
        if (result == -1 ){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Delete successfully!", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();
    }
    public int update(ModelDB modelDB){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put("marka",modelDB.getMarka());
        degerler.put("yorum",modelDB.getYorum());
        int result= sqLiteDatabase.update(table_BRAND,degerler,brand_ID + " = ? ",new String[] {String.valueOf(modelDB.getId())});
        if (result == -1 ){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Update successfully!", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();
        return result;
    }
}