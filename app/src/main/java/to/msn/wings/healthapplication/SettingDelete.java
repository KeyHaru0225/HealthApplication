package to.msn.wings.healthapplication;



import android.app.ActivityManager;
import android.app.Service;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;



public class SettingDelete extends AppCompatActivity {

    private Button mSetting_main_screen_height_button1;
    private Button mSetting_data_button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 全体的にまだレイアウトが完成していないから未着手
        setContentView(R.layout.setting_delete);

        mSetting_main_screen_height_button1 = (Button) findViewById(R.id.setting_main_screen_height_button1);
        mSetting_data_button2 = (Button) findViewById(R.id.setting_data_button2);



        // 全データの削除
        /*
        mSetting_data_button2.setOnClickListener(v -> {
            ActivityManager am = (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
            am.clearApplicationUserData();
        });
         */




        // 画面遷移 前に戻る
        mSetting_main_screen_height_button1.setOnClickListener(v -> {
            Intent intent_msh = new Intent(getApplication(), SettingMainScreen.class);
            startActivity(intent_msh);
        });


    }
}

