package com.tome.im.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tome.im.R;

/**
 * Created by zhangyufei
 * 聊天页面底部的聊天输入菜单栏
 */
public class ChatInputMenu extends LinearLayout {
    FrameLayout primaryMenuContainer, emojiconMenuContainer;
    protected ChatPrimaryMenuBase chatPrimaryMenu;
    protected FrameLayout chatExtendMenuContainer;
    protected ChatExtendMenu chatExtendMenu;
    private ChatInputMenuListener listener;
    private Context context;
    protected LayoutInflater layoutInflater;
    private Handler handler = new Handler();
    public ChatInputMenu(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public ChatInputMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatInputMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.widget_chat_input_menu, this);
        primaryMenuContainer = (FrameLayout) findViewById(R.id.primary_menu_container);
        emojiconMenuContainer = (FrameLayout) findViewById(R.id.emojicon_menu_container);
        chatExtendMenuContainer = (FrameLayout) findViewById(R.id.extend_menu_container);

        // 扩展按钮栏
        chatExtendMenu = (ChatExtendMenu) findViewById(R.id.extend_menu);

    }

    public void init() {
        // 主按钮菜单栏,没有自定义的用默认的
        if(chatPrimaryMenu == null){
            chatPrimaryMenu = (ChatPrimaryMenu) layoutInflater.inflate(R.layout.layout_chat_primary_menu, null);
        }
        primaryMenuContainer.addView(chatPrimaryMenu);


        processChatMenu();
        // 初始化extendmenu
        chatExtendMenu.init();
    }

    protected void processChatMenu() {
        // 发送消息栏
        chatPrimaryMenu.setChatPrimaryMenuListener(new ChatPrimaryMenuBase.EaseChatPrimaryMenuListener() {

            @Override
            public void onSendBtnClicked(String content) {
                if (listener != null)
                    listener.onSendMessage(content);
            }

            @Override
            public void onToggleVoiceBtnClicked() {
                hideExtendMenuContainer();
            }

            @Override
            public void onToggleExtendClicked() {
                toggleMore();
            }


            @Override
            public void onEditTextClicked() {
                hideExtendMenuContainer();
            }


            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                if(listener != null){
                    return listener.onPressToSpeakBtnTouch(v, event);
                }
                return false;
            }
        });
    }

    public void setChatInputMenuListener(ChatInputMenuListener listener) {
        this.listener = listener;
    }

    public interface ChatInputMenuListener {
        /**
         * 发送消息按钮点击
         *
         * @param content
         *            文本内容
         */
        void onSendMessage(String content);

        /**
         * 长按说话按钮touch事件
         * @param v
         * @param event
         * @return
         */
        boolean onPressToSpeakBtnTouch(View v, MotionEvent event);
    }

    /**
     * 隐藏整个扩展按钮栏(包括表情栏)
     */
    public void hideExtendMenuContainer() {
        chatExtendMenu.setVisibility(View.GONE);
        chatExtendMenuContainer.setVisibility(View.GONE);
        chatPrimaryMenu.onExtendMenuContainerHide();
    }

    /**
     * 显示或隐藏图标按钮页
     *
     */
    protected void toggleMore() {
        if (chatExtendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    chatExtendMenuContainer.setVisibility(View.VISIBLE);
                    chatExtendMenu.setVisibility(View.VISIBLE);
                }
            }, 50);
        } else {

        }

    }

    /**
     * 隐藏软键盘
     */
    private void hideKeyboard() {
        chatPrimaryMenu.hideKeyboard();
    }
}
