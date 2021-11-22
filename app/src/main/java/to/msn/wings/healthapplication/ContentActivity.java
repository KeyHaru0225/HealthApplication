package to.msn.wings.healthapplication;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class ContentActivity extends AppCompatActivity {

    private String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        currentDate = intent.getStringExtra("date");
    }
}
