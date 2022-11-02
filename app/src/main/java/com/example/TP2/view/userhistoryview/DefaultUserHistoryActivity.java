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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.presenter.userhistorypresenter.DefaultUserHistoryPresenter;
import com.example.TP2.presenter.userhistorypresenter.UserHistoryPresenter;
import com.example.TP2.view.userhistoryview.menuview.DefaultMenuActivity;

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
        ImageButton btn_menu = findViewById(R.id.menu_button);
        btn_menu.setOnClickListener(btnListener);

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
                case R.id.menu_button:
                    Log.i(TAG, "Se hizo click en ver menú");
                    setMenuView();
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

    public void loadUserHistoryEntityList(/*List<User> userEntityList*/) {
        /* Adds a row to the dollar table */
        TableLayout tl = (TableLayout) findViewById(R.id.user_history_table);
        tl.removeViews(1, Math.max(0, tl.getChildCount() - 1));

        View view = findViewById(R.id.user_history_row);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        /*for (UserEntity user: userEntityList) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(layoutParams);

            tr.addView(generateTextView(user.getName(), R.id.user_name_text));
            tr.addView(generateTextView(user.getLastAccess().toString(), R.id.user_last_access_text));

            tl.addView(tr, tl.getLayoutParams());
        }*/
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
    public void setMenuView() {
        //se genera un Intent para poder lanzar la activity principal
        Intent intent = new Intent(this, DefaultMenuActivity.class);

        //se inicia la activity principal
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        setMenuView();
    }

    @SuppressLint("Range")
    public void setString(String string) {}
}