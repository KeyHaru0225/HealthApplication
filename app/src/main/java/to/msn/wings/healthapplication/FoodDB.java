package to.msn.wings.healthapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

public class FoodDB extends AppCompatActivity {

    private TextView mFood_date;
    private ImageView mImage_view_morning, mImage_view_lunch, mImage_view_dinner, mImage_view_snack;
    private EditText mMemo_one, mMemo_two, mMemo_three, mMemo_four;

    private FoodTestOpenHelper helper;
    private SQLiteDatabase db;
    private Button mFood_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);

        mFood_date = (TextView) findViewById(R.id.food_date);
        mImage_view_morning = (ImageView) findViewById(R.id.food_morning_img);
        mImage_view_lunch = (ImageView) findViewById(R.id.food_lunch_img);
        mImage_view_dinner = (ImageView) findViewById(R.id.food_dinner_img);
        mImage_view_snack = (ImageView) findViewById(R.id.food_snack_img);
        mMemo_one = (EditText) findViewById(R.id.food_morning_txt);
        mMemo_two = (EditText) findViewById(R.id.food_lunch_txt);
        mMemo_three = (EditText) findViewById(R.id.food_dinner_txt);
        mMemo_four = (EditText) findViewById(R.id.food_snack_txt);

        mFood_btn = (Button) findViewById(R.id.food_btn);

        final int[]  baseIds = new int[] {
                R.id.food_morning_img,
                R.id.food_lunch_img,
                R.id.food_dinner_img,
                R.id.food_snack_img
        };



        mFood_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (helper == null) {
                    helper = new FoodTestOpenHelper(getApplication());
                }

                if (db == null) {
                    db = helper.getWritableDatabase();
                }

                // よびだした値を文字に変換、格納
                String food_date = mFood_date.getText().toString();
                String food_morning_txt = mMemo_one.getText().toString();
                String food_lunch_txt = mMemo_two.getText().toString();
                String food_dinner_txt = mMemo_three.getText().toString();
                String food_snack_txt = mMemo_four.getText().toString();

                // DB格納可能にするため drawble→ bitmap→ byte[] 変換
                @SuppressLint("UseCompatLoadingForDrawables") Drawable drawableM = getResources().getDrawable(baseIds[0]);
                Bitmap bitmapM = ((BitmapDrawable) drawableM).getBitmap();
                ByteArrayOutputStream bosM = new ByteArrayOutputStream();
                bitmapM.compress(Bitmap.CompressFormat.JPEG, 100, bosM);
                byte[] bytemorning = bosM.toByteArray();

                @SuppressLint("UseCompatLoadingForDrawables") Drawable drawableL = getResources().getDrawable(baseIds[1]);
                Bitmap bitmapL = ((BitmapDrawable) drawableL).getBitmap();
                ByteArrayOutputStream bosL = new ByteArrayOutputStream();
                bitmapL.compress(Bitmap.CompressFormat.JPEG, 100, bosL);
                byte[] bytelunch = bosL.toByteArray();

                @SuppressLint("UseCompatLoadingForDrawables") Drawable drawableE = getResources().getDrawable(baseIds[2]);
                Bitmap bitmapE = ((BitmapDrawable) drawableE).getBitmap();
                ByteArrayOutputStream bosE = new ByteArrayOutputStream();
                bitmapE.compress(Bitmap.CompressFormat.JPEG, 100, bosE);
                byte[] bytedinner = bosE.toByteArray();

                @SuppressLint("UseCompatLoadingForDrawables") Drawable drawableS = getResources().getDrawable(baseIds[3]);
                Bitmap bitmapS = ((BitmapDrawable) drawableS).getBitmap();
                ByteArrayOutputStream bosS = new ByteArrayOutputStream();
                bitmapS.compress(Bitmap.CompressFormat.JPEG, 100, bosS);
                byte[] bytesnack = bosS.toByteArray();

                insertData(db, food_date, bytemorning, bytelunch, bytedinner, bytesnack,
                        food_morning_txt, food_lunch_txt, food_dinner_txt, food_snack_txt);
            }
        });
    }

    private void readData() {
        if (helper == null) {
            helper = new FoodTestOpenHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }
        Log.d("debug", "**********Cursor");

        Cursor cursor = db.query(
                "food_db",
                new String[]{"food_date", "morning_blob", "lunch_blob", "dinner_blob", "snack_blob", "txt_one", "txt_two", "txt_three", "txt_four"},
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


    private void insertData(SQLiteDatabase db, String fdate, byte[] morning, byte[] lunch, byte[] dinner, byte[] snack, String txtone, String txttwo, String txtthree, String txtfour) {

        String str_f1 = new String(morning);
        String str_f2 = new String(lunch);
        String str_f3 = new String(dinner);
        String str_f4 = new String(snack);

        ContentValues values = new ContentValues();
        values.put("food_date", fdate);
        values.put("morning_blob", str_f1);
        values.put("lunch_blob", str_f2);
        values.put("dinner_blob", str_f3);
        values.put("snack_blob", str_f4);
        values.put("txt_one", txtone);
        values.put("txt_two", txttwo);
        values.put("txt_three", txtthree);
        values.put("txt_four", txtfour);

        db.insert("food_db", null, values);
    }
}

