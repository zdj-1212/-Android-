package com.example.appfinal;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.appfinal.UserSQL.UserViewModel;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView mWelcome;
    private Button mTiaoguo;
    Thread thread;
int t=4;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                    try {
                        for (int i=1;i<=5;i++)
                        {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                fuzhi();
                            }
                        });
                        }
                    } catch (InterruptedException e) {
                        System.out.println("线程已中断");
                    }

                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        thread.start();
        mTiaoguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.interrupt();
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
public void fuzhi(){
    mTiaoguo.setText("跳过"+t--);
}
    private void initViews() {
        mWelcome = findViewById(R.id.welcome);
        mTiaoguo = findViewById(R.id.tiaoguo);
    }

}