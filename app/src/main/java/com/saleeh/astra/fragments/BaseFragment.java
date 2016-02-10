package com.saleeh.astra.fragments;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

/**
 * Created by saleeh on 2/11/16.
 */
public class BaseFragment extends Fragment {
    public void showMessage(String error) {
        if (getView() != null) {
            Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
        }
    }
}
