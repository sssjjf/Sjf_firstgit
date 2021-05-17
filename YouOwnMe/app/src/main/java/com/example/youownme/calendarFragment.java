package com.example.youownme;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class calendarFragment extends Fragment {

    public calendarFragment() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  static String mainItem_time;
    private ArrayList<Sender> reviewList =new ArrayList<Sender>();
    private  ReviewAdapter reviewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_main_item, container, false);

        //初始化time为系统时间
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        String markMonth=new String();
        String markDay=new String();
        if(month<10)
            markMonth="0";
        if(date<10)
            markDay="0";
        mainItem_time=year+"年"+markMonth+month+"月"+markDay+date+"日";
        System.out.println(mainItem_time);

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new myCalenderSelect());

        ListView listView = view.findViewById(R.id.new_Listview);
        reviewAdapter = new ReviewAdapter( this.getContext(),R.layout.
                review_item,reviewList);
        listView.setAdapter(reviewAdapter);
        return view;
    }
    public class ReviewAdapter extends ArrayAdapter<Sender> {
        private int resourceId;

        public ReviewAdapter(@NonNull Context context, int resource, @NonNull List<Sender> objects) {
            super(context, resource, objects);
            this.resourceId=resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Sender sender = getItem(position);//获取当前项的实例
            View view;
            if(null==convertView)
                view = LayoutInflater.from(getContext()).inflate(this.resourceId, parent, false);
            else
                view=convertView;
            ((TextView) view.findViewById(R.id.review_textView1)).setText(sender.getName());
            ((TextView) view.findViewById(R.id.review_textView2)).setText(sender.getTime());
            ((TextView) view.findViewById(R.id.review_textView3)).setText(sender.getMoney());
            return view;
        }
    }

    private class myCalenderSelect implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            String markMonth=new String();
            String markDay=new String();
            if(month<10)
                markMonth="0";
            if(dayOfMonth<10)
                markDay="0";
            mainItem_time=new String (year+"年"+markMonth+(month+1)+"月"+markDay+dayOfMonth+"日");
            System.out.println(mainItem_time);
            reviewList.clear();
            for(Sender sender:sendFragment.senderlist){
                if(sender.getTime().equals(mainItem_time)){
                    reviewList.add(new Sender(sender.getName(),mainItem_time,"随礼"+sender.getMoney()));
                }
            }
            for(ReceiveItem receive:receiveFragment.group_list){
                if(receive.getReceiveItemTime().equals(mainItem_time)){
                    reviewList.add(new Sender(receive.getReceiveItemName(),mainItem_time,"收礼"));
                }
            }
            if(reviewList.size()==0){
                reviewList.add(new Sender("xxx",mainItem_time,"没有人情往来!"));
            }
            reviewAdapter.notifyDataSetChanged();
        }
    }
}