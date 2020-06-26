package com.githubissuetracker.app.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.githubissuetracker.app.KeyConstants;
import com.githubissuetracker.app.R;
import com.githubissuetracker.app.Utils;
import com.githubissuetracker.app.models.DataModel;
import com.githubissuetracker.app.viewmodels.IssueDetailViewModel;

public class IssueDetailActivity extends ToolBarActivity<IssueDetailViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);
        DataModel model = getIntent().getParcelableExtra(KeyConstants.ISSUE);
        initView(model);
    }

    private void initView(DataModel model) {

        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(model.getTitle());
        TextView txtDateAndUser = findViewById(R.id.txtUserName);
        txtDateAndUser.setText(getString(R.string.user_name_with_time, Utils.getTimeStamp(model.getCreatedAt())
                , model.getUser().getLogin()));

        TextView txtBody = findViewById(R.id.txtBody);
        txtBody.setText(model.getBody());
    }

    @Override
    Class<IssueDetailViewModel> viewModelTypeClass() {
        return IssueDetailViewModel.class;
    }
}
