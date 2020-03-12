package com.example.progandroid;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_NOHP = "nohp";
    public static final String CONTACTS_COLUMN_PASSWORD = "password";
    private HashMap hp;

    public static final String TABLE_USERS = " CREATE TABLE " + CONTACTS_TABLE_NAME + "(" + CONTACTS_COLUMN_EMAIL + " TEXT NOT NULL PRIMARY KEY, " + CONTACTS_COLUMN_NOHP + " TEXT NOT NULL, "+ CONTACTS_COLUMN_PASSWORD + " TEXT NOT NULL)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(TABLE_USERS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(CONTACTS_COLUMN_NOHP, user.nohp);

        //Put email in  @values
        values.put(CONTACTS_COLUMN_EMAIL, user.email);

        //Put password in  @values
        values.put(CONTACTS_COLUMN_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(CONTACTS_TABLE_NAME, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(CONTACTS_TABLE_NAME,// Selecting Table
                new String[]{CONTACTS_COLUMN_NOHP, CONTACTS_COLUMN_EMAIL, CONTACTS_COLUMN_PASSWORD},//Selecting columns want to query
                CONTACTS_COLUMN_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(null, cursor.getString(1), cursor.getString(2));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(CONTACTS_TABLE_NAME,// Selecting Table
                new String[]{CONTACTS_COLUMN_NOHP, CONTACTS_COLUMN_EMAIL, CONTACTS_COLUMN_PASSWORD},//Selecting columns want to query
                CONTACTS_COLUMN_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }


//    public boolean insertContact (String nama, String email, String nohp, String password) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", nama);
//        contentValues.put("email", email);
//        contentValues.put("phone", nohp);
//        contentValues.put("password", password);
//        sqLiteDatabase.insert("DATA", null, contentValues);
//        return true;
//    }
//
//    public Cursor getData(String email) {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor res =  sqLiteDatabase.rawQuery( "select * from contacts where email = "+email+"", null );
//        return res;
//    }
//
//    public int numberOfRows(){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, CONTACTS_TABLE_NAME);
//        return numRows;
//    }
//
//    public boolean updateContact (String nama, String email, String nohp) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", nama);
//        contentValues.put("email", email);
//        contentValues.put("phone", nohp);
//        sqLiteDatabase.update("contacts", contentValues, "email = ? ", new String[] { email } );
//        return true;
//    }
//
//    public Integer deleteContact (String email) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        return sqLiteDatabase.delete("contacts",
//                "email = ? ",
//                new String[] { email });
//    }
//
//    public ArrayList<String> getAllContacts() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor res =  sqLiteDatabase.rawQuery( "select * from contacts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NOHP)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
}
