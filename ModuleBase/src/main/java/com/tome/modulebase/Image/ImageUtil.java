package com.tome.modulebase.Image;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;

import com.tome.modulebase.Image.config.GlobalConfig;
import com.tome.modulebase.Image.config.SingleConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class ImageUtil {
    public static SingleConfig.BitmapListener getBitmapListenerProxy(final SingleConfig.BitmapListener listener) {
        return (SingleConfig.BitmapListener) Proxy.newProxyInstance(SingleConfig.class.getClassLoader(),
                listener.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {

                        runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Object object = method.invoke(listener, args);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        return null;
                    }
                });
    }


    public static void runOnUIThread(Runnable runnable) {
        GlobalConfig.getMainHandler().post(runnable);
    }

    public static int dip2px(float dipValue) {
        final float scale = GlobalConfig.context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 等比压缩（宽高等比缩放）
     *
     * @param bitmap
     * @param needRecycle
     * @param targetWidth
     * @param targeHeight
     * @return
     */
    public static Bitmap compressBitmap(Bitmap bitmap, boolean needRecycle, int targetWidth, int targeHeight) {
        float sourceWidth = bitmap.getWidth();
        float sourceHeight = bitmap.getHeight();

        float scaleWidth = targetWidth / sourceWidth;
        float scaleHeight = targeHeight / sourceHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight); //长和宽放大缩小的比例
        Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (needRecycle) {
            bitmap.recycle();
        }
        bitmap = bm;
        return bitmap;
    }

    public static String appendUrl(String url) {
        String newUrl = url;
        if (TextUtils.isEmpty(newUrl)) {
            return newUrl;
        }
        boolean hasHost = url.contains("http:") || url.contains("https:");
        if (!hasHost) {
            if (!TextUtils.isEmpty(GlobalConfig.baseUrl)) {
                newUrl = GlobalConfig.baseUrl + url;
            }
        }

        return newUrl;
    }

    /**
     * 不校验证书
     *
     * @return
     */
    private static OkHttpClient getAllPassClient() {

        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[]{};
                return x509Certificates;
                // return null;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory())
                .hostnameVerifier(DO_NOT_VERIFY)
                .readTimeout(0, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS) //设置超时
                .build();

        return client;
    }

    private static OkHttpClient getNormalClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                //.sslSocketFactory(sslContext.getSocketFactory())
                //.hostnameVerifier(DO_NOT_VERIFY)
                .readTimeout(0, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS) //设置超时
                .build();
        return client;
    }

    public static boolean shouldSetPlaceHolder(SingleConfig config) {
        if (config.getPlaceHolderResId() <= 0) {
            return false;
        }

        if (config.getResId() > 0 || !TextUtils.isEmpty(config.getFilePath()) || GlobalConfig.getLoader().isCached(config.getUrl())) {
            return false;
        } else {//只有在图片源为网络图片,并且图片没有缓存到本地时,才给显示placeholder
            return true;
        }
    }
}
