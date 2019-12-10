package com.example.problem.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.problem.Model.CommentsModel;
import com.example.problem.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private List<CommentsModel> commentsModels;

    public CommentsAdapter(List<CommentsModel> commentsModels) {
        this.commentsModels = commentsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        CommentsModel commentsModel = commentsModels.get(position);
        viewHolder.commName.setText(commentsModel.getUserName());
        viewHolder.commDescription.setText(commentsModel.getComments());
        if (!commentsModel.getUserImg().equals("")) {
            Picasso.get().load(commentsModel.getUserImg()).into(viewHolder.commImg);
        }
    }

    @Override
    public int getItemCount() {
        return commentsModels == null ? 0 : commentsModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView commImg;
        TextView commName;
        TextView commDescription;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            commImg = itemView.findViewById(R.id.comment_img);
            commName = itemView.findViewById(R.id.comment_name);
            commDescription = itemView.findViewById(R.id.comment_description);
        }
    }
}
