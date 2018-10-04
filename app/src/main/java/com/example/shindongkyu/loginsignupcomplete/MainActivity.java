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

public class MainActivity extends AppCompatActivity {

    APIinterface apiinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiinterface = APIClient.getClient().create(APIinterface.class);

        Button b1 = (Button)findViewById(R.id.button);
        Button b2 = (Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {

            EditText emailtxt = (EditText) findViewById(R.id.editText);
            EditText pwdtxt = (EditText) findViewById(R.id.editText2);



            @Override
            public void onClick(View v) {

                String email = emailtxt.getText().toString();
                String pwd = pwdtxt.getText().toString();

                User user = new User(email,pwd);

                Call<Example> gotologin = apiinterface.login(user);
                gotologin.enqueue(new Callback<Example>() {

                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {

                        String displayResponse = "";

                        int code = response.code();
                        String stringcode = Integer.toString(code);


                        if ("201".equals(stringcode)){
                            Example loginresponse = response.body();
                            displayResponse += "loginstat: " + loginresponse.stat +"\n";
                            Data data = loginresponse.data;
                            displayResponse += "email: " + data.email+ "\n" + "ink: " +data.ink + "\n" + "userid: " + data.userId + "\n";
                            Toast toast = Toast.makeText(getApplicationContext(), displayResponse, Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(), "정보를 제대로 입력해주세요", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(), "인터넷연결이 안되어있습니다.", Toast.LENGTH_LONG);
                        toast.show();


                        call.cancel();

                    }
                });


            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View view){
                Intent intent = new Intent(
                        getApplicationContext(),
                        SignupActivity.class);
                startActivity(intent);

            }
        });
    }
}
