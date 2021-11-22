package to.msn.wings.healthapplication;



import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



public class InitialScreenCamera extends AppCompatActivity {

    private Uri m_uri;
    private static final int REQUEST_CHOOSER = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);
        Views[] views = {new Views(), new Lunch(), new Dinner(), new Snack()};

        for (Views v : views) {
            v.setViews();
        }
    }


    class Views {
        public void setViews() {
            Button morning_button = (Button)findViewById(R.id.morning_button);
            morning_button.setOnClickListener(morning_button_onClick);
        }
        private View.OnClickListener morning_button_onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGallery();
            }
        };
    }


    class Lunch extends Views {
        @Override
        public void setViews() {
            Button lunch_button = (Button)findViewById(R.id.lunch_button);
            lunch_button.setOnClickListener(lunch_button_onClick);
        }
        private View.OnClickListener lunch_button_onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGallery();
            }
        };
    }


    class Dinner extends Views {
        @Override
        public void setViews() {
            Button dinner_button = (Button)findViewById(R.id.dinner_button);
            dinner_button.setOnClickListener(dinner_button_onClick);
        }
        private View.OnClickListener dinner_button_onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGallery();
            }
        };
    }


    class Snack extends Views {
        @Override
        public void setViews() {
            Button snack_button = (Button)findViewById(R.id.snack_button);
            snack_button.setOnClickListener(snack_button_onClick);
        }
        private View.OnClickListener snack_button_onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGallery();
            }
        };
    }



    private void showGallery() {

        // カメラの起動Intentの用意
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, photoName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        m_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

        // ギャラリー用Intent作成
        Intent intentGallery;
        if (Build.VERSION.SDK_INT < 19) {
            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
            intentGallery.setType("image/*");
        } else {
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
            intentGallery.setType("image/jpeg");
        }
        Intent intent = Intent.createChooser(intentCamera, "画像の選択");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {intentGallery});
        startActivityForResult(intent, REQUEST_CHOOSER);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CHOOSER) {
            if(resultCode != RESULT_OK) {
                // キャンセル時
                return;
            }

            Uri resultUri = (data != null ? data.getData() : m_uri);

            if(resultUri == null) {
                // 取得失敗
                return;
            }

            // ギャラリーへスキャンを促す
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/jpeg"},
                    null
            );



            // 画像を設定
            ImageView imageView = (ImageView) findViewById(R.id.image_view_morning);
            imageView.setImageURI(resultUri);

            ImageView imageView2 = (ImageView) findViewById(R.id.image_view_lunch);
            imageView2.setImageURI(resultUri);

            ImageView imageView3 = (ImageView) findViewById(R.id.image_view_evening);
            imageView3.setImageURI(resultUri);

            ImageView imageView4 = (ImageView) findViewById(R.id.image_view_snack);
            imageView4.setImageURI(resultUri);
        }
    }
}
