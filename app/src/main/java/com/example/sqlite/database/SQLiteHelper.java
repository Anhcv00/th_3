package com.example.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlite.model.Work;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "exam.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table works(" +
                "id Integer primary key autoincrement," +
                "name Text, `desc` Text, dateline Text, status Text, collab Text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Work> getAll(){
        List<Work> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "dateline DESC";
        Cursor rs = st.query("works", null, null, null,
                null, null, order);

        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String desc = rs.getString(2);
            String dateline = rs.getString(3);
            String status = rs.getString(4);
            String collab = rs.getString(5);
            list.add(new Work(id, name, desc, dateline, status, collab));
        }
        return list;
    }


    public long addItem(Work i){
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("desc", i.getDesc());
        values.put("dateline", i.getDateline());
        values.put("status", i.getStatus());
        values.put("collab", i.getCollab());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("works", null, values);
    }

    public List<Work> getByDateline(String dateline){
        List<Work> list = new ArrayList<>();
        String whereClause = "dateline like ?";
        String[] whereArgs = {dateline};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("works", null, whereClause, whereArgs, null, null, null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String desc = rs.getString(2);
            String status = rs.getString(3);
            String collab = rs.getString(4);
            list.add(new Work(id, name, desc, dateline, status, collab));
        }
        return list;
    }

    public int update(Work work){
        ContentValues values = new ContentValues();
        values.put("name", work.getName());
        values.put("desc", work.getDesc());
        values.put("dateline", work.getDateline());
        values.put("status", work.getStatus());
        values.put("collab", work.getCollab());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(work.getId())};
        return sqLiteDatabase.update("works", values, whereClause, whereArgs);
    }

    public int delete(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("works", whereClause, whereArgs);
    }

    public List<Work> searchByName(String key){
        List<Work> list = new ArrayList<>();
        String whereClause = "name like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("works", null, whereClause, whereArgs, null, null, null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String desc = rs.getString(2);
            String dateline = rs.getString(3);
            String status = rs.getString(4);
            String collab = rs.getString(5);

            list.add(new Work(id, name, desc, dateline, status, collab));
        }
        return list;
    }

    public List<Work> searchByDesc(String desc){
        List<Work> list = new ArrayList<>();
        String whereClause = "desc like ?";
        String[] whereArgs = {desc};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("works", null, whereClause, whereArgs, null, null, null);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String des = rs.getString(2);
            String dateline = rs.getString(3);
            String status = rs.getString(4);
            String collab = rs.getString(5);

            list.add(new Work(id, name, desc, dateline, status, collab));
        }
        return list;
    }

//    public List<Work> searchByDatelineFromTo(String from, String to){
//        List<Work> list = new ArrayList<>();
//        String whereClause = "date between ? and ?";
//        String[] whereArgs = {from.trim(), to.trim()};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("works", null, whereClause, whereArgs, null, null, null);
//        while (rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String name = rs.getString(1);
//            String desc = rs.getString(2);
//            String status = rs.getString(3);
//            String dateline = rs.getString(4);
//
//            list.add(new Work(id, name, desc, status, dateline));
//        }
//        return list;
//    }
}
