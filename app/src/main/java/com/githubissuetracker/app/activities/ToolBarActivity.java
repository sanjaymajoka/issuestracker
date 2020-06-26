package com.githubissuetracker.app.activities;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.githubissuetracker.app.R;
import com.githubissuetracker.app.viewmodels.BaseViewModel;

import androidx.appcompat.widget.Toolbar;

abstract class ToolBarActivity<V extends BaseViewModel> extends BaseActivity<V> {

    @Override
    public void setContentView(int layoutResID) {
        View toolbarView = LayoutInflater.from(this)
                .inflate(R.layout.layout_toolbar, null, false);
        FrameLayout frameLayout = toolbarView.findViewById(R.id.mainContainer);
        LayoutInflater.from(this).inflate(layoutResID, frameLayout);
        setContentView(toolbarView);
        Toolbar toolbar = toolbarView.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (enableBackArrow()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView txtTitle = toolbarView.findViewById(R.id.txtTitle);
        txtTitle.setText(title());

    }

    protected String title(){
        return "";
    }

    protected boolean enableBackArrow() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
