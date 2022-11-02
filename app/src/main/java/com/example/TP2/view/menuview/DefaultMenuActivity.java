package com.example.TP2.view.menuview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.presenter.menupresenter.DefaultMenuPresenter;
import com.example.TP2.presenter.menupresenter.MenuPresenter;
import com.example.TP2.view.dollarview.DefaultDollarActivity;
import com.example.TP2.view.loginview.DefaultLoginActivity;
import com.example.TP2.view.userhistoryview.DefaultUserHistoryActivity;

public class DefaultMenuActivity extends AppCompatActivity implements MenuActivity {
    private static final String TAG = "MenuActivity";

    private final MenuPresenter presenter;

    public DefaultMenuActivity() {
        this.presenter = new DefaultMenuPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btn_dollar = findViewById(R.id.view_dollars_button);
        btn_dollar.setOnClickListener(btnListener);
        Button btn_close_session = findViewById(R.id.close_session_button);
        btn_close_session.setOnClickListener(btnListener);
        Button btn_user_history = findViewById(R.id.user_history_button);
        btn_user_history.setOnClickListener(btnListener);

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.view_dollars_button:
                    Log.i(TAG, "Se hizo click en ver dollar view");
                    setDollarView();
                    break;
                case R.id.close_session_button:
                    Log.i(TAG, "Se hizo click en cerrar sesión");
                    setLoginView();
                    break;
                case R.id.user_history_button:
                    Log.i(TAG, "Se hizo click en ver historial de usuarios");
                    setUserHistoryView();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setLoginView() {
        Intent intent = new Intent(this, DefaultLoginActivity.class);
        startActivity(intent);

        finish();
    }

    public void setUserHistoryView() {
        Intent intent = new Intent(this, DefaultUserHistoryActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void setDollarView() {
        Intent intent = new Intent(this, DefaultDollarActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        setDollarView();
    }

    @SuppressLint("Range")
    @Override
    public void setString(String string) {}
}