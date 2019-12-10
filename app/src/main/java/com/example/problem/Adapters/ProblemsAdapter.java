package com.example.problem.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.problem.CommentActivity;
import com.example.problem.Model.CommentsModel;
import com.example.problem.Model.ProblemsModel;
import com.example.problem.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProblemsAdapter extends RecyclerView.Adapter<ProblemsAdapter.ViewHolder> {
    private String[] data = {"Responsible person", "Garnik Manukyan", "Simon Karapetyan", "Bagrat Isoyan ", "Arman Aleqsanyan"};
    private List<ProblemsModel> problemsModels;

    public ProblemsAdapter(List<ProblemsModel> problemsModels) {
        this.problemsModels = problemsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.problems_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ProblemsModel problemsModel = problemsModels.get(position);
        viewHolder.name.setText(problemsModel.getName());
        viewHolder.problemDescription.setText(problemsModel.getProblemDescription());
        viewHolder.address.setText(problemsModel.getAddress());
        if (!problemsModel.getUserImg().equals("")) {
            Picasso.get().load(problemsModel.getUserImg()).into(viewHolder.userImg);

        }
        if (!problemsModel.getProblemImg().equals("")) {
            Picasso.get().load(problemsModel.getProblemImg()).into(viewHolder.problemImg);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(viewHolder.itemView.getContext(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.spinner.setAdapter(adapter);
        viewHolder.spinner.setPrompt("Title");

    }

    @Override
    public int getItemCount() {
        return problemsModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Spinner spinner;
        ImageView userImg;
        TextView name;
        ImageView problemImg;
        TextView problemDescription;
        TextView address;
        ImageButton commButton;
        ImageButton comfirm;
        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinner);
            userImg = itemView.findViewById(R.id.user_Img);
            name = itemView.findViewById(R.id.user_name);
            problemImg = itemView.findViewById(R.id.problem_Img);
            problemDescription = itemView.findViewById(R.id.problem_description);
            address = itemView.findViewById(R.id.address);
            commButton = itemView.findViewById(R.id.post_coment);
            comfirm = itemView.findViewById(R.id.comfirm);
            textView = itemView.findViewById(R.id.resp);

            comfirm.setOnClickListener(v -> {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection(problemsModels.get(getAdapterPosition()).getId())
                        .add(new CommentsModel("", "ADMIN", "The problem is solved"));
                comfirm.setImageResource(R.drawable.ic_check_green_24dp);
                comfirm.setClickable(false);
            });

            commButton.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), CommentActivity.class);
                intent.putExtra("ID", problemsModels.get(getAdapterPosition()).getId());
                intent.putExtra("USERNAME", problemsModels.get(getAdapterPosition()).getName());
                intent.putExtra("IMGURL", problemsModels.get(getAdapterPosition()).getUserImg());
                itemView.getContext().startActivity(intent);
            });
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    textView.setText(data[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
        }
    }
}
