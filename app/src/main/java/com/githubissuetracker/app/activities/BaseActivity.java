package com.githubissuetracker.app.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.githubissuetracker.app.R;
import com.githubissuetracker.app.viewmodels.BaseViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {

    V viewModel;
    private View loaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(viewModelTypeClass());
        initObserver(viewModel);
    }

    abstract Class<V> viewModelTypeClass();

    private void initObserver(BaseViewModel viewModel) {
        viewModel.getLdLoader().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoader();
                } else {
                    hideLoader();
                }
            }
        });

        viewModel.getLdToastMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                showToast(message);
            }
        });

        viewModel.getLdNetworkDialog().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showNetworkAlert();
                } else {
                    if (alertDialog != null && alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                }
            }
        });
    }

    public void showToast(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    protected void showLoader() {
        if (loaderView == null) {
            loaderView = LayoutInflater.from(this)
                    .inflate(R.layout.loader_view, null, false);
            getWindow().addContentView(loaderView, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        loaderView.setVisibility(View.VISIBLE);

    }

    protected void hideLoader() {
        if (loaderView != null) {
            loaderView.setVisibility(View.GONE);
        }
    }

    private AlertDialog alertDialog;

    private void showNetworkAlert() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.network_error))
                .setMessage(getString(R.string.network_error_message))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                        BaseActivity.this.finish();
                    }
                })
                .show();
    }

}
