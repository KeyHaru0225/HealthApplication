package to.msn.wings.healthapplication;

// package com.example.multiintenttest1;  //　ブクマ参照

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.CursorJoiner;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;



public class initial_screenFragment extends AppCompatActivity {

    static final private String DBNAME = "initial.sqlite";
    static final private int VERSION = 1;
    static final int REQUEST_GET_IMAGE = 100;
    // private static final int CONTEXT_MENU_IMAGE_VIEW_ITEM_ID_1;
    private boolean flg = true;    // イメージボタンの判定で使用

    private TextView mInitial_date;
    private Button mInitial_button1;
    private Button mInitial_button2;
    private TextView mInitial_message2;
    private EditText mInitial_weight;
    private TextView mThe_day_before;
    private TextView mInitial_before_weight;
    private EditText mInitial_memo;
    private Button mInitial_completed;

    private ImageView mImage_view_morning;
    private ImageView mImage_view_lunch;
    private ImageView mImage_view_evening;
    private ImageView mImage_view_snack;

    private ImageButton mImageView_calendar;
    private ImageButton mImageView_graph;
    private ImageButton mImageView_food;
    private ImageButton mImageView_exercise;

    private SQLiteDatabase db;

    Connection connection = null;
    Statement statement = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);

        mInitial_date = (TextView) findViewById(R.id.initial_date);
        mInitial_button1 = (Button) findViewById(R.id.initial_button1);
        mInitial_button2 = (Button) findViewById(R.id.initial_button2);
        mInitial_message2 = (TextView) findViewById(R.id.initial_message2);
        mInitial_weight = (EditText) findViewById(R.id.initial_weight);
        mThe_day_before = (TextView) findViewById(R.id.the_day_before);
        mInitial_before_weight = (TextView) findViewById(R.id.initial_before_weight);
        mInitial_memo = (EditText) findViewById(R.id.initial_memo);
        mInitial_completed = (Button) findViewById(R.id.initial_completed);

        mImage_view_morning = (ImageView) findViewById(R.id.image_view_morning);
        mImage_view_lunch = (ImageView) findViewById(R.id.image_view_lunch);
        mImage_view_evening = (ImageView) findViewById(R.id.image_view_evening);
        mImage_view_snack = (ImageView) findViewById(R.id.image_view_snack);

        mInitial_button1 = (Button) findViewById(R.id.initial_button1);
        mInitial_button2 = (Button) findViewById(R.id.initial_button2);

        // イメージボタンを設定
        mImageView_calendar = (ImageButton) findViewById(R.id.imageView_calendar);
        mImageView_graph = (ImageButton) findViewById(R.id.imageView_graph);
        mImageView_food = (ImageButton) findViewById(R.id.imageView_food);
        mImageView_exercise = (ImageButton) findViewById(R.id.imageView_exercise);



        // 翌日　ボタン選択による画面遷移
        mInitial_button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v){

                InitialTestOpenHelper helper = new InitialTestOpenHelper(getApplicationContext());
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                try {
                    // sqliteのJDBCが存在するかチェック（存在しないとClassNotFoundException）
                    Class.forName("org.sqlite.JDBC");

                    // DBに接続（c:\db\initial_dbに接続）
                    connection = DriverManager.getConnection("jdbc:sqlite:/c:/db/initial_db.db");
                    statement = connection.createStatement();

                    // 前日の体重データ
                    String weight_sql = "select initial_weight from mInitial_date where strNextDate";
                    ResultSet rs = statement.executeQuery(weight_sql);

                    ResultSet[] resultset = {rs};

                    for (int i = 0; i < 1; i++) {
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


        // 前日  ボタン選択による画面遷移
        mInitial_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitialTestOpenHelper helper = new InitialTestOpenHelper(getApplicationContext());
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                try {
                    // sqliteのJDBCが存在するかチェック（存在しないとClassNotFoundException）
                    Class.forName("org.sqlite.JDBC");

                    // DBに接続（c:\db\initial_dbに接続）
                    connection = DriverManager.getConnection("jdbc:sqlite:/c:/db/initial_db.db");
                    statement = connection.createStatement();

                    // 前日の体重データ
                    String weight_sql = "select initial_weight from initial_db where strPreviousDate";
                    ResultSet rs1 = statement.executeQuery(weight_sql);

                    // 前々日の体重データ
                    String before_weight_sql = "select initial_weight from initial_db where strDbyviousDate";
                    ResultSet rs2 = statement.executeQuery(before_weight_sql);

                    // 朝食画像(前日)
                    String morning_sql = "select morning_blob from initial_db where strPreviousDate";
                    ResultSet rs3 = statement.executeQuery(morning_sql);

                    // 昼食画像(前日)
                    String lunch_sql = "select lunch_blob from initial_db where strPreviousDate";
                    ResultSet rs4 = statement.executeQuery(lunch_sql);

                    // 夕食画像(前日)
                    String dinner_sql = "select dinner_blob from initial_db where strPreviousDate";
                    ResultSet rs5 = statement.executeQuery(dinner_sql);

                    // 間食画像(前日)
                    String snack_sql = "select snack_blob from initial_db whrere strPreviousDate";
                    ResultSet rs6 = statement.executeQuery(snack_sql);

                    // メモ(前日)
                    String memo_sql = "select initial_memo from initial_db whrere strPreviousDate";
                    ResultSet rs7 = statement.executeQuery(memo_sql);

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


        // 前日、翌日　画面遷移
        // 前日
        mInitial_button1.setOnClickListener(v -> {
            Intent intent_b1 = new Intent(getApplication(), initial_screenFragment.class);
            startActivity(intent_b1);
        });

        // 翌日
        mInitial_button2.setOnClickListener(v -> {
            Intent intent_b2 = new Intent(getApplication(), initial_screenFragment.class);
            startActivity(intent_b2);
        });


        // 各画面へ遷移(イメージボタンから)
        mImageView_calendar.setOnClickListener(v -> {
            if (flg) {
                Intent intent_c = new Intent(getApplication(), calendarFragment.class);
                startActivity(intent_c);
            }
        });
        mImageView_graph.setOnClickListener(v -> {
            if (flg) {
                Intent intent_g = new Intent(getApplication(), GraphFragment.class);
                startActivity(intent_g);
            }
        });
        mImageView_food.setOnClickListener(v -> {
            if (flg) {
                Intent intent_f = new Intent(getApplication(), FoodList.class);
                startActivity(intent_f);
            }
        });
        mImageView_exercise.setOnClickListener(v -> {
            if (flg) {
                Intent intent_e = new Intent(getApplication(), TaskList.class);
                startActivity(intent_e);
            }
        });


        // 各画面へ遷移
        mImageView_calendar.setOnClickListener(v -> {
            if (flg) {
                Intent intent_c = new Intent(getApplication(), calendarFragment.class);
                startActivity(intent_c);
            }
        });
        mImageView_graph.setOnClickListener(v -> {
            if (flg) {
                Intent intent_g = new Intent(getApplication(), GraphFragment.class);
                startActivity(intent_g);
            }
        });
        mImageView_food.setOnClickListener(v -> {
            if (flg) {
                Intent intent_f = new Intent(getApplication(), FoodList.class);
                startActivity(intent_f);
            }
        });
        mImageView_exercise.setOnClickListener(v -> {
            if (flg) {
                Intent intent_e = new Intent(getApplication(), TaskList.class);
                startActivity(intent_e);
            }
        });

        /*
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
         */
    }


    // 削除予定　現在日時の取得
    public static String getNowDate() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date mInitial_date = new Date(System.currentTimeMillis());
        return df.format(mInitial_date);
    }


    // 現在日時の取得
    public class GetDate {

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



    /*
    // コンテキストメニュー
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        int viewId = view.getId();

        // コンテキストメニューの設定
        if (viewId == R.id.image_view_morning) {
            menu.setHeaderTitle("朝食画像");

            menu.add(0, CONTEXT_MENU_IMAGE_VIEW_ITEM_ID_1, 0, "ImageView　メニュー1");
            menu.add(0, CONTEXT_MENU_IMAGE_VIEW_ITEM_ID_2, 0, "ImageView　メニュー2");
        } else if (viewId == R.id.initial_before_weight) {
            menu.setHeaderTitle("TextViewのコンテキストメニュー");
            menu.setHeaderIcon(getResources().getDrawable(R.drawable.ic_launcher));

            menu.add(0, CONTEXT_MENU_TEXT_VIEW_ITEM_ID_1, 0, "TextView メニュー 1");
            menu.add(0, CONTEXT_MENU_TEXT_VIEW_ITEM_ID_2, 0, "TextView メニュー 2");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CONTEXT_MENU_IMAGE_VIEW_ITEM_ID_1:
                // TODO メニュー押下時の操作
                return true;
            case CONTEXT_MENU_IMAGE_VIEW_ITEM_ID_2:
                // TODO メニュー押下時の操作
                return true;
            case CONTEXT_MENU_TEXT_VIEW_ITEM_ID_1:
                // TODO メニュー押下時の操作
                return true;
            case CONTEXT_MENU_TEXT_VIEW_ITEM_ID_2:
                // TODO メニュー押下時の操作
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    // クリックでメニューをインスタンス化する
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.initial_menu_main, menu);
        return true;
    }
    */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.image_view_morning || id == R.id.image_view_lunch || id == R.id.image_view_evening || id == R.id.image_view_snack ) {
            Intent pickPhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
            pickPhotoIntent.setType("image/*");

            Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            Intent chooserIntent = Intent.createChooser(pickPhotoIntent, "Picture...");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePhotoIntent});

            startActivityForResult(chooserIntent, REQUEST_GET_IMAGE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_GET_IMAGE == requestCode && resultCode == Activity.RESULT_OK && data != null) {
            try {
                if (data.getExtras() != null && data.getExtras().get("data") != null) {
                    Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
                    mImage_view_morning.setImageBitmap(capturedImage);
                    mImage_view_lunch.setImageBitmap(capturedImage);
                    mImage_view_evening.setImageBitmap(capturedImage);
                    mImage_view_snack.setImageBitmap(capturedImage);
                } else {
                    InputStream stream = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(stream);
                    stream.close();
                    mImage_view_morning.setImageBitmap(bitmap);
                    mImage_view_lunch.setImageBitmap(bitmap);
                    mImage_view_evening.setImageBitmap(bitmap);
                    mImage_view_snack.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}