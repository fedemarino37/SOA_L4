package com.example.TP2.repository.sqlLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.TP2.entity.UserEntity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class DefaultSQLLiteRepository implements SQLLiteRepository {
    private Context context;
    private SQLiteDatabase sql;
    private final String dataBaseName = "dbUsersHistory";
    private final String tableUsersHistory = "usersHistory";

    public DefaultSQLLiteRepository(Context context) {
        this.context = context;
        sql = this.context.openOrCreateDatabase(dataBaseName, this.context.MODE_PRIVATE, null);
        sql.execSQL(createIfNotExists(tableUsersHistory));
    }


    public List<UserEntity> retrieveUsersHistory(Context ctx) {
        List<UserEntity> usersList = new ArrayList<> ();
        UserEntity user = new UserEntity();
        Cursor cr = sql.rawQuery(selectAllFrom(tableUsersHistory),null);

        cr.moveToFirst();

        while( cr != null) {
            user.setName(cr.getString(0));
            user.setLastName(cr.getString(1));
            user.setTimeStampLastAccess(cr.getString(2));
        }

        return usersList;
    }

    private String selectAllFrom(String table) {
        return "select * from " + table;
    }

    private String createIfNotExists(String tableUsersHistory) {
        String nameTableField = "Name";
        String lastNameTableField = "LastName";
        String timeStampLastAccess = "LastAccess";

        return "create table if not exists " + tableUsersHistory + " ("
                + nameTableField + " text, "
                + lastNameTableField + "text, "
                + timeStampLastAccess + " text)";
    }


}
