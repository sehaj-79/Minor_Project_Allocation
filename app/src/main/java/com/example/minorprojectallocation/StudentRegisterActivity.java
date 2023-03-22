package com.example.minorprojectallocation;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import android.os.Bundle;

public class StudentRegisterActivity extends AppCompatActivity {


    TextInputEditText editTextStudentName, editTextRegistrationNo, editTextStudentEmail, editTextStudentPassword, editTextStudentDepartment, editTextStudentPhoneNo ;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        mAuth = FirebaseAuth.getInstance();
        editTextStudentEmail = findViewById(R.id.student_email);
        editTextRegistrationNo = findViewById(R.id.registration_no);
        editTextStudentName = findViewById(R.id.student_name);
        editTextStudentPassword = findViewById(R.id.student_password);
        editTextStudentDepartment= findViewById(R.id.student_department);
        editTextStudentPhoneNo= findViewById(R.id.student_phoneno);
        buttonReg = findViewById(R.id.register_button);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String student_name, registration_no, student_email, student_password, student_department, student_phoneno;
                student_email = String.valueOf(editTextStudentEmail.getText());
                student_password = String.valueOf(editTextStudentPassword.getText());
                registration_no = String.valueOf(editTextRegistrationNo.getText());
                student_name = String.valueOf(editTextStudentName.getText());
                student_department = String.valueOf(editTextStudentDepartment.getText());
                student_phoneno = String.valueOf(editTextStudentPhoneNo.getText());

                if (TextUtils.isEmpty(student_email)) {
                    Toast.makeText(StudentRegisterActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(student_password)) {
                    Toast.makeText(StudentRegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(registration_no)) {
                    Toast.makeText(StudentRegisterActivity.this, "Enter Faculty Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(student_name)) {
                    Toast.makeText(StudentRegisterActivity.this, "Enter Faculty Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(student_email, student_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    String userId = firebaseUser.getUid();

                                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                                    Toast.makeText(StudentRegisterActivity.this, referenceProfile.toString(), Toast.LENGTH_SHORT).show();

                                    HashMap<String, String> hashMap = new HashMap<>();

                                    hashMap.put("Id", userId);
                                    hashMap.put("Sid", registration_no);
                                    hashMap.put("Sname", student_name);
                                    hashMap.put("StudentEmail", student_email);
                                    hashMap.put("StudentDepartment", student_department);
                                    hashMap.put("StudentPhoneNo", student_phoneno);
                                    hashMap.put("Type", "Student");


                                    referenceProfile.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(StudentRegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(StudentRegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });

                                } else {
                                    Toast.makeText(StudentRegisterActivity.this, task.toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }
}