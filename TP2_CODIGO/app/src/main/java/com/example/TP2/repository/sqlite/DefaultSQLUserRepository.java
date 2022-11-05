package com.example.TP2.repository.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.TP2.entity.SQLUserEntity;

import java.util.LinkedList;
import java.util.List;

public class DefaultSQLUserRepository implements SQLUserRepository {
    private SQLiteDatabase sql;

    private static final String DB_NAME = "dbUsersHistory";

    // Table's names
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


    public DefaultSQLUserRepository() {}

    public List<SQLUserEntity> retrieveUsersHistory(Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        this.sql.execSQL(CREATE_TABLE_USERS_HISTORY);

        LinkedList<SQLUserEntity> usersList;

        Cursor cr = this.sql.rawQuery(SELECT_ALL_FROM_USERS_HISTORY, null);

        if (cr.getCount() > 0) {
            cr.moveToFirst();
            usersList = new LinkedList<>();

            do {
                int id = Integer.parseInt(cr.getString(cr.getColumnIndexOrThrow("Id")));
                String name = cr.getString(cr.getColumnIndexOrThrow(NAME));
                String lastName = cr.getString(cr.getColumnIndexOrThrow(LAST_NAME));
                String lastAccess = cr.getString(cr.getColumnIndexOrThrow(LAST_ACCESS));

                usersList.add(id-1, new SQLUserEntity(name,lastName,null,lastAccess));
            } while (cr.moveToNext());
        } else
            usersList = null;

        cr.close();
        this.sql.close();
        return usersList;
    }

    public List<SQLUserEntity> getUsersTable(Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        this.sql.execSQL(CREATE_TABLE_USERS);

        LinkedList<SQLUserEntity> usersList;

        Cursor cr = this.sql.rawQuery(SELECT_ALL_FROM_USERS, null);

        if (cr.getCount() > 0) {
            cr.moveToFirst();
            usersList = new LinkedList<>();

            do {
                int id = Integer.parseInt(cr.getString(cr.getColumnIndexOrThrow("Id")));
                String name = cr.getString(cr.getColumnIndexOrThrow(NAME));
                String lastName = cr.getString(cr.getColumnIndexOrThrow(LAST_NAME));
                String email = cr.getString(cr.getColumnIndexOrThrow(EMAIL));

                usersList.add(id-1, new SQLUserEntity(name,lastName,email,null));
            } while (cr.moveToNext());
        } else
            usersList = null;

        cr.close();
        this.sql.close();
        return usersList;
    }

    public void deleteTables(Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        sql.execSQL("DELETE FROM " + USERS_TABLE);
        sql.execSQL("DROP TABLE " + USERS_TABLE);
        sql.execSQL("DELETE FROM " + USER_HISTORY_TABLE);
        sql.execSQL("DROP TABLE " + USER_HISTORY_TABLE);

        System.out.println("TABLAS ELIMINADAS!");
    }

    public SQLUserEntity getUserData(Context ctx, String userEmail) {
        openOrCreateDatabase(ctx);
        SQLUserEntity user = null;

        Cursor cr = sql.rawQuery(selectFromUsersTable(userEmail), null);

        if (cr.getCount() > 0) {
            cr.moveToFirst();
            user = new SQLUserEntity();

            user.setName(cr.getString(1));
            user.setLastName(cr.getString(2));
        }

        cr.close();

        return user;
    }

    public void saveUserHistory(Context ctx, SQLUserEntity user) {
       openOrCreateDatabase(ctx);

        this.sql.execSQL(insertIntoUsersHistory(user));
    }

    public void insertNewUser(Context ctx, SQLUserEntity newUser) {
        openOrCreateDatabase(ctx);

        this.sql.execSQL(insertIntoUsers(newUser));
    }

    private void openOrCreateDatabase(Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        this.sql.execSQL(CREATE_TABLE_USERS);
        this.sql.execSQL(CREATE_TABLE_USERS_HISTORY);
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
