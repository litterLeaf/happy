package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.view.loan.model.BuildUpLineModel;

import java.util.List;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  @author  admin
 *  on 2018/4/28
 *
 */
public class BuildUpLineAdapter extends BaseAdapter {

    List<BuildUpLineModel> mList;
    Context mContext;
    LayoutInflater mInflater;

    public BuildUpLineAdapter(Context context, List<BuildUpLineModel> list) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_build_up_line,null);

            holder = new ViewHolder();
            convertView.setTag(holder);

            holder.icon = (ImageView)convertView.findViewById(R.id.icon);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.line = (View)convertView.findViewById(R.id.spaceLine);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.icon.setImageResource(mList.get(position).getIcon());
        holder.name.setText(mList.get(position).getDesc());
        if(position==getCount()-1)
            holder.line.setVisibility(View.INVISIBLE);

        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView name;
        View line;
    }
}
