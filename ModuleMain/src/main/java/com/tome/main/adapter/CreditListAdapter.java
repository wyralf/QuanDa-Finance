package com.tome.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tome.main.model.CreditItem;
import com.tome.main.widget.CreditListItemView;

import java.util.List;

/**
 * Created by zhangyufei
 */
public class CreditListAdapter extends RecyclerView.Adapter<CreditListAdapter.ItemViewHolder>{
    private Context mContext;
    private List<CreditItem> mCreditListItems;
    private OnItemClickListener mOnItemClickListener;

    public CreditListAdapter(Context context, List<CreditItem> items) {
        mContext = context;
        mCreditListItems = items;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CreditListItemView itemView = new CreditListItemView(mContext);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final CreditItem item = mCreditListItems.get(position);
        holder.mItemView.bindView(item);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(item.mCreditName);
                }
            }
        });
        holder.mItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick(item.mCreditName);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mCreditListItems == null) {
            return 0;
        }
        return mCreditListItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public CreditListItemView mItemView;

        public ItemViewHolder(CreditListItemView itemView) {
            super(itemView);
            mItemView = itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String name);
        void onItemLongClick(String name);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }
}
