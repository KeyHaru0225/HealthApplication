package to.msn.wings.healthapplication;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import java.math.BigDecimal;


import androidx.appcompat.app.AppCompatActivity;



public class height_weightFragment extends AppCompatActivity {

    private EditText mHeight;
    private EditText mWeight;
    private TextView mText_bmi;
    private Button mHeight_weight_button;
    private BigDecimal bd;
    private TextView mFirst_height_weight;
    private TextView mTextView2;


    private String  caution = "数値を入力してください";
    private String a = "";
    private String b = "";
    private String mH = mHeight.getText().toString();
    private String mW = mWeight.getText().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.height_weight);

        mHeight = (EditText) findViewById(R.id.height);                            // ユーザーが入力するフィールド(身長)
        mWeight = (EditText) findViewById(R.id.weight);                            // ユーザーが入力するフィールド(体重)
        mText_bmi = (TextView) findViewById(R.id.text_bmi);                        // BMI値
        mHeight_weight_button = (Button) findViewById(R.id.height_weight_button);  // ボタン
        mFirst_height_weight = (TextView) findViewById(R.id.first_height_weight);
        mTextView2 = (TextView) findViewById(R.id.textView2);

        String str1 = mHeight.getText().toString();
        String str2 = mWeight.getText().toString();
        Double mH = Double.parseDouble(str1);
        Double mW = Double.parseDouble(str2);

        // 画面遷移
        mHeight_weight_button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), settingFragment.class);
            startActivity(intent);
        });


        // 身長・体重欄に値が入力されているか否かの判定、及びBMI値の出力
        Double mT = mW / mH / mH;  // BMI=体重÷身長÷身長
        // 結果を小数点第1位までに丸める(丸めたかった)
        String str3 = String.valueOf(mT);
        mText_bmi.setText(str3);
        //Double bd = new BigDecimal(mT);
        //Double bd = bd.setScale(1, RoundingMode.HALF_UP);


        // 数値入力がされているか否かの判定
        if(a.equals(mH) || b.equals(mW) ) {
            Toast toast = Toast.makeText (
                    this, caution, Toast.LENGTH_LONG);
            toast.show();
        } else {
            mText_bmi.setText(String.valueOf(bd));
        }

    }
}
