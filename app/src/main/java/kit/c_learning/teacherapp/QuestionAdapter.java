package kit.c_learning.teacherapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sokrim on 2/8/2018.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>{

    private Context mContext;
    private List<Question> questionList;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.displayPublic.setText(question.getDisplayPublic());
        holder.title_quickQuestion.setText(question.getTitle_quickQuestion());
        holder.registeredDate.setText(question.getRegisteredDate());
        holder.Guest.setText(question.getGuest());
        holder.openTotalResult.setText(question.getOpenTotalResult());
        holder.Submission.setText(question.getSubmission()+ "");


        holder.title_quickQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,YesNo_Chart.class);
                mContext.startActivity(intent);
            }
        });

//        holder.editQuestionIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.editQuestionIcon);
//            }
//        });
    }

    /* Showing popup menu when tapping on 3 dots */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_editquestion, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.edit_questionnairesInfo:
                    Toast.makeText(mContext, "Edit Questionnaires Information", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.edit_question:
                    Toast.makeText(mContext, "Edit Questionnaires", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.copyQuestion:
                    Toast.makeText(mContext, "Copy of the Questionnaires", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.downloadCSV:
                    Toast.makeText(mContext, "Download answer CSV", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.reset:
                    Toast.makeText(mContext, "Reset submission status", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.deleteQuestion:
                    Toast.makeText(mContext, "Delete Questionnaires", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView displayPublic, registeredDate, title_quickQuestion, Guest, openTotalResult , Submission;
        public ImageView previewIcon, editQuestionIcon;
        public LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);
            displayPublic = view.findViewById(R.id.display_Public);
            registeredDate = view.findViewById(R.id.registeredDate);
            title_quickQuestion = view.findViewById(R.id.title_quickQuestion);
            Guest = view.findViewById(R.id.Guest);
            openTotalResult = view.findViewById(R.id.openTotalResult);
            Submission = view.findViewById(R.id.Submission);
            previewIcon = view.findViewById(R.id.previewIcon);
            layout = view.findViewById(R.id.edit_questIcon);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(layout);
                }
            });
        }
    }

    public QuestionAdapter(Context mContext, List<Question> questionList) {
        this.mContext = mContext;
        this.questionList = questionList;
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
