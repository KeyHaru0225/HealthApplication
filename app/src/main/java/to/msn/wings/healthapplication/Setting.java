package to.msn.wings.healthapplication;



import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;



public class Setting extends AppCompatActivity {

    private Button mSetting_button;
    private Button mSetting_completed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        mSetting_button = (Button) findViewById(R.id.setting_button);
        mSetting_completed = (Button) findViewById(R.id.setting_completed);

        // 画面遷移 戻る
        mSetting_button.setOnClickListener(v -> {
            Intent intent_sb = new Intent(getApplication(), height_weightFragment.class);
            startActivity(intent_sb);
        });
        // 画面遷移 進む
        mSetting_completed.setOnClickListener(v -> {
            Intent intent_sc = new Intent(getApplication(), helloFragment.class);
            startActivity(intent_sc);
        });
    }

}
