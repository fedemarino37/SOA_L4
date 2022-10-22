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
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        sql.execSQL(createIfNotExistsUSERSHISTORY());

        List<SQLUserEntity> usersList = new ArrayList<> ();
        SQLUserEntity user = new SQLUserEntity();
        Cursor cr = sql.rawQuery(selectAllFrom(USER_HISTORY_TABLE),null);

        cr.moveToFirst();

        do {
            user.setName(cr.getString(cr.getColumnIndexOrThrow(NAME)));
            user.setLastName(cr.getString(cr.getColumnIndexOrThrow(LAST_NAME)));
            user.setTimeStampLastAccess(cr.getString(cr.getColumnIndexOrThrow(LAST_ACCESS)));
            user.setEmail(cr.getString(cr.getColumnIndexOrThrow(EMAIL)));
        } while (cr.moveToNext());

        cr.close();
        sql.close();
        return usersList;
    }


    public SQLUserEntity getUserData(Context ctx, String userEmail) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

        SQLUserEntity user = new SQLUserEntity();

        Cursor cr = sql.rawQuery(selectUserFromWhere(USERS_TABLE,userEmail),null);
        cr.moveToFirst();

        if(cr != null) {
            user.setName(cr.getString(0));
            user.setLastName(cr.getString(1));
        }

        return user;
    }


    public boolean saveUserHistory(Context ctx, SQLUserEntity user) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        this.sql.rawQuery(createIfNotExistsUSERSHISTORY(),null);
        /*
        *   Se elimino el metodo open connection.
        *       Antes: Dado un contexto, abria la conexion con la BD, y creaba o abria una unica tabla.
        *       Ahora: Se estan usando dos tablas, en contextos diferentes.
        * */


        ContentValues cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(LAST_NAME, user.getLastName());
        cv.put(LAST_ACCESS, user.getTimeStampLastAccess());

        return this.sql.insert(USER_HISTORY_TABLE,null,cv) > 0;
    }

    public boolean insertNewUser(Context ctx, SQLUserEntity newUser) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        this.sql.rawQuery(createIfNotExistsUSERS(),null);

        ContentValues cv = new ContentValues();
        cv.put(NAME, newUser.getName());
        cv.put(LAST_NAME, newUser.getLastName());
        cv.put(EMAIL, newUser.getEmail());

        return this.sql.insert(USERS_TABLE,null,cv) > 0;
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
