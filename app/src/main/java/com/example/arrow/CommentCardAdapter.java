package com.example.arrow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class CommentCardAdapter extends RecyclerView.Adapter<CommentCardAdapter.FeaturedViewHolder>{
    ArrayList<CommentHelperClass> commentItem;

    public CommentCardAdapter(ArrayList<CommentHelperClass> commentItem) {
        this.commentItem = commentItem;
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {


        TextView fName;
        TextView lName;
        TextView course;
        TextView learning;
        TextView attendance;
        TextView grading;
        TextView review;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            fName = itemView.findViewById(R.id.comment_first_name);
            lName = itemView.findViewById(R.id.comment_last_name);
            course = itemView.findViewById(R.id.comment_course);
            learning = itemView.findViewById(R.id.learning_event);
            attendance = itemView.findViewById(R.id.attendance);
            grading = itemView.findViewById(R.id.grading);
            review = itemView.findViewById(R.id.review_comment);
        }
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_card, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        CommentHelperClass commentHelperClass = commentItem.get(position);

        holder.fName.setText(commentHelperClass.getfName());
        holder.lName.setText(commentHelperClass.getlName());
        holder.course.setText(commentHelperClass.getCourse());
        holder.learning.setText(commentHelperClass.getLearning());
        holder.attendance.setText(commentHelperClass.getAttendance());
        holder.grading.setText(commentHelperClass.getGrading());
        holder.review.setText(commentHelperClass.getReview());

    }

    @Override
    public int getItemCount() {
        return commentItem.size();
    }

}
