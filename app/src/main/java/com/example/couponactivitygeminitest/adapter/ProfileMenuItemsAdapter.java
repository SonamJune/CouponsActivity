package com.example.couponactivitygeminitest.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.couponactivitygeminitest.model.DrawerItem;
import com.example.couponactivitygeminitest.R;

import java.util.List;

public class ProfileMenuItemsAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<DrawerItem> mRightDrawerItems;

    public ProfileMenuItemsAdapter(Context context, List<DrawerItem> rightDrawerItems) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        mRightDrawerItems = rightDrawerItems;
    }

    @Override
    public int getCount() {

        if (mRightDrawerItems != null && mRightDrawerItems.size() > 0) {
            return mRightDrawerItems.size();
        } else return 0;

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
        View view = convertView;

        if (view == null) {
            view = mInflater.inflate(R.layout.drawer_list_item, parent, false);
            MenuItemHolder holder = new MenuItemHolder(view);
            view.setTag(holder);
        }

        MenuItemHolder holder = (MenuItemHolder) view.getTag();
        DrawerItem drawerItem = mRightDrawerItems.get(position);
        holder.itemName.setText(drawerItem.getName());
        holder.itemIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
//        if (drawerItem.getDeepLinkUrl() != null && drawerItem.getDeepLinkUrl().contains(Route.LANGUAGE_SELECTION)) {
//            holder.itemIcon.setCompoundDrawablesWithIntrinsicBounds(mContext.getDrawable(R.drawable.language_icon), null, null, null);
//            holder.itemIcon.setText("");
//        } else {
//            holder.itemIcon.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
//            holder.itemIcon.setText(Html.fromHtml("&#x" + drawerItem.getIconCode() + ";"));
//        }

        /*switch (position) {
            case 0:
                holder.itemName.setText(mContext.getString(R.string.my_account));
                holder.itemIcon.setText(mContext.getString(R.string.login_icon_id));
                break;
            case 1:
                holder.itemName.setText(mContext.getString(R.string.my_orders));
                holder.itemIcon.setText(mContext.getString(R.string.filled_bag_icon_id));
                break;
            case 2:
                holder.itemName.setText(mContext.getString(R.string.my_wishlist));
                holder.itemIcon.setText(mContext.getString(R.string.favourite_empty_heart_icon));
                break;
            case 3:
                holder.itemName.setText(mContext.getString(R.string.my_reviews));
                holder.itemIcon.setText(mContext.getString(R.string.write_review_icon));
                break;
            case 4:
                holder.itemName.setText(mContext.getString(R.string.beauty_profile));
                holder.itemIcon.setText(mContext.getString(R.string.beauty_profile_icon));
                break;
            case 5:
                holder.itemName.setText(mContext.getString(R.string.create_story));
                holder.itemIcon.setText(mContext.getString(R.string.create_icon_id));
                break;
            case 6:
                holder.itemName.setText(mContext.getString(R.string.purplle_wallet));
                holder.itemIcon.setText(mContext.getString(R.string.wallet_unfilled_icon_id));
                break;
            case 7:
                holder.itemName.setText(mContext.getString(R.string.refer_earn));
                holder.itemIcon.setText(mContext.getString(R.string.filled_refer_earn_icon_id));
                break;
            case 8:
                holder.itemName.setText(mContext.getString(R.string.setting));
                holder.itemIcon.setText(mContext.getString(R.string.filled_setting_icon_id));
                break;
            case 9:
                if (PreferenceUtil.isUserLogin(mContext)) {
                    holder.itemName.setText(mContext.getString(R.string.logout));
                    holder.itemIcon.setText(mContext.getString(R.string.logout_icon_id));
                } else {
                    holder.itemName.setText(mContext.getString(R.string.login_or_register));
                    holder.itemIcon.setText(mContext.getString(R.string.login_icon_id));
                }
                break;
        }*/
        return view;
    }

    private class MenuItemHolder {
        private TextView itemName;
        private TextView itemIcon;

        MenuItemHolder(View itemView) {
//            itemName = itemView.findViewById(5);
//            itemIcon = itemView.findViewById(8);
//            itemIcon.setText(mContext.getString(R.string.favourite_empty_heart_icon));
//            itemView.findViewById(R.id.next_icon).setVisibility(View.GONE);
        }
    }
}
