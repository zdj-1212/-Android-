package com.example.appfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.Guideline;
import androidx.lifecycle.ViewModelProvider;
import com.example.appfinal.UserSQL.UserBean;
import com.example.appfinal.UserSQL.UserViewModel;

public class UpdatainfoActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private Button mUpdata;
    private EditText mTel;
    private EditText mAddress;
    private ImageView mHeadimg;
    private Button mExit;
    private UserViewModel userViewModel;
    private String[] head={"head1","head2","head3","head4","head5","head6","head7","head8","head9","head10"};
    private int headid=1,headid2=0;
    private String username="";
    private boolean ishead=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatainfo);
        initViews();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo2", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        userViewModel.getUserByUsername(username, user -> {
            if (user != null) {
                mHeadimg.setImageResource(getResources().getIdentifier(user.getHead(),"drawable",getPackageName()));
                mUsername.setText(user.getUname());
                mPassword.setText(user.getPassword());
                mTel.setText(user.getTel());
                mAddress.setText(user.getAddress());

            } else {
                runOnUiThread(() -> Toast.makeText(UpdatainfoActivity.this, "一个未知的错误" , Toast.LENGTH_SHORT).show());
            }
        });
        setListener();
    }

    private void setListener() {
        mUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userViewModel.getUserByUsername(username, user -> {
                    if (user != null) {
                        user.setUname(mUsername.getText().toString());
                        user.setPassword(mPassword.getText().toString());
                        user.setTel(mTel.getText().toString());
                        if(ishead){
                            user.setHead(head[headid2]);
                        }
                        user.setAddress(mAddress.getText().toString());
                        userViewModel.update(user);
                        runOnUiThread(() ->{
                            Toast.makeText(UpdatainfoActivity.this, "个人信息修改成功，请重新登录" , Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(UpdatainfoActivity.this,LoginActivity.class);
                            startActivity(intent);
                        } );
                    } else {
                        runOnUiThread(() -> Toast.makeText(UpdatainfoActivity.this, "一个未知的错误" , Toast.LENGTH_SHORT).show());
                    }
                });
            }
        });
        mHeadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHeadimg.setImageResource(getResources().getIdentifier(head[headid],"drawable",getPackageName()));
                headid2=headid;
                headid=(headid+1)%10;
                ishead=true;
            }
        });
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mUpdata = findViewById(R.id.updata);
        mTel = findViewById(R.id.tel);
        mAddress = findViewById(R.id.address);
        mHeadimg = findViewById(R.id.headimg);
        mExit = findViewById(R.id.exit);
    }
}