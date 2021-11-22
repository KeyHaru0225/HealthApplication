package to.msn.wings.healthapplication;




import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.print.PrintManager;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;

public class SettingData extends AppCompatActivity {

    private Button mSetting_button_data1;
    private Button mSetting_data_button2;

    private PrintManager printManager;
    private Object PrintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_data);

        mSetting_button_data1 = (Button) findViewById(R.id.setting_button_data1);
        mSetting_data_button2 = (Button) findViewById(R.id.setting_data_button2);


        // 画面遷移 前に戻る
        mSetting_button_data1.setOnClickListener(v -> {
            Intent intent_d1 = new Intent(getApplication(), SettingMain.class);
            startActivity(intent_d1);
        });


        mSetting_data_button2.setOnClickListener(v -> {
            printWindow();{

            }
        });
    }

    private void printWindow() {
        View view = getWindow().getDecorView();
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        PrintHelper printHelper = new PrintHelper(this);
        printHelper.setColorMode(PrintHelper.COLOR_MODE_COLOR);
        printHelper.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        printHelper.printBitmap("view", bitmap);
    }
}
