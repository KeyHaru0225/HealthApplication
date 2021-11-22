package to.msn.wings.healthapplication;



import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


public class SettingCalendar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO 全体的にまだレイアウトが完成していないから未着手
        setContentView(R.layout.setting_main_screen_calendar);

        /*
        mSetting_main_screen_calendar_button1 = (Button) findViewById(R.id.setting_main_screen_calendar_button1);

        // 画面遷移 前に戻る
        mSetting_main_screen_calendar_button1.setOnClickListener(view -> {
            Intent intent_mib = new Intent(getApplication(), SettingMainScreen.class);
            startActivity(intent_mib);
        });

         */

    }
}

