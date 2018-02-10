package kit.c_learning.teacherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sokrim on 2/9/2018.
 */

public class YesNo_Chart extends AppCompatActivity {
    private PieChart pieChart;
    private PieData pieData;
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

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(YesNo_Chart.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.displayQuestionnaires));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        displaySpinner.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(YesNo_Chart.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.guest));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        guestSpinner.setAdapter(myAdapter2);

        pieChart = findViewById(R.id.piechart);
        pieData = new PieData(getXvalue(),getYvalue());
        pieChart.setData(pieData);


    }

    private IPieDataSet getYvalue() {
        PieDataSet pieDataSet ;

        ArrayList<Entry> entries = new ArrayList<>();
        Entry entry = new Entry(100,0);
        entries.add(entry);

        pieDataSet = new PieDataSet(entries,"yes");
        return pieDataSet;
    }

    private List<String> getXvalue() {
        ArrayList<String> xvalue = new ArrayList<>();
        xvalue.add("Student");
        return xvalue;
    }
}
