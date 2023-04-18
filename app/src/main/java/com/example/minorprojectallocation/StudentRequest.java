package com.example.minorprojectallocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

public class StudentRequest extends AppCompatActivity {
    FirebaseUser firebaseUser;
    private ListView mListView;
    private DatabaseReference mDatabaseRef;
    private List<String> mProjectsList;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        id = firebaseUser.getUid();

        mListView = findViewById(R.id.listView);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Students").child(firebaseUser.getUid()).child("Projects").child(""+id);;
        mProjectsList = new ArrayList<>();

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot projectSnapshot : dataSnapshot.getChildren()) {
                    String project = projectSnapshot.child("title").getValue(String.class);
                    mProjectsList.add(project);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(StudentRequest.this, android.R.layout.simple_list_item_1, mProjectsList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}