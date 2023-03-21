package com.example.minorprojectallocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText editTextFacultyName, editTextFacultyId, editTextEmail, editTextPassword, editTextDepartment, editTextPhoneNo, editTextDesignation, editTextFieldOfExpertise ;
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
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextFacultyId = findViewById(R.id.fac_id);
        editTextFacultyName = findViewById(R.id.fac_name);
        editTextPassword = findViewById(R.id.password);
        editTextDepartment= findViewById(R.id.department);
        editTextPhoneNo= findViewById(R.id.phoneno);
        editTextDesignation=findViewById(R.id.designation);
        editTextFieldOfExpertise=findViewById(R.id.expertise);
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
                String fac_name, fac_id, email, password, department, phoneno, expertise, designation;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                fac_id = String.valueOf(editTextFacultyId.getText());
                fac_name = String.valueOf(editTextFacultyName.getText());
                department= String.valueOf(editTextDepartment.getText());
                phoneno= String.valueOf(editTextPhoneNo.getText());
                expertise= String.valueOf(editTextFieldOfExpertise.getText());
                designation=String.valueOf(editTextDesignation.getText());


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fac_id)) {
                    Toast.makeText(RegisterActivity.this, "Enter Faculty Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fac_name)) {
                    Toast.makeText(RegisterActivity.this, "Enter Faculty Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    String userId = firebaseUser.getUid();

                                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                                    Toast.makeText(RegisterActivity.this, referenceProfile.toString(), Toast.LENGTH_SHORT).show();

                                    HashMap<String,String> hashMap = new HashMap<>();

                                    hashMap.put("Id",userId);
                                    hashMap.put("Fid",fac_id);
                                    hashMap.put("Fname",fac_name);
                                    hashMap.put("Email",email);
                                    hashMap.put("Department",department);
                                    hashMap.put("PhoneNo",phoneno);
                                    hashMap.put("Expertise",expertise);
                                    hashMap.put("Designation",designation);


                                    referenceProfile.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });

                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, task.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}