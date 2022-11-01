package com.example.TP2.view.dollarview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.entity.DollarEntity;
import com.example.TP2.presenter.dollarpresenter.DefaultDollarPresenter;
import com.example.TP2.presenter.dollarpresenter.DollarPresenter;
import com.example.TP2.sensors.ShakeDetector;
import com.example.TP2.view.menuview.DefaultMenuActivity;

import java.util.List;

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
        ImageButton btn_menu = findViewById(R.id.menu_button);
        btn_menu.setOnClickListener(btnListener);

        presenter.onDollarListUpdate(getApplicationContext());

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                presenter.onDollarListUpdate(getApplicationContext());
            }
        });
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

    private View.OnClickListener btnListener = view -> {
        Intent intent;
        switch (view.getId()) {
            case R.id.update_dollar_button:
                presenter.onDollarListUpdate(getApplicationContext());
                Log.i(TAG, "Se hizo click en udpate dollar");
                break;
            case R.id.menu_button:
                Log.i(TAG, "Se hizo click en ver menu");
                setMenuView();
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

        for (DollarEntity dollarEntity: dollarEntityList) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(layoutParams);

            tr.addView(generateTextView(dollarEntity.getType(), R.id.dollar_type_text));
            tr.addView(generateTextView(dollarEntity.getBuyValue().toString(), R.id.dollar_buy_text));
            tr.addView(generateTextView(dollarEntity.getSellValue().toString(), R.id.dollar_sell_text));
            tr.addView(generateTextView(dollarEntity.getDateTime().toString(), R.id.dollar_date_text));

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

    public void setMenuView() {
        //se genera un Intent para poder lanzar la activity principal
        Intent intent = new Intent(this, DefaultMenuActivity.class);

        //se inicia la activity principal
        startActivity(intent);

        finish();
    }

    @SuppressLint("Range")
    @Override
    public void setString(String string) {}
}