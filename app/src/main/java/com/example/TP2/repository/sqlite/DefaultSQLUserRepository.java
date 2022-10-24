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
    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name text, LastName text, Email text)";
    private static final String CREATE_TABLE_USERS_HISTORY = "CREATE TABLE IF NOT EXISTS usersHistory (Name text, LastName text, LastAccess text)";
    private static final String SELECT_ALL_FROM_USERS = "SELECT * FROM users";
    private static final String SELECT_ALL_FROM_USERS_HISTORY = "SELECT * FROM usersHistory";
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
        sql.execSQL(CREATE_TABLE_USERS_HISTORY);

        List<SQLUserEntity> usersList = new ArrayList<> ();
        SQLUserEntity user = new SQLUserEntity();
        Cursor cr = sql.rawQuery(SELECT_ALL_FROM_USERS_HISTORY,null);

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

    public List<SQLUserEntity> getUsersTable (Context ctx) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        sql.execSQL(CREATE_TABLE_USERS);

        List<SQLUserEntity> usersList = new ArrayList<> ();
        SQLUserEntity user = new SQLUserEntity();
        Cursor cr = sql.rawQuery(SELECT_ALL_FROM_USERS,null);

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
        sql.execSQL("delete from " + USERS_TABLE);
        sql.execSQL("drop table " + USERS_TABLE);
        sql.execSQL("delete from " + USER_HISTORY_TABLE);
        sql.execSQL("drop table " + USER_HISTORY_TABLE);
        System.out.println("TABLAS ELIMINADAS!");
    }


// Todo: Eliminar todos los datos de la tabla user y verificar que solo haya uno igual.
    // Hay que agregarle un id para que no se repitan, incluir el dni si es posible
    // o un numero autoincrementable.

    public SQLUserEntity getUserData(Context ctx, String userEmail) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
//        sql.execSQL(CREATE_TABLE_USERS);
        SQLUserEntity user = new SQLUserEntity();


        Cursor cr = sql.rawQuery(selectUserFromWhere(USERS_TABLE,userEmail),null);
        cr.moveToFirst();

        if(cr.getCount() > 0) {
            user.setName(cr.getString(0));
            user.setLastName(cr.getString(1));
        }

        return user;
    }


    public void saveUserHistory(Context ctx, SQLUserEntity user) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
//        this.sql.rawQuery(CREATE_TABLE_USERS_HISTORY,null);
        this.sql.execSQL(CREATE_TABLE_USERS_HISTORY);



        ContentValues cv = new ContentValues();
        cv.put(NAME, user.getName());
        cv.put(LAST_NAME, user.getLastName());
        cv.put(LAST_ACCESS, user.getTimeStampLastAccess());

        this.sql.execSQL(insertIntoUsersHistory(user));
//        return this.sql.insert(USER_HISTORY_TABLE,null,cv) > 0;
    }

    public void insertNewUser(Context ctx, SQLUserEntity newUser) {
        this.sql = ctx.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
//        this.sql.rawQuery(createIfNotExistsUSERS(),null);
        this.sql.execSQL(CREATE_TABLE_USERS);

//        ContentValues cv = new ContentValues();
//        cv.put(NAME, newUser.getName());
//        cv.put(LAST_NAME, newUser.getLastName());
//        cv.put(EMAIL, newUser.getEmail());

        this.sql.execSQL(insertIntoUsers(newUser));

    }

    private String insertIntoUsers(SQLUserEntity newUser) {
        String insertIntoUsers =
                "INSERT INTO users " + "values( " +
                "'" + newUser.getName() + "'" + ", " +
                "'" + newUser.getLastName() + "'" + ", " +
                "'" + newUser.getEmail() + "'" + ");";

        return insertIntoUsers;
    }

    private String insertIntoUsersHistory(SQLUserEntity user) {
        String insertIntoUsers =
                "INSERT INTO usersHistory " + "values( " +
                        "'" + user.getName() + "'" + ", " +
                        "'" + user.getLastName() + "'" + ", " +
                        "'" + user.getTimeStampLastAccess() + "'" + ");";

        return insertIntoUsers;
    }


//    // Todo: Corregir esto. hacerlo solo para usersTable
    private String selectUserFromWhere(String usersTable, String userEmail) {
        // Todo: Pasar todas las queries a final static
        return "SELECT * FROM " + USERS_TABLE + " WHERE email = '" + userEmail + "'";
    }
//
//    private String selectAllFrom() {
//        return "select * from users";
//    }
//
//    private String createIfNotExistsUSERSHISTORY() {
//        return "create table if not exists " + USER_HISTORY_TABLE + " ("
//                + NAME + " text, "
//                + LAST_NAME + "text, "
//                + LAST_ACCESS + " text)";
//    }
//
//    private String createIfNotExistsUSERS() {
//
//
//        return "create table if not exists " + USERS_TABLE + " ("
//                + NAME + " text, "
//                + LAST_NAME + "text, "
//                + EMAIL + " text)";
//    }
}
