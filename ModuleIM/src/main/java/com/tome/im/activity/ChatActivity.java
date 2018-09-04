package com.tome.im.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.tome.im.presenter.ChatPresenter;
import com.tome.im.presenter.ChatPresenterImpl;
import com.tome.im.view.ChatView;
import com.tome.im.R;
import com.tome.im.R2;
import com.tome.im.adapter.EMMessageListenerAdapter;
import com.tome.im.adapter.MessageListAdapter;
import com.tome.im.adapter.TextWatcherAdapter;
import com.tome.im.widget.ChatInputMenu;
import com.tome.modulebase.control.BaseActivity;
import com.tome.modulebase.Constant.Constants;
import com.tome.modulebase.Constant.RouterConstants;
import com.tome.modulebase.utils.ThreadUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(value = RouterConstants.ROUTER_CHAT)
public class ChatActivity extends BaseActivity implements ChatView {
    @BindView(R2.id.title)
    TextView mTitle;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.back)
    ImageView mBack;
    @BindView(R2.id.input_menu)
    ChatInputMenu inputMenu;

    private ChatPresenter mChatPresenter;
    @InjectParam(key = Constants.Extra.USER_NAME)
    String mUserName;

    private MessageListAdapter mMessageListAdapter;

    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void init() {
        super.init();
        Router.injectParams(this);
        mChatPresenter = new ChatPresenterImpl(this);
        String title = String.format(getString(R.string.chat_with), mUserName);
        mTitle.setText(title);
        initRecyclerView();
        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListener);
        mChatPresenter.loadMessages(mUserName);
        // init input menu
        inputMenu.setChatInputMenuListener(new ChatInputMenu.ChatInputMenuListener() {


            @Override
            public void onSendMessage(String content) {
                // 发送文本消息
                sendTextMessage(content);
            }

            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageListAdapter = new MessageListAdapter(this, mChatPresenter.getMessages());
        mRecyclerView.setAdapter(mMessageListAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_chat;
    }

    @Override
    public void onStartSendMessage() {
        updateList();
    }

    @Override
    public void onSendMessageSuccess() {
        hideProgress();
        updateList();
    }

    private void updateList() {
        mMessageListAdapter.notifyDataSetChanged();
        smoothScrollToBottom();
    }

    @Override
    public void onSendMessageFailed() {
        hideProgress();
        toast(getString(R.string.send_failed));
    }

    @Override
    public void onMessagesLoaded() {
        mMessageListAdapter.notifyDataSetChanged();
        scrollToBottom();
    }

    @Override
    public void onMoreMessagesLoaded(int size) {
        mMessageListAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(size);
    }

    @Override
    public void onNoMoreData() {
        toast(getString(R.string.no_more_data));
    }

    @OnClick({R2.id.back})
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.back){
            finish();
        }
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition == 0) {
                    mChatPresenter.loadMoreMessages(mUserName);
                }
            }
        }
    };

    private void sendMessage() {
        mChatPresenter.sendMessage(mUserName, "name");
        hideKeyBoard();
        //mEdit.getText().clear();
    }

    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage();
                return true;
            }
            return false;
        }
    };

    private EMMessageListenerAdapter mEMMessageListener = new EMMessageListenerAdapter() {
        @Override
        public void onMessageReceived(final List<EMMessage> list) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final EMMessage emMessage = list.get(0);
                    if (emMessage.getUserName().equals(mUserName)) {
                        mChatPresenter.makeMessageRead(mUserName);
                        mMessageListAdapter.addNewMessage(emMessage);
                        smoothScrollToBottom();
                    }
                }
            });
        }
    };

    private void smoothScrollToBottom() {
        mRecyclerView.smoothScrollToPosition(mMessageListAdapter.getItemCount() - 1);
    }

    private void scrollToBottom() {
        mRecyclerView.scrollToPosition(mMessageListAdapter.getItemCount() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(mEMMessageListener);
    }

    //发送消息方法
    //==========================================================================
    protected void sendTextMessage(String content) {
        EMMessage message = EMMessage.createTxtSendMessage(content, mUserName);
        //sendMessage(message);
    }
}
