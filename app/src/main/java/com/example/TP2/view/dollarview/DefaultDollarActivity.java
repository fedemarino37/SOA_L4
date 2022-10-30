package com.example.TP2.view.dollarview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.presenter.dollarpresenter.DollarPresenter;

public class DefaultDollarActivity extends AppCompatActivity implements DollarActivity {

    private DollarPresenter presenter;
    private static final String TAG = "DollarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dollar);

        Button btn_update = findViewById(R.id.update_dollar_button);
        btn_update.setOnClickListener(btnListener);

    }

    private View.OnClickListener btnListener = view -> {
        Intent intent;
        switch (view.getId()) {
            case R.id.update_dollar_button:
                addDollarToTable();
                Log.i(TAG, "Se hizo click en udpate dollar");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    };

    private void addDollarToTable() {
        /* Adds a row to the dollar table */

        TableLayout tl = (TableLayout) findViewById(R.id.dollar_table);
        TableRow tr = new TableRow(this);
        View view = findViewById(R.id.dollar_example_row);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        tr.setLayoutParams(layoutParams);

        tr.addView(generateTextView("MEP", R.id.dollar_type_example_text));
        tr.addView(generateTextView("$100", R.id.dollar_buy_example_text));
        tr.addView(generateTextView("$101", R.id.dollar_sell_example_text));
        tr.addView(generateTextView("22/10/2022", R.id.dollar_date_example_text));

        tl.addView(tr, tl.getLayoutParams());
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

    @SuppressLint("Range")
    @Override
    public void setString(String string) {}
}