package to.msn.wings.healthapplication;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class calendarFragment extends AppCompatActivity {

    private boolean flg = true;
    private TextView mCalendar_date1;
    private Button mCalendar_button1;
    private Button mCalendar_button2;
    private TextView mCalendar_month;
    private TextView mCal_date;
    private TextView mCalendar_date2;
    private TextView mCalendar_weight;
    private TextView mCalendar_bmi;

    private TextView titleText;
    private Button prevButton, nextButton;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;

    private ImageButton mImageView_calendar;
    private ImageButton mImageView_graph;
    private ImageButton mImageView_food;
    private ImageButton mImageView_exercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        mCal_date = (TextView) findViewById(R.id.cal_date);
        // titleText = (TextView) findViewById(R.id.titleText);
        prevButton = (Button) findViewById(R.id.prevButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        // イメージボタンを設定
        mImageView_calendar = (ImageButton) findViewById(R.id.imageView_calendar);
        mImageView_graph = (ImageButton) findViewById(R.id.imageView_graph);
        mImageView_food = (ImageButton) findViewById(R.id.imageView_food);
        mImageView_exercise = (ImageButton) findViewById(R.id.imageView_exercise);


        // 各画面へ遷移
        mImageView_calendar.setOnClickListener(view -> {
            if (flg) {
                Intent intent_c = new Intent(getApplication(), calendarFragment.class);
                startActivity(intent_c);
            }
        });
        mImageView_graph.setOnClickListener(view -> {
            if (flg) {
                Intent intent_g = new Intent(getApplication(), GraphFragment.class);
                startActivity(intent_g);
            }
        });
        mImageView_food.setOnClickListener(view -> {
            if (flg) {
                Intent intent_f = new Intent(getApplication(), FoodList.class);
                startActivity(intent_f);
            }
        });
        mImageView_exercise.setOnClickListener(view -> {
            if (flg) {
                Intent intent_e = new Intent(getApplication(), TaskList.class);
                startActivity(intent_e);
            }
        });



        // 前月、次月に遷移
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.prevMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.nextMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        calendarGridView = findViewById(R.id.calendarGridView);
        mCalendarAdapter = new CalendarAdapter(this);
        calendarGridView.setAdapter(mCalendarAdapter);
        titleText.setText(mCalendarAdapter.getTitle());
    }



    // 現在日時の取得
    public static String getNowDate() {
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        final Date mCal_date = new Date(System.currentTimeMillis());
        return df.format(mCal_date);
    }
}
