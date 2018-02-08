package kit.c_learning.teacherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;


public class Activity_Question extends AppCompatActivity {

    Toolbar questionnairesToolbar;
    Button quickButton, createButton;
    LinearLayout quick_layout;


    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionnairesToolbar = findViewById(R.id.questionnaires_toolbar);
        setSupportActionBar(questionnairesToolbar);
        getSupportActionBar().setTitle("Questionnaires");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createButton = findViewById(R.id.create_btn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Question.this,Questionnaires.class);
                startActivity(intent);

            }
        });

        quickButton = findViewById(R.id.quick_btn);
        quick_layout = findViewById(R.id.quick_layout);
        if (quick_layout != null)
        {
            openQuickButton();
        }
    }
    private void closeQuickButton() {
        quickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quick_layout.setVisibility(View.GONE);
                openQuickButton();
            }
        });
    }

    private void openQuickButton() {
        quickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quick_layout.setVisibility(View.VISIBLE);
                closeQuickButton();
            }
        });
    }
}

