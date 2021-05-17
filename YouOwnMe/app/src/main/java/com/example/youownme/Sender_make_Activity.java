package com.example.youownme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sender_make_Activity extends AppCompatActivity {
    private Button button_make_ok;
    private Button button_make_cancel;
    private EditText name_editView;
    private EditText time_editView;
    private EditText money_editView;
    private String name;
    private String time;
    private String money;
    private String marked;
    private int childPosition;
    private int groupPosition;
    private  int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender_make_);

        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        time=intent.getStringExtra("time");
        money=intent.getStringExtra("money");
        marked = intent.getStringExtra("marked");
        position = intent.getIntExtra("position",0);
        childPosition = intent.getIntExtra("childPosition",0);
        groupPosition = intent.getIntExtra("groupPosition",0);


        name_editView=this.findViewById(R.id.sender_name_editView);
        time_editView=this.findViewById(R.id.sender_time_editView);
        money_editView=this.findViewById(R.id.sender_money_editView);
        if(name!=null)
            name_editView.setText(name);
        if(time!=null)
            time_editView.setText(time);
        if(money!=null)
            money_editView.setText(money);

        button_make_ok=this.findViewById(R.id.button_make_ok);
        button_make_cancel=this.findViewById(R.id.button_make_cancel);
        button_make_ok.setOnClickListener(new myButtonClick());
        button_make_cancel.setOnClickListener(new myButtonClick());
    }

    private class myButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button=(Button)v;
            if(button==button_make_ok) {
                Intent intent;
                if (marked.equals("main")) {
                    intent = new Intent(Sender_make_Activity.this, MainActivity.class);
                    sendFragment.senderlist.add(new Sender(name_editView.getText().toString(),
                            time_editView.getText().toString(), money_editView.getText().toString()));
                    sendFragment.sendersBank.Save();
                    sendFragment.adapter.notifyDataSetChanged();
                    setResult(1);
                } else {
                    if (marked.equals("sender")) {
                        intent = new Intent(Sender_make_Activity.this, sendFragment.class);
                    } else {
                        intent = new Intent(Sender_make_Activity.this, MainActivity.class);
                    }
                    intent.putExtra("name", name_editView.getText().toString());
                    intent.putExtra("time", time_editView.getText().toString());
                    intent.putExtra("money", money_editView.getText().toString());
                    intent.putExtra("childPosition",childPosition);
                    intent.putExtra("groupPosition",groupPosition);
                    intent.putExtra("position",position);
                    setResult(RESULT_OK, intent);
                }
            }
            else {}
            finish();
        }
    }
}