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

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

    private List<DataModel> modelList;
    private Context context;

    public CommentListAdapter(Context context) {
        this.context = context;
        modelList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_comment, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        DataModel model = modelList.get(position);
        holder.txtName.setText(model.getUser().getLogin());
        holder.txtBody.setText(model.getBody());
        holder.txtCommentedOn.setText(context.getString(R.string.commented_on,Utils.getTimeStamp(model.getCreatedAt())));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setList(List<DataModel> list){
        this.modelList = list;
        notifyDataSetChanged();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView txtName;
        private MaterialTextView txtBody;
        private View verticalLine;
        private MaterialTextView txtCommentedOn;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtBody = itemView.findViewById(R.id.txtBody);
            txtCommentedOn = itemView.findViewById(R.id.txtCommentedOn);

        }
    }
}
