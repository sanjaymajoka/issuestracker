package com.githubissuetracker.app.activities;

import android.content.Intent;
import android.os.Bundle;

import com.githubissuetracker.app.KeyConstants;
import com.githubissuetracker.app.R;
import com.githubissuetracker.app.Utils;
import com.githubissuetracker.app.adapter.IssueListAdapter;
import com.githubissuetracker.app.models.DataModel;
import com.githubissuetracker.app.viewmodels.IssuesListViewModel;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class IssuesListActivity extends ToolBarActivity<IssuesListViewModel> implements IssueListAdapter
        .IssueItemClickListener {

    private IssueListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues_list);
        initObserver(viewModel);
        initView();
        viewModel.loadIssues();
    }



    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IssueListAdapter(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    Class<IssuesListViewModel> viewModelTypeClass() {
        return IssuesListViewModel.class;
    }

    @Override
    protected boolean enableBackArrow() {
        return false;
    }

    private void initObserver(IssuesListViewModel viewModel) {
        viewModel.getLdListIssues().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                adapter.setList(dataModels);
            }
        });
    }

    @Override
    protected String title() {
        return getString(R.string.issues);
    }

    @Override
    public void onItemClick(DataModel dataModel) {
        if (Utils.isNetworkConnectionAvailable(this)) {
            Intent intent = new Intent(this, CommentsActivity.class);
            intent.putExtra(KeyConstants.ISSUE, dataModel);
            startActivity(intent);
        } else {
            showToast(getString(R.string.network_error_message));
        }
    }

}
