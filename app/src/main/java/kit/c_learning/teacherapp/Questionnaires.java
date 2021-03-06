package kit.c_learning.teacherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.View;

import java.util.concurrent.ExecutionException;

/**
 * Created by sokrim on 2/2/2018.
 */

public class Questionnaires extends AppCompatActivity implements View.OnClickListener{

    Toolbar myCourseToolbar;
    private CardView questionCard, quizCard,attendance;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnnaires);
     //   Intent intent = getIntent();

        myCourseToolbar = findViewById(R.id.myCourse_toolbar);
        setSupportActionBar(myCourseToolbar);
        getSupportActionBar().setTitle("C programming");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        questionCard = findViewById(R.id.question_card);
        questionCard.setOnClickListener(this);

        quizCard = findViewById(R.id.quiz_card);
        quizCard.setOnClickListener(this);

        attendance = findViewById(R.id.attendanceTracking);
        attendance.setOnClickListener(this);


        ArrayMap<String, String> headers = new ArrayMap<>();
        ArrayMap<String, String> data = new ArrayMap<>();
        data.put("qb","qb04874738");
        data.put("com","1");
        data.put("mode","ALL");

        HttpRequestAsync myHttp = new HttpRequestAsync(headers,data);
        try {
            String text= myHttp.execute("https://kit.c-learning.jp/t/ajax/quest/Bent", "POST").get();
            System.out.println("=========================================123 " + text);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.question_card : intent = new Intent(this, Activity_Question.class);
                startActivity(intent);
                break;
            case R.id.quiz_card : intent = new Intent(this, DemoActivity.class);
                startActivity(intent);
                break;
            case R.id.attendanceTracking : intent = new Intent(this,Bar_Chart.class);
                startActivity(intent);

            default:break;

        }
    }

}
