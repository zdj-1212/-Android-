package com.example.appfinal;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.FragmentManager;

public class AnswerActivity extends AppCompatActivity {

    private BottomTabFragment mBottom;
    private RadioGroup mOne;
    private RadioGroup mTwo;
    private RadioGroup mThree;
    private RadioGroup mFour;
    private RadioGroup mFive;
    private RadioGroup mSix;
    private RadioGroup mSeven;
    private RadioGroup mEight;
    private RadioGroup mNine;
    private RadioGroup mTen;
    private Button mTijiao;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        initViews();
        setListener();
    }

    private void setListener() {
        mTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int scroe = 0;
                if(mOne.getCheckedRadioButtonId()==-1||mTwo.getCheckedRadioButtonId()==-1||mThree.getCheckedRadioButtonId()==-1||mFour.getCheckedRadioButtonId()==-1||mFive.getCheckedRadioButtonId()==-1||mSix.getCheckedRadioButtonId()==-1||mSeven.getCheckedRadioButtonId()==-1||mEight.getCheckedRadioButtonId()==-1||mNine.getCheckedRadioButtonId()==-1||mTen.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(AnswerActivity.this,"请选择答案",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(mOne.getCheckedRadioButtonId()==R.id.one1){
                        scroe+=10;
                    }
                    if(mTwo.getCheckedRadioButtonId()==R.id.two1){
                        scroe+=10;
                    }
                    if(mThree.getCheckedRadioButtonId()==R.id.three4){
                        scroe+=10;
                    }
                    if(mFour.getCheckedRadioButtonId()==R.id.four4){
                        scroe+=10;
                    }
                    if(mFive.getCheckedRadioButtonId()==R.id.five4){
                        scroe+=10;
                    }
                    if(mSix.getCheckedRadioButtonId()==R.id.six3){
                        scroe+=10;
                    }
                    if(mSeven.getCheckedRadioButtonId()==R.id.seven4){
                        scroe+=10;
                    }
                    if(mEight.getCheckedRadioButtonId()==R.id.eight4){
                        scroe+=10;
                    }
                    if(mNine.getCheckedRadioButtonId()==R.id.nine1){
                        scroe+=10;
                    }
                    if(mTen.getCheckedRadioButtonId()==R.id.ten3){
                        scroe+=10;
                    }
                    Toast.makeText(AnswerActivity.this,"您的分数为："+scroe,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initViews() {
        manager=getSupportFragmentManager();
        mBottom = (BottomTabFragment) manager.findFragmentById(R.id.bottom);
        mOne = findViewById(R.id.one);
        mTwo = findViewById(R.id.two);
        mThree = findViewById(R.id.three);
        mFour = findViewById(R.id.four);
        mFive = findViewById(R.id.five);
        mSix = findViewById(R.id.six);
        mSeven = findViewById(R.id.seven);
        mEight = findViewById(R.id.eight);
        mNine = findViewById(R.id.nine);
        mTen = findViewById(R.id.ten);
        mTijiao = findViewById(R.id.tijiao);

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
        mIdati.setImageResource(R.drawable.dati2);
        mTdati.setTextColor(getResources().getColor(R.color.colorPrimary));
        mIdati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
        mTdati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
    }
}