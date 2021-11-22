package to.msn.wings.healthapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;



public class helloFragment extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello);
    }

    // 画面タップで遷移
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                // 画面遷移
                Intent intent_sf = new Intent(getApplication(), initial_screenFragment.class);
                startActivity(intent_sf);
                break;
        }
        return true;
    }
}
