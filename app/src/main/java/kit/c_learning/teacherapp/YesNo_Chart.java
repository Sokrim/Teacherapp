package kit.c_learning.teacherapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * Created by sokrim on 2/9/2018.
 */

public class YesNo_Chart extends AppCompatActivity {

    EditText updateQuestion;

    private PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yesno_chart);

        Toolbar resultToolbar = findViewById(R.id.totalResult_toolbar);
        setSupportActionBar(resultToolbar);
        getSupportActionBar().setTitle("Total Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner displaySpinner = findViewById(R.id.display_Questionnaires);
        Spinner guestSpinner = findViewById(R.id.guest);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(YesNo_Chart.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.displayQuestionnaires));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        displaySpinner.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(YesNo_Chart.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.guest));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        guestSpinner.setAdapter(myAdapter2);

        pieChart = findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.15f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValue = new ArrayList<>();
        yValue.add(new PieEntry(75f, "YES"));
        yValue.add(new PieEntry(25f, "NO"));

        pieChart.animateY(1000, Easing.EasingOption.EaseInCubic);

        PieDataSet dataSet = new PieDataSet(yValue, "");
        dataSet.setSliceSpace(3f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);

        updateQuestion = findViewById(R.id.updateQuestion);
        updateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion.setCursorVisible(true);
                updateQuestion.setHint("");
            }
        });

    }
}
