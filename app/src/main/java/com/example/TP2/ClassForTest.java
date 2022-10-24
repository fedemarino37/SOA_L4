package com.example.TP2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;
import com.example.TP2.usecase.CreateSQLUserEntity;
import com.example.TP2.usecase.LoginUser;
import com.example.TP2.usecase.RegisterUser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ClassForTest {
    SQLUserEntity testUser;
    final static String PASSWORD = "12345678";
    final static int COMISION = 1900;
    final static int GRUPO = 4;
    final static int dni = 12345678;


    public ClassForTest() {
        this.testUser = new SQLUserEntity();
        testUser.setName("qwe");
        testUser.setLastName("qwe");
        testUser.setEmail("qwe@mail.com");
    }

    public void testLoginWithAPI(Context ctx) {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("qwe@mail.com");
        loginUserRequest.setPassword("12345678");
        LoginUser loginUser = new LoginUser();
        try {
            loginUser.execute(ctx, loginUserRequest);
        } catch (NetworkConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testLoginSQL(Context ctx) {
        SQLUserRepository sql = new DefaultSQLUserRepository();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());

        SQLUserEntity sqlUserEntity = sql.getUserData(ctx, testUser.getEmail());
        sqlUserEntity.setTimeStampLastAccess(currentDateAndTime);


        sql.saveUserHistory(ctx, sqlUserEntity);
    }

    public void testRegisterSQL(Context ctx) {
        SQLUserRepository sql = new DefaultSQLUserRepository();
        sql.insertNewUser(ctx,this.testUser);
    }

    public void testShowUsersHistoryTable(Context ctx) {
        SQLUserRepository sqlUserRepository = new DefaultSQLUserRepository();
        List<SQLUserEntity> users = sqlUserRepository.retrieveUsersHistory(ctx);

        if(users.isEmpty())
            Toast.makeText(ctx, "VACIO!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < users.size(); i++) {
            Toast.makeText(ctx, "NO SE MUESTRAN", Toast.LENGTH_SHORT).show();
            System.out.print(users.get(i).getName() + " | ");
            System.out.print(users.get(i).getLastName() + " | ");
            System.out.println(users.get(i).getTimeStampLastAccess());
        }

    }

    public void testShowUsers(Context ctx) {
        SQLUserRepository sqlUserRepository = new DefaultSQLUserRepository();
        List<SQLUserEntity> users = sqlUserRepository.getUsersTable(ctx);

        if(users.isEmpty())
            Toast.makeText(ctx, "VACIO!", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < users.size(); i++) {
            Toast.makeText(ctx, "NO SE MUESTRAN", Toast.LENGTH_SHORT).show();
            System.out.print(users.get(i).getName() + " | ");
            System.out.print(users.get(i).getLastName() + " | ");
            System.out.println(users.get(i).getEmail());
        }
    }

    public void testRegisterWithAPI(Context ctx) {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setName("qwe");
        registerUserRequest.setLastName("qwe");
        registerUserRequest.setDni(12345678);
        registerUserRequest.setEmail("qwe@mail.com");
        registerUserRequest.setPassword("12345678");
        registerUserRequest.setCommission(1900);
        registerUserRequest.setGroup(4);
       RegisterUser registerUser = new RegisterUser();
        try {
            registerUser.execute(ctx, registerUserRequest);
        } catch (NetworkConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
