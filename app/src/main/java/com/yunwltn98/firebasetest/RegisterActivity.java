package com.yunwltn98.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    EditText editNickname;
    Button btnRegister;
    TextView txtLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("회원가입");

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.btnLogin);
        txtLogin = findViewById(R.id.txtRegister);
        editNickname = findViewById(R.id.editNickname);

        firebaseAuth =  FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editEmail.getText().toString().trim();

                Pattern pattern = Patterns.EMAIL_ADDRESS;
                if (pattern.matcher(email).matches() == false) {
                    Toast.makeText(RegisterActivity.this, "이메일 형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 비밀번호 체크
                String password = editPassword.getText().toString().trim();

                // 기획에 맞게 처리 비밀번호 길이 4~12자리만 허옹
                if (password.length() < 6 || password.length() > 12) {
                    Toast.makeText(RegisterActivity.this, "비밀번호 길이를 확인하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //가입 성공시
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();
                            String name = editNickname.getText().toString().trim();

                            //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                            HashMap<Object, String> hashMap = new HashMap<>();

                            hashMap.put("uid",uid);
                            hashMap.put("email",email);
                            hashMap.put("name",name);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(uid).setValue(hashMap);

                            //가입이 완료되면 메인화면으로 돌아간다
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                            return;

                        }
                    }
                });
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();
            }
        });

    }
}