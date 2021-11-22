package to.msn.wings.healthapplication;



import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


public class SettingHeight extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main_screen_height);

        //mSetting_main_screen_height_button1 = (Button) findViewById(R.id.setting_main_screen_height_button1);
        //TODO  OK CANCEL入れる？

        // 画面遷移 前に戻る
        /*
        mSetting_main_screen_height_button1.setOnClickListener(view -> {
            Intent intent_msh = new Intent(getApplication(), SettingMainScreen.class);
            startActivity(intent_msh);
        });
         */
        // TODO OK CANCEL入れる？

    }
}

