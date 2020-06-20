package com.example.instragram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");
        edtLoginEmail = findViewById(R.id.edtEnterEmail);
        edtLoginPassword = findViewById(R.id.edtEnterPassword);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });
        btnSignUpActivity = findViewById(R.id.btnSignUp);
        btnLoginActivity = findViewById(R.id.btnlogin);
        btnLoginActivity.setOnClickListener(this);
        btnSignUpActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnlogin:

                if (edtLoginEmail.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this, "Email Password id required!", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();

                } else {

                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged In", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                            }
                        }
                    });
                }
                break;
            case R.id.btnSignUp:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;

        }
    }

    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);

    }
}