package com.example.instragram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {
    private EditText Name, PunchSpeed, PunchPower, KickSpeed, KickPower;
    private Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Name = findViewById(R.id.edtName);
        PunchSpeed = findViewById(R.id.edtPunchSpeed);
        PunchPower = findViewById(R.id.edtPunchPower);
        KickSpeed = findViewById(R.id.edtKickSpeed);
        KickPower = findViewById(R.id.edtKickPower);
        Save = findViewById(R.id.btnSave);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try {

                    final ParseObject kickboxer = new ParseObject("KickBoxer");
                    kickboxer.put("name", Name.getText().toString());
                    kickboxer.put("punchspeed",PunchSpeed.getText().toString());
                    kickboxer.put("punchPower", PunchPower.getText().toString());
                    kickboxer.put("kickSpeed", KickSpeed.getText().toString());
                    kickboxer.put("kickPower", KickPower.getText().toString());
                    kickboxer.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, kickboxer.get("name") + " is saved to server ", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                        }
                    });
                } catch (Exception e) {
                    FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                }

            }
        });


    }


}
