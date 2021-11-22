package to.msn.wings.healthapplication;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TaskDB extends AppCompatActivity {

    private TextView mTask_date;
    private EditText mTask1;
    private EditText mTask2;
    private EditText mTask3;
    private EditText mTask_txt1;
    private EditText mTask_txt2;
    private EditText mTask_txt3;
    private TextView mTask_achievement;

    private TaskTestOpenHelper helper;
    private SQLiteDatabase db;
    private Button mTask_completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);

        mTask_date = (TextView) findViewById(R.id.task_date);
        mTask1 = (EditText) findViewById(R.id.task1);
        mTask2 = (EditText) findViewById(R.id.task2);
        mTask3 = (EditText) findViewById(R.id.task3);
        mTask_txt1 = (EditText) findViewById(R.id.task_txt1);
        mTask_txt2 = (EditText) findViewById(R.id.task_txt2);
        mTask_txt3 = (EditText) findViewById(R.id.task_txt3);
        mTask_achievement = (TextView) findViewById(R.id.task_achievement);

        mTask_completed = (Button) findViewById(R.id.task_completed);

        mTask_completed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (helper == null) {
                    helper = new TaskTestOpenHelper(getApplicationContext());
                }

                if (db == null) {
                    db = helper.getWritableDatabase();
                }

                // 呼び出しだ値を文字に変換、格納
                String task_date = mTask_date.getText().toString();
                String task1 = mTask1.getText().toString();
                String task2 = mTask2.getText().toString();
                String task3 = mTask3.getText().toString();
                String task_txt1 = mTask_txt1.getText().toString();
                String task_txt2 = mTask_txt2.getText().toString();
                String task_txt3 = mTask_txt3.getText().toString();
                String task_achievement = mTask_achievement.getText().toString();

                insertData(db, task_date, task1, task2, task3, task_txt1, task_txt2, task_txt3, task_achievement);

            }
        });
    }

    //TODO 読みだしは飛ばした

    private void readData() {
        if (helper == null) {
            helper = new TaskTestOpenHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }

        Log.d("debug", "**********Cursor");

        Cursor cursor = db.query(
                "task_db",
                new String[]{"task_date", "task_one", "task_two", "task_three", "memo_one", "memo_two", "memo_three", "achievement"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        cursor.close();
    }

    private void insertData(SQLiteDatabase db, String date, String taskone, String tasktwo, String taskthree, String memoone, String memotwo, String memothree, String achieve) {

        ContentValues values = new ContentValues();
        values.put("task_date", date);
        values.put("task_one", taskone);
        values.put("task_two", tasktwo);
        values.put("task_three", taskthree);
        values.put("memo_one", memoone);
        values.put("memo_two", memotwo);
        values.put("memo_three", memothree);
        values.put("achievement", achieve);

        db.insert("task_db", null, values);
    }

}


