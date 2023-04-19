package com.example.minorprojectallocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Adapter.RequestAdapter;
import Adapter.StudentProjAdapter;
import Model.Project_Model;
import Model.Student;

public class RequestActivity extends AppCompatActivity {


    FirebaseUser firebaseUser;
    private RecyclerView recyclerView_tickets;

    RequestAdapter project_adapter;

    private List<Project_Model> projects;

    String id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        id = firebaseUser.getUid();

        recyclerView_tickets = findViewById(R.id.Request_recycler);


        projects = new ArrayList<>();
        recyclerView_tickets.setHasFixedSize(true);
        recyclerView_tickets.setLayoutManager(new LinearLayoutManager(RequestActivity.this));


        read_ticket_items();

    }

    private void read_ticket_items() {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Request");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                projects.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Project_Model user = snapshot.getValue(Project_Model.class);

                    if (user != null && user.getName() != null && Objects.equals(id, user.getFid())) {
                        projects.add(user);
                    }

                    project_adapter = new RequestAdapter(RequestActivity.this, projects, "false");
                    recyclerView_tickets.setAdapter(project_adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}


