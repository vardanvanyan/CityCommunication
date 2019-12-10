package com.example.problem.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.problem.ProblemsListActivity;
import com.example.problem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    static FirebaseUser userA;
    private TextInputEditText eMail;
    private TextInputEditText password;
    Button login;
    Button register;
    private TextInputLayout emailLay;
    private TextInputLayout passLay;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();
        eMail = view.findViewById(R.id.email);
        password = view.findViewById(R.id.pass);
        login = view.findViewById(R.id.log_btn);
        register = view.findViewById(R.id.reg_btn);
        emailLay = view.findViewById(R.id.ed_text_lay_mail);
        passLay = view.findViewById(R.id.ed_text_lay_pass);

        register.setOnClickListener(v -> {
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().replace(R.id.fffff, new RegisterFragment()).commit();
        });

        login.setOnClickListener(v -> {
            if (validate(eMail, password)) {
                singUp(Objects.requireNonNull(eMail.getText()).toString(), Objects.requireNonNull(password.getText()).toString());
            }
        });
        return view;
    }


    private void singUp(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), task -> {
                    if (task.isSuccessful()) {
                        userA = mAuth.getCurrentUser();
                        Intent intent = new Intent(getContext(), ProblemsListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validate(TextInputEditText email, TextInputEditText password) {
        boolean valid = true;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=\\S+$).{6,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        String validEmail = Objects.requireNonNull(email.getText()).toString();
        String validPass = Objects.requireNonNull(password.getText()).toString();
        matcher = pattern.matcher(validPass);
        if (validEmail.isEmpty()) {
            emailLay.setError("Field shouldn't be empty");
            valid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(validEmail).matches()) {
            emailLay.setError("Enter a valid emailLay address");
            valid = false;
        } else {
            emailLay.setError(null);
        }
        if (validPass.isEmpty()) {
            passLay.setError("Field shouldn't be empty");
            valid = false;
        } else if (!matcher.matches()) {
            passLay.setError("Password must be be at least 6 characters long and contain upper and lower case letters and digits");
            valid = false;
        } else {
            passLay.setError(null);
        }
        return valid;
    }
}
