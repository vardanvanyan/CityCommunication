package com.example.problem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.problem.Adapters.CommentsAdapter;
import com.example.problem.Model.CommentsModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class CommentActivity extends AppCompatActivity {
    String id;
    String name;
    String imgUrl;
    FirebaseFirestore db;
    ArrayList<CommentsModel> models;
    CommentsAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        id = getIntent().getStringExtra("ID");
        name = getIntent().getStringExtra("USERNAME");
        imgUrl = getIntent().getStringExtra("IMGURL");
        db = FirebaseFirestore.getInstance();
        models = new ArrayList<>();
        recyclerView = findViewById(R.id.comm_list_recycler);
        readList();
    }

    private void readList() {
        db.collection(id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            CommentsModel model = document.toObject(CommentsModel.class);
                            models.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
        adapter = new CommentsAdapter(models);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}
