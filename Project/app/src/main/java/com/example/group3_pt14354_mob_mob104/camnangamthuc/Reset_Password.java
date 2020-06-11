package com.example.group3_pt14354_mob_mob104.camnangamthuc;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group3_pt14354_mob_mob104.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_Password extends AppCompatActivity {
    private EditText ed_email;
    private Button btn_reset_password;
    private TextView tv_1,tv_2, tv_3, tv_4,tv_5;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__password);
        ed_email = findViewById(R.id.edChangePassword);
        btn_reset_password = findViewById(R.id.btnChangePassword);
        tv_1 = findViewById(R.id.tvreset);
        tv_2 = findViewById(R.id.tvreset2);
        tv_3 = findViewById(R.id.tvreset3);
        tv_4 = findViewById(R.id.tvreset4);
        tv_5 = findViewById(R.id.tvreset5);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Arial.ttf");
        tv_1.setTypeface(typeface);
        tv_2.setTypeface(typeface);
        tv_3.setTypeface(typeface);
        tv_4.setTypeface(typeface);
        tv_5.setTypeface(typeface);
        btn_reset_password.setTypeface(typeface);

        firebaseAuth = FirebaseAuth.getInstance();
        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString().trim();
                if (email.isEmpty()){
                    Toast.makeText(Reset_Password.this,"Mời nhập Username",Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Đổi mật khẩu trong email",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Reset_Password.this,LoginActivity.class));
                            return;
                        } else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}