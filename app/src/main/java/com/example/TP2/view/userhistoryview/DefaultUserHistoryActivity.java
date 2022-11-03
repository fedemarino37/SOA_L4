package com.example.TP2.view.userhistoryview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.presenter.userhistorypresenter.DefaultUserHistoryPresenter;
import com.example.TP2.presenter.userhistorypresenter.UserHistoryPresenter;
import com.example.TP2.view.dollarview.DefaultDollarActivity;

import java.util.List;

public class DefaultUserHistoryActivity extends AppCompatActivity implements UserHistoryActivity {
    private static final String TAG = "UserHistoryActivity";

    private final UserHistoryPresenter presenter;

    public DefaultUserHistoryActivity() {
        this.presenter = new DefaultUserHistoryPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);

        Button btn_update = findViewById(R.id.update_user_history_button);
        btn_update.setOnClickListener(btnListener);

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        presenter.onUserHistoryListUpdate(getApplicationContext());
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.update_user_history_button:
                    Log.i(TAG, "Se hizo click en actualizar listado de usuarios");
                    presenter.onUserHistoryListUpdate(getApplicationContext());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setDollarView();
                break;
        }
        return true;
    }

    @Override
    public void loadUserHistoryList(List<SQLUserEntity> userHistoryList) {
        /* Adds a row to the dollar table */
        TableLayout tl = (TableLayout) findViewById(R.id.user_history_table);
        tl.removeViews(1, Math.max(0, tl.getChildCount() - 1));

        View view = findViewById(R.id.user_history_row);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        for (SQLUserEntity sqlUserEntity : userHistoryList) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(layoutParams);

            tr.addView(generateTextView(sqlUserEntity.getName(), R.id.user_name_text));
            tr.addView(generateTextView(sqlUserEntity.getTimeStampLastAccess(), R.id.user_last_access_text)); //TODO: review this

            tl.addView(tr, tl.getLayoutParams());
        }
    }

    private TextView generateTextView(String text, int metaIdTextView) {
        /* Generates the text view object using the meta of another text view */

        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        View view = findViewById(metaIdTextView);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        textView.setLayoutParams(layoutParams);
        textView.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textView.setGravity(((TextView) view).getGravity());
        textView.setText(text);
        return textView;
    }

    @Override
    public void onBackPressed() {
        setDollarView();
    }

    private void setDollarView() {
        Intent intent = new Intent(this, DefaultDollarActivity.class);
        startActivity(intent);

        finish();
    }


    @Override
    public void showLoading() {
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}