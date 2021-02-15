package com.example.remotemvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private SQLiteDatabase mydb;

    public DatabaseHelper(Context context){
        mydb = new MyDatabase(context).getWritableDatabase();
        Log.d("aaa","Database");
    }


    public void addNewEmployee(Employee employee){
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("phone", employee.getPhone());
        values.put("address", employee.getAddress());
        values.put("password", employee.getPassword());
        values.put("time", employee.getTime());

        mydb.insert(MyDatabase.tableEmployees, null, values);
        mydb.close();
    }

    public void addNewIMEI(IMEIconnectedPHONES imei){
        ContentValues values = new ContentValues();
        values.put("imeii", imei.getIMEIthis());

        mydb.insert(MyDatabase.tablelastConnection, null, values);
        mydb.close();
        Log.d("aaa","~~~~   added to database   ~~~~~~");
    }

    public void addNewAccessIMEI(IMEIconnectedPHONES imei){
        ContentValues values = new ContentValues();
        values.put("imeii", imei.getIMEIthis());

        mydb.insert(MyDatabase.tableAccessList, null, values);
        mydb.close();
        Log.d("aaa","~~~~   added to database Access  ~~~~~~");
    }

    public void addNewNotAccessIMEI(IMEIconnectedPHONES imei){
        ContentValues values = new ContentValues();
        values.put("imeii", imei.getIMEIthis());

        mydb.insert(MyDatabase.tableNotAccessList, null, values);
        mydb.close();
        Log.d("aaa","~~~~   added to database Access  ~~~~~~");
    }

    public List<Employee> getListOfEmployees(){

        Cursor c = mydb.rawQuery("select * from " + MyDatabase.tableEmployees, null);
        List<Employee> employees = new ArrayList<>();

        while (c.moveToNext()){

            Employee em = new Employee();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.ID)));
            em.setName(c.getString(c.getColumnIndex(MyDatabase.NAME)));
            em.setPhone(c.getString(c.getColumnIndex(MyDatabase.PHONE)));
            em.setAddress(c.getString(c.getColumnIndex(MyDatabase.ADDRESS)));
            em.setPassword(c.getString(c.getColumnIndex(MyDatabase.PASSWORD)));
            em.setTime(c.getString(c.getColumnIndex(MyDatabase.TIME)));

            employees.add(em);
        }

        c.close();
        mydb.close();
        return employees;
    }

    public List<IMEIconnectedPHONES> getListOfConnectedIMEI(){

        Log.d("aaa","getListOfConnectedIMEI ");
        Cursor c = mydb.rawQuery("select * from " + MyDatabase.tablelastConnection, null);
        Log.d("aaa","query ");
        List<IMEIconnectedPHONES> imeis = new ArrayList<>();

        while (c.moveToNext()){

            IMEIconnectedPHONES em = new IMEIconnectedPHONES();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.IDIMEIconnected)));
            em.setIMEIthis(c.getString(c.getColumnIndex(MyDatabase.IMEII)));

            imeis.add(em);
        }

        Log.d("aaa","imeis "+imeis);

        c.close();
        mydb.close();
        return imeis;
    }

    public List<IMEIconnectedPHONES> getListOfAccessIMEI(){

        Log.d("aaa","getListOfAccessIMEI ");
        Cursor c = mydb.rawQuery("select * from " + MyDatabase.tableAccessList, null);
        Log.d("aaa","query ");
        List<IMEIconnectedPHONES> imeis = new ArrayList<>();

        while (c.moveToNext()){

            IMEIconnectedPHONES em = new IMEIconnectedPHONES();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.IDIMEIconnected)));
            em.setIMEIthis(c.getString(c.getColumnIndex(MyDatabase.IMEII)));

            imeis.add(em);
        }

//        Log.d("aaa","imeis "+imeis);

        c.close();
        mydb.close();
        return imeis;
    }

    public List<IMEIconnectedPHONES> getListOfNotAccessIMEI(){

        Log.d("aaa","getListOfNotAccessIMEI ");
        Cursor c = mydb.rawQuery("select * from " + MyDatabase.tableNotAccessList, null);
        List<IMEIconnectedPHONES> imeis = new ArrayList<>();

        while (c.moveToNext()){

            IMEIconnectedPHONES em = new IMEIconnectedPHONES();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.IDIMEIconnected)));
            em.setIMEIthis(c.getString(c.getColumnIndex(MyDatabase.IMEII)));

            imeis.add(em);
        }

        Log.d("aaa","imeis in getListOfNotAccessIMEI "+imeis);

        c.close();
        mydb.close();
        return imeis;
    }


    public void editEmployee(Employee employee){
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("phone", employee.getPhone());
        values.put("address", employee.getAddress());
        values.put("password", employee.getPassword());
        values.put("time",employee.getTime());

        mydb.update(MyDatabase.tableEmployees, values, "id = " + employee.getId(), null);
        mydb.close();
    }

    public void editIMEI(IMEIconnectedPHONES imei){
        ContentValues values = new ContentValues();
        values.put("imeii", imei.getIMEIthis());

        mydb.update(MyDatabase.tablelastConnection, values, "idIMEIconnected = " + imei.getId(), null);
        mydb.close();
    }

    public void editIMEIAccess(IMEIconnectedPHONES imei){
        ContentValues values = new ContentValues();
        values.put("imeii", imei.getIMEIthis());

        mydb.update(MyDatabase.tableAccessList, values, "idIMEIconnected = " + imei.getId(), null);
        mydb.close();
    }

    public void deleteEmployee(Employee employee){
        mydb.delete(MyDatabase.tableEmployees, "id = " + employee.getId(), null);
        mydb.close();
    }

    public void deleteIMEI(IMEIconnectedPHONES imei){
        Log.d("aaa","****   deleteIMEI  ****");
        Log.d("aaa","get id imei "+imei.getId());
        mydb.delete(MyDatabase.tablelastConnection, "idIMEIconnected = " + imei.getId(), null);
        Log.d("aaa","DONE");
        mydb.close();
    }

    public void deleteIMEIAccess(IMEIconnectedPHONES imei){
        Log.d("aaa","****   deleteIMEI  ****");
        mydb.delete(MyDatabase.tableAccessList, "idIMEIconnected = " + imei.getId(), null);
        Log.d("aaa","get id imei "+imei.getId());
        mydb.close();

    }

    public void deleteIMEIAccessByInputString(String imei)
    {
        Log.d("aaa","****   deleteIMEI  ****");
        mydb.delete(MyDatabase.tableAccessList, "imeii = " + imei, null);
        Log.d("aaa","get id imei "+imei);
        mydb.close();
    }

    public void deleteIMEINotAccessByInputString(String imei)
    {
        Log.d("aaa","****   deleteIMEI  ****");
        mydb.delete(MyDatabase.tableNotAccessList, "imeii = " + imei, null);
        Log.d("aaa","get imei "+imei);
        mydb.close();
    }

    public void deleteIMEINotAccess(IMEIconnectedPHONES imei){
        Log.d("aaa","****   deleteIMEI  ****");
        mydb.delete(MyDatabase.tableNotAccessList, "idIMEIconnected = " + imei.getId(), null);
        Log.d("aaa","get id imei "+imei.getId());
        mydb.close();
    }

    public void deleteAllIMEI()
    {
        mydb.execSQL("delete from "+ MyDatabase.tablelastConnection);
    }

    public void deleteAllIMEIAccess()
    {
        mydb.execSQL("delete from "+ MyDatabase.tableAccessList);
    }

    public void deleteAllemployee()
    {
        mydb.execSQL("delete from "+ MyDatabase.tableEmployees);
    }

    public List<Employee> searchByName(String name){

        Cursor c = mydb.rawQuery("select * from " + MyDatabase.tableEmployees + " where name like '%" + name + "%'", null);
        List<Employee> employees = new ArrayList<>();

        while (c.moveToNext()){

            Employee em = new Employee();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.ID)));
            em.setName(c.getString(c.getColumnIndex(MyDatabase.NAME)));
            em.setPhone(c.getString(c.getColumnIndex(MyDatabase.PHONE)));
            em.setAddress(c.getString(c.getColumnIndex(MyDatabase.ADDRESS)));
            em.setPassword(c.getString(c.getColumnIndex(MyDatabase.PASSWORD)));
            employees.add(em);
        }

        c.close();
        mydb.close();
        return employees;
    }

    public List<Employee> searchByExactName(String name){

        String selectQuery =  "SELECT " +
                MyDatabase.ID + "," +
                MyDatabase.NAME + "," +
                MyDatabase.PHONE + "," +
                MyDatabase.ADDRESS + "," +
                MyDatabase.PASSWORD+
                " FROM " + MyDatabase.tableEmployees
                + " WHERE " +
                MyDatabase.NAME + "=?";
        Cursor c = mydb.rawQuery(selectQuery, new String[] { String.valueOf(name) } );
        List<Employee> employees = new ArrayList<>();
//        Log.d("aaa","befor while in name");
        while (c.moveToNext()){
//            Log.d("aaa","in while in name");
            Employee em = new Employee();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.ID)));
            em.setName(c.getString(c.getColumnIndex(MyDatabase.NAME)));
            em.setPhone(c.getString(c.getColumnIndex(MyDatabase.PHONE)));
            em.setAddress(c.getString(c.getColumnIndex(MyDatabase.ADDRESS)));
            em.setPassword(c.getString(c.getColumnIndex(MyDatabase.PASSWORD)));
            employees.add(em);
        }

        c.close();
        mydb.close();
        return employees;
    }

    public List<Employee> searchByExactDoor(String doorName){

        String selectQuery =  "SELECT " +
                MyDatabase.ID + "," +
                MyDatabase.NAME + "," +
                MyDatabase.PHONE + "," +
                MyDatabase.ADDRESS + "," +
                MyDatabase.PASSWORD+
                " FROM " + MyDatabase.tableEmployees
                + " WHERE " +
                MyDatabase.ADDRESS + "=?";
        Cursor c = mydb.rawQuery(selectQuery, new String[] { String.valueOf(doorName) } );
        List<Employee> employees = new ArrayList<>();
//        Log.d("aaa","befor while in doorname");
        while (c.moveToNext()){
//            Log.d("aaa","in while in doorname");
            Employee em = new Employee();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.ID)));
            em.setName(c.getString(c.getColumnIndex(MyDatabase.NAME)));
            em.setPhone(c.getString(c.getColumnIndex(MyDatabase.PHONE)));
            em.setAddress(c.getString(c.getColumnIndex(MyDatabase.ADDRESS)));
            em.setPassword(c.getString(c.getColumnIndex(MyDatabase.PASSWORD)));
            employees.add(em);
        }

        c.close();
        mydb.close();
        return employees;
    }

}

