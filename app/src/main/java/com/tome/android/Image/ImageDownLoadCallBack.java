package com.tome.android.Image;

import android.graphics.Bitmap;

public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
