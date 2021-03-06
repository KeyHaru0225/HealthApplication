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

public class FoodCamera extends AppCompatActivity {

    private Uri m_uri;
    private static final int REQUEST_CHOOSER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
    }

    class Views {
        private void setViews() {
            Button morning_button = (Button) findViewById(R.id.food_morning_btn);
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
        private void setViews() {
            Button lunch_button = (Button) findViewById(R.id.food_lunch_btn);
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
        private void setViews() {
            Button dinner_button = (Button) findViewById(R.id.food_dinner_btn);
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
        private void setViews() {
            Button snack_button = (Button) findViewById(R.id.food_snack_btn);
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

        // ??????????????????Intent?????????
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, photoName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        m_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

        // ??????????????????Intent??????
        Intent intentGallery;
        if (Build.VERSION.SDK_INT < 19) {
            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
            intentGallery.setType("image/*");
        } else {
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
            intentGallery.setType("image/jpeg");
        }
        Intent intent = Intent.createChooser(intentCamera, "???????????????");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {intentGallery});
        startActivityForResult(intent, REQUEST_CHOOSER);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CHOOSER) {
            if(resultCode != RESULT_OK) {
                // ??????????????????
                return;
            }

            Uri resultUri = (data != null ? data.getData() : m_uri);

            if(resultUri == null) {
                // ????????????
                return;
            }

            // ???????????????????????????????????????
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/jpeg"},
                    null
            );

            // ???????????????
            ImageView imageView = (ImageView) findViewById(R.id.food_morning_img);
            imageView.setImageURI(resultUri);

            ImageView imageView2 = (ImageView) findViewById(R.id.food_lunch_img);
            imageView2.setImageURI(resultUri);

            ImageView imageView3 = (ImageView) findViewById(R.id.food_lunch_img);
            imageView3.setImageURI(resultUri);

            ImageView imageView4 = (ImageView) findViewById(R.id.food_lunch_img);
            imageView4.setImageURI(resultUri);
        }
    }
}
