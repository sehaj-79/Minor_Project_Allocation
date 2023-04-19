package com.example.minorprojectallocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import Adapter.Project_Adapter;
import Adapter.StudentProjAdapter;
import Model.Project_Model;
import Model.Student;

public class StudentRequest extends AppCompatActivity {


    FirebaseUser firebaseUser;
    private RecyclerView recyclerView_tickets;

    StudentProjAdapter project_adapter;

    private List<Project_Model> projects;

    String Sname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request);

        recyclerView_tickets = findViewById(R.id.proj_recycler);


        projects = new ArrayList<>();
        recyclerView_tickets.setHasFixedSize(true);
        recyclerView_tickets.setLayoutManager(new LinearLayoutManager(StudentRequest.this));

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);

                assert student != null;
                Sname = student.getSname();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        read_ticket_items();

    }

    private void read_ticket_items() {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Projects");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                projects.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Project_Model user = snapshot.getValue(Project_Model.class);

                    if (user != null && user.getName() != null) {
                        projects.add(user);
                    }

                    project_adapter = new StudentProjAdapter(StudentRequest.this, projects, false , Sname);
                    recyclerView_tickets.setAdapter(project_adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}


