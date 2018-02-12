package kit.c_learning.teacherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class BarChart extends AppCompatActivity {
    com.github.mikephil.charting.charts.BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        barChart = findViewById(R.id.barchart);
        barChart.setPinchZoom(false);
        barChart.setVisibleYRangeMaximum(3, YAxis.AxisDependency.LEFT);
        barChart.setDrawValueAboveBar(false);
        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setEnabled(false);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setSpaceTop(0);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);
        rightAxis.setSpaceTop(0);
        rightAxis.setAxisMinimum(0f);

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);

        setData(new int[] {2, 6, 1});
    }

    private void setData(int[] size) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i=0; i<size.length; i++){
            yVals1.add(new BarEntry(i, size[i]));
        }
        BarDataSet set1;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "All Display");

            set1.setDrawIcons(false);

            set1.setColors(rgb("#0277BD"),
                    rgb("#00C853"),
                    rgb("#CC0000"));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(0.0f);
            data.setBarWidth(1.0f);

            barChart.setData(data);
        }
    }
}
