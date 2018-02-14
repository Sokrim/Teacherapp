package kit.c_learning.teacherapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
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
    Button quickButton,
            yesNoButton, yesNoButtonComment,
            agreeDisagree,agreeDisagreeComment,
            twoChoice,twoChoiceComment,
            threeChoice,threeChoiceComment,
            fourChoice,fourChoiceComment,fiveChoice,
            fiveChoiceComment,commentOnly;


    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<Question> questionList;
    String[] questionTitle = null,publishDate=null,qbID = null;
    public static String[] questionType = null, questionID = null, questionComment = null, qbTitle = null;
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
        adapter = new QuestionAdapter(this,questionList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //request api
        requestApi();

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

    //check internet connection
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private  void displayQuickDialog(){
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.test);

        d.show();
        Window window = d.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


      /*  yesNoButton = d.findViewById(R.id.yesNo);
        yesNoButtonComment = d.findViewById(R.id.yesNoComment);
        agreeDisagree = d.findViewById(R.id.agreeDisagree);
        agreeDisagreeComment = d.findViewById(R.id.agreeDisagreeComment);
        twoChoice = d.findViewById(R.id.twoChoice);
        twoChoiceComment = d.findViewById(R.id.twoChoiceComment);
        threeChoice = d.findViewById(R.id.threeChoice);
        threeChoiceComment = d.findViewById(R.id.threeChoiceComment);
        fourChoice = d.findViewById(R.id.fourChoice);

        fourChoiceComment = d.findViewById(R.id.fourChoiceComment);
        fiveChoice = d.findViewById(R.id.fiveChoice);
        fiveChoiceComment = d.findViewById(R.id.fiveChoiceComment);
        commentOnly = d.findViewById(R.id.commentOnly);

        navigateScreen(yesNoButton,Pie_Chart.class);
        navigateScreen(yesNoButtonComment,Pie_Chart.class);
        navigateScreen(agreeDisagree,AgreeDisagree_PieChart.class);
        navigateScreen(agreeDisagreeComment,AgreeDisagree_PieChart.class);
        navigateScreen(twoChoice,Pie_Chart_Comment.class);
        navigateScreen(twoChoiceComment,Pie_Chart_Comment.class);
        navigateScreen(threeChoice,Bar_Chart.class);
        navigateScreen(threeChoiceComment,Bar_Chart.class);
        navigateScreen(fourChoice,FourChoice_BarChart.class);
        navigateScreen(fourChoiceComment,FourChoice_BarChart.class);
        navigateScreen(fiveChoice,FiveChoices_BarChart.class);
        navigateScreen(fiveChoiceComment,FiveChoices_BarChart.class);
        navigateScreen(commentOnly,Comment_Only.class);*/

    }

    private void navigateScreen(Button btn, final Class next) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Question.this, next);
                startActivity(intent);
            }
        });
    }


    @SuppressLint("LongLogTag")
    private void requestApi(){
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
            qbID = new String[jsonArray.length()];
            questionType = new String[jsonArray.length()];
            questionID = new String[jsonArray.length()];
            questionComment = new String[jsonArray.length()];
            qbTitle = new String[jsonArray.length()];

            Log.d("langht of the value=========", String.valueOf(questionTitle.length));

            for(int i = 0; i< jsonArray.length(); i++){
                JSONObject row = jsonArray.getJSONObject(i);
                questionTitle[i] = row.getString("qbTitle");
                publishDate[i] =row.getString("qbDate");
                qbID[i] = row.getString("qbID");
                questionType[i] = row.getString("qbQuickMode");
                questionID[i] = row.getString("qbID");
                questionComment[i] = row.getString("qbComment");
                qbTitle[i] = row.getString("qbTitle");
                Log.d("view qbID=======================",qbID[i]);
                Log.d("string value ---------------", questionTitle[i]);
                prepareQuestion(questionTitle[i],publishDate[i]);
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
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void prepareQuestion(String questionTitle,String publishDate) {
        Question a = new Question ("Now Public", questionTitle, publishDate, "Non-disclosure", "Anonymous", 2);
        questionList.add(a);
        adapter.notifyDataSetChanged();
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

