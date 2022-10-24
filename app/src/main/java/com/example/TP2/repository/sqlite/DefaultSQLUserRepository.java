package com.example.TP2.repository.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.TP2.entity.SQLUserEntity;

import java.util.ArrayList;
import java.util.List;

public class DefaultSQLUserRepository implements SQLUserRepository {
    private SQLiteDatabase sql;

    private static final String DB_NAME = "dbUsersHistory";
    private static final String USER_HISTORY_TABLE = "usersHistory";
    private static final String USERS_TABLE = "users";

    // Table fields
    private static final String NAME = "Name";
    private static final String LAST_NAME = "LastName";
    private static final String LAST_ACCESS = "LastAccess";
    private static final String EMAIL = "Email";

    // Queries
    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name text, LastName text, Email text)";
    private static final String CREATE_TABLE_USERS_HISTORY = "CREATE TABLE IF NOT EXISTS usersHistory (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name text, LastName text, LastAccess text)";
    private static final String SELECT_ALL_FROM_USERS = "SELECT * FROM users";
    private static final String SELECT_ALL_FROM_USERS_HISTORY = "SELECT * FROM usersHistory";


    public DefaultSQLUserRepository() {
    }


    public List<SQLUserEntity> retrieveUsersHistory(Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        sql.execSQL(CREATE_TABLE_USERS_HISTORY);

        List<SQLUserEntity> usersList = new ArrayList<>();
        SQLUserEntity user = new SQLUserEntity();
        Cursor cr = sql.rawQuery(SELECT_ALL_FROM_USERS_HISTORY, null);

        cr.moveToFirst();

        do {
            user.setName(cr.getString(cr.getColumnIndexOrThrow(NAME)));
            user.setLastName(cr.getString(cr.getColumnIndexOrThrow(LAST_NAME)));
            user.setTimeStampLastAccess(cr.getString(cr.getColumnIndexOrThrow(LAST_ACCESS)));

            usersList.add(user);
        } while (cr.moveToNext());

        cr.close();
        sql.close();
        return usersList;
    }

    public List<SQLUserEntity> getUsersTable(Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        sql.execSQL(CREATE_TABLE_USERS);

        List<SQLUserEntity> usersList = new ArrayList<>();
        SQLUserEntity user = new SQLUserEntity();
        Cursor cr = sql.rawQuery(SELECT_ALL_FROM_USERS, null);

        cr.moveToFirst();

        do {
            user.setName(cr.getString(cr.getColumnIndexOrThrow(NAME)));
            user.setLastName(cr.getString(cr.getColumnIndexOrThrow(LAST_NAME)));
            user.setEmail(cr.getString(cr.getColumnIndexOrThrow(EMAIL)));

            usersList.add(user);
        } while (cr.moveToNext());

        cr.close();
        sql.close();
        return usersList;
    }

    @Override
    public void deleteTables(Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        sql.execSQL("DELETE FROM " + USERS_TABLE);
        sql.execSQL("DROP TABLE " + USERS_TABLE);
        sql.execSQL("DELETE FROM " + USER_HISTORY_TABLE);
        sql.execSQL("DROP TABLE " + USER_HISTORY_TABLE);
        System.out.println("TABLAS ELIMINADAS!");
    }


    public SQLUserEntity getUserData(Context ctx, String userEmail) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        SQLUserEntity user = new SQLUserEntity();

        Cursor cr = sql.rawQuery(selectFromUsersTable(userEmail), null);
        cr.moveToFirst();

        if (cr.getCount() > 0) {
            user.setName(cr.getString(0));
            user.setLastName(cr.getString(1));
        }

        return user;
    }


    public void saveUserHistory(Context ctx, SQLUserEntity user) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        this.sql.execSQL(CREATE_TABLE_USERS_HISTORY);

        this.sql.execSQL(insertIntoUsersHistory(user));
    }

    public void insertNewUser(Context ctx, SQLUserEntity newUser) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        this.sql.execSQL(CREATE_TABLE_USERS);

        this.sql.execSQL(insertIntoUsers(newUser));

    }

    private String insertIntoUsers(SQLUserEntity newUser) {
        String insertIntoUsers =
                "INSERT INTO users ("
                        + NAME + ", " + LAST_NAME + ", " + EMAIL +
                        ") VALUES ( " +
                        "'" + newUser.getName() + "'" + ", " +
                        "'" + newUser.getLastName() + "'" + ", " +
                        "'" + newUser.getEmail() + "'" + ");";

        return insertIntoUsers;
    }

    private String insertIntoUsersHistory(SQLUserEntity user) {
        String insertIntoUsersHistory =
                "INSERT INTO usersHistory ("
                        + NAME + ", " + LAST_NAME + ", " + LAST_ACCESS
                        + ") VALUES ( " +
                        "'" + user.getName() + "'" + ", " +
                        "'" + user.getLastName() + "'" + ", " +
                        "'" + user.getTimeStampLastAccess() + "'" + ");";

        return insertIntoUsersHistory;
    }

    private String selectFromUsersTable(String userEmail) {
        return "SELECT * FROM " + USERS_TABLE + " WHERE Email = '" + userEmail + "'";
    }
}
