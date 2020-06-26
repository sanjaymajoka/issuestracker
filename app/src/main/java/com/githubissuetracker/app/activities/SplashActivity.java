package com.githubissuetracker.app.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.githubissuetracker.app.R;
import com.githubissuetracker.app.viewmodels.SplashViewModel;

import androidx.lifecycle.Observer;

public class SplashActivity extends BaseActivity<SplashViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initObserver(viewModel);
        viewModel.handleSplash();
    }

    @Override
    Class<SplashViewModel> viewModelTypeClass() {
        return SplashViewModel.class;
    }

    private void initObserver(SplashViewModel viewModel) {
        viewModel.getLdOpenHome().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aVoid) {
                startActivity(new Intent(SplashActivity.this, IssuesListActivity.class));
                SplashActivity.this.finish();
            }
        });
    }

}
