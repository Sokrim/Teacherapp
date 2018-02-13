package kit.c_learning.teacherapp;


import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class Activity_Question extends AppCompatActivity {

    Toolbar questionnairesToolbar;
    Button quickButton, yesNoButton, yesNoButtonComment;
  //  LinearLayout quick_layout;


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


        recyclerView = findViewById(R.id.recycler_view);

        questionList = new ArrayList<>();
        adapter = new QuestionAdapter(this,questionList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareQuestion();


        //When click on quick questionnaires button & create questionnaires

        quickButton = findViewById(R.id.quick_btn);
        quickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayQuickDialog();
            }
        });
       /* quick_layout = findViewById(R.id.quick_layout);
        if (quick_layout != null)
        {
            openQuickButton();
        }*/
    }


    private  void displayQuickDialog(){
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.test);
        d.show();
        Window window = d.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        yesNoButton = d.findViewById(R.id.yesNo);
        yesNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Question.this, YesNo_Chart.class);
                startActivity(i);
            }
        });
    }

    private void prepareQuestion() {
        Question a = new Question ("Now Public", "[Q] Yes/No", "2018/02/09 10:20", "Non-disclosure", "Anonymous", 2);
        questionList.add(a);

        a = new Question ("Non-disclosure", "[Q] Agree/Disagree", "2018/02/12 09:20", "Now Public", "", 0);
        questionList.add(a);

        a = new Question ("Non-disclosure", "[Q] Agree/Disagree C- learning is good", "2018/02/08 09:20", "Now Public", "", 0);
        questionList.add(a);

        a = new Question ("Now Public", "[Q] Agree/Disagree*", "2018/02/08 06:10", "Now Public", "Registered", 0);
        questionList.add(a);

        a = new Question ("Now Public", "[Q] Agree/Disagree*", "2018/02/09 09:20", "Now Public", "Registered", 0);
        questionList.add(a);
        a = new Question ("Now Public", "[Q] Four Choices*", "2018/02/08 09:20", "Now Public", "Registered", 0);
        questionList.add(a);
        adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /*  private void closeQuickButton() {
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

                //Yeo No button
                Button myYeoNoButton = findViewById(R.id.yesNo);
                myYeoNoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Activity_Question.this,YesNo_Chart.class);
                        startActivity(intent);
                    }
                });

                Button myYeoNoCommentButton = findViewById(R.id.yesNoComment);
                myYeoNoCommentButton.setOnClickListener(this);

                closeQuickButton();
            }
        });
    }

*/

}

