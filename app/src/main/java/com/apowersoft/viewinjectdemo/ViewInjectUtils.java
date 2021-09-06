package com.apowersoft.viewinjectdemo;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 简易的butterknife实现
 */
public class ViewInjectUtils {

    public static void ViewInjectActivity(Activity activity) {
        Class cls = activity.getClass();
        //获取所有的属性
        Field[] fields = cls.getDeclaredFields();

        for (Field f : fields) {
            //判断属性是否被ViewInject注解声明
            if (f.isAnnotationPresent(ViewInject.class)) {
                ViewInject viewInject = f.getAnnotation(ViewInject.class);
                //获取注解中的id
                int resId = viewInject.value();
                View view = activity.findViewById(resId);
                //设置访问权限
                f.setAccessible(true);
                try {
                    f.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
