package com.example.dbms_app.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms_app.R;
import com.example.dbms_app.model.Election;

import java.util.ArrayList;

public class ElectionAdapter extends RecyclerView.Adapter<ElectionAdapter.ElectionViewHolder> {
    private ArrayList<Election> dataList;
    private static ClickListener clickListener;

    public ArrayList<Election> getDataList() {
        return dataList;
    }

    public ElectionAdapter(ArrayList<Election> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ElectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_election, viewGroup, false);
        return new ElectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionViewHolder electionViewHolder, int i) {
        //TO BE DONE!!!
        electionViewHolder.txtID.setText(dataList.get(i).getId());
        electionViewHolder.txtType.setText(dataList.get(i).getType());
        electionViewHolder.txtNoOfConst.setText(String.valueOf(dataList.get(i).getNoOfConst()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ElectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // TO BE DONE!!
        TextView txtID,txtType,txtNoOfConst;
        public ElectionViewHolder(@NonNull View itemView) {
            super(itemView);
            // TO BE DONE!!
            txtID = (TextView)itemView.findViewById(R.id.txt_id);
            txtType = (TextView)itemView.findViewById(R.id.txt_type);
            txtNoOfConst = (TextView)itemView.findViewById(R.id.txt_no_of_const);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        ElectionAdapter.clickListener = clickListener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
