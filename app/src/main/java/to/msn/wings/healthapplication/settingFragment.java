package to.msn.wings.healthapplication;



import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import com.google.android.material.chip.Chip;

import androidx.appcompat.app.AppCompatActivity;



public class settingFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting); //TODO 修正

        mSetting = (TextView) findViewById(R.id.setting); //一般設定
        mSetting_button = (Button) findViewById(R.id.setting_button); // < ボタン
        mSetting_button.setOnClickListener(view -> finish()); // 身長体重(初期設定画面)に戻る

        mLow_fat_percentage = (TextView) findViewById(R.id.low_fat_percentage); //低脂肪率の記録
        mPass_lock = (TextView) findViewById(R.id.pass_lock); //パスワードロック
        mPreventing_forgetting_to_enter = (TextView) findViewById(R.id.preventing_forgetting_to_enter); //入力忘れ防止機能
        mText_preventing_forgetting_to_enter = (TextView) findViewById(R.id.text_preventing_forgetting_to_enter); //入力忘れの説明
        mSetting_completed = (Button) findViewById(R.id.setting_completed); //設定完了ボタン
        // helloFragmentへ遷移
        mSetting_completed.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), helloFragment.class);
            startActivity(intent);
        });

        mSetting_chip1 = (Chip) findViewById(R.id.setting_chip1); //丸ボタン１
        mSetting_chip2 = (Chip) findViewById(R.id.setting_chip2); //丸ボタン２
        mSetting_chip3 = (Chip) findViewById(R.id.setting_chip3); //丸ボタン３
    }

    private TextView mSetting;
    private Button mSetting_button;
    private TextView mLow_fat_percentage;
    private TextView mPass_lock;
    private TextView mPreventing_forgetting_to_enter;
    private TextView mText_preventing_forgetting_to_enter;
    private Button mSetting_completed;
    private Chip mSetting_chip1;
    private Chip mSetting_chip2;
    private Chip mSetting_chip3;


    // ボタンが押された時の処理
    public void setting_button_onClick(View view) {

    }

    public void setting_completed_onClick(View view) {

    }

    public void setting_chip1_onClick(View view) {

    }

    public void setting_chip2_onClick(View view) {

    }

    public void setting_chip3_onClick(View view) {

    }

}

