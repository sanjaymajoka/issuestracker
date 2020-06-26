package com.githubissuetracker.app.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.githubissuetracker.app.KeyConstants;
import com.githubissuetracker.app.R;
import com.githubissuetracker.app.Utils;
import com.githubissuetracker.app.adapter.CommentListAdapter;
import com.githubissuetracker.app.models.DataModel;
import com.githubissuetracker.app.viewmodels.CommentsViewModel;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommentsActivity extends ToolBarActivity<CommentsViewModel> implements View.OnClickListener {

    private CommentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initObserver(viewModel);
        DataModel model = getIntent().getParcelableExtra(KeyConstants.ISSUE);
        initView(model);
        loadComments(model);
    }

    private void initView(DataModel model) {


        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(model.getTitle());


        TextView txtDateAndUser = findViewById(R.id.txtUserName);
        txtDateAndUser.setText(getString(R.string.user_name_with_time, Utils.getTimeStamp(model.getCreatedAt())
                , model.getUser().getLogin()));

        TextView viewMore = findViewById(R.id.txtViewMore);

        TextView txtBody = findViewById(R.id.txtBody);
        if (model.getBody() == null || model.getBody().trim().equals("")) {
            txtBody.setVisibility(View.GONE);
        } else {
            txtBody.setText(model.getBody());
            if (model.getBody().length() > 140) {
                viewMore.setVisibility(View.VISIBLE);
                viewMore.setOnClickListener(this);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentListAdapter(this);
        recyclerView.setAdapter(adapter);


    }

    private void initObserver(CommentsViewModel viewModel) {
        viewModel.getLdList().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                adapter.setList(dataModels);
            }
        });

        viewModel.getLdNoCommentAlert().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showNoCommentsAlert();
                }
            }
        });
    }

    private void loadComments(DataModel model) {
        String number = model.getNumber();
        viewModel.loadComments(number);
    }

    @Override
    protected String title() {
        return getString(R.string.detail);
    }

    @Override
    Class<CommentsViewModel> viewModelTypeClass() {
        return CommentsViewModel.class;
    }


    private AlertDialog alertDialog;

    private void showNoCommentsAlert() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.not_comments))
                .setMessage(getString(R.string.no_comment_message))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        intent.setClass(this, IssueDetailActivity.class);
        startActivity(intent);
    }
}
