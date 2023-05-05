package com.example.taxi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqliteDB_09 extends SQLiteOpenHelper {

    public static final String tableName = "Taxi_01";
    public static final String Id = "Id";
    public static final String carId = "carId";
    public static final String distance = "distance";
    public static final String unitPrice = "unitPrice";
    public static final String promotion = "promotion";


    public SqliteDB_09(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "Create table if not exists "+ tableName + "("
                +Id + " Integer primary key autoincrement, "
                +carId + " Text, "
                +distance + " Float, "
                +unitPrice + " Float, "
                +promotion + " Integer)";
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists "+ tableName);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Taxi_01 item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(carId, item.getCarId());
        values.put(distance, item.getDistance());
        values.put(unitPrice, item.getUnitPrice());
        values.put(promotion, item.getPromotion());
        db.insert(tableName, null, values);
        db.close();
    }

    public void updateContact(int id, Taxi_01 item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, item.getId());
        values.put(carId, item.getCarId());
        values.put(distance, item.getDistance());
        values.put(unitPrice, item.getUnitPrice());
        values.put(promotion, item.getPromotion());
        db.update(tableName, values, Id+ "=?", new String[]{String.valueOf(id)});
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete From "+tableName+" Where ID = "+id;
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<Taxi_01> getAllContact(){
        ArrayList<Taxi_01> list = new ArrayList<>();
        String sql = "Select * from "+ tableName +" Order by carId ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            while (cursor.moveToNext()){
                Taxi_01 item = new Taxi_01(cursor.getInt(0), cursor.getString(1), cursor.getFloat(2),
                        cursor.getFloat(3), cursor.getInt(4));
                list.add(item);
            }
        }
        return list;
    }
}
