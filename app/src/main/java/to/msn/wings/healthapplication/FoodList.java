package to.msn.wings.healthapplication;




import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.net.Uri;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;



public class FoodList extends AppCompatActivity {
    private boolean flg = true;
    private ImageView mImage_view_morning;
    private ImageView mImage_view_lunch;
    private ImageView mImage_view_evening;
    private ImageView mImage_view_snack;
    private ImageButton mImageView_calendar;
    private ImageButton mImageView_graph;
    private ImageButton mImageView_food;
    private ImageButton mImageView_exercise;

    // 端末内の画像を取得
    private static final int RESULT_PICK_IMAGEFILE = 1000;
    private ImageView FoodMooningImg;
    private ImageView FoodLunchImg;
    private ImageView FoodDinnerImg;
    private ImageView FoodSnackImg;

    private TextView mFood_date;

    private Button mFood_button1;
    private Button mFood_button2;

    private Button mFood_morning_btn;
    private Button mFood_lunch_btn;
    private Button mFood_dinner_btn;
    private Button mFood_snack_btn;

    private Button mInitial_button1;

    private EditText mFood_morning_txt;
    private EditText mFood_lunch_txt;
    private EditText mFood_dinner_txt;
    private EditText mFood_snack_txt;

    private SQLiteDatabase db;

    Connection connection = null;
    Statement statement = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);

        mImage_view_morning = (ImageView) findViewById(R.id.image_view_morning);
        mImage_view_lunch = (ImageView) findViewById(R.id.image_view_lunch);
        mImage_view_evening = (ImageView) findViewById(R.id.image_view_evening);
        mImage_view_snack = (ImageView) findViewById(R.id.image_view_snack);

        // イメージボンを設定
        mImageView_calendar = (ImageButton) findViewById(R.id.imageView_calendar);
        mImageView_graph = (ImageButton) findViewById(R.id.imageView_graph);
        mImageView_food = (ImageButton) findViewById(R.id.imageView_food);
        mImageView_exercise = (ImageButton) findViewById(R.id.imageView_exercise);
        // 各食事ボタンを設定
        FoodMooningImg = (ImageView) findViewById(R.id.food_morning_img);
        FoodLunchImg = (ImageView) findViewById(R.id.food_lunch_img);
        FoodDinnerImg = (ImageView) findViewById(R.id.food_dinner_img);
        FoodSnackImg = (ImageView) findViewById(R.id.food_snack_img);

        mFood_date = (TextView) findViewById(R.id.food_date);

        mFood_button1 = (Button) findViewById(R.id.food_button1);
        mFood_button2 = (Button) findViewById(R.id.food_button2);

        mFood_morning_txt = (EditText) findViewById(R.id.food_morning_txt);
        mFood_lunch_txt = (EditText) findViewById(R.id.food_lunch_txt);
        mFood_dinner_txt = (EditText) findViewById(R.id.food_dinner_txt);
        mFood_snack_txt = (EditText) findViewById(R.id.food_snack_txt);

        mFood_morning_btn = (Button) findViewById(R.id.food_morning_btn);
        mFood_lunch_btn = (Button) findViewById(R.id.food_lunch_btn);
        mFood_dinner_btn = (Button) findViewById(R.id.food_dinner_btn);
        mFood_snack_btn = (Button) findViewById(R.id.food_snack_btn);

        mInitial_button1 = (Button) findViewById(R.id.initial_button1);


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


        // 前日　ボタン選択による画面遷移
        mFood_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FoodTestOpenHelper helper = new FoodTestOpenHelper(getApplicationContext());
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                try {
                    // sqliteのJDBCが存在するかチェック（存在しないとClassNotFoundException）
                    Class.forName("org.sqlite.JDBC");

                    // DBに接続（c:\db\initial_dbに接続）
                    connection = DriverManager.getConnection("jdbc:sqlite:/c:/db/food_db.db");
                    statement = connection.createStatement();

                    // 朝食　クエリ発行
                    String morningimg_sql = "select morning_blob from food_db where strPreviousDate";
                    ResultSet rs1 = statement.executeQuery(morningimg_sql);

                    // 昼食
                    String lunchimg_sql = "select lunch_blob from food_db where strPreviousDate";
                    ResultSet rs2 = statement.executeQuery(lunchimg_sql);

                    // 夕食
                    String dinnerimg_sql = "select dinner_blob from food_db where strPreviousDate";
                    ResultSet rs3 = statement.executeQuery(dinnerimg_sql);

                    // 間食
                    String snackimg_sql = "select snack_blob from food_db where strPreviousDate";
                    ResultSet rs4 = statement.executeQuery(snackimg_sql);

                    // 朝食メモ
                    String mormemo_sql = "select txt_one from food_db where strPreviousDate";
                    ResultSet rs5 = statement.executeQuery(mormemo_sql);

                    // 昼食メモ
                    String lunmemo_sql = "select txt_two from food_db where strPreviousDate";
                    ResultSet rs6 = statement.executeQuery(lunmemo_sql);

                    // 夕食メモ
                    String dinmemo_sql = "select txt_three from initial_db where strPreviousDate";
                    ResultSet rs7 = statement.executeQuery(dinmemo_sql);

                    // 間食メモ
                    String snackmemo_sql = "select txt_four from food_db where strPreviousDate";
                    ResultSet rs8 = statement.executeQuery(snackmemo_sql);

                    ResultSet[] resultset = {rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8};

                    for (int i = 0; i < 8; i++) {
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


        /*
        // 画像パスからBitmapを作成、各ImageViewに格納　　　
        ActivityResultLauncher<Intent> _launcherSelectSingleImage = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent resultData = result.getData();
                            if (resultData != null) {
                                Uri uri = resultData.getData();

                                try {
                                    Bitmap bmp = getBitmapFromUri(uri);
                                    FoodMooningImg.setImageBitmap(bmp);
                                    FoodLunchImg.setImageBitmap(bmp);
                                    FoodDinnerImg.setImageBitmap(bmp);
                                    FoodSnackImg.setImageBitmap(bmp);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
                });
                        // 画像フォルダの読み込みを設定
                        mFood_morning_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent_m = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                                intent_m.addCategory(Intent.CATEGORY_OPENABLE);
                                intent_m.setType("image/*");
                                intent_m.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                                Intent chooserIntent = Intent.createChooser(intent_m, "単一画像の選択");
                                _launcherSelectSingleImage.launch(chooserIntent);
                            }

                            // 画像パスからBitmapを形成、LyaoutのImageViewへセット
                            private Bitmap getBitmapFromUri(Uri uri) throws IOException {
                                ParcelFileDescriptor parcelFileDescriptor =
                                        getContentResolver().openFileDescription(uri, "r");
                                FileDescription fileDescription = parcelFileDescriptor.getFileDescriptor();
                                Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                                parcelFileDescriptor.close();
                                return image;
                            }
                        });

                        mFood_lunch_btn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent_m = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                                intent_m.addCategory(Intent.CATEGORY_OPENABLE);
                                intent_m.setType("image/*");
                                intent_m.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                                Intent chooserIntent = Intent.createChooser(intent_m, "単一画像の選択");

                                _launcherSelectSingleImage.launch(chooserIntent);

                            }

                            // 画像パスからBitmapを形成、LyaoutのImageViewへセット
                            private Bitmap getBitmapFromUri(Uri uri) throws IOException {
                                ParcelFileDescriptor parcelFileDescriptor =
                                        getContentResolver().openFileDescription(uri, "r");
                                FileDescription fileDescription = parcelFileDescriptor.getFileDescriptor();
                                Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                                parcelFileDescriptor.close();
                                return image;
                            }
                        });

                        mFood_dinner_btn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent_m = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                                intent_m.addCategory(Intent.CATEGORY_OPENABLE);
                                intent_m.setType("image/*");
                                intent_m.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                                Intent chooserIntent = Intent.createChooser(intent_m, "単一画像の選択");

                                _launcherSelectSingleImage.launch(chooserIntent);

                            }

                            // 画像パスからBitmapを形成、LyaoutのImageViewへセット
                            private Bitmap getBitmapFromUri(Uri uri) throws IOException {
                                ParcelFileDescriptor parcelFileDescriptor =
                                        getContentResolver().openFileDescription(uri, "r");
                                FileDescription fileDescription = parcelFileDescriptor.getFileDescriptor();
                                Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                                parcelFileDescriptor.close();
                                return image;
                            }
                        });

                        mFood_snack_btn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent_m = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                                intent_m.addCategory(Intent.CATEGORY_OPENABLE);
                                intent_m.setType("image/*");
                                intent_m.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                                Intent chooserIntent = Intent.createChooser(intent_m, "単一画像の選択");

                                _launcherSelectSingleImage.launch(chooserIntent);
                            }

                            // 画像パスからBitmapを形成、LyaoutのImageViewへセット
                            private Bitmap getBitmapFromUri(Uri uri) throws IOException {
                                ParcelFileDescriptor parcelFileDescriptor =
                                        getContentResolver().openFileDescription(uri, "r");
                                FileDescription fileDescription = parcelFileDescriptor.getFileDescriptor();
                                Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                                parcelFileDescriptor.close();
                                return image;
                            }
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


                    }

    }

         */

