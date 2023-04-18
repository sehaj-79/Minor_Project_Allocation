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

import Model.Users;

public class MainActivity extends AppCompatActivity {

    ImageView imageView,logout;
    TextView tv_name,tv_designation,tv_faculty,tv_school,tv_department, home_designation, tv_phoneno, tv_expertise;
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
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        imageView = findViewById(R.id.im_gender);
        logout = findViewById(R.id.logout);
        tv_name = findViewById(R.id.tv_name);
        tv_designation = findViewById(R.id.tv_designation);
        tv_faculty = findViewById(R.id.tv_faculty);
        tv_department = findViewById(R.id.tv_department);
        tv_school = findViewById(R.id.tv_school);
        tv_expertise=findViewById(R.id.tv_expertise);
        home_designation=findViewById(R.id.home_designation);


        tv_phoneno=findViewById(R.id.tv_phoneno);

        class_info = findViewById(R.id.classinfo);
        chats = findViewById(R.id.chats);
        about = findViewById(R.id.about);
        home_splash=findViewById(R.id.home_splash);
        home_page= findViewById(R.id.home_page);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });


        class_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RequestActivity.class);
                startActivity(intent);
            }
        });
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                assert user != null;

                tv_name.setText(user.getFname());
                tv_designation.setText(user.getFid());

                tv_department.setText("Department: "+user.getDepartment());
                tv_faculty.setText("Faculty Id: "+user.getFid());
                tv_school.setText("Email: "+user.getEmail());
                tv_phoneno.setText("Phone No. : "+user.getPhoneNo());
                home_designation.setText(("Designation : "+user.getDesignation()));
                tv_expertise.setText(("Field of Expertise : "+user.getExpertise()));




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