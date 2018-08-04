package com.tome.android.presenterimpl;

import com.hyphenate.chat.EMMessage;
import com.tome.android.presenter.ChatPresenter;
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
    public void sendMessage(String userName, String message) {

    }

    @Override
    public List<EMMessage> getMessages() {
        return null;
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
}
