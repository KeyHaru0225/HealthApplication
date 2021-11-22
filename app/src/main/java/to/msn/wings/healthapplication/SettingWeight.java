package to.msn.wings.healthapplication;



import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


public class SettingWeight extends AppCompatActivity {

    private Button mSetting_main_screen_weight_button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        mSetting_main_screen_weight_button1 = (Button) findViewById(R.id.setting_main_screen_weight_button1);
        //TODO  OK CANCEL入れる？

        // 画面遷移 前に戻る
        mSetting_main_screen_weight_button1.setOnClickListener(view -> {
            Intent intent_msw = new Intent(getApplication(), SettingMainScreen.class);
            startActivity(intent_msw);
        });
        // TODO OK CANCEL入れる？

    }
}
