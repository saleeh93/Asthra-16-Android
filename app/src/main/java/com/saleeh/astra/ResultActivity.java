package com.saleeh.astra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.saleeh.astra.fragments.ParticipientFragment;
import com.saleeh.astra.fragments.ParticipientResultFragment;

public class ResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partcipient);
//        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.container, ParticipientResultFragment.newInstance(getIntent().getStringExtra("id"), getIntent().getStringExtra("name"))).commit();
        setUp();
    }
}
