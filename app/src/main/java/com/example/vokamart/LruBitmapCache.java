package com.example.vokamart;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

public class LruBitmapCache extends LruCache<String, Bitmap> {

    public LruBitmapCache(Context context) {
        // Adjust the cache size based on your requirements
        super(getCacheSize(context));
    }

    private static int getCacheSize(Context context) {
        // Use a fraction of the available memory for the cache
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8; // Adjust as needed
        return cacheSize;
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        // Specify the size of the cached item
        return value.getByteCount() / 1024;
    }
}