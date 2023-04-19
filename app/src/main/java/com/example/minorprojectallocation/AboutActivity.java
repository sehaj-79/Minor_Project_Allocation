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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
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
    String load = "Loading", Fname;

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

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                assert user != null;

                Fname = user.getFname();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

                String id = ""+gen();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Projects").child(""+id);
                DatabaseReference refProj = FirebaseDatabase.getInstance().getReference("Projects").child(""+id);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put("Id",id);
                hashMap.put("Name",name);
                hashMap.put("Desc",desc);
                hashMap.put("Fname",Fname);
                hashMap.put("Seats","2");
                hashMap.put("Fid",firebaseUser.getUid());

                ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AboutActivity.this, "Project Added", Toast.LENGTH_SHORT).show();
                        add_proj.setVisibility(GONE);
                    }
                });

                refProj.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });



            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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