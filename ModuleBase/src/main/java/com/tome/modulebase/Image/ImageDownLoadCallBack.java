package com.tome.modulebase.Image;

import android.graphics.Bitmap;

public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
