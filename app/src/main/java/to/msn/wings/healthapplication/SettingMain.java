package to.msn.wings.healthapplication;


import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


public class SettingMain extends AppCompatActivity {
    private Button mSetting_main_btn1;
    private Button mSetting_main_btn2;
    private Button mSetting_main_btn3;
    private Button mSetting_main_btn4;
    private Button mSetting_main_btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);

        mSetting_main_btn1 = (Button) findViewById(R.id.setting_main_btn1);
        mSetting_main_btn2 = (Button) findViewById(R.id.setting_main_btn2);
        mSetting_main_btn3 = (Button) findViewById(R.id.setting_main_btn3);
        mSetting_main_btn4 = (Button) findViewById(R.id.setting_main_btn4);
        mSetting_main_btn5 = (Button) findViewById(R.id.setting_main_btn5);

        // 画面遷移
        mSetting_main_btn1.setOnClickListener(view -> {
            Intent intent_smb1 = new Intent(getApplication(), SettingMainScreen.class);
            startActivity(intent_smb1);
        });
        mSetting_main_btn2.setOnClickListener(view -> {
            Intent intent_smb2 = new Intent(getApplication(), SettingData.class);
            startActivity(intent_smb2);
        });
        mSetting_main_btn3.setOnClickListener(view -> {
            Intent intent_smb3 = new Intent(getApplication(), SettingHelp.class);
            startActivity(intent_smb3);
        });
        mSetting_main_btn4.setOnClickListener(view -> {
            Intent intent_smb4 = new Intent(getApplication(), SettingInformation.class);
            startActivity(intent_smb4);
        });
        mSetting_main_btn5.setOnClickListener(view -> {
            Intent intent_smb5 = new Intent(getApplication(), SettingDelete.class);
            startActivity(intent_smb5);
        });

    }
}

