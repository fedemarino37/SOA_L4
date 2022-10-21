package com.example.TP2.repository.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.TP2.entity.SQLUserEntity;

import java.util.ArrayList;
import java.util.List;

public class DefaultSQLUserRepository implements SQLUserRepository {
    private static final String DB_NAME = "dbUsersHistory";
    private static final String USER_HISTORY_TABLE = "usersHistory";
    private static final String USERS_TABLE = "users";
    private SQLiteDatabase sql;
    /*
    * Agrego como atributo "SQLiteDatabase" porque lo necesito en otros metodos, pero lo inicializo
    *   en cada metodo para tener el contexto correcto.
    * */

    // Table fields
    private static final String NAME = "Name";
    private static final String LAST_NAME = "LastName";
    private static final String LAST_ACCESS = "LastAccess";
    private static final String EMAIL = "Email";





    public DefaultSQLUserRepository() {}


    public List<SQLUserEntity> retrieveUsersHistory(Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, ctx.MODE_PRIVATE, null);
        sql.execSQL(createIfNotExistsUSERSHISTORY());

        List<SQLUserEntity> usersList = new ArrayList<> ();
        SQLUserEntity user = new SQLUserEntity();
        Cursor cr = sql.rawQuery(selectAllFrom(USER_HISTORY_TABLE),null);

        cr.moveToFirst();

        while( cr != null) {
            user.setName(cr.getString(0));
            user.setLastName(cr.getString(1));
            user.setTimeStampLastAccess(cr.getString(2));
        }

        sql.close();
        return usersList;
    }


    public SQLUserEntity getUserData(Context ctx, String userEmail) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, ctx.MODE_PRIVATE, null);

        SQLUserEntity user = new SQLUserEntity();

        Cursor cr = sql.rawQuery(selectUserFromWhere(USERS_TABLE,userEmail),null);
        cr.moveToFirst();

        if(cr != null) {
            user.setName(cr.getString(0));
            user.setLastName(cr.getString(1));
        }

        return user;
    }

    // Todo: Hacer saveUserHistorty: crear una entrada en la tabla, con el usuario del parametro.
    public boolean saveUserHistory(Context ctx, SQLUserEntity user) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, ctx.MODE_PRIVATE, null);
        this.sql.rawQuery(createIfNotExistsUSERSHISTORY(),null);

        ContentValues cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(LAST_NAME, user.getLastName());
        cv.put(LAST_ACCESS, user.getTimeStampLastAccess());

        return this.sql.insert(USER_HISTORY_TABLE,null,cv) > 0;
    }

    private String selectUserFromWhere(String usersTable, String userEmail) {
        return "SELECT * FROM " + usersTable + " WHERE userEmail = '" + userEmail + "'";
    }

    private String selectAllFrom(String table) {
        return "select * from " + table;
    }

    private String createIfNotExistsUSERSHISTORY() {
        return "create table if not exists " + USER_HISTORY_TABLE + " ("
                + NAME + " text, "
                + LAST_NAME + "text, "
                + LAST_ACCESS + " text)";
    }

    private String createIfNotExistsUSERS() {
        return "create table if not exists " + USERS_TABLE + " ("
                + NAME + " text, "
                + LAST_NAME + "text, "
                + EMAIL + " text)";
    }
}
