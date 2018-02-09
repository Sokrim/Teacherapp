package kit.c_learning.teacherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;


/**
 * Created by sokrim on 2/9/2018.
 */

public class YesNo_Chart extends AppCompatActivity {
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
    }
}
