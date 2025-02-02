package com.example.appfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EncyclopediaActivity extends AppCompatActivity {
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
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);
        initViews();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize item list and add some items
        itemList = new ArrayList<>();
        itemList.add(new Item(R.raw.video1));
        itemList.add(new Item(R.raw.video2));
        itemList.add(new Item(R.raw.video3));
        itemList.add(new Item(R.raw.video4));
        itemList.add(new Item(R.raw.video5));
        itemList.add(new Item(R.raw.video6));
        adapter = new MyAdapter(itemList, this);
        recyclerView.setAdapter(adapter);
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
        mIbaike.setImageResource(R.drawable.baike2);
        mTbaike.setTextColor(getResources().getColor(R.color.colorPrimary));
        mIbaike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
        mTbaike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            for (int i = 0; i < adapter.getItemCount(); i++) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
                if (viewHolder instanceof MyAdapter.MyViewHolder) {
                    ((MyAdapter.MyViewHolder) viewHolder).releasePlayer();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            for (int i = 0; i < adapter.getItemCount(); i++) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
                if (viewHolder instanceof MyAdapter.MyViewHolder) {
                    ((MyAdapter.MyViewHolder) viewHolder).releasePlayer();
                }
            }
        }
    }
}