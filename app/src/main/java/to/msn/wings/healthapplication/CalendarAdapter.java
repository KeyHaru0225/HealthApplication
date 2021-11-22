package to.msn.wings.healthapplication;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.AbsListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;




public class CalendarAdapter extends BaseAdapter {

    private List<Date> dateArray = new ArrayList();
    private Context mContext;
    public jp.co.apps.workout.calendarsample.DateManager mDateManager;
    private LayoutInflater mLayoutInflater;

    private boolean flg = true;

    //カスタムセルを拡張したらここでWigetを定義
    private static class ViewHolder {
        public TextView dateText;
    }

    // private ImageButton mImageView_calendar;
    // private ImageButton mImageView_graph;
    // private ImageButton mImageView_food;
    // private ImageButton mImageView_exercise;



    //protected void onCreate(Bundle savedInstanceState) {
    //  super.onCreate(savedInstanceState);
    //  setContentView(R.layout.calendar);

    // イメージボタンを設定
    // mImageView_calendar = (ImageButton) findViewById(R.id.imageView_calendar);
    // mImageView_graph = (ImageButton) findViewById(R.id.imageView_graph);
    // mImageView_food = (ImageButton) findViewById(R.id.imageView_food);
    // mImageView_exercise = (ImageButton) findViewById(R.id.imageView_exercise);

        /* 各画面へ遷移
        mImageView_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), calendarFragment.class);
                startActivity(intent);
            }
        });

        mImageView_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), GraphFragment.class);
                startActivity(intent);
            }
        });

        mImageView_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), FoodList.class);
                startActivity(intent);
            }
        });

        mImageView_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TaskList.class);
                startActivity(intent);
            }
        }); */
    //}


    public CalendarAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDateManager = new jp.co.apps.workout.calendarsample.DateManager();
        dateArray = mDateManager.getDays();
    }

    @Override
    public int getCount () {
        return dateArray.size();
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent){
        CalendarAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.calendar_cell, null);
            holder = new ViewHolder();
            holder.dateText = convertView.findViewById(R.id.dateText);
            convertView.setTag(holder);
        } else {
            holder = (CalendarAdapter.ViewHolder) convertView.getTag();
        }


        // セルのサイズを指定
        float dp = mContext.getResources().getDisplayMetrics().density;
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(parent.getWidth() / 7 - (int) dp, (parent.getHeight() - (int) dp * mDateManager.getWeeks()) / mDateManager.getWeeks());
        convertView.setLayoutParams(params);

        // 日付のみ表示させる
        SimpleDateFormat dateFormat = new SimpleDateFormat("d", Locale.US);
        holder.dateText.setText(dateFormat.format(dateArray.get(position)));

        // 当月以外のセルをグレーアウト
        if (mDateManager.isCurrentMonth(dateArray.get(position))) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.LTGRAY);
        }

        // 日曜日を赤、土曜日を青に
        int colorId;
        switch (mDateManager.getDayOfWeek(dateArray.get(position))) {
            case 1:
                colorId = Color.RED;
                break;
            case 7:
                colorId = Color.BLUE;
                break;

            default:
                colorId = Color.BLACK;
                break;
        }
        holder.dateText.setTextColor(colorId);

        return convertView;
    }

    // 開始曜日の変更


    @Override
    public long getItemId ( int position){
        return 0;
    }

    @Override
    public Object getItem ( int position){
        return null;
    }

    //表示月を取得
    public String getTitle () {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM", Locale.US);
        return format.format(mDateManager.mCalendar.getTime());
    }

    //翌月表示
    public void nextMonth () {
        mDateManager.nextMonth();
        dateArray = mDateManager.getDays();
        this.notifyDataSetChanged();
    }

    //前月表示
    public void prevMonth () {
        mDateManager.prevMonth();
        dateArray = mDateManager.getDays();
        this.notifyDataSetChanged();
    }
}


