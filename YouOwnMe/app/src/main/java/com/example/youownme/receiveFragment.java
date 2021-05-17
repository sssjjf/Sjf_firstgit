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


import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import android.widget.TextView;
import android.widget.Toast;
import com.example.youownme.Receiver.Receivers;
import java.util.ArrayList;




public class receiveFragment extends Fragment {

    public static ReceiveBank receiveBank;

    private ExpandableListView mListView;
    private LayoutInflater mInflater;
    public static ArrayList<ReceiveItem> group_list;
    public static Adapter adapter;


    private EditText receive_editText;
    private Button receive_search;

    public receiveFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_receive, container, false);
        InitData();
        InitView(view);
        return view;
    }

    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        ExpandableListView.ExpandableListContextMenuInfo info =(ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if(type == ExpandableListView.PACKED_POSITION_TYPE_GROUP){
            menu.setHeaderTitle("操作");
            menu.add(3, 1, 2, "删除");
            menu.add(3, 2, 2, "修改");
        }

        if(type == ExpandableListView.PACKED_POSITION_TYPE_CHILD){
            menu.setHeaderTitle("操作");
            menu.add(2, 1, 2, "新增");
            menu.add(2, 2, 2, "删除");
            menu.add(2, 3, 2, "修改");
        }
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo menuInfo =
                (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        long packedPosition = menuInfo.packedPosition;
        final int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
        final int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);

        if(item.getGroupId()==3){
            if(item.getItemId()==1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                builder.setTitle("提示");
                builder.setIcon(R.drawable.icon1);
                builder.setMessage("是否确定删除" + group_list.get(groupPosition).getReceiveItemName() + "?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        group_list.remove(groupPosition);
                        receiveBank.Save();
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }else{
                Intent intent = new Intent(getContext(), Receive_make_Activity.class);
                intent.putExtra("receiveItem",group_list.get(groupPosition).getReceiveItemName() );
                intent.putExtra("receiveTime",group_list.get(groupPosition).getReceiveItemTime());
                intent.putExtra("marked","re");
                intent.putExtra("position",groupPosition);
                startActivityForResult(intent, 1);
            }
            return super.onContextItemSelected(item);
        }
        if (item.getGroupId()==2) {
            ReceiveItem receive = group_list.get(groupPosition);
            switch (item.getItemId()) {
                case 1:
                    Intent intent1 = new Intent(this.getActivity(), Sender_make_Activity.class);
                    intent1.putExtra("name", receive.getChild_list().get(childPosition).getName());
                    intent1.putExtra("time", receive.getChild_list().get(childPosition).getTime());
                    intent1.putExtra("money",receive.getChild_list().get(childPosition).getMoney());
                    intent1.putExtra("childPosition",childPosition);
                    intent1.putExtra("groupPosition",groupPosition);
                    intent1.putExtra("marked","re");
                    startActivityForResult(intent1, 2);
                    break;
                case 2:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                    builder.setTitle("提示");
                    builder.setIcon(R.drawable.icon1);
                    builder.setMessage("是否确定删除" + group_list.get(groupPosition).getChild_list().get(childPosition).getName() + "?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            group_list.get(groupPosition).getChild_list().remove(childPosition);
                            receiveBank.Save();
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
                    Intent intent3 = new Intent(this.getActivity(), Sender_make_Activity.class);
                    intent3.putExtra("name", receive.getChild_list().get(childPosition).getName());
                    intent3.putExtra("time", receive.getChild_list().get(childPosition).getTime());
                    intent3.putExtra("money",receive.getChild_list().get(childPosition).getMoney());
                    intent3.putExtra("marked","re");
                    intent3.putExtra("childPosition",childPosition);
                    intent3.putExtra("groupPosition",groupPosition);
                    startActivityForResult(intent3, 3);
                default:
                    break;
            }
            return super.onContextItemSelected(item);
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (requestCode){
                case 1:
                    String receiveItemName =data.getStringExtra("receiveItem");
                    String receiveItemTime =data.getStringExtra("receiveTime");
                    int groupPosition = data.getIntExtra("position",0);
                    group_list.get(groupPosition).setReceiveItemName(receiveItemName);
                    group_list.get(groupPosition).setReceiveItemTime(receiveItemTime);
                    receiveBank.Save();
                    adapter.notifyDataSetChanged();
                case 2:
                    String receiverNmae =data.getStringExtra("name");
                    String receiverTime =data.getStringExtra("time");
                    String receiverMoney = data.getStringExtra("money");
                    int childPosition = data.getIntExtra("childPosition",0);
                    int rcgroupPosition = data.getIntExtra("groupPosition",0);
                    group_list.get(rcgroupPosition).getChild_list().add(new Receivers(receiverNmae,receiverTime,receiverMoney));
                    receiveBank.Save();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this.getContext(), "新增成功",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    String receiveItem1 =data.getStringExtra("name");
                    String receiveTime1 =data.getStringExtra("time");
                    String receiveMoney1 = data.getStringExtra("money");
                    int childPosition1 = data.getIntExtra("childPosition",0);
                    int groupPosition1 = data.getIntExtra("groupPosition",0);
                    group_list.get(groupPosition1).getChild_list().get(childPosition1).setName(receiveItem1);
                    group_list.get(groupPosition1).getChild_list().get(childPosition1).setTime(receiveTime1);
                    group_list.get(groupPosition1).getChild_list().get(childPosition1).setMoney(receiveMoney1);
                    receiveBank.Save();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this.getContext(), "修改成功",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

/********************* 顶部搜索栏响应事件********************************/
    private class searchClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String text = receive_editText.getText().toString();
            int count=0;
            for(ReceiveItem rc:group_list){
                if(rc.getReceiveItemName().equals(text)||rc.getReceiveItemTime().equals(text)){
                    count = rc.getChildSize();
                    break;
                }
            }
            AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                    .setTitle("项目"+text+"一共有"+count+"条结果!")
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

    public void InitData(){
        receiveBank = new ReceiveBank(this.getContext());
        receiveBank.Load();
        group_list = receiveBank.getArrayListReceive();
    }

/****************************************************************************/
/*********************************************************************************/
    private void InitView(View view) {

        receive_editText = view.findViewById(R.id.search_EditView);
        receive_search = view.findViewById(R.id.receive_search);
        receive_search.setOnClickListener(new searchClick());
        mListView = (ExpandableListView) view.findViewById(R.id.receive_ListView);
        this.registerForContextMenu(mListView);
        mInflater = LayoutInflater.from(view.getContext());

        adapter = new Adapter();
        mListView.setDivider(null);

        //ExpandableListView的组展开监听
        mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

         //ExpandableListView的组合拢监听
        mListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        mListView.setAdapter(adapter);
    }


    public  class Adapter extends BaseExpandableListAdapter {
        //获取子元素对象
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return group_list.get(groupPosition).getChild_list().get(childPosition);
        }
        //获取子元素Id
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return groupPosition+childPosition;
        }
        //加载子元素并显示
        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            View view=null;
            ChildHolder childholder = null;
            if(convertView!=null){
                view = convertView;
                childholder = (ChildHolder) view.getTag();
            }else{
                view = View.inflate(getContext(),R.layout.receivers_item, null);
                childholder = new ChildHolder();
                //childholder.mImage = (ImageView) view.findViewById(R.id.image);
                childholder.mTime = (TextView) view.findViewById(R.id.receivers_time_textView);
                childholder.mName = (TextView) view.findViewById(R.id.receivers_name_textView);
                childholder.mMoney = (TextView) view.findViewById(R.id.receivers_money_textView);
                view.setTag(childholder);
            }
           /* childholder.mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });*/
            childholder.mTime.setText(group_list.get(groupPosition)
                    .getChild_list().get(childPosition).getTime());
            int len = group_list.get(groupPosition)
                    .getChild_list().size();
            childholder.mName.setText(group_list.get(groupPosition)
                    .getChild_list().get(childPosition).getName());
            childholder.mMoney.setText(group_list.get(groupPosition)
                    .getChild_list().get(childPosition).getMoney());
            return view;
        }
        //获取子元素数目
        @Override
        public int getChildrenCount(int groupPosition) {
            return group_list.get(groupPosition).getChildSize();
        }
        //获取组元素对象
        @Override
        public Object getGroup(int groupPosition) {
            return group_list.get(groupPosition);
        }
        //获取组元素数目
        @Override
        public int getGroupCount() {
            return group_list.size();
        }
        //获取组元素Id
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        //加载并显示组元素
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            View view=null;
            GroupHolder groupholder = null;
            if(convertView!=null){
                view = convertView;
                groupholder = (GroupHolder) view.getTag();
            }else{
                view = View.inflate(getContext(),R.layout.receive_item, null);
                groupholder =new GroupHolder();
                groupholder.mReceiveItem = (TextView) view.findViewById(R.id.recevie_item_textview);
                groupholder.mReceiveTime = (TextView) view.findViewById(R.id.recevie_time_textview);
                view.setTag(groupholder);
            }
            groupholder.mReceiveItem.setText(group_list.get(groupPosition).getReceiveItemName());
            groupholder.mReceiveTime.setText(group_list.get(groupPosition).getReceiveItemTime());
            return view;
        }

        @Override
        public boolean hasStableIds() { return true; }


        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }
    static class GroupHolder{
        public TextView mReceiveItem;
        public TextView mReceiveTime;
    }

    static class ChildHolder{
        //ImageView mImage;
        public TextView mName;
        public TextView mTime;
        public TextView mMoney;
    }
}