package com.example.appfinal;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.appfinal.UserSQL.UserBean;
import com.example.appfinal.UserSQL.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mRegister;
    private Button mExit;
    private TextView mTextView4;
    private EditText mTel;
    private TextView mTextView5;
    private ImageView mHeadimg;
    private EditText mAddress;
    private UserViewModel userViewModel;
    private String[] head={"head1","head2","head3","head4","head5","head6","head7","head8","head9","head10"};
    private int headid=1,headid2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        initViews();
        setListeners();

    }

    private void setListeners() {
        mHeadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通过图像文件名获取图片资源
                mHeadimg.setImageResource(getResources().getIdentifier(head[headid],"drawable",getPackageName()));
                headid2=headid;
                headid=(headid+1)%10;
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!"".equals(mUsername.getText().toString())&&!"".equals(mPassword.getText().toString()))
                {
                    String username=mUsername.getText().toString();
                    userViewModel.getUserByUsername(username, user -> {
                        if (user != null) {
                            runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "用户名已存在" , Toast.LENGTH_SHORT).show());
                        } else {
                            UserBean user1=new UserBean();
                            user1.setHead(head[headid2]);
                            user1.setUname(mUsername.getText().toString());
                            user1.setPassword(mPassword.getText().toString());
                            user1.setTel(mTel.getText().toString());
                            user1.setAddress(mAddress.getText().toString());
                            userViewModel.insert(user1);
                            runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "注册成功" , Toast.LENGTH_SHORT).show());
                            finish();
                        }
                    });

                }
                else {
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "用户名或密码不能为空" , Toast.LENGTH_SHORT).show());

                }
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
        mHeadimg = findViewById(R.id.headimg);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mRegister = findViewById(R.id.register);
        mTextView4 = findViewById(R.id.textView4);
        mTel = findViewById(R.id.tel);
        mTextView5 = findViewById(R.id.textView5);
        mAddress = findViewById(R.id.address);
        mExit=findViewById(R.id.exit);
    }
}