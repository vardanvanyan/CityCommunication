package com.example.problem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.problem.Fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fffff, new LoginFragment()).commit();
    }
}
