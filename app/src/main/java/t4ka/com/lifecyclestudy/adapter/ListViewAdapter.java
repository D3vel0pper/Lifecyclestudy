package t4ka.com.lifecyclestudy.adapter;

/**
 * Created by taka-dhu on 2015/10/31.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import t4ka.com.lifecyclestudy.R;
import t4ka.com.lifecyclestudy.commons.DBDatas;

//read this site and compliete
//http://qiita.com/mizofumi0411/items/fd51dea947f2e65f534b

public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater = null;
    ArrayList<DBDatas> dataList;

    public ListViewAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDataList(ArrayList<DBDatas> dbDatas) {
        this.dataList = dbDatas;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.dbdialog,parent,false);

        ((TextView)convertView.findViewById(R.id.id_tv)).setText(dataList.get(position).getId());
        ((TextView)convertView.findViewById(R.id.name_tv)).setText(dataList.get(position).getName());
        ((TextView)convertView.findViewById(R.id.comment_tv)).setText(dataList.get(position).getComment());
        return convertView;

    }

}

