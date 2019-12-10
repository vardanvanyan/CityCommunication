package com.example.problem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.problem.Adapters.ProblemsAdapter;
import com.example.problem.Model.ProblemsModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProblemsListActivity extends AppCompatActivity {
    List<ProblemsModel> list;
    private FirebaseFirestore db;
    private ProblemsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems_list);
        recyclerView = findViewById(R.id.recviewposts);
        db = FirebaseFirestore.getInstance();
        readDB();
    }

    void readDB() {
        list = new ArrayList<>();
        db.collection("problems")
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            ProblemsModel model = document.toObject(ProblemsModel.class);
                            model.setId(document.getId());
                            list.add(model);
                        }
                    }
                    adapter = new ProblemsAdapter(list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProblemsListActivity.this));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                });
    }
}
