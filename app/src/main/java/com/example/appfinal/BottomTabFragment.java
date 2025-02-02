package com.example.appfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomTabFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
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
    public BottomTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomTabFragment newInstance(String param1, String param2) {
        BottomTabFragment fragment = new BottomTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_tab, container, false);
        mIdati =view.findViewById(R.id.idati);
        mIchaxun = view.findViewById(R.id.ichaxun);
        mIbaike = view.findViewById(R.id.ibaike);
        mIshezhi = view.findViewById(R.id.ishezhi);
        mIriji = view.findViewById(R.id.iriji);
        mTdati = view.findViewById(R.id.tdati);
        mTchaxun = view.findViewById(R.id.tchaxun);
        mTbaike = view.findViewById(R.id.tbaike);
        mTshezhi = view.findViewById(R.id.tshezhi);
        mTriji = view.findViewById(R.id.triji);
        mIdati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AnswerActivity.class);

                startActivity(intent);
            }
        });
        mTdati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AnswerActivity.class);

                startActivity(intent);
            }
        });
        mIchaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SelectActivity.class);
                startActivity(intent);
            }
        });
        mTchaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SelectActivity.class);
                startActivity(intent);
            }
        });
        mTbaike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EncyclopediaActivity.class);
                startActivity(intent);
            }
        });
        mIbaike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EncyclopediaActivity.class);
                startActivity(intent);
            }
        });
        mIshezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        mTshezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        mTriji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DiaryActivity.class);
                startActivity(intent);
            }
        });
        mIriji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DiaryActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}