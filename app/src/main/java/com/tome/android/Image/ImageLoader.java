package com.tome.android.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zhangyufei
 */
public class ImageLoader {

    private static final int TAG = 0;
    private static ImageLoader self;
    private Loader loader;

    private ImageLoader(Loader loader) {
        this.loader = loader;
    }

    public static void init(Context context, Loader loader) {
        loader.init(context);
        self = new ImageLoader(loader);
    }

    public static ImageLoader getInstance() {
        if (self == null) {
            throw new RuntimeException("ImageLoader has not been initialized !!!");
        }
        return self;
    }

    public void setImageURL(int tag, ImageView target, String url, CallBack callBack) {
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        loader.loadImageURI(tag, target, Uri.parse(url), callBack);
    }

    public void setImageURL(ImageView target, String url) {
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        loader.loadImageURI(loader.getDefaultTag(), target, Uri.parse(url), null);
    }

    public void setImageURL(ImageView target, String url, CallBack callBack) {
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        loader.loadImageURI(loader.getDefaultTag(), target, Uri.parse(url), callBack);
    }

    public void setImageURL(int tag, ImageView target, String url) {
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        loader.loadImageURI(tag, target, Uri.parse(url), null);
    }

    public void setImageURI(int tag, ImageView target, Uri uri, CallBack callBack) {
        loader.loadImageURI(tag, target, uri, callBack);
    }

    public void setImageURI(ImageView target, Uri uri) {
        loader.loadImageURI(loader.getDefaultTag(), target, uri, null);
    }

    public void setImageURI(ImageView target, Uri uri, CallBack callBack) {
        loader.loadImageURI(loader.getDefaultTag(), target, uri, callBack);
    }

    public void setImageURI(int tag, ImageView target, Uri uri) {
        loader.loadImageURI(tag, target, uri, null);
    }

    public void setRatio(ImageView target, float ratio) {
        loader.setRatio(target, ratio);
    }

    public void setSelectImage(ImageView target, Uri select, Uri normal) {
        loader.setSelectImage(target, select, normal);
    }

    public void getBitmap(String url, ResultBitmap resultBitmap) {
        if (TextUtils.isEmpty(url)) {//因为要走comple回调，请不要再改回去了哈
            url = "";
        }

        getBitmap(Uri.parse(url), resultBitmap);
    }

    public void getBitmap(Uri uri, ResultBitmap resultBitmap) {
        loader.getBitmap(uri, resultBitmap);
    }

    public void setBackground(View view, Uri uri) {
        loader.setBackground(view, uri);
    }

    public void setBackgroundRes(View target, int res) {
        loader.setBackgroundRes(target, res);
    }

    public void loadUrl(Uri uri) {
        loader.loadUrl(uri);
    }

    public void loadUrl(String uri) {
        Uri temUri;
        if (!TextUtils.isEmpty(uri)) {
            temUri = Uri.parse(uri);
        } else {
            temUri = Uri.parse("error://say error");
        }
        loader.loadUrl(temUri);
    }

    public String getCachePath() {
        return loader.getCachePath();
    }

    public long getFileSize() {
        return loader.getFileSize();
    }

    public void setNewImageURL(int tag, ImageView target, String url, CallBack callBack) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri = Uri.parse(url);
        loader.cleanCacheForUri(uri);
        loader.loadImageURI(tag, target, uri, callBack);
    }

    public void setNewImageURL(ImageView target, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri = Uri.parse(url);
        loader.cleanCacheForUri(uri);
        loader.loadImageURI(loader.getDefaultTag(), target, uri, null);
    }

    public void setNewImageURL(ImageView target, String url, CallBack callBack) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri = Uri.parse(url);
        loader.cleanCacheForUri(uri);
        loader.loadImageURI(loader.getDefaultTag(), target, uri, callBack);
    }

    public void setNewImageURL(int tag, ImageView target, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri = Uri.parse(url);
        loader.cleanCacheForUri(uri);
        loader.loadImageURI(tag, target, uri, null);
    }

    public void setNewImageURI(int tag, ImageView target, Uri uri, CallBack callBack) {
        loader.cleanCacheForUri(uri);
        loader.loadImageURI(tag, target, uri, callBack);
    }

    public void setNewImageURI(ImageView target, Uri uri) {
        loader.cleanCacheForUri(uri);
        loader.loadImageURI(loader.getDefaultTag(), target, uri, null);
    }

    public void setNewImageURI(ImageView target, Uri uri, CallBack callBack) {
        loader.cleanCacheForUri(uri);
        loader.loadImageURI(loader.getDefaultTag(), target, uri, callBack);
    }

    public void setNewImageURI(int tag, ImageView target, Uri uri) {
        loader.cleanCacheForUri(uri);
        loader.loadImageURI(tag, target, uri, null);
    }


    public void getNewBitmap(String url, ResultBitmap resultBitmap) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri = Uri.parse(url);
        loader.cleanCacheForUri(uri);
        getNewBitmap(uri, resultBitmap);
    }

    public void getNewBitmap(Uri uri, ResultBitmap resultBitmap) {
        loader.cleanCacheForUri(uri);
        loader.getBitmap(uri, resultBitmap);
    }

    public void setNewBackground(View view, Uri uri) {
        loader.cleanCacheForUri(uri);
        loader.setBackground(view, uri);
    }

    public void loadNewUrl(Uri uri) {
        loader.cleanCacheForUri(uri);
        loader.loadUrl(uri);
    }


    public void cleanCacheForUri(Uri uri) {
        loader.cleanCacheForUri(uri);
    }

    public void loadNewImage(Uri uri) {
        loader.loadNewImage(uri);
    }

    public boolean isUriCache(Uri uri) {
        return loader.isUriCache(uri);
    }

    public void cleanAllData() {
        loader.cleanAllData();
    }


    public interface Result {
        void resultDrawable(StateListDrawable stateListDrawable);
    }

    public interface Loader {

        int getDefaultTag();

        void init(Context context);

        void loadImageURI(int tag, ImageView target, Uri uri, CallBack callBack);

        void setRatio(ImageView target, float ratio);

        void setSelectImage(ImageView target, Uri select, Uri normal);

        void setBackground(View target, Uri uri);

        void getBitmap(Uri uri, ResultBitmap resultBitmap);

        void loadUrl(Uri uri);

        void cleanAllData();

        boolean isUriCache(Uri uri);

        void cleanCacheForUri(Uri uri);

        void loadNewImage(Uri uri);

        void setBackgroundRes(View target, int res);

        String getCachePath();

        long getFileSize();

    }

    // 待修改
    public interface CallBack {

        void onError(Exception e);

        void onFinish(Object object);


    }

    public interface ResultBitmap {
        void resultBitmap(Bitmap bitmap);

        void complete();
    }
}
