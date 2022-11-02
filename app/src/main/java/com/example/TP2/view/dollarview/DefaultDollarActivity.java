package com.example.TP2.view.dollarview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.entity.DollarEntity;
import com.example.TP2.presenter.dollarpresenter.DefaultDollarPresenter;
import com.example.TP2.presenter.dollarpresenter.DollarPresenter;
import com.example.TP2.sensors.ShakeDetector;
import com.example.TP2.view.userhistoryview.DefaultUserHistoryActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DefaultDollarActivity extends AppCompatActivity implements DollarActivity {

    private DollarPresenter presenter;
    private static final String TAG = "DollarActivity";
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;


    public DefaultDollarActivity() {
        this.presenter = new DefaultDollarPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dollar);

        Button btn_update = findViewById(R.id.update_dollar_button);
        btn_update.setOnClickListener(btnListener);

        presenter.onDollarListUpdate(getApplicationContext());

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(count -> presenter.onDollarListUpdate(getApplicationContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dollar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.user_history_menu_item:
                setUserHistoryView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener btnListener = view -> {
        switch (view.getId()) {
            case R.id.update_dollar_button:
                presenter.onDollarListUpdate(getApplicationContext());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    };

    public void loadDollarEntityList(List<DollarEntity> dollarEntityList) {
        /* Adds a row to the dollar table */
        TableLayout tl = (TableLayout) findViewById(R.id.dollar_table);
        tl.removeViews(1, Math.max(0, tl.getChildCount() - 1));

        View view = findViewById(R.id.dollar_row);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        for (DollarEntity dollarEntity: dollarEntityList) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(layoutParams);

            tr.addView(generateTextView(dollarEntity.getType(), R.id.dollar_type_text));
            tr.addView(generateTextView(dollarEntity.getBuyValue().toString(), R.id.dollar_buy_text));
            tr.addView(generateTextView(dollarEntity.getSellValue().toString(), R.id.dollar_sell_text));
            tr.addView(generateTextView(sdf.format(dollarEntity.getDateTime()), R.id.dollar_date_text));

            tl.addView(tr, tl.getLayoutParams());
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
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
    public void showLoading() {
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }


    private void setUserHistoryView() {
        Intent intent = new Intent(this, DefaultUserHistoryActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("Range")
    @Override
    public void setString(String string) {}
}