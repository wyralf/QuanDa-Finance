package com.tome.android.Image;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.MemoryCategory;

public class GlideLoader implements ILoader{
    @Override
    public void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {

    }

    @Override
    public void request(SingleConfig config) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void clearDiskCache() {

    }

    @Override
    public void clearMomoryCache(View view) {

    }

    @Override
    public void clearMomory() {

    }

    @Override
    public boolean isCached(String url) {
        return false;
    }

    @Override
    public void trimMemory(int level) {

    }

    @Override
    public void clearAllMemoryCaches() {

    }

    @Override
    public void saveImageIntoGallery(DownLoadImageService downLoadImageService) {

    }
}
