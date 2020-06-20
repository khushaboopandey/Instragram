package com.example.instragram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignUp, btnLoginIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEnterEmail);
        edtUsername = findViewById(R.id.edtEnterName);
        edtPassword = findViewById(R.id.edtEnterPassword);

        // for hiding the keybord when user enter the password
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp);
                }
                return false;
            }
        });
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLoginIn = findViewById(R.id.btnlogin);

        btnSignUp.setOnClickListener(SignUp.this);
        btnLoginIn.setOnClickListener(SignUp.this);

        if (ParseUser.getCurrentUser() != null) {
            // ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                if (edtEmail.getText().toString().equals("") || edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                    FancyToast.makeText(SignUp.this, "Email UserName Password id required!", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();

                } else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is Signed up", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

                                transitionToSocialMediaActivity();

                            } else {
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                            }
                            progressDialog.dismiss();

                        }
                    });
                }
                break;
            case R.id.btnlogin:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                break;
        }


    }

    //When youser tapped empty area of the layout.
    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);

    }
}