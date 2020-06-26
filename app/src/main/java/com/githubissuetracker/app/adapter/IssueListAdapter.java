package com.githubissuetracker.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githubissuetracker.app.R;
import com.githubissuetracker.app.Utils;
import com.githubissuetracker.app.models.DataModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IssueListAdapter extends RecyclerView.Adapter<IssueListAdapter.IssueViewHolder> {

    private Context context;
    private List<DataModel> modelList;

    public IssueListAdapter(Context context) {
        this.context = context;
        modelList = new ArrayList<>();
    }

    @NonNull
    @Override
    public IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IssueViewHolder(LayoutInflater.from(context).inflate(R.layout.item_issue, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewHolder holder, int position) {
        DataModel dataModel = modelList.get(position);
        holder.txtTitle.setText(dataModel.getTitle());
        if (dataModel.getBody() != null && !dataModel.getBody().trim().equals("")) {
            holder.txtBody.setVisibility(View.VISIBLE);
            holder.txtBody.setText(dataModel.getBody());
        } else {
            holder.txtBody.setVisibility(View.GONE);
        }
        holder.txtUserName.setText(context.getString(R.string.user_name_with_time, Utils.getTimeStamp(dataModel.getCreatedAt())
                , dataModel.getUser().getLogin()));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setList(List<DataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    class IssueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MaterialTextView txtTitle;
        private MaterialTextView txtBody;
        private MaterialTextView txtUserName;

        public IssueViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() != -1 && clickListener != null) {
                clickListener.onItemClick(modelList.get(getAdapterPosition()));

            }
        }
    }

    private IssueItemClickListener clickListener;

    public void setClickListener(IssueItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface IssueItemClickListener {
        void onItemClick(DataModel dataModel);
    }
}
