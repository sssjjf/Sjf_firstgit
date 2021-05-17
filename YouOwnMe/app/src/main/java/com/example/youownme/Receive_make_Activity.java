package com.example.youownme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.youownme.Receiver.Receivers;

import java.util.ArrayList;

public class Receive_make_Activity extends AppCompatActivity {
    private Button button_make_ok;
    private Button button_make_cancel;
    private EditText receiveItem_make;
    private EditText receiveTime_make;
    private  String ReceiveFileName;
    private  int position;
    private  String mark = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_make_);

        Intent intent = getIntent();
        String receiveItem = intent.getStringExtra("receiveItem");
        String receiveTime = intent.getStringExtra("receiveTime");
        position = intent.getIntExtra("position",0);
        mark = intent.getStringExtra("marked");

        receiveItem_make = this.findViewById(R.id.recevieItem_make_editView);
        receiveTime_make = this.findViewById(R.id.receviewTime_make_editViw);
        if(receiveItem!=null){
            receiveItem_make.setText(receiveItem);
        }
        if(receiveTime!=null){
            receiveTime_make.setText(receiveTime);
        }

        button_make_ok = this.findViewById(R.id.recevie_button_make_ok);
        button_make_cancel = this.findViewById(R.id.recevie_button_make_cancel);
        button_make_ok.setOnClickListener(new myClick());
        button_make_cancel.setOnClickListener(new myClick());
    }

    private class myClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Button button =(Button)v;
            if(button==button_make_ok){
                if(mark.equals("main")){
                    Intent intent =new Intent(Receive_make_Activity.this, MainActivity.class);
                    ArrayList<Receivers> child_list = new  ArrayList<Receivers>();
                    Receivers receivers =new Receivers("张三", receiveTime_make.getText().toString(),"100￥");
                    child_list.add(receivers);
                    receiveFragment.group_list.add(new ReceiveItem(receiveItem_make.getText().toString(),
                            receiveTime_make.getText().toString(),child_list));
                    receiveFragment.receiveBank.Save();
                    receiveFragment.adapter.notifyDataSetChanged();
                    setResult(1);
                }else {
                    Intent intent = new Intent(Receive_make_Activity.this, receiveFragment.class);
                    intent.putExtra("receiveItem", receiveItem_make.getText().toString());
                    intent.putExtra("receiveTime", receiveTime_make.getText().toString());
                    intent.putExtra("position",position);
                    setResult(RESULT_OK, intent);
                }
            }
            else {}
            finish();
        }
    }
}