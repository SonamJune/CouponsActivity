package com.manash.purpllebase.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.manash.purpllebase.R;
import com.manash.purpllebase.model.common.Option;
import com.manash.purpllebase.util.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CommonOptionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private List<Option> mOptions;
    private final int mColorAccent, mColorMediumGray, mColorViolet;
    private final String mRadioActive;
    private final String mRadioInactive;
    private int mSelectedPosition = -1;
    private int mPreviousPosition = -1;
    private int mDefaultSelectedPosition = -1;
    private boolean mIsMultiCheck;
    private OnItemClickListener mListener;
    private static final int NEW_SORT_BY_ITEM=1;
    private int type;

    public CommonOptionsAdapter(Context context, List<Option> options, int defaultSelectedPosition, boolean isMultiCheck, OnItemClickListener listener, int viewType) {
        mContext = context;
        mOptions = options;
        mColorAccent = ContextCompat.getColor(context, R.color.colorAccent);
        mColorMediumGray = ContextCompat.getColor(context, R.color.medium_gray_color);
        mColorViolet = ContextCompat.getColor(context, R.color.dark_washed_purple);
        mRadioActive = context.getString(R.string.chech_box_checked_icon);
        mRadioInactive = context.getString(R.string.check_box_unchecked_icon);
        mSelectedPosition = defaultSelectedPosition;
        mDefaultSelectedPosition = defaultSelectedPosition;
        mIsMultiCheck = isMultiCheck;
        mListener = listener;
        type= viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(type==NEW_SORT_BY_ITEM){
            //as per new sort revamp mock which is called from Shop Activity
             view = LayoutInflater.from(mContext).inflate(R.layout.filter_checkbox_layout_new, parent, false);
             return new NewViewHolder(view);
        }else {
            //to support review activity( not a part of sort revamp)
             view = LayoutInflater.from(mContext).inflate(R.layout.filter_checkbox_layout, parent, false);
             return new OldViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Option option = mOptions.get(position);
        if(type== NEW_SORT_BY_ITEM){
            updateUINew(option, (NewViewHolder) holder, position);
        }else {
            updateUI(option, (OldViewHolder) holder, position);
        }
    }

    private void updateUINew(final Option option, NewViewHolder holder, final int position){
        holder.titleSortBy.setText(option.getDisplayName());
        boolean isChecked = mIsMultiCheck || mSelectedPosition == position;
        if (option.isSelected() && isChecked) {
            //set bold text style
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            holder.titleSortBy.setTypeface(boldTypeface);
            holder.filterRadioBtn.setChecked(true);
        } else {
            //set normal text style
            Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
            holder.titleSortBy.setTypeface(normalTypeface);
            holder.filterRadioBtn.setChecked(false);
        }

        //remove divider for last item
        if(position== mOptions.size()-1)
            holder.divider.setVisibility(View.INVISIBLE);
        else
            holder.divider.setVisibility(View.VISIBLE);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreviousPosition = mSelectedPosition;
                mSelectedPosition = position;
                option.setSelected(!option.isSelected());
                if(mIsMultiCheck) {
                    mListener.onItemClick(v, position, option);
                } else {
                    if (mPreviousPosition == mSelectedPosition) {
                        mSelectedPosition = mDefaultSelectedPosition;
                        mOptions.get(mDefaultSelectedPosition).setSelected(true);
                        mListener.onItemClick(v, position, mOptions.get(mDefaultSelectedPosition));
                    } else {
                        mOptions.get(mPreviousPosition).setSelected(!mOptions.get(mPreviousPosition).isSelected());
                        mListener.onItemClick(v, position, option);
                    }
                }
                notifyDataSetChanged();
            }
        });
    }


    // to support review activity, old view is also kept
    private void updateUI(final Option option, OldViewHolder holder, final int position) {
        holder.filterNameView.setText(option.getDisplayName());
        boolean isChecked = mIsMultiCheck || mSelectedPosition == position;
        if (option.isSelected() && isChecked) {
            holder.filterRadioBtn.setText(mRadioActive);
            holder.filterRadioBtn.setTextColor(mColorAccent);
        } else {
            holder.filterRadioBtn.setText(mRadioInactive);
            holder.filterRadioBtn.setTextColor(mColorMediumGray);
        }

        holder.filterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreviousPosition = mSelectedPosition;
                mSelectedPosition = position;
                option.setSelected(!option.isSelected());
                if(mIsMultiCheck) {
                    mListener.onItemClick(v, position, option);
                } else {
                    if (mPreviousPosition == mSelectedPosition) {
                        mSelectedPosition = mDefaultSelectedPosition;
                        mOptions.get(mDefaultSelectedPosition).setSelected(true);
                        mListener.onItemClick(v, position, mOptions.get(mDefaultSelectedPosition));
                    } else {
                        mOptions.get(mPreviousPosition).setSelected(!mOptions.get(mPreviousPosition).isSelected());
                        mListener.onItemClick(v, position, option);
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    public List<Option> getOptions() {
        return mOptions;
    }

    public void updateList(ArrayList<Option> list) {
        mOptions = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mOptions.size();
    }

    public void clearSelection() {
        for (Option option : mOptions) {
            option.setSelected(false);
        }
    }
    public class OldViewHolder extends RecyclerView.ViewHolder {

        private TextView filterNameView;
        private TextView filterRadioBtn;
        private RelativeLayout filterLayout;

        public OldViewHolder(View view) {
            super(view);
            filterNameView = view.findViewById(R.id.title_view);
            filterRadioBtn = view.findViewById(R.id.checkbox_view);
            filterLayout = view.findViewById(R.id.check_box_layout);
        }
    }


    public class NewViewHolder extends RecyclerView.ViewHolder {
        private TextView titleSortBy;
        private CheckBox filterRadioBtn;
        private ConstraintLayout parentLayout;
        private View divider;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            titleSortBy = itemView.findViewById(R.id.tv_title);
            filterRadioBtn = itemView.findViewById(R.id.cb_view);
            parentLayout = itemView.findViewById(R.id.check_box_layout);
            divider= itemView.findViewById(R.id.view);
        }
    }
}
