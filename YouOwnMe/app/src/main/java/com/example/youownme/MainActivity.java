package com.example.youownme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public  class MainActivity extends AppCompatActivity {
    private String[] items2={"收礼","随礼"};
    private  AlertDialog alertDialog;
    private  String time;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPageAdapter myPageAdapter = new MyPageAdapter(this.getSupportFragmentManager());
        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        datas.add(new receiveFragment());
        datas.add(new calendarFragment());
        datas.add(new sendFragment());
        myPageAdapter.setData(datas);

        ArrayList<String> titles = new ArrayList<String>();
        titles.add("$收礼");
        titles.add("");
        titles.add("$随礼");

        myPageAdapter.setTitles(titles);

        TabLayout tabLayout = findViewById(R.id.table_layout_view);
        ViewPager viewPager = findViewById(R.id.viewpager_view);
        viewPager.setAdapter(myPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab_one =tabLayout.getTabAt(1);
        tab_one.setIcon(R.drawable.d5);
        viewPager.setCurrentItem(1);

        Button button = findViewById(R.id.main_second_button);
        button.setOnClickListener(new click());

        alertDialog = new AlertDialog.Builder(this)
                .setTitle("选择您要添加的类型")
                //.setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(items2, 0, new DialogInterface.OnClickListener() {//添加单选框
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        index = i;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent;
                        switch (index){
                            case 0:
                                intent=new Intent(MainActivity.this,Receive_make_Activity.class);
                                intent.putExtra("marked","main");
                                intent.putExtra("receiveTime",time);
                                startActivity(intent);
                                break;
                            case 1:
                                intent=new Intent(MainActivity.this,Sender_make_Activity.class);
                                intent.putExtra("marked","main");
                                intent.putExtra("time",time);
                                startActivity(intent);
                                break;
                            default:break;
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> datas;
        ArrayList<String> titles;

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setData(ArrayList<Fragment> datas) {
            this.datas = datas;
        }

        public void setTitles(ArrayList<String> titles) {
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            if (datas != null)
                return datas.get(position);
            else
                return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles == null ? null : titles.get(position);
        }

        @Override
        public int getCount() {
            if (titles != null)
                return titles.size();
            return 0;
        }
    }

    private class click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            time= calendarFragment.mainItem_time;
            alertDialog.show();
        }
    }
}
