package com.example.group3_pt14354_mob_mob104.camnangamthuc;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_pt14354_mob_mob104.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private EditText ed_username, ed_password;
    private Button btn_login, btn_Register;
    private TextView tvtieude,tvusername,tvpass,tv_register,tvchangePassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng nhập");
        ed_username = findViewById(R.id.edUserName);
        ed_password = findViewById(R.id.edPassword);
        tvtieude = findViewById(R.id.textView);
        tvusername = findViewById(R.id.textView3);
        tvpass = findViewById(R.id.textView4);
        tv_register = findViewById(R.id.tv_regist);
        btn_Register = findViewById(R.id.btnRegister);
        tvchangePassword = findViewById(R.id.tvchangePassword);



        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Arial.ttf");
        ed_username.setTypeface(typeface);
        ed_password.setTypeface(typeface);
        tvtieude.setTypeface(typeface);
        tvusername.setTypeface(typeface);
        tvpass.setTypeface(typeface);
        firebaseAuth = FirebaseAuth.getInstance();



        btn_login = findViewById(R.id.btnLogin);
        btn_login.setTypeface(typeface);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
            }
        });
        tvchangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Reset_Password.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_username.getText().toString().trim();
                String password = ed_password.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    ed_username.setError("Không để trống vùng nhập");
                    return;
                }
                else if (TextUtils.isEmpty(password)){
                    ed_password.setError("Không để trống vùng nhập");
                    return;
                }
                else if(password.length() < 5){
                    ed_password.setError("phải lớn hơn 5");
                }

                
                firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Sai tên tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                            ed_password.setText("");
                            ed_username.setText("");
                        }
                    }
                });
            }
        });
    }
}

