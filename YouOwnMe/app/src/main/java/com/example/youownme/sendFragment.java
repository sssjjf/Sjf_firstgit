package com.example.youownme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youownme.Receiver.ReceiversBank;

import java.util.ArrayList;
import java.util.List;


public class sendFragment extends Fragment {
    public  static  ArrayList<Sender> senderlist=new ArrayList<>();
    public  static SendersBank sendersBank;
    public static sendAdapter adapter;
    private EditText editText;
    private Button button;
    private static String[] sendname={"赵云","关羽","张飞","刘备"};
    private static String[] sendtime={"2018年08月05日","2019年05月05日","2019年10月07日","2020年01月05日"};
    private static String[] sendmoney={"300￥","400￥","500￥","600￥"};

    public sendFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_send, container, false);
        Intidata();

        adapter=new sendAdapter(this.getContext(),R.layout.send_item, senderlist);
        ListView sendListView=view.findViewById(R.id.send_list_view);
        sendListView.setAdapter(adapter);//给ListView添加适配器
        sendFragment.this.registerForContextMenu(sendListView);//给ListView注册上下文菜单

        editText =view.findViewById(R.id.send_search_EditView);
        button =view.findViewById(R.id.send_search);
        button.setOnClickListener(new sendSeachClick());
        return view;
    }

    //定义上下文菜单
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v==this.getActivity().findViewById(R.id.send_list_view)){
            menu.setHeaderTitle("操作");
            menu.add(1,1,1,"新增");
            menu.add(1,2,1,"删除");
            menu.add(1,3,1,"修改");
        }
    }

    //响应上下文菜单
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final int position = menuinfo.position;
        if(item.getGroupId()==1) {
            Sender sender = senderlist.get(position);
            switch (item.getItemId()) {
                case 1:
                    Intent intent = new Intent(this.getActivity(), Sender_make_Activity.class);
                    intent.putExtra("name", sender.getName());
                    intent.putExtra("time", sender.getTime());
                    intent.putExtra("money", sender.getMoney());
                    intent.putExtra("marked", "sender");
                    startActivityForResult(intent, 1);
                    break;
                case 2:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                    builder.setTitle("提示");
                    builder.setIcon(R.drawable.icon1);
                    builder.setMessage("是否确定删除" + senderlist.get(position).getName() + "?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            senderlist.remove(position);
                            sendersBank.Save();
                            adapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.show();
                    break;
                case 3:
                    Intent intent1 = new Intent(this.getActivity(), Sender_make_Activity.class);
                    intent1.putExtra("name", sender.getName());
                    intent1.putExtra("time", sender.getTime());
                    intent1.putExtra("money", sender.getMoney());
                    intent1.putExtra("position", position);
                    intent1.putExtra("marked", "sender");
                    startActivityForResult(intent1, 2);
                    break;
                default:
                    break;
            }
            return super.onContextItemSelected(item);
        }
        return false;
    }

    //对Sender——make——Activity中传回的数据进行响应
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
        {
            switch (requestCode){
                case 1:
                    String name=data.getStringExtra("name");
                    String time=data.getStringExtra("time");
                    String money=data.getStringExtra("money");
                    senderlist.add(new Sender(name,time,money));
                    adapter.notifyDataSetChanged();
                    sendersBank.Save();
                    Toast.makeText(this.getContext(), "新增成功",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    String name1=data.getStringExtra("name");
                    String time1=data.getStringExtra("time");
                    String money1=data.getStringExtra("money");
                    int position1=data.getIntExtra("position",0);
                    senderlist.get(position1).setName(name1);
                    senderlist.get(position1).setTime(time1);
                    senderlist.get(position1).setMoney(money1);
                    adapter.notifyDataSetChanged();
                    sendersBank.Save();
                    Toast.makeText(this.getContext(), "修改成功",Toast.LENGTH_SHORT).show();
                default:
                    break;
            }
        }
    }

    //自定义适配器
    public static class sendAdapter extends ArrayAdapter<Sender>{
        private int resourceId;

        public sendAdapter(@NonNull Context context, int resource, @NonNull List<Sender> objects) {
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
            ((TextView) view.findViewById(R.id.send_name_textView)).setText(sender.getName());
            ((TextView) view.findViewById(R.id.send_time_textView)).setText(sender.getTime());
            ((TextView) view.findViewById(R.id.send_money_textView)).setText(sender.getMoney());
            return view;
        }
    }

    //初始化数据senderlist
    public  void Intidata(){
        sendersBank =new SendersBank(this.getContext());
        sendersBank.Load();
        senderlist = sendersBank.getSender();
        if(senderlist.size()==0) {
            for (int i = 0; i < sendmoney.length; i++) {
                senderlist.add(new Sender(sendname[i], sendtime[i], sendmoney[i]));
            }
        }
    }

    private class sendSeachClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String text = editText.getText().toString();
            int count=0;
            for(Sender sender:senderlist){
                if(sender.getTime().equals(text)||sender.getMoney().equals(text)||sender.getName().equals(text)){
                    count++;
                }
            }
            AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                    .setTitle("查询"+text+"一共有"+count+"条结果!")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getContext(), "查询结束", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create();
            alertDialog.show();
        }
    }
}
