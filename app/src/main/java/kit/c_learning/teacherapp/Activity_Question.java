package kit.c_learning.teacherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Activity_Question extends AppCompatActivity {

    Toolbar questionnairesToolbar;
    Button quickButton, createButton;
    LinearLayout quick_layout;


    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<Question> questionList;
    String[] questionTitle = null,publishDate=null;
    JSONObject jsonObject =null,success=null;

    @SuppressLint("LongLogTag")
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
        adapter = new QuestionAdapter(questionList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        //request api

        android.util.ArrayMap<String, String> headers = new android.util.ArrayMap<>();
        android.util.ArrayMap<String, String> data = new android.util.ArrayMap<>();

        HttpRequestAsync myHttp = new HttpRequestAsync(headers);
        try {
            String text= myHttp.execute("https://kit.c-learning.jp/t/ajax/quest/Question", "GET").get();
            System.out.println("=========================================123 " + text);

            jsonObject = new JSONObject(text);
            Log.d("json object----------------",jsonObject.toString());

            JSONArray jsonArray = jsonObject.getJSONArray("data");
            Log.d("mer json array¥--------------",jsonArray.toString());

            questionTitle = new String[jsonArray.length()];
            publishDate = new String[jsonArray.length()];
            Log.d("langht of the value=========", String.valueOf(questionTitle.length));

            for(int i = 0; i< jsonArray.length(); i++){
                JSONObject row = jsonArray.getJSONObject(i);
                questionTitle[i] = row.getString("qbTitle");
                publishDate[i] =row.getString("qbDate");
                Log.d("string value ---------------", questionTitle[i]);
                Log.d("string value ---------------", publishDate[i]);
                Log.d("lenght of value-------------", String.valueOf(questionTitle[i].length()));
                prepareQuestion(questionTitle[i],publishDate[i]);

//                for(int j =0;j<value[i].length();j++){
//                    Log.d("value of length^^^^^^^^", String.valueOf(value.length));
//                    prepareQuestion(value[i]);
//                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e("eerorror1---------",e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.e("eerorror2---------",e.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("eerorror3---------",e.toString());
        }

        //When click on quick questionnaires button & create questionnaires
        createButton = findViewById(R.id.create_btn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Question.this, Questionnaires.class);
                startActivity(intent);

            }
        });

        quickButton = findViewById(R.id.quick_btn);
        quick_layout = findViewById(R.id.quick_layout);
        if (quick_layout != null) {
            openQuickButton();
        }
    }

    private void prepareQuestion(String questionTitle,String publishDate) {
        Question a = new Question ("Now Public", questionTitle, publishDate, "Non-disclosure", "Anonymous", 2);
        questionList.add(a);
        adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
               myYeoNoCommentButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent = new Intent(Activity_Question.this,Questionnaires.class);
                       startActivity(intent);
                       finish();
                   }
               });
                closeQuickButton();
            }
        });
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

}

