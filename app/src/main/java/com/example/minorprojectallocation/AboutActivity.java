package com.example.minorprojectallocation;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import Adapter.Project_Adapter;
import Model.Project_Model;
import Model.Users;

public class AboutActivity extends AppCompatActivity {


    FirebaseUser firebaseUser;
    private RecyclerView recyclerView_tickets;

    Project_Adapter project_adapter;

    TextView back,add;
    private TextView loading;
    String load = "Loading";

    LinearLayout add_proj;

    EditText et_name,et_desc;

    Button btn_add_proj;

    String desc, name;

    private List<Project_Model> projects;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        recyclerView_tickets = findViewById(R.id.ticket_recycler);
        loading = findViewById(R.id.tvLoading);


        back = findViewById(R.id.tvBack);
        add = findViewById(R.id.tvAdd);
        add_proj = findViewById(R.id.add_proj);
        btn_add_proj = findViewById(R.id.bt_add_proj);
        et_desc = findViewById(R.id.et_project_desc);
        et_name =findViewById(R.id.et_project_name);

        projects = new ArrayList<>();
        recyclerView_tickets.setHasFixedSize(true);
        recyclerView_tickets.setLayoutManager(new LinearLayoutManager(AboutActivity.this));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_proj.setVisibility(View.VISIBLE);
            }
        });


        btn_add_proj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name= String.valueOf(et_name.getText());
                desc= String.valueOf(et_desc.getText());

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Projects").child(""+gen());

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        read_ticket_items();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void read_ticket_items() {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Projects");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                projects.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Project_Model user = snapshot.getValue(Project_Model.class);

                    if (user != null && user.getName() != null) {
                        projects.add(user);
                    }

                    project_adapter = new Project_Adapter(AboutActivity.this, projects, false);
                    recyclerView_tickets.setAdapter(project_adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Projects");

        //int id = gen();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                Project_Model user = snapshot.getValue(Project_Model.class);

                assert user != null;
                user.setId(snapshot.child("Id").getValue().toString());
                user.setName(snapshot.child("Name").getValue().toString());
                user.setDesc(snapshot.child("Desc").getValue().toString());


                projects.add(user);

                project_adapter = new Project_Adapter(AboutActivity.this,projects,false);
                recyclerView_tickets.setAdapter(project_adapter);

                loading.setVisibility(GONE);
                recyclerView_tickets.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AboutActivity.this, "Database Error : "+error, Toast.LENGTH_SHORT).show();

            }
        });
*/
    }


    public int gen() {

        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(99999);
    }

}