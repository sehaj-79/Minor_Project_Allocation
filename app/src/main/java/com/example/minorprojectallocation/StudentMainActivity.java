package com.example.minorprojectallocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Student;
import Model.Users;

public class StudentMainActivity extends AppCompatActivity {

    ImageView imageView,logout;
    TextView student_name,student_regno,tv_regno,tv_emailid,tv_studepartment, tv_stuphoneno;
    ConstraintLayout home_splash,home_page;
    FrameLayout class_info,chats,about;
    FirebaseUser firebaseUser;
    CountDownTimer cdt_change_update,cdt_splash;
    long change_update = 60000;
    int currentupdate = 0;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        Firebase.setAndroidContext(this);

        imageView = findViewById(R.id.im_gender);
        logout = findViewById(R.id.logout);
        student_name = findViewById(R.id.student_name);
        student_regno = findViewById(R.id.student_regno);
        tv_regno = findViewById(R.id.tv_regno);
        tv_studepartment = findViewById(R.id.tv_studepartment);
        tv_emailid = findViewById(R.id.tv_emailid);


        tv_stuphoneno=findViewById(R.id.tv_stuphoneno);

        class_info = findViewById(R.id.classinfo);
        chats = findViewById(R.id.chats);

        home_splash=findViewById(R.id.home_splash);
        home_page= findViewById(R.id.home_page);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentMainActivity.this, LoginActivity.class));
            }
        });


        class_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentMainActivity.this,StudentRequest.class);
                startActivity(intent);
            }
        });
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentMainActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });



        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Student").child(firebaseUser.getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);

                student_name.setText(student.getSname());
                student_regno.setText(student.getSid());

                tv_studepartment.setText("Student Department: "+student.getStudentDepartment());
                tv_regno.setText("Registration No: "+student.getSid());
                tv_emailid.setText("Email: "+student.getStudentEmail());
                tv_stuphoneno.setText("Phone No. : "+student.getStudentPhoneNo());





                /*if(user.getGender().equals("Male"))
                    imageView.setBackgroundResource(R.drawable.boy);
                else
                    imageView.setBackgroundResource(R.drawable.girl);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }

}