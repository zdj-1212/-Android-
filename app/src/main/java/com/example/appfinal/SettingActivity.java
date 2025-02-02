package com.example.appfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.example.appfinal.UserSQL.UserViewModel;

public class SettingActivity extends AppCompatActivity {
    private ImageView mIdati;
    private ImageView mIchaxun;
    private ImageView mIbaike;
    private ImageView mIshezhi;
    private ImageView mIriji;
    private TextView mTdati;
    private TextView mTchaxun;
    private TextView mTbaike;
    private TextView mTshezhi;
    private TextView mTriji;
    private FragmentManager manager;
    private BottomTabFragment mBottom;

    private ImageView mHead;
    private TextView mWelcome;
    private Button mUpdatainfo;
    private Button mExitlogin;
    private Button mExitapp;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViews();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo2", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        userViewModel.getUserByUsernameAndPassword(username, password, user -> {
            if (user != null) {
                mWelcome.setText("欢迎您，" + user.getUname());
                mHead.setImageResource(getResources().getIdentifier(user.getHead(),"drawable",getPackageName()));
            } else {
                Toast.makeText(this, "出现未知错误", Toast.LENGTH_SHORT).show();
            }
        });

        setListener();
    }

    private void setListener() {
        mUpdatainfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, UpdatainfoActivity.class);
                startActivity(intent);
            }
        });
        mExitapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
        mExitlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        manager=getSupportFragmentManager();
        mBottom = (BottomTabFragment) manager.findFragmentById(R.id.bottom);
        mIdati =mBottom.getView().findViewById(R.id.idati);
        mIchaxun = mBottom.getView().findViewById(R.id.ichaxun);
        mIbaike = mBottom.getView().findViewById(R.id.ibaike);
        mIshezhi = mBottom.getView().findViewById(R.id.ishezhi);
        mIriji = mBottom.getView().findViewById(R.id.iriji);
        mTdati = mBottom.getView().findViewById(R.id.tdati);
        mTchaxun = mBottom.getView().findViewById(R.id.tchaxun);
        mTbaike = mBottom.getView().findViewById(R.id.tbaike);
        mTshezhi = mBottom.getView().findViewById(R.id.tshezhi);
        mTriji = mBottom.getView().findViewById(R.id.triji);
        mIshezhi.setImageResource(R.drawable.shezhi2);
        mTshezhi.setTextColor(getResources().getColor(R.color.colorPrimary));
        mIshezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
        mTshezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });

        mHead = findViewById(R.id.head);
        mWelcome = findViewById(R.id.welcome);
        mUpdatainfo = findViewById(R.id.updatainfo);
        mExitlogin = findViewById(R.id.exitlogin);
        mExitapp = findViewById(R.id.exitapp);
    }
}