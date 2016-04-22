package t4ka.com.lifecyclestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import t4ka.com.lifecyclestudy.R;
import t4ka.com.lifecyclestudy.commons.DBDatas;

/**
 * Created by taka-dhu on 2015/12/08.
 */
public class DataAdapter extends ArrayAdapter<DBDatas> {
    private LayoutInflater inflater;

    public DataAdapter(Context context, int textViewResourceId, List<DBDatas> objects){
        super(context,textViewResourceId,objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent){
        //get position's data
//        DBDatas item = getItem(position);
        //if you want to show newest one in the top position, use this logic
        DBDatas item = getItem(getCount() - 1 - position);
        //make a single instance of convertView
        if(convertView == null){
            convertView = inflater.inflate(R.layout.db_list_items,null);
        }

        //Set each data of DBDatas for each view
        TextView idtv = (TextView)convertView.findViewById(R.id.id_tv);
        idtv.setText(item.getId());
        TextView nametv = (TextView)convertView.findViewById(R.id.name_tv);
        nametv.setText(item.getName());
        TextView commenttv = (TextView)convertView.findViewById(R.id.comment_tv);
        commenttv.setText(item.getComment());

        return convertView;
    }

}
