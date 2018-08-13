package com.tome.android.Image;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
}
