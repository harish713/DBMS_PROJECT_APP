package com.example.dbms_app.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms_app.R;
import com.example.dbms_app.model.Candidate;
import com.example.dbms_app.model.Election;

import java.util.ArrayList;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder> {
    private ArrayList<Candidate> dataList;
    private static ClickListener clickListener;

    public ArrayList<Candidate> getDataList() {
        return dataList;
    }

    public CandidateAdapter(ArrayList<Candidate> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_candidate, viewGroup, false);
        return new CandidateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateViewHolder electionViewHolder, int i) {
        //TO BE DONE!!!
        electionViewHolder.txtName.setText(dataList.get(i).getCandidateName());
        electionViewHolder.txtParty.setText(dataList.get(i).getCandidateParty());
        electionViewHolder.txtVotes.setText(String.valueOf(dataList.get(i).getNumberOfVotes()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CandidateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // TO BE DONE!!
        TextView txtName,txtParty,txtVotes;
        public CandidateViewHolder(@NonNull View itemView) {
            super(itemView);
            // TO BE DONE!!
            txtName = (TextView)itemView.findViewById(R.id.txt_candidate_name);
            txtParty = (TextView)itemView.findViewById(R.id.txt_party);
            txtVotes = (TextView)itemView.findViewById(R.id.txt_candidate_votes);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        CandidateAdapter.clickListener = clickListener;
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
