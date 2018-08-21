package com.tome.im.presenter;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by zhangyufei
 */
public interface ChatPresenter {

    void sendMessage(String userName, String message);

    List<EMMessage> getMessages();

    void loadMessages(String userName);

    void loadMoreMessages(String userName);

    void makeMessageRead(String userName);
}
