package com.saleeh.astra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.saleeh.astra.fragments.HomeFragment;
import com.saleeh.astra.fragments.ParticipientFragment;

public class PartcipientActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partcipient);
        //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.container, ParticipientFragment.newInstance(getIntent().getStringExtra("id"), getIntent().getStringExtra("name"))).
                commit();
        setUp();
    }
}
