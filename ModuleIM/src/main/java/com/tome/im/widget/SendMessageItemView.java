package com.tome.im.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.DateUtils;
import com.tome.im.R;
import com.tome.im.R2;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyufei
 */
public class SendMessageItemView extends RelativeLayout {

    @BindView(R2.id.send_message)
    TextView mSendMessage;
    @BindView(R2.id.send_message_progress)
    ImageView mSendMessageProgress;
    @BindView(R2.id.timestamp)
    TextView mTimestamp;

    public SendMessageItemView(Context context) {
        this(context, null);
    }

    public SendMessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_send_message_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(EMMessage emMessage, boolean showTimestamp) {
        updateTimestamp(emMessage, showTimestamp);
        updateMessageBody(emMessage);
        updateSendingStatus(emMessage);
    }

    private void updateTimestamp(EMMessage emMessage, boolean showTimestamp) {
        if (showTimestamp) {
            mTimestamp.setVisibility(VISIBLE);
            String time = DateUtils.getTimestampString(new Date(emMessage.getMsgTime()));
            mTimestamp.setText(time);
        } else {
            mTimestamp.setVisibility(GONE);
        }
    }

    private void updateMessageBody(EMMessage emMessage) {
        EMMessageBody body = emMessage.getBody();
        if (body instanceof EMTextMessageBody) {
            mSendMessage.setText(((EMTextMessageBody) body).getMessage());
        } else {
            mSendMessage.setText(getContext().getString(R.string.no_text_message));
        }
    }

    private void updateSendingStatus(EMMessage emMessage) {
        if(emMessage == null){
            return;
        }
        if(emMessage.status() == EMMessage.Status.INPROGRESS){
            mSendMessageProgress.setVisibility(VISIBLE);
            mSendMessageProgress.setImageResource(R.drawable.send_message_progress);
            AnimationDrawable drawable = (AnimationDrawable) mSendMessageProgress.getDrawable();
            drawable.start();
        }
        if(emMessage.status() == EMMessage.Status.SUCCESS){
            mSendMessageProgress.setVisibility(GONE);
        }
        if(emMessage.status() == EMMessage.Status.FAIL){
            mSendMessageProgress.setImageResource(R.mipmap.msg_error);
        }
    }
}
