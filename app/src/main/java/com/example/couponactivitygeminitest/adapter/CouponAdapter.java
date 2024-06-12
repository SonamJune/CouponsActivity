package com.example.couponactivitygeminitest.adapter;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

//import com.manash.analytics.AnalyticsHelper;
//import com.manash.analytics.models.EventData;
import com.example.couponactivitygeminitest.R;
import com.example.couponactivitygeminitest.model.cartCoupon.CouponItem;
//import com.example.couponbase.PurplleApplication;
import com.example.couponbase.helper.PurplleTypeFace;
import com.example.couponbase.helper.PurplleTypefaceSpan;
import com.example.couponbase.util.OnItemClickListener;
import com.example.couponbase.views.PurplleTextView;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {
    public static final int VIEW_TYPE_COUPON = R.layout.coupon_header;
    public static final int VIEW_TYPE_HEADER = 2;
    public static final int VIEW_TYPE_LIST = R.layout.coupon_list_item;
    public static final int VIEW_TYPE_EMPTY_STATE = R.layout.coupon_empty_state;

    private final Context mContext;
    private final OnItemClickListener mListener;
    private List<CouponItem> mList;
    private String mEditableField;

    private String mCouponCode;
    private int mSelectedPosition;
    private final String mSelectedIconId;
    private final String mUnSelectedIconId;
    private final int mSelectedColor;
    private final int mUnSelectedColor;
    private String mErrorString;
    private RecyclerView mRecyclerview;


    public CouponAdapter(Context context, List<CouponItem> itemList, OnItemClickListener onItemClickListener, int selectedPosition) {
        mContext = context;
        mListener = onItemClickListener;
        mEditableField = "both";
        mList = itemList;
        mSelectedPosition = selectedPosition;
        mSelectedColor = ContextCompat.getColor(mContext, R.color.purplle_base);
        mUnSelectedColor = ContextCompat.getColor(mContext, R.color.purplle_base);
        mSelectedIconId = mContext.getString(R.string.radio_active_icon_id);
        mUnSelectedIconId = mContext.getString(R.string.unchecked_radio_button);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                return new ViewHolder(new PurplleTextView(mContext), viewType);
            default:
                return new ViewHolder(LayoutInflater.from(mContext).inflate(viewType, parent,
                        false), viewType);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(holder.getItemViewType() == VIEW_TYPE_COUPON) {
            if (mCouponCode != null) {
                holder.editApplyCoupon.setText(mCouponCode);
                holder.editApplyCoupon.setSelection(mCouponCode.length());
            } else {
                holder.editApplyCoupon.setText("");
            }
            holder.editApplyCoupon.setEnabled(mEditableField.equalsIgnoreCase("both") ||
                    mEditableField.equalsIgnoreCase("edit"));

            if (mErrorString != null) {
                holder.errorString.setVisibility(View.VISIBLE);
                holder.errorString.setText(mErrorString);
            } else {
                holder.errorString.setVisibility(View.GONE);
            }
        } else if (holder.getItemViewType() == VIEW_TYPE_LIST) {
            updateList(holder);
        } else if (holder.getItemViewType() == VIEW_TYPE_HEADER) {
            holder.headerText.setText(Html.fromHtml(mList.get(holder.getAdapterPosition()).getTitle()));
        }
    }

    private void updateList(final ViewHolder holder) {
        final CouponItem couponItem = mList.get(holder.getAdapterPosition());
        holder.couponCode.setText(couponItem.getCouponCode());
        String couponText = couponItem.getCouponText();
        if (couponText != null && !couponText.isEmpty()) {
            holder.couponText.setText(Html.fromHtml(couponText));
            holder.couponText.setVisibility(View.VISIBLE);
        } else {
            holder.couponText.setVisibility(View.INVISIBLE);
        }
        if (mSelectedPosition == holder.getAdapterPosition()) {
            holder.checkBox.setText(mSelectedIconId);
            holder.checkBox.setTextColor(mSelectedColor);
        } else {
            holder.checkBox.setText(mUnSelectedIconId);
            holder.checkBox.setTextColor(mUnSelectedColor);
        }
        if ((mEditableField.equalsIgnoreCase("both") ||
                mEditableField.equalsIgnoreCase("checkbox"))) {
            holder.checkBox.setClickable(true);
            holder.checkBox.setEnabled(true);
        } else {
            holder.checkBox.setEnabled(false);
            holder.checkBox.setClickable(false);
        }
        if (couponItem.getIsValid()) {
            holder.checkBox.setClickable(true);
            holder.checkBox.setEnabled(true);
            holder.itemView.setAlpha((float) 1);
            holder.itemView.setEnabled(true);
        } else {
            holder.checkBox.setEnabled(false);
            holder.checkBox.setClickable(false);
            holder.itemView.setAlpha((float) 0.5);
            holder.itemView.setEnabled(false);
        }
        String couponDesc = couponItem.getCouponMessage();
        if (couponDesc != null && !couponDesc.isEmpty()) {
            addCouponDescription(couponDesc, couponItem, holder);
        } else {
            holder.description.setVisibility(View.GONE);
        }
        String couponExpiry = couponItem.getCouponExpiry();
        if (couponExpiry != null && !couponExpiry.trim().isEmpty()) {
            holder.couponExpiry.setText(couponExpiry);
            holder.couponExpiry.setVisibility(View.VISIBLE);
        } else {
            holder.couponExpiry.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mErrorString = null;
                if (mSelectedPosition == holder.getAdapterPosition()) {
                    mSelectedPosition = -1;
                    mEditableField = "both";
                    couponItem.setIsSelected("1");
                } else {
                    mEditableField = "checkbox";
                    mCouponCode = null;
                    mSelectedPosition = holder.getAdapterPosition();
                    couponItem.setIsSelected("0");
                }
                mListener.onItemClick(view, holder.getAdapterPosition(), couponItem);
                notifyDataSetChanged();
            }
        });

    }

    private void addCouponDescription(String couponDesc, final CouponItem couponItem, final ViewHolder holder) {
        holder.description.setVisibility(View.VISIBLE);
        String detailedMsg = couponItem.getCouponMessageDetail();
        if (detailedMsg != null && !detailedMsg.trim().isEmpty()) {
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    mListener.onItemClick(textView, holder.getAdapterPosition(), couponItem);
//                    EventData eventData = AnalyticsHelper.getClickStreamEventData(null, null, null,
//                            AnalyticsHelper.COUPON_LISTING, null, null, "details", null, null, null);
//                    AnalyticsHelper.trackCleverTapAnalytics(mContext.getApplicationContext(), AnalyticsHelper.CLICK_STREAM, eventData);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(true);
                }
            };
            SpannableString offerDesc = new SpannableString(Html.fromHtml(couponDesc) + " " + "Details");
            offerDesc.setSpan(clickableSpan, couponDesc.length() + 1, offerDesc.length(), 0);
            offerDesc.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.purplle_base)), couponDesc.length() + 1, offerDesc.length(), 0);
            //offerDesc.setSpan(new PurplleTypefaceSpan(PurplleTypeFace.getManropeBold(PurplleApplication.getAppContext())), couponDesc.length() + 1, offerDesc.length(), 0);
            holder.description.setText(offerDesc);
            holder.description.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            holder.description.setText(Html.fromHtml(couponDesc));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getViewType();
    }

    public String getCouponCode() {
        return mCouponCode;

    }

    public void setCouponCode(String couponCode) {
        mCouponCode = couponCode;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerview = recyclerView;
    }

    public void updateData(List<CouponItem> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setCouponError(String couponError) {
        this.mErrorString = couponError;
        notifyItemChanged(1);

    }

    private void notiyItem(final int position) {
        if (mRecyclerview.isComputingLayout()) {
            mRecyclerview.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemChanged(position);
                }
            });
        } else {
            notifyItemChanged(position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText editApplyCoupon;
        private TextView errorString;

        private TextView checkBox;
        private TextView couponCode;
        private TextView description;
        private TextView couponText;
        private TextView couponExpiry;

        private TextView headerText;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if(viewType == VIEW_TYPE_COUPON) {
                editApplyCoupon = itemView.findViewById(R.id.edit_apply_coupon);
                errorString = itemView.findViewById(R.id.coupon_msg);
                editApplyCoupon.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        mCouponCode = charSequence.toString();

                        if (i2 == 1) {
                            mEditableField = "edit";
                            if (mErrorString != null) {
                                mErrorString = null;
                                notiyItem(0);

                            }
                            int local = mSelectedPosition;
                            if (mSelectedPosition != -1) {
                                mSelectedPosition = -1;
                                notiyItem(local);
                            }
                        } else if (i2 == 0) {
                            mEditableField = "both";
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
            } else if (viewType == VIEW_TYPE_LIST) {
                checkBox = itemView.findViewById(R.id.check_box);
                couponCode = itemView.findViewById(R.id.coupon_code_text);
                description = itemView.findViewById(R.id.description);
                couponText = itemView.findViewById(R.id.coupon_text);
                couponExpiry = itemView.findViewById(R.id.expiry);
            } else if (viewType == VIEW_TYPE_HEADER) {
                headerText = (TextView) itemView;
//                    int padding = (int) mContext.getResources().getDimension(R.dimen._20dp);
//                    int paddingBottom = (int) mContext.getResources().getDimension(R.dimen._10dp);
                //headerText.setPadding(padding, padding, padding, paddingBottom);
                //headerText.setTypeface(PurplleTypeFace.getManropeBold(PurplleApplication.getAppContext()));
                headerText.setTextSize(TypedValue.COMPLEX_UNIT_PX,mContext.getResources().getDimension(R.dimen._12sp));
                headerText.setTextColor(ContextCompat.getColor(mContext, R.color.toolbar_item_color));
            }
        }
    }
}
