package Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorprojectallocation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import Model.Project_Model;

public class Project_Adapter extends RecyclerView.Adapter<Project_Adapter.ViewHolder>{
    private Context mContext;
    private List<Project_Model> mUsers;




    public Project_Adapter(Context mContext, List<Project_Model> mUsers, boolean b) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.project_item,parent,false);
        return new Project_Adapter.ViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        final Project_Model project_item = mUsers.get(position);
        Log.i(TAG, "onBindViewHolder: "+position);



        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        assert firebaseUser != null;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Projects");


       

        holder.project_bookingid.setText(project_item.getId());
        holder.project_name.setText(project_item.getName());
        holder.project_desc.setText(project_item.getDesc());

        holder.project_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.project_layout_main.setVisibility(View.GONE);
                holder.project_layout_delete.setVisibility(View.VISIBLE);
            }
        });

        holder.project_nodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.project_layout_main.setVisibility(View.VISIBLE);
                holder.project_layout_delete.setVisibility(View.GONE);
            }
        });



        holder.project_yesdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        int noORequests = (int) dataSnapshot.getChildrenCount();
                        String id = project_item.getId();

                        if (dataSnapshot.hasChild(""+id)){
                            String checkID = dataSnapshot.child(""+id).child("Id").getValue().toString();
                            if (checkID.equals(id)){
                                reference.child(""+id).removeValue();
                                mUsers.clear();
                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

            }
        });



    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView project_bookingid,project_name,project_desc, project_deletetv;
        public Button project_delete,project_yesdelete,project_nodelete;

        public ConstraintLayout project_layout_main, project_layout_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            project_bookingid = itemView.findViewById(R.id.item_booking);
            project_name = itemView.findViewById(R.id.item_name);
            project_desc = itemView.findViewById(R.id.item_desc);
            project_delete = itemView.findViewById(R.id.item_delete);
            project_deletetv = itemView.findViewById(R.id.item_tv_suredelete);
            project_nodelete = itemView.findViewById(R.id.item_tv_nodelete);
            project_yesdelete = itemView.findViewById(R.id.item_tv_yesdelete);

            project_layout_delete = itemView.findViewById(R.id.item_constraint_delete);
            project_layout_main = itemView.findViewById(R.id.item_constraint_main);


        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}