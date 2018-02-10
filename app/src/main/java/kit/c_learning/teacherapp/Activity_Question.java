package kit.c_learning.teacherapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Activity_Question extends AppCompatActivity {

    Toolbar questionnairesToolbar;
    Button quickButton, createButton;
    LinearLayout quick_layout;


    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<Question> questionList;
    String[] value = null;

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

//        prepareQuestion();

        //Some url endpoint that you may have
//        String myUrl = "https://kit.c-learning.jp/t/ajax/quest/Question";
//        //String to place our result in
//        String result;
//        //Instantiate new instance of our class
//        HttpGetRequest getRequest = new HttpGetRequest();
//        //Perform the doInBackground method, passing in our url
//        try {
//            result = getRequest.execute(myUrl).get();
//            Log.d("mer result-------------",result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        HttpURLConnection httpURLConnection;
//        BufferedReader reader;
//        try {
//            URL url = new URL("https://kit.c-learning.jp/t/ajax/quest/Question");
//            httpURLConnection=(HttpURLConnection) url.openConnection();
//            httpURLConnection.connect();
//
//            InputStream inputStream = httpURLConnection.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//            while ((line = reader.readLine()) !=null){
//                buffer.append(line);
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            Request request = Bridge
//                    .get("https://kit.c-learning.jp/t/ajax/quest/Question")
//                    .request();
//
//            Log.d("mer request =--------------",request.toString());
//        } catch (BridgeException e) {
//            e.printStackTrace();
//        }


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

    @SuppressLint("LongLogTag")
    public void makePostRequest(String stringUrl)throws IOException {
        Log.e("==============================", stringUrl);
        URL url = new URL(stringUrl);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        String line;
        StringBuilder jsonString = new StringBuilder();

        uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        uc.setRequestMethod("GET");
        uc.setDoInput(true);
        uc.setInstanceFollowRedirects(false);
        uc.connect();
        OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");

        JSONObject object = new JSONObject();
        try {
            object.put("qbTitle", value);
            object.put("qbID", 5);
            Log.d("mer qbtitle-------------------------",value.toString());


        } catch (Exception ex) {
            Log.i("############## ", ex.toString());
        }
        try {
//            trywriter.write(getPostDataString(object));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.close();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        uc.disconnect();
    }

//    @SuppressLint("LongLogTag")
//    public void requestApi(String stringUrl){
//        //request api
//        String myUrl = "https://kit.c-learning.jp/t/ajax/quest/Question";
//        JSONArray dataJson;
//        String result = null;
//        HttpGetRequest httpGetRequest = new HttpGetRequest();
//
//        try {
//            result = httpGetRequest.execute(myUrl).get();
//            Log.i("log mer result================ ",result);
//            dataJson = new JSONArray(result);
//            Log.i("this is jsonarray============",dataJson.toString());
//            value = new String[dataJson.length()];
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    private void prepareQuestion() {
        Question a = new Question ("Now Public", "[Q] Yes/No", "2018/02/09 10:20", "Non-disclosure", "Anonymous", 2);
        questionList.add(a);

        a = new Question ("Non-disclosure", "[Q] Agree/Disagree", "2018/02/08 09:20", "Now Public", "", 0);
        questionList.add(a);

        a = new Question ("Non-disclosure", "[Q] Agree/Disagree C- learning is good", "2018/02/08 09:20", "Now Public", "", 0);
        questionList.add(a);

        a = new Question ("Now Public", "[Q] Agree/Disagree*", "2018/02/08 09:20", "Now Public", "Registered", 0);
        questionList.add(a);

        a = new Question ("Now Public", "[Q] Agree/Disagree*", "2018/02/08 09:20", "Now Public", "Registered", 0);
        questionList.add(a);
        a = new Question ("Now Public", "[Q] Four Choices*", "2018/02/08 09:20", "Now Public", "Registered", 0);
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
                       Intent intent = new Intent(Activity_Question.this,ChartActivity.class);
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

