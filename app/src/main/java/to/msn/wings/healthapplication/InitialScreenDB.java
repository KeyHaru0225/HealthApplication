package to.msn.wings.healthapplication;


import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

public class InitialScreenDB extends AppCompatActivity {

    private TextView mInitial_date;    // 日付
    private EditText mInitial_weight;  // 体重(当日)
    private TextView mInitial_before_weight;  // 体重(前日比)
    private ImageView mImage_view_morning, mImage_view_lunch, mImage_view_evening, mImage_view_snack;  // 各食事画像
    private EditText mInitial_memo;    // メモ

    private InitialTestOpenHelper helper;
    private SQLiteDatabase db;
    private Button mInitial_button1;
    private Button mInitial_button2;
    private Button mInitial_completed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);

        mInitial_date = (TextView) findViewById(R.id.initial_date);
        mInitial_weight = (EditText) findViewById(R.id.initial_weight);
        mInitial_before_weight = (TextView) findViewById(R.id.initial_before_weight);
        mImage_view_morning = (ImageView) findViewById(R.id.image_view_morning);
        mImage_view_lunch = (ImageView) findViewById(R.id.image_view_lunch);
        mImage_view_evening = (ImageView) findViewById(R.id.image_view_evening);
        mImage_view_snack = (ImageView) findViewById(R.id.image_view_snack);
        mInitial_memo = (EditText) findViewById(R.id.initial_memo);

        mInitial_button1 = (Button) findViewById(R.id.initial_button1);
        mInitial_button2 = (Button) findViewById(R.id.initial_button2);
        mInitial_completed = (Button) findViewById(R.id.initial_completed);

        final int[]  baseIds = new int[] {
                R.id.image_view_morning,
                R.id.image_view_lunch,
                R.id.image_view_evening,
                R.id.image_view_snack
        };



        mInitial_completed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (helper == null) {
                    helper = new InitialTestOpenHelper(getApplicationContext());
                }

                if (db == null) {
                    db = helper.getWritableDatabase();
                }

                // よびだした値を文字に変換、格納
                String initial_date = mInitial_date.getText().toString();
                String initial_weight = mInitial_weight.getText().toString();
                String initial_before_weight = mInitial_before_weight.getText().toString();
                String initial_memo = mInitial_memo.getText().toString();

                // DB格納可能にするため drawble→ bitmap→ byte[] 変換
                Drawable drawableM = getResources().getDrawable(baseIds[0]);
                Bitmap bitmapM = ((BitmapDrawable) drawableM).getBitmap();
                ByteArrayOutputStream bosM = new ByteArrayOutputStream();
                bitmapM.compress(Bitmap.CompressFormat.JPEG, 100, bosM);
                byte[] bytemorning = bosM.toByteArray();

                Drawable drawableL = getResources().getDrawable(baseIds[1]);
                Bitmap bitmapL = ((BitmapDrawable) drawableL).getBitmap();
                ByteArrayOutputStream bosL = new ByteArrayOutputStream();
                bitmapL.compress(Bitmap.CompressFormat.JPEG, 100, bosL);
                byte[] bytelunch = bosL.toByteArray();

                Drawable drawableE = getResources().getDrawable(baseIds[2]);;
                Bitmap bitmapE = ((BitmapDrawable) drawableE).getBitmap();
                ByteArrayOutputStream bosE = new ByteArrayOutputStream();
                bitmapE.compress(Bitmap.CompressFormat.JPEG, 100, bosE);
                byte[] byteevening = bosE.toByteArray();

                Drawable drawableS = getResources().getDrawable(baseIds[3]);;
                Bitmap bitmapS = ((BitmapDrawable) drawableS).getBitmap();
                ByteArrayOutputStream bosS = new ByteArrayOutputStream();
                bitmapS.compress(Bitmap.CompressFormat.JPEG, 100, bosS);
                byte[] bytesnack = bosS.toByteArray();

                insertData(db, bytemorning, bytelunch, byteevening, bytesnack,
                        initial_date, initial_weight, initial_before_weight, initial_memo);
            }
        });


        // 書かなくてもよい(読みだしのため)
        /*
        Button readButton = findViewById(R.id.button_read);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
         */
    }


    private void readData() {
        if (helper == null) {
            helper = new InitialTestOpenHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }
        Log.d("debug", "**********Cursor");

        Cursor cursor = db.query(
                "initial_db",
                new String[]{"morning_blob", "lunch_blob", "dinner_blob", "snack_blob", "initial_date", "initial_weight", "initial_ratio", "initial_memo"},
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

    private void insertData(SQLiteDatabase db, byte[] morning, byte[] lunch, byte[] dinner, byte[] snack, String date, String weight, String ratio, String memo) {

        String str_i1 = new String(morning);
        String str_i2 = new String(lunch);
        String str_i3 = new String(dinner);
        String str_i4 = new String(snack);

        double double1 = Double.parseDouble(weight);
        double double2 = Double.parseDouble(ratio);


        ContentValues values = new ContentValues();
        values.put("morning_blob", str_i1);
        values.put("lunch_blob", str_i2);
        values.put("dinner_blob", str_i3);
        values.put("snack_blob", str_i4);
        values.put("initial_date", date);
        values.put("initial_weight", double1);
        values.put("initial_ratio", double2);
        values.put("initial_memo", memo);

        db.insert("initial_db", null, values);
    }

}