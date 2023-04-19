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
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorprojectallocation.R;

import java.util.List;

import Model.Project_Model;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{
    private Context mContext;
    private List<Project_Model> mUsers;
    private String dd_val;




    public RequestAdapter(Context mContext, List<Project_Model> mUsers, String dd_val) {
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.dd_val = dd_val;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.request_item,parent,false);
        return new RequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        final Project_Model user = mUsers.get(position);
        Log.i(TAG, "onBindViewHolder: "+position);

        final String check_noreq = user.getName();

        if (check_noreq.equals("No Requests"))
        {
            holder.p_name.setVisibility(View.GONE);
            holder.p_name.setVisibility(View.GONE);
            holder.f_accept.setVisibility(View.GONE);
            holder.f_decline.setVisibility(View.GONE);
        }

        holder.p_name.setText(user.getName());
        holder.s_name.setText(user.getSname());

        holder.f_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //accept request

            }
        });

        holder.f_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //delete request

            }
        });


    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView p_name,s_name;
        public Button f_accept,f_decline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            p_name = itemView.findViewById(R.id.item_booking);
            s_name = itemView.findViewById(R.id.item_name);
            f_accept = itemView.findViewById(R.id.button_accept);
            f_decline = itemView.findViewById(R.id.button_decline);



        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}