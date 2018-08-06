package com.tome.android.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.tome.android.R;
import com.tome.android.adapter.MessageListAdapter;
import com.tome.android.app.Constants;
import com.tome.android.presenter.ChatPresenter;
import com.tome.android.presenterimpl.ChatPresenterImpl;
import com.tome.android.view.ChatView;

import butterknife.BindView;
import butterknife.OnClick;
import imageloader.libin.com.images.loader.ImageLoader;

public class ChatActivity extends BaseActivity implements ChatView{
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.edit)
    EditText mEdit;
    @BindView(R.id.send)
    Button mSend;
    @BindView(R.id.back)
    ImageView mBack;

    private ChatPresenter mChatPresenter;
    private String mUserName;

    private MessageListAdapter mMessageListAdapter;

    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void init() {
        super.init();
        mChatPresenter = new ChatPresenterImpl(this);
        mUserName = getIntent().getStringExtra(Constants.Extra.USER_NAME);
        String title = String.format(getString(R.string.chat_with), mUserName);
        mTitle.setText(title);
        initRecyclerView();

        mChatPresenter.loadMessages(mUserName);
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

    }

    @Override
    public void onSendMessageSuccess() {

    }

    @Override
    public void onSendMessageFailed() {

    }

    @Override
    public void onMessagesLoaded() {

    }

    @Override
    public void onMoreMessagesLoaded(int size) {

    }

    @Override
    public void onNoMoreData() {

    }

    @OnClick({R.id.back, R.id.send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.send:
                sendMessage();
                break;
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
        mChatPresenter.sendMessage(mUserName, mEdit.getText().toString().trim());
        hideKeyBoard();
        mEdit.getText().clear();
    }
}
