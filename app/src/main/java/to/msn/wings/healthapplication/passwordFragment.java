package to.msn.wings.healthapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class passwordFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);

        mPassword = (TextView) findViewById(R.id.password);  //
        mPassword_num1 = (EditText) findViewById(R.id.password_num1);
        mPassword_num2 = (EditText) findViewById(R.id.password_num2);
        mPassword_num3 = (EditText) findViewById(R.id.password_num3);
        mPassword_num4 = (EditText) findViewById(R.id.password_num4);
    }

    private TextView mPassword;
    private TextView mPassword_num1;
    private TextView mPassword_num2;
    private TextView mPassword_num3;
    private TextView mPassword_num4;
}

