package com.example.appfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.example.appfinal.DiarySQL.DiaryBean;
import com.example.appfinal.DiarySQL.DiaryViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiaryActivity extends AppCompatActivity {
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
    private ImageView mAdditem;
    private ListView mDiaryview;
    private DiaryViewModel diaryViewModel;

    List<DiaryBean> diaryList=new ArrayList<>();
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        initViews();
        adapter = new MyAdapter(diaryList);
        mDiaryview.setAdapter(adapter);
        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);
        setListener();
        SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo2", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        diaryViewModel.getDiariesByUname(username).observe(this, newDiaries -> {
            // 更新列表数据
            diaryList.clear();
            if (newDiaries != null) {
                diaryList.addAll(newDiaries);

            }
            adapter.notifyDataSetChanged();
        });
        // 设置长按事件监听器
        mDiaryview.setOnItemLongClickListener((parent, view, position, id) -> {
            showOptionsDialog(position);
            return true;
        });
    }
    private void showOptionsDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择操作");

        String[] options = {"修改", "删除"};
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                // 修改
                showEditDialog(position);
            } else if (which == 1) {
                // 删除
                diaryViewModel.delete(diaryList.get(position));
                Toast.makeText(DiaryActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    private void showEditDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改日记");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_edit_diary, (ViewGroup) findViewById(android.R.id.content), false);
        EditText editTitle = viewInflated.findViewById(R.id.editTitle);
        EditText editContent = viewInflated.findViewById(R.id.editContent);

        // 设置当前日记的内容
        DiaryBean diary = diaryList.get(position);
        editTitle.setText(diary.getTitle());
        editContent.setText(diary.getContent());

        builder.setView(viewInflated);

        builder.setPositiveButton("保存", (dialog, which) -> {
            String newTitle = editTitle.getText().toString();
            String newContent = editContent.getText().toString();
            Calendar calendar = Calendar.getInstance();
            // 更新日记内容
            diary.setTitle(newTitle);
            diary.setContent(newContent);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // 注意：月份从0开始，需要加1
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            String currentTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
            diary.setDate(currentTime);
            diaryViewModel.update(diary);
            Toast.makeText(DiaryActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("取消", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    class MyAdapter extends BaseAdapter {
        private  List<DiaryBean> diaryList;
        public MyAdapter(List<DiaryBean> diaryList) {
            this.diaryList = diaryList;
        }

        @Override
        public int getCount() {

            return diaryList.size();
        }

        @Override
        public Object getItem(int i) {
            return diaryList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary, parent, false);
            }
            TextView titleTextView = convertView.findViewById(R.id.title);
            TextView contentTextView = convertView.findViewById(R.id.content);
            TextView dateTextView = convertView.findViewById(R.id.date);

            DiaryBean diary = diaryList.get(position);
            titleTextView.setText(diary.getTitle());
            contentTextView.setText(diary.getContent());
            dateTextView.setText(diary.getDate());

            return convertView;
        }
    }

    private void setListener() {
        mAdditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, AddDiaryActivity.class);
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
        mIriji.setImageResource(R.drawable.riji2);
        mTriji.setTextColor(getResources().getColor(R.color.colorPrimary));
        mIriji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
        mTriji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });

        mAdditem = findViewById(R.id.additem);
        mDiaryview = findViewById(R.id.diaryview);
    }
}