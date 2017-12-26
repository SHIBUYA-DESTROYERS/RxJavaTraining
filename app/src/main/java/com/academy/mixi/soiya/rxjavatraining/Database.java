package com.academy.mixi.soiya.rxjavatraining;

import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;

class Database {

    private static final String TAG = Database.class.getSimpleName();

    static String readValue() throws IOException {

        for (int i = 0; i < 100; i++) {
            Log.i(TAG, String.format("Reading value: %d%%", i));
            SystemClock.sleep(20);
        }
        Log.i(TAG, "Reading value: 100%");

        return "DBへのアクセスが終了しました";
//        return null;
    }
}
