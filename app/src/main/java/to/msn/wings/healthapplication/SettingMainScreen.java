package to.msn.wings.healthapplication;



import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;


public class SettingMainScreen extends AppCompatActivity {
    private Button mSetting_main_screen_button1;
    private Button mSetting_main_screen_button2;
    private Button mSetting_main_screen_button3;
    private Button mSetting_main_screen_button4;
    private Chip mSetting_main_screen_chip1;
    private Chip mSetting_main_screen_chip2;
    private Chip mSetting_main_screen_chip3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO 全体的にまだレイアウトが完成していないから未着手
        setContentView(R.layout.setting_main_screen);

        mSetting_main_screen_button1 = (Button) findViewById(R.id.setting_main_screen_button1);
        mSetting_main_screen_button2 = (Button) findViewById(R.id.setting_main_screen_button2);
        mSetting_main_screen_button3 = (Button) findViewById(R.id.setting_main_screen_button3);
        mSetting_main_screen_button4 = (Button) findViewById(R.id.setting_main_screen_button4);
        mSetting_main_screen_chip1 = (Chip) findViewById(R.id.setting_main_screen_chip1);
        mSetting_main_screen_chip2 = (Chip) findViewById(R.id.setting_main_screen_chip2);
        mSetting_main_screen_chip3 = (Chip) findViewById(R.id.setting_main_screen_chip3);

        // 画面遷移 前に戻る
        mSetting_main_screen_button1.setOnClickListener(view -> {
            Intent intent_msc = new Intent(getApplication(), SettingMain.class);
            startActivity(intent_msc);
        });

        mSetting_main_screen_button2.setOnClickListener(view -> {
            Intent intent_msc = new Intent(getApplication(), SettingHeight.class);
            startActivity(intent_msc);
        });

        mSetting_main_screen_button3.setOnClickListener(view -> {
            Intent intent_msc = new Intent(getApplication(), SettingWeight.class);
            startActivity(intent_msc);
        });
        // 保留
        mSetting_main_screen_chip1.setOnClickListener(view -> {
            Intent intent_msc = new Intent(getApplication(), SettingMain.class);
            startActivity(intent_msc);
        });
        // 保留
        mSetting_main_screen_chip2.setOnClickListener(view -> {
            Intent intent_msc = new Intent(getApplication(), SettingMain.class);
            startActivity(intent_msc);
        });
        // 保留
        mSetting_main_screen_chip3.setOnClickListener(view -> {
            Intent intent_msc = new Intent(getApplication(), SettingMain.class);
            startActivity(intent_msc);
        });
        mSetting_main_screen_button4.setOnClickListener(view -> {
            Intent intent_msc = new Intent(getApplication(), SettingMainCalendar.class);
            startActivity(intent_msc);
        });

    }
}
