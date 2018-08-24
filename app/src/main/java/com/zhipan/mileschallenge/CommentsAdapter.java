package com.zhipan.mileschallenge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comment, viewGroup, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        if (i%2 == 0) {
            viewHolder.profileThumbnail.setImageResource(R.drawable.face1);
            viewHolder.profileName.setText(R.string.profile_name_1);
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d", Locale.ENGLISH);
            viewHolder.commentDate.setText(sdf.format(new Date()));
            viewHolder.commentBody.setText(R.string.default_comment);
        } else {
            viewHolder.profileThumbnail.setImageResource(R.drawable.face2);
            viewHolder.profileName.setText(R.string.profile_name_2);
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d", Locale.ENGLISH);
            viewHolder.commentDate.setText(sdf.format(new Date()));
            viewHolder.commentBody.setText(R.string.default_comment);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView profileThumbnail;
        public TextView profileName;
        public TextView commentDate;
        public TextView commentBody;

        public MyViewHolder(View v) {
            super(v);

            profileThumbnail = v.findViewById(R.id.profile_thumbnail);
            profileName = v.findViewById(R.id.profile_name);
            commentDate = v.findViewById(R.id.comment_date);
            commentBody = v.findViewById(R.id.comment_body);
        }
    }
}
