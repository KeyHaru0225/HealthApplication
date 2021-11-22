package to.msn.wings.healthapplication;

/*
Copyright 2018 Philipp Jahoda

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific language governing permissions
and limitations under the License.
 */



import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;



public class GraphFragment<view> extends AppCompatActivity {

    private ImageButton mImageView_calendar;
    private ImageButton mImageView_graph;
    private ImageButton mImageView_food;
    private ImageButton mImageView_exercise;

    private LineChart mChart;
    private boolean flg = true;

    private InitialTestOpenHelper helper;
    private SQLiteDatabase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);



        // イメージボダンを設定
        mImageView_calendar = (ImageButton) findViewById(R.id.imageView_calendar);
        mImageView_graph = (ImageButton) findViewById(R.id.imageView_graph);
        mImageView_food = (ImageButton) findViewById(R.id.imageView_food);
        mImageView_exercise = (ImageButton) findViewById(R.id.imageView_exercise);


        // 各画面へ遷移
        mImageView_calendar.setOnClickListener(view -> {
            if (flg) {
                Intent intent_c = new Intent(getApplication(), calendarFragment.class);
                startActivity(intent_c);
            }
        });
        mImageView_graph.setOnClickListener(view -> {
            if (flg) {
                Intent intent_g = new Intent(getApplication(), GraphFragment.class);
                startActivity(intent_g);
            }
        });
        mImageView_food.setOnClickListener(view -> {
            if (flg) {
                Intent intent_f = new Intent(getApplication(), FoodList.class);
                startActivity(intent_f);
            }
        });
        mImageView_exercise.setOnClickListener(view -> {
            if (flg) {
                Intent intent_e = new Intent(getApplication(), TaskList.class);
                startActivity(intent_e);
            }
        });



        mChart = findViewById(R.id.line_chart);

        // Grid背景色
        mChart.setDrawGridBackground(true);

        // no description text
        mChart.getDescription().setEnabled(true);

        // Grid縦軸を破線
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();

        // Y軸最大最小設定
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(30f);

        // Grid横軸を破線
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);

        // 右側の目盛り
        mChart.getAxisRight().setEnabled(false);

        // add data
        setData();

        mChart.animateX(2500);
        //mChart.invalidate();

        // dont forget to refresh the drawing
        // mChart.invalidate();
    }



    private void readData() {
        if(helper == null) {
            helper = new InitialTestOpenHelper(getApplicationContext());
        }

        if(db == null) {
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "initial_db",
                new String[] {"initial_date", "initial_weight"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            str.append(cursor.getString(0));
            str.append(cursor.getDouble(1));
            cursor.moveToNext();
        }
        cursor.close();

        Log.d("debug","**********"+str.toString());
        // textView.setText(str.toString());
    }

    private void setData () {

        // データベース参照
        Cursor c = db.query(
                "initial_db",
                new String[] {"initial_date", "initial_weight"},
                "id=?", new String[]{"id=5000"},
                null,
                null,
                null,
                null
        );
        try{
            if (c.moveToNext()) {
                String initial_date = c.getString(c.getColumnIndex("initial_date"));
                Double initial_weight = c.getDouble(c.getColumnIndex("initial_weight"));
            }
        } finally {
            c.close();
        }

        // Entry()を使ってLineDataSetに設定できる形に変更してarrayを新しく作成
        int initial_weight[];

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < initial_weight.length; i++) {
            values.add(new Entry(i,initial_weight[i], null, null));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {

            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet");

            set1.setDrawIcons(false);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(0f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set1.setFillColor(Color.BLUE);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData lineData = new LineData(dataSets);

            // set data
            mChart.setData(lineData);
        }
    }
}

