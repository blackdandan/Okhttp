package yzh.com.okhttp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @description 请描述此类作用
 * @author 杨泽惠 
 * @date 2018/7/3
 */

public class MainActivity extends AppCompatActivity {
    /**
     *  LOG TAG
     */
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder();
        okhttpClient.interceptors();
        Dispatcher dispatcher = new Dispatcher();
        okhttpClient.connectionPool(new ConnectionPool());
        okhttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d(TAG, "do====MainActivity.intercept.1");
                return chain.proceed(chain.request());
            }
        });
        okhttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d(TAG, "do====MainActivity.intercept.2");
                return chain.proceed(chain.request());
            }
        });
        final Request request = new Request.Builder().url("http://www.baidu.com").build();
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = okhttpClient.build().newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public static void main(String[] args){
        HashMap<String,ConcurrentHashMap<String,String>> test = new HashMap<>();
        test.get(null);
    }
}
