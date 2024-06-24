package com.example.BehavioralBiometricsCollection;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;

import com.example.BehavioralBiometricsCollection.R;

import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    EditText userID, age, gender, email;
    RadioButton weChat, tikTok, webSite, checkedButton;
    RadioGroup session;
    int chooseId = -1;
    boolean checked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*registerButton.setOnClickListener(this);*/
        setContentView(R.layout.activity_register);
        userID = findViewById(R.id.editTextUserID);
        age = findViewById(R.id.editTextAge);
        age.setInputType(InputType.TYPE_CLASS_NUMBER);
        gender = findViewById(R.id.editTextGender);
        email = findViewById(R.id.editTextEmail);
        weChat = (RadioButton) findViewById(R.id.WeChat);
        tikTok = (RadioButton) findViewById(R.id.TikTok);
        webSite = (RadioButton) findViewById(R.id.Website);
        session = (RadioGroup) findViewById(R.id.session);
        session.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                chooseId = checkedId;
                checkedButton = (RadioButton) group.findViewById(checkedId);

            }
        });
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onAgreeChecked(View view) {
        if(view.getId() == R.id.agreeChecked)
        {
            checked = ((CheckBox) view).isChecked();
            if(checked)
            {
                checked = true;
            }
            else
            {
                checked = false;
            }
        }
    }

    public void onLoginClick(View view){

        String useridStr = userID.getText().toString();
        String ageStr = age.getText().toString();
        boolean isNumeric = ageStr.matches("-?\\d+(\\.\\d+)?");
        String genderStr = gender.getText().toString();
        String emailStr = email.getText().toString();
        //Log.d("myTag", checkedButton.getText().toString());
        if(isNumeric)
        {
            System.out.println("############################### isNumeric");
        }

       if(isNumeric && useridStr.length() != 0 && ageStr.length() != 0 && genderStr.length() != 0 && isEmail(emailStr) && chooseId != -1)
        {
            /*Log.d("myTag", (String) checkedButton.getText());*/
            if(checked)
            {
                Log.d("myTag", useridStr);
                Log.d("myTag", ageStr);
                Log.d("myTag", genderStr);
                Log.d("myTag", emailStr);
                Log.d("myTag", checkedButton.getText().toString());
                Bundle bundle=new Bundle();
                bundle.putString("1:",useridStr);
                bundle.putString("2:",ageStr);
                bundle.putString("3:",genderStr);
                bundle.putString("4:",emailStr);
                bundle.putString("5:",checkedButton.getText().toString());

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(RegisterActivity.this
                        ,
                        "Please read and agree to the relevant agreement", Toast.LENGTH_SHORT).show();
            }

        }
       else
       {
           Toast.makeText(RegisterActivity.this
                ,
                "Please fill in relevant registration information as required", Toast.LENGTH_SHORT).show();
       }

        /*startActivity(new Intent(this,MainActivity.class));*/
        /*overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);*/

    }

    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }



}