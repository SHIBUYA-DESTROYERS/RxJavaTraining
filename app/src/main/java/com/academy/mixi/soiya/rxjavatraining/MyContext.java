package com.academy.mixi.soiya.rxjavatraining;

import android.content.Context;

// ContextのおさらいとApplicationContextをどこからでも参照できるようにする方法
// https://qiita.com/eimei4coding/items/0585b8240873ec5e9c20

public class MyContext {
    private static MyContext instance = null;
    private Context applicationContext;

    // publicをつけないのは意図的
    // MyApplicationと同じパッケージにして、このメソッドのアクセスレベルはパッケージローカルとする
    // 念のため意図しないところで呼び出されることを防ぐため
    static void onCreateApplication(Context applicationContext) {
        // Application#onCreateのタイミングでシングルトンが生成される
        instance = new MyContext(applicationContext);
    }

    private MyContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static MyContext getInstance() {
        if (instance == null) {
            // こんなことは起きないはず
            throw new RuntimeException("MyContext should be initialized!");
        }
        return instance;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
}