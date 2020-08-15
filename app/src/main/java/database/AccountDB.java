package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import entities.Account;

public class AccountDB extends SQLiteOpenHelper {

    private static String dbName = "accountDB";
    private static String tableName = "account";
    private static String idColumn = "id";
    private static String usernameColumn = "username";
    private static String passwordColumn = "password";
    private static String fullNameColumn = "fullName";
    private static String emailColumn = "email";
    private static String genderColumn = "gender";
    private static String dateOfBirthColumn = "birth";
    private static String image = "image";
    public AccountDB(Context context) {
        super(context, dbName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumn + " interger primary key, " +
                usernameColumn + " text, " +
                passwordColumn + " text, " +
                fullNameColumn + " text, " +
                emailColumn + " text, " +
                genderColumn + " text, " +
                dateOfBirthColumn + " text, " +
                image + " blob " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public boolean create(Account account) {
        boolean result = true;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(usernameColumn, account.getUsername());
            contentValues.put(passwordColumn, account.getPassword());
            contentValues.put(fullNameColumn, account.getFullName());
            contentValues.put(emailColumn, account.getEmail());
            result = sqLiteDatabase.insert(tableName, null, contentValues) > 0;
            return result;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public Account checkUsername(String username) {
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ? ", new String[]{username});
            if (cursor.moveToFirst()) {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setFullName(cursor.getString(3));
                account.setEmail(cursor.getString(4));
            }
        } catch (Exception e) {
            account = null;
        }
        return account;
    }

    public Account login(String username, String password) {
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ? and password = ?", new String[]{username, password});
            if (cursor.moveToFirst()) {
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setFullName(cursor.getString(3));
                account.setEmail(cursor.getString(4));
            }
        } catch (Exception e) {
            account = null;
        }
        return account;
    }
}
