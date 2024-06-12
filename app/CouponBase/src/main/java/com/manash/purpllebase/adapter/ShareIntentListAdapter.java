package com.manash.purpllebase.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manash.purpllebase.PurplleApplication;
import com.manash.purpllebase.R;
import com.manash.purpllebase.util.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShareIntentListAdapter extends RecyclerView.Adapter<ShareIntentListAdapter.ViewHolder> {
    private final List<ResolveInfo> mItems;
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final OnItemClickListener mOnItemClickListener;
    private final int mViewType;
    public static int VIEW_TYPE_DEFAULT = 0;
    public static int VIEW_TYPE_GRID = 1;

    public ShareIntentListAdapter(Context context, List<ResolveInfo> items, OnItemClickListener clickListener, int viewType) {
        mContext = context;
        mItems = items;
        mViewType = viewType;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mOnItemClickListener = clickListener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        if(mViewType == VIEW_TYPE_GRID)
            return new ViewHolder(mInflater.inflate(R.layout.share_item_layout, parent, false));
        else
            return new ViewHolder(mInflater.inflate(R.layout.share_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivityInfo item = mItems.get(position).activityInfo;
        if(mViewType == VIEW_TYPE_GRID) {
            if(item == null) {
                holder.shareName.setText(mContext.getString(R.string.copy_link));
                holder.imageShare.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_copy_link));
            } else {
                holder.shareName.setText(item.loadLabel(mContext.getPackageManager()).toString());
                holder.imageShare.setImageDrawable(item.loadIcon(mContext.getPackageManager()));
            }
        } else {
            holder.shareName.setText(item.loadLabel(mContext.getPackageManager()).toString());
            holder.imageShare.setImageDrawable(item.loadIcon(mContext.getPackageManager()));
        }

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView shareName;
        ImageView imageShare;

        public ViewHolder(View itemView) {
            super(itemView);
            shareName = itemView.findViewById(R.id.shareName);
            imageShare = itemView.findViewById(R.id.shareImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, 0, mItems.get((int) view.getTag()));
                }
            });
        }
    }
}
