package com.saleeh.astra;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.parse.ParseInstallation;


/**
 * Created by saleeh on 2/12/16.
 */
public class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    public void setUp() {
        final ParseInstallation in = ParseInstallation.getCurrentInstallation();
        Integer theme = (Integer) in.get("theme");
        if (theme != null) {
            setAppTheme(Integer.valueOf(theme));
        }


    }

    private void setAppTheme(int color) {
        if (toolbar != null)
            toolbar.setBackgroundColor(color);
        else
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        setTheme(R.style.AETheme);
        if (Build.VERSION.SDK_INT >= 21) {

            getWindow().setNavigationBarColor(color);
            getWindow().setStatusBarColor(color);
        }
    }
}
