package com.yinshan.happycash.network.common;

import com.yinshan.happycash.network.common.network.GsonAdapter;
import com.yinshan.happycash.network.common.network.NullOnEmptyConverterFactory;
import com.yinshan.happycash.network.common.network.RequestInterceptor;
import com.yinshan.happycash.network.common.network.ResponseInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  @author  admin
 *  on 2018/1/30
 *
 */

public class RxHttpUtils {
    private static RxHttpUtils instance;

    private static Retrofit mRetrofit;
    private static final String API_BASE_URL = SourceKey.API_BASE_URL;
    public static final String HARVESTER_URL = SourceKey.UPLOADDATA;


    //单例
    public static RxHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new RxHttpUtils();
                }
            }
        }
        return instance;
    }

    private  RxHttpUtils() {
        //HTTP log
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(new ResponseInterceptor());
        builder.addInterceptor(httpLoggingInterceptor);
        OkHttpClient mOkHttpClient = builder.build();

        //Retrofit
         mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                 .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(new NullOnEmptyConverterFactory())
                .baseUrl(API_BASE_URL)//替换为你自己的BaseUrl
                .build();

    }

    public  <T> T createService(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

}
