package com.example.problem.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.problem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;
    private EditText name;
    private EditText surname;
    private TextInputEditText email;
    private TextInputEditText password;
    EditText repassword;
    Button register;
    private TextInputLayout firstnamelLay;
    private TextInputLayout lastnameLay;
    private TextInputLayout eMailLay;
    private TextInputLayout passwordLay;
    TextInputLayout repassLay;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        name = view.findViewById(R.id.reg_name);
        surname = view.findViewById(R.id.reg_surname);
        email = view.findViewById(R.id.reg_email);
        password = view.findViewById(R.id.reg_pass);
        repassword = view.findViewById(R.id.reg_repass);
        register = view.findViewById(R.id.reg_register);
        firstnamelLay = view.findViewById(R.id.reg_name_lay);
        lastnameLay = view.findViewById(R.id.reg_last_name_lay);
        eMailLay = view.findViewById(R.id.reg_email_lay);
        passwordLay = view.findViewById(R.id.reg_pass_lay);
        repassLay = view.findViewById(R.id.reg_repass_lay);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(v -> {
            if (validate(email, password)) {
                createAccount(Objects.requireNonNull(email.getText()).toString(), Objects.requireNonNull(password.getText()).toString());
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.fffff, new LoginFragment()).commit();
            }
        });
        return view;
    }

    void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
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
        String validName = name.getText().toString();
        String validSurname = surname.getText().toString();
        matcher = pattern.matcher(validPass);
        if (validEmail.isEmpty()) {
            eMailLay.setError("Field shouldn't be empty");
            valid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(validEmail).matches()) {
            eMailLay.setError("Enter a valid emailLay address");
            valid = false;
        } else {
            eMailLay.setError(null);
        }
        if (validPass.isEmpty()) {
            passwordLay.setError("Field shouldn't be empty");
            valid = false;
        } else if (!matcher.matches()) {
            passwordLay.setError("Password must be be at least 6 characters long and contain upper and lower case letters and digits");
            valid = false;
        } else {
            passwordLay.setError(null);
        }
        if (validName.isEmpty()) {
            firstnamelLay.setError("Field shouldn't be empty");
            valid = false;
        }
        if (validSurname.isEmpty()) {
            lastnameLay.setError("Field shouldn't be empty");
            valid = false;
        }
        return valid;
    }
}
