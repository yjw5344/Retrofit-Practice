package com.example.shindongkyu.loginsignupcomplete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shindongkyu.loginsignupcomplete.Model.Data;
import com.example.shindongkyu.loginsignupcomplete.Model.Example;
import com.example.shindongkyu.loginsignupcomplete.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {


    APIinterface apiinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        apiinterface = APIClient.getClient().create(APIinterface.class);

        Button b1 = (Button)findViewById(R.id.button4);
        Button b2 = (Button)findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {

            EditText emailtxt = (EditText) findViewById(R.id.editText3);
            EditText pwdtxt = (EditText) findViewById(R.id.editText4);

            @Override
            public void onClick(View v) {
                String email = emailtxt.getText().toString();
                String pwd = pwdtxt.getText().toString();

                User user = new User(email,pwd);

                Call<Example> gotojoin= apiinterface.join(user);
                gotojoin.enqueue(new Callback<Example>() {

                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {


                        String displayResponse = "";

                        int code = response.code();
                        String stringcode = Integer.toString(code);

                        if ("201".equals(stringcode)){
                            Example registerresponse = response.body();
                            displayResponse += "registerstat: " + registerresponse.stat +"\n";
                            Data data = registerresponse.data;
                            //displayResponse += "email: " + data.email+ "\n" + "ink: " +data.ink + "\n" + "userid: " + data.userId + "\n";
                            displayResponse += "email: " + data.email+ "\n" + "ink: " +data.ink + "\n" + "회원가입되었습니다." + "\n";
                            Toast toast = Toast.makeText(getApplicationContext(), displayResponse, Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        }
                        else if("401".equals(stringcode)){
                            Toast toast = Toast.makeText(getApplicationContext(), "이메일이 중복됩니다.", Toast.LENGTH_SHORT);
                            toast.show();
                            call.cancel();
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(), "정보를 기입해세요.", Toast.LENGTH_SHORT);
                            toast.show();
                            call.cancel();
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(), "인터넷연결이 안되어있습니다.", Toast.LENGTH_SHORT);
                        toast.show();


                        call.cancel();

                    }
                });


            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View view){
                finish();


            }
        });
    }



}
