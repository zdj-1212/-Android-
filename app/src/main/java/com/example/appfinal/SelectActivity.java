package com.example.appfinal;

import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SelectActivity extends AppCompatActivity {
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
    private ListView mRubbishlist;
    private RubbishBean.ResultDTO result1;
    private EditText mSearch1;
    private Button mSearch2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        initViews();
        setListener();
    }

    private void setListener() {
        mSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getnetData(mSearch1.getText().toString());
            }
        });
    }

    private RubbishBean.ResultDTO parsenetData(String data) throws JSONException{
        JSONObject jsonObject=new JSONObject(data);
        String code=jsonObject.getString("code");
        if(code.equals("200")){
            String result=jsonObject.getString("result");
            Gson gson=new Gson();
            RubbishBean.ResultDTO resultDTO=gson.fromJson(result,RubbishBean.ResultDTO.class);
            return resultDTO;
       }
        else {
            Toast.makeText(this, "未查询到该垃圾数据", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    private void getnetData(String value) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String tianapi_data = "";
                try {
                    URL url = new URL( "https://apis.tianapi.com/lajifenlei/index");
                    HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                    OutputStream outputStream = conn.getOutputStream();
                    String content = "key=de543e1bb0062b5a8592d4e39d8ca08b&word="+value;
                    outputStream.write(content.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
                    BufferedReader bufferedReader = new BufferedReader (inputStreamReader);
                    StringBuilder tianapi = new StringBuilder();
                    String temp = null;
                    while ( null != (temp = bufferedReader.readLine())){
                        tianapi.append(temp);
                    }
                    tianapi_data = tianapi.toString();
                    inputStream.close();
                    RubbishBean.ResultDTO resultDTO=parsenetData(tianapi_data);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            result1=resultDTO;
                            mRubbishlist.setAdapter(new MyAdapter());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(tianapi_data);
            }
        }.start();

    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return result1.getList().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Myholder holder;
            if(view == null)
            {
                holder = new Myholder();
                view = getLayoutInflater().inflate(R.layout.item_select,null);
                holder.mName=view.findViewById(R.id.name);
                holder.mExplain=view.findViewById(R.id.explain);
                holder.mContain=view.findViewById(R.id.contain);
                holder.mTip=view.findViewById(R.id.tip);
                view.setTag(holder);
            }
            else {
                holder = (Myholder) view.getTag();
            }
            holder.mName.setText(result1.getList().get(i).getName());
            holder.mExplain.setText("解释："+result1.getList().get(i).getExplain());
            holder.mContain.setText("常见："+result1.getList().get(i).getContain());
            holder.mTip.setText("提示"+result1.getList().get(i).getTip());
            return view;
        }
    }
    class Myholder{
        private TextView mName;
        private TextView mExplain;
        private TextView mContain;
        private TextView mTip;

    }
    private void initViews() {
        mRubbishlist=findViewById(R.id.rubbishlist);
        mSearch1=findViewById(R.id.search1);
        mSearch2=findViewById(R.id.search2);

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
        mIchaxun.setImageResource(R.drawable.chaxun2);
        mTchaxun.setTextColor(getResources().getColor(R.color.colorPrimary));
        mIchaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
        mTchaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
            }
        });
    }
}