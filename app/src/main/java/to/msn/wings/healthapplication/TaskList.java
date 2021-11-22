package to.msn.wings.healthapplication;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TaskList extends AppCompatActivity {
    private EditText mTask1;
    private EditText mTask2;
    private EditText mTask3;
    private EditText mTask_txt1;
    private EditText mTask_txt2;
    private EditText mTask_txt3;
    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private TextView mTask_date;
    private TextView mTask_achievement;
    private ImageButton mImageView_calendar;
    private ImageButton mImageView_graph;
    private ImageButton mImageView_food;
    private ImageButton mImageView_exercise;
    private Button mTask_button1;
    private Button mTask_button2;
    private Button mTask_completed;

    private boolean flg = true;

    private SQLiteDatabase db;

    Connection connection = null;
    Statement statement = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);

        mTask1 = (EditText) findViewById(R.id.task1);
        mTask2 = (EditText) findViewById(R.id.task2);
        mTask3 = (EditText) findViewById(R.id.task3);
        mTask_txt1 = (EditText) findViewById(R.id.task_txt1);
        mTask_txt2 = (EditText) findViewById(R.id.task_txt2);
        mTask_txt3 = (EditText) findViewById(R.id.task_txt3);

        mCheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        mCheckBox3 = (CheckBox) findViewById(R.id.checkBox3);

        mTask_date = (TextView) findViewById(R.id.task_date);
        mTask_achievement = (TextView) findViewById(R.id.task_achievement);

        // イメージボタンを設定
        mImageView_calendar = (ImageButton) findViewById(R.id.imageView_calendar);
        mImageView_graph = (ImageButton) findViewById(R.id.imageView_graph);
        mImageView_food = (ImageButton) findViewById(R.id.imageView_food);
        mImageView_exercise = (ImageButton) findViewById(R.id.imageView_exercise);

        mTask_button1 = (Button) findViewById(R.id.task_button1);
        mTask_button2 = (Button) findViewById(R.id.task_button2);

        mTask_completed = (Button) findViewById(R.id.task_completed);



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


        // チェックボックス判定
        mTask_completed.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view){
                if (mCheckBox1.isChecked() == true && mCheckBox2.isChecked() == true && mCheckBox3.isChecked() == true) {
                    mTask_achievement.setText("3/3 !!!");
                } else if ((mCheckBox1.isChecked() == true && mCheckBox2.isChecked() == true && mCheckBox3.isChecked() == false) ||
                        (mCheckBox1.isChecked() == true && mCheckBox2.isChecked() == false && mCheckBox3.isChecked() == true) ||
                        (mCheckBox1.isChecked() == false && mCheckBox2.isChecked() == true && mCheckBox3.isChecked() == true)) {
                    mTask_achievement.setText("2/3 !!");
                } else if ((mCheckBox1.isChecked() == true && mCheckBox2.isChecked() == false && mCheckBox3.isChecked() == false) ||
                        (mCheckBox1.isChecked() == false && mCheckBox2.isChecked() == true && mCheckBox3.isChecked() == false) ||
                        (mCheckBox1.isChecked() == false && mCheckBox2.isChecked() == false && mCheckBox3.isChecked() == true)) {
                    mTask_achievement.setText("1/3 !");
                } else {
                    mTask_achievement.setText("0/3");
                }
            }});


        // 現在日時の取得
    /*
    public static String getNowDate() {
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        final Date mTask_date = new Date(System.currentTimeMillis());
        return df.format(mTask_date);
    }
     */


        // 現在日時の取得
        class GetDate {

            public void main(String[] args) {
                // 自動生成されたメソッド・スタブ

                // 当日
                Date nowDate = new Date();

                System.out.println(nowDate.toString());

                // yyyy-MM-dd形式へ
                String strDate = new SimpleDateFormat("yyyy-MM-dd").format(nowDate);


                Calendar cal = Calendar.getInstance();

                // 翌日
                cal.setTime(nowDate);
                cal.add(Calendar.DAY_OF_MONTH, 1);

                // yyyy-MM-dd形式へ
                String strNextDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());


                // 前日
                cal.setTime(nowDate);
                cal.add(Calendar.DAY_OF_MONTH, -1);


                // yyyy-MM-dd形式へ
                String strPreviousDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());


                // 前々日
                cal.setTime(nowDate);
                cal.add(Calendar.DAY_OF_MONTH, -2);


                // yyyy-MM-dd形式へ  Dby=day before yesterday
                String strDbyviousDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

            }

        }


        // 前日  ボタン選択による画面遷移
        mTask_button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                TaskTestOpenHelper helper = new TaskTestOpenHelper(getApplicationContext());
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                try {
                    // sqliteのJDBCが存在するかチェック（存在しないとClassNotFoundException）
                    Class.forName("org.sqlite.JDBC");

                    // DBに接続（c:\db\initial_dbに接続）
                    connection = DriverManager.getConnection("jdbc:sqlite:/c:/db/food_db.db");
                    statement = connection.createStatement();

                    // タスク１(前日)
                    String taskone_sql = "select task_one from task_db where strPreviousDate";
                    ResultSet rs1 = statement.executeQuery(taskone_sql);

                    // タスク２(前日)
                    String tasktwo_sql = "select task_two from task_db where strPreviousDate";
                    ResultSet rs2 = statement.executeQuery(tasktwo_sql);

                    // タスク３(前日)
                    String taskthree_sql = "select task_three from task_db where strPreviousDate";
                    ResultSet rs3 = statement.executeQuery(taskthree_sql);

                    // メモ１(前日)
                    String memoone_sql = "select memo_one from task_db where strPreviousDate";
                    ResultSet rs4 = statement.executeQuery(memoone_sql);

                    // メモ２(前日)
                    String memotwo_sql = "select memo_two from task_db where strPreviousDate";
                    ResultSet rs5 = statement.executeQuery(memotwo_sql);

                    // メモ３(前日)
                    String memothree_sql = "select memo_three from task_db where strPreviousDate";
                    ResultSet rs6 = statement.executeQuery(memothree_sql);

                    // 達成率(前日)
                    String achieve_sql = "select achievement from task_db where strPreviousDate";
                    ResultSet rs7 = statement.executeQuery(achieve_sql);


                    ResultSet[] resultset = {rs1, rs2, rs3, rs4, rs5, rs6, rs7};

                    for (int i = 0; i < 7; i++) {
                        // カラム名を指定して値を取得
                        System.out.println(resultset[i].getString("ID"));
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    // ステートメントとコネクションはクローズする
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}
