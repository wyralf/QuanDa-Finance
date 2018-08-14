package com.tome.android.presenterimpl;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.tome.android.adapter.EMCallBackAdapter;
import com.tome.android.presenter.ChatPresenter;
import com.tome.android.utils.ThreadUtils;
import com.tome.android.view.ChatView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyufei
 */
public class ChatPresenterImpl implements ChatPresenter{

    private ChatView mChatView;

    private List<EMMessage> mEMMessageList;

    public ChatPresenterImpl(ChatView chatView) {
        mChatView = chatView;
        mEMMessageList = new ArrayList<EMMessage>();
    }
    @Override
    public void sendMessage(final String userName, final String message) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                EMMessage emMessage = EMMessage.createTxtSendMessage(message, userName);
                emMessage.setStatus(EMMessage.Status.INPROGRESS);
                emMessage.setMessageStatusCallback(mEMCallBackAdapter);
                mEMMessageList.add(emMessage);
                EMClient.getInstance().chatManager().sendMessage(emMessage);
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.onStartSendMessage();
                    }
                });
            }
        });
    }

    @Override
    public List<EMMessage> getMessages() {
        return mEMMessageList;
    }

    @Override
    public void loadMessages(String userName) {

    }

    @Override
    public void loadMoreMessages(String userName) {

    }

    @Override
    public void makeMessageRead(String userName) {

    }

    private EMCallBackAdapter mEMCallBackAdapter = new EMCallBackAdapter() {
        @Override
        public void onSuccess() {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChatView.onSendMessageSuccess();
                }
            });
        }

        @Override
        public void onError(int i, String s) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChatView.onSendMessageFailed();
                }
            });
        }
    };
}
