package com.example.group3_pt14354_mob_mob104.camnangamthuc;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_pt14354_mob_mob104.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegistActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$"
            );

    private EditText ed_username,ed_password,ed_repassword;
    private TextView tv_tieude,tv_username,tv_password,tv_repassword;
    private Button btn_register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ed_username = findViewById(R.id.edRegisterUserName);
        ed_password = findViewById(R.id.edRegisterPassword);
        ed_repassword = findViewById(R.id.edRegisterPassword2);
        tv_tieude = findViewById(R.id.tvregist);
        tv_username = findViewById(R.id.tvregist2);
        tv_password = findViewById(R.id.tvregist3);
        tv_repassword = findViewById(R.id.tvregist4);
        btn_register = findViewById(R.id.btnRegister);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Arial.ttf");
        ed_username.setTypeface(typeface);
        ed_password.setTypeface(typeface);
        ed_repassword.setTypeface(typeface);
        tv_tieude.setTypeface(typeface);
        tv_username.setTypeface(typeface);
        tv_password.setTypeface(typeface);
        tv_repassword.setTypeface(typeface);
        btn_register.setTypeface(typeface);




        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_username.getText().toString().trim();
                String password = ed_password.getText().toString().trim();
                String repassword = ed_repassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    ed_username.setError("ko de trong");
                    return;
                }
                else if (username.length() < 5){
                    ed_username.setError("phải trên 5 kí tự");
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    ed_username.setError("Vui long nhap dung dinh dang Email");
                    return;
                }

                else if (TextUtils.isEmpty(password)){
                    ed_password.setError("ko de trong");
                    return;
                }
                else if (password.length() < 5){
                    ed_password.setError("phải trên 5 kí tự");
                    return;
                }
                else if (repassword.length() < 5){
                    ed_repassword.setError("phải trên 5 kí tự");
                    return;
                }
                else if(!repassword.equals(password)){
                    ed_repassword.setError("Repassword phải trùng với Password");
                    return;
                }
                else if(!PASSWORD_PATTERN.matcher(password).matches()) {
                    ed_password.setError("Password phải có kí tự thường, kí tự hoa, kí tự số và kí tự đặc biệt");
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegistActivity.this, "Đăng ký thành công",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                        else {
                            Toast.makeText(RegistActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}
