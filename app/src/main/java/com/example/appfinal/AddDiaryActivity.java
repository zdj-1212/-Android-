package com.example.appfinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.Guideline;
import androidx.lifecycle.ViewModelProvider;
import com.example.appfinal.DiarySQL.DiaryBean;
import com.example.appfinal.DiarySQL.DiaryViewModel;

import java.util.Calendar;

public class AddDiaryActivity extends AppCompatActivity {
    private EditText mEdittitle;
    private EditText mEditcontent;
    private Button mSubmit;
    private DiaryViewModel diaryViewModel;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        initViews();
        SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo2", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);
        setLinstener();
    }

    private void setLinstener() {
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=mEdittitle.getText().toString();
                String content=mEditcontent.getText().toString();
                if ("".equals(title)&& "".equals(content))
                {
                    Toast.makeText(AddDiaryActivity.this, "标题或内容不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    DiaryBean newDiary = new DiaryBean();
                    newDiary.setTitle(title);
                    newDiary.setContent(content);
                    newDiary.setUname(username);
                    Calendar calendar = Calendar.getInstance();

                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1; // 注意：月份从0开始，需要加1
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    int second = calendar.get(Calendar.SECOND);
                    String currentTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
                    newDiary.setDate(currentTime);
                    diaryViewModel.insert(newDiary);
                    Toast.makeText(AddDiaryActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initViews() {
        mEdittitle = findViewById(R.id.edittitle);
        mEditcontent = findViewById(R.id.editcontent);
        mSubmit = findViewById(R.id.submit);
    }
}