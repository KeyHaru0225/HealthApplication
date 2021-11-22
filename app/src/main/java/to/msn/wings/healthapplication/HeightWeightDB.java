package to.msn.wings.healthapplication;



import android.content.ContentValues;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Blob;


public class HeightWeightDB extends AppCompatActivity {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BLOB = "";

    private TextView textView;

    private TextView initial_date, task_date, food_date, graph_date, calendar_date;  // 日付
    private TextView weight;                                                         // 体重(初期設定)
    private EditText initial_weight;                                                 // 体重(エディット)
    private TextView initial_before_weight;                                          // 体重(前日比)
    private Blob image_view_morning, food_morning_img;                               // 朝食
    private Blob image_view_lunch, food_lunch_img;                                   // 昼食
    private Blob image_view_evening, food_dinner_img;                                // 夕食
    private Blob image_view_snack, food_snack_img;                                   // 間食
    private EditText initial_memo;                                                   // 初期画面(アプリを立ち上げた際)のメモ
    private EditText task1, task2, task3, task_txt1, task_txt2, task_txt3;           // タスク、そのメモ
    private TextView task_achievement;                                               // タスクの達成率
    private EditText food_morning_txt, food_lunch_txt, food_dinner_txt, food_snack_txt;  // 各食事に関するメモ

    private HeightWeightTestOpenHelper helper;
    private SQLiteDatabase db;
    private EditText mInitial_weight;
    private TextView mInitial_com;      // 前日比
    private TextView minitial_message2;
    private Button mInitial_button1;
    private Button mInitial_button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);

        mInitial_weight = (EditText)findViewById(R.id.initial_weight);
        mInitial_com = (TextView)findViewById(R.id.initial_before_weight);
        minitial_message2 = (TextView)findViewById(R.id.initial_message2);

        mInitial_button1 = (Button) findViewById(R.id.initial_button1);
        mInitial_button2 = (Button) findViewById(R.id.initial_button2);

        mInitial_button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(helper == null) {
                    helper = new HeightWeightTestOpenHelper(getApplication());
                }

                if(db == null) {
                    db = helper.getWritableDatabase();
                }

                // よびだした値を文字に変換、格納
                String weight = mInitial_weight.getText().toString();
                String com = mInitial_com.getText().toString();
            }
        });


        //Button readButton = findViewById(R.id.button_read);
        //readButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        readData();
        //    } // 読みだし
        //});
    }


    private void readData() {
        if(helper == null) {
            helper = new HeightWeightTestOpenHelper(getApplicationContext());
        }

        if(db == null) {
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "healthdb",
                new String[] { "weight", "bmi" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        // 忘れずに！
        cursor.close();
    }

    private void insertData(SQLiteDatabase db, String weight, int bmi) {

        ContentValues values = new ContentValues();
        values.put("weight", weight);
        values.put("bmi", bmi);

        db.insert("healthdb", null, values);
    }
}

