package com.devmina.droid_ant.numberitemclicks;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener{
private List<Data> mDataList;
private RecyclerView mRecyclerView;
private TextView mCounter;
private int position,counter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCounter=findViewById(R.id.counter);

        //fill the list
        mDataList=fill_List();

        mRecyclerView=findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        //set recycler view in horizontal
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        BAdapter bAdapter =new BAdapter(mDataList,getApplication(),this);
        mRecyclerView.setAdapter(bAdapter);

        //restore the pos and the counter  when the orientation is changed
        if(savedInstanceState!=null){
            mCounter.setText("Item   " +savedInstanceState.getString("pos")+
                    "  N°clicks   "+savedInstanceState.getString("count"));
        }





    }
    //TODO set adapter 

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
//fill the list
    private List<Data> fill_List() {
        List<Data> data=new ArrayList<>();

            data.add(new Data(R.drawable.flower));
            data.add(new Data(R.drawable.dahlia));
            data.add(new Data(R.drawable.hydrangea));
            data.add(new Data(R.drawable.rose));
            data.add(new Data(R.drawable.wedelia));




        return data;

    }
     //get the item's position and the number of clicks
    @Override
    public void clickListener(int pos, int count) {
        position=pos;
        counter=count;

        mCounter.setText("Item  "+pos+"  N°clicks  "+count);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
             outState.putInt("pos",position);
             outState.putInt("count",counter);
    }
}
