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

public class StudentProjAdapter extends RecyclerView.Adapter<StudentProjAdapter.ViewHolder>{
    private Context mContext;
    private List<Project_Model> mUsers;




    public StudentProjAdapter(Context mContext, List<Project_Model> mUsers, boolean b) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public StudentProjAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.req_proj_item,parent,false);
        return new StudentProjAdapter.ViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull final StudentProjAdapter.ViewHolder holder, int position) {


        final Project_Model project_item = mUsers.get(position);
        Log.i(TAG, "onBindViewHolder: "+position);



        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        assert firebaseUser != null;



        holder.project_name.setText(project_item.getName());
        holder.project_desc.setText(project_item.getDesc());
        holder.project_fname.setText(project_item.getFname());
        holder.project_seats.setText(project_item.getSeats());



    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView project_name,project_desc, project_fname,project_seats;
        public Button project_request;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            project_name = itemView.findViewById(R.id.item_booking);
            project_desc = itemView.findViewById(R.id.item_name);
            project_fname = itemView.findViewById(R.id.item_desc);
            project_seats = itemView.findViewById(R.id.item_seats);
            project_request = itemView.findViewById(R.id.item_request);

        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}