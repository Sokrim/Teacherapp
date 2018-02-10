package kit.c_learning.teacherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    private PieChart pieChart;
    private PieData pieData;
    private BarChart barChart;
    private BarData barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

//        pieChart = findViewById(R.id.piechart1);
//        pieData = new PieData(getXvalue(),getYvalue());
//        pieChart.setData(pieData);

        barChart = findViewById(R.id.piechart1);
        barChart.setData(barData);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
//        BarDataSet dataSet = new BarDataSet(entries,"student");
//
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");

        BarData barData = new BarData(labels, dataset);
        barChart.setData(barData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);


    }

//    private List<IBarDataSet> getYvalue() {
//        BarDataSet barDataSet;
//
//        ArrayList<Entry> entries = new ArrayList<>();
//        Entry entry = new Entry(100,0);
//        entries.add(entry);
//        barDataSet = new BarDataSet(entries,"yes");
//
//        return barDataSet;
//    }
//
//    private ArrayList<String> getXvalue() {
//
//        ArrayList<String> xvalue = new ArrayList<>();
//        xvalue.add("student");
//        return xvalue;
//    }


//    private IPieDataSet getYvalue() {
//        PieDataSet pieDataSet ;
//
//        ArrayList<Entry> entries = new ArrayList<>();
//        Entry entry = new Entry(100,0);
//        entries.add(entry);
//
//        pieDataSet = new PieDataSet(entries,"yes");
//        return pieDataSet;
//    }
//
//    private List<String> getXvalue() {
//        ArrayList<String> xvalue = new ArrayList<>();
//        xvalue.add("Student");
//        return xvalue;
//    }

}
