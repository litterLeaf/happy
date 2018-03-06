package com.yinshan.happycash.network.common;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.yinshan.happycash.network.common.interceptor.AddCookiesInterceptor;
import com.yinshan.happycash.network.common.interceptor.CacheInterceptor;
import com.yinshan.happycash.network.common.interceptor.HeaderInterceptor;
import com.yinshan.happycash.network.common.interceptor.ReceivedCookiesInterceptor;
import com.yinshan.happycash.network.common.network.RequestInterceptor;
import com.yinshan.happycash.network.common.network.ResponseInterceptor;
import com.yinshan.happycash.network.common.network.RetrofitClient;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by admin on 2018/3/5.
 */

public class SingleHttpUtils {
    private static SingleHttpUtils instance;

    private String baseUrl;

    private Map<String, Object> headerMaps = new HashMap<>();

    private boolean isShowLog = true;
    private boolean cache = false;
    private boolean saveCookie = true;

    private String cachePath;
    private long cacheMaxSize;

    private long readTimeout;
    private long writeTimeout;
    private long connectTimeout;


    private OkHttpClient okClient;

    private List<Converter.Factory> converterFactories = new ArrayList<>();
    private List<CallAdapter.Factory> adapterFactories = new ArrayList<>();

    /**
     * 不使用单利模式是因为单个请求的参数配置是单次有效的
     *
     * @return SingleRxHttp
     */
    public static SingleHttpUtils getInstance() {
        instance = new SingleHttpUtils();
        return instance;
    }

    public SingleHttpUtils baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * 局部设置Converter.Factory,默认GsonConverterFactory.create()
     */
    public SingleHttpUtils addConverterFactory(Converter.Factory factory) {
        if (factory != null) {
            converterFactories.add(factory);
        }
        return this;
    }

    /**
     * 局部设置CallAdapter.Factory,默认RxJavaCallAdapterFactory.create()
     */
    public SingleHttpUtils addCallAdapterFactory(CallAdapter.Factory factory) {
        if (factory != null) {
            adapterFactories.add(factory);
        }
        return this;
    }

    public SingleHttpUtils addHeaders(Map<String, Object> headerMaps) {
        this.headerMaps = headerMaps;
        return this;
    }

    public SingleHttpUtils log(boolean isShowLog) {
        this.isShowLog = isShowLog;
        return this;
    }

    public SingleHttpUtils cache(boolean cache) {
        this.cache = cache;
        return this;
    }

    public SingleHttpUtils saveCookie(boolean saveCookie) {
        this.saveCookie = saveCookie;
        return this;
    }

    public SingleHttpUtils cachePath(String cachePath, long maxSize) {
        this.cachePath = cachePath;
        this.cacheMaxSize = maxSize;
        return this;
    }

    public SingleHttpUtils readTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public SingleHttpUtils writeTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public SingleHttpUtils connectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }


    public SingleHttpUtils client(OkHttpClient okClient) {
        this.okClient = okClient;
        return this;
    }

    /**
     * 使用自己自定义参数创建请求
     *
     * @param cls
     * @param <K>
     * @return
     */
    public <K> K createSApi(Class<K> cls) {
        return getSingleRetrofitBuilder().build().create(cls);
    }


    /**
     * 单个RetrofitBuilder
     *
     * @return
     */
    public Retrofit.Builder getSingleRetrofitBuilder() {

        Retrofit.Builder singleRetrofitBuilder = new Retrofit.Builder();

        if (converterFactories.isEmpty()) {
            //获取全局的对象重新设置
            List<Converter.Factory> listConverterFactory = RetrofitClient.getInstance().getRetrofit().converterFactories();
            for (Converter.Factory factory : listConverterFactory) {
                singleRetrofitBuilder.addConverterFactory(factory);
            }
        } else {
            for (Converter.Factory converterFactory : converterFactories) {
                singleRetrofitBuilder.addConverterFactory(converterFactory);
            }
        }

        if (adapterFactories.isEmpty()) {
            //获取全局的对象重新设置
            List<CallAdapter.Factory> listAdapterFactory = RetrofitClient.getInstance().getRetrofit().callAdapterFactories();
            for (CallAdapter.Factory factory : listAdapterFactory) {
                singleRetrofitBuilder.addCallAdapterFactory(factory);
            }

        } else {
            for (CallAdapter.Factory adapterFactory : adapterFactories) {
                singleRetrofitBuilder.addCallAdapterFactory(adapterFactory);
            }
        }


        if (TextUtils.isEmpty(baseUrl)) {
            singleRetrofitBuilder.baseUrl(RetrofitClient.getInstance().getRetrofit().baseUrl());
        } else {
            singleRetrofitBuilder.baseUrl(baseUrl);
        }

        singleRetrofitBuilder.client(okClient == null ? getSingleOkHttpBuilder().build() : okClient);

        return singleRetrofitBuilder;
    }

    /**
     * 获取单个 OkHttpClient.Builder
     *
     * @return
     */
    private OkHttpClient.Builder getSingleOkHttpBuilder() {

        OkHttpClient.Builder singleOkHttpBuilder = new OkHttpClient.Builder();

        singleOkHttpBuilder.retryOnConnectionFailure(true);

        singleOkHttpBuilder.addInterceptor(new HeaderInterceptor(headerMaps));

        if (cache) {
            CacheInterceptor cacheInterceptor = new CacheInterceptor();
            Cache cache;
            if (!TextUtils.isEmpty(cachePath) && cacheMaxSize > 0) {
                cache = new Cache(new File(cachePath), cacheMaxSize);
            } else {
                cache = new Cache(new File(Environment.getExternalStorageDirectory().getPath() + "/rxHttpCacheData")
                        , 1024 * 1024 * 100);
            }
            singleOkHttpBuilder.addInterceptor(cacheInterceptor)
                    .addNetworkInterceptor(cacheInterceptor)
                    .cache(cache);
        }
        if (isShowLog) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("RxHttpUtils", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            singleOkHttpBuilder.addInterceptor(loggingInterceptor);
        }

        if (saveCookie) {
            singleOkHttpBuilder
                    .addInterceptor(new AddCookiesInterceptor())
                    .addInterceptor(new ReceivedCookiesInterceptor());
        }

        singleOkHttpBuilder.readTimeout(readTimeout > 0 ? readTimeout : 10, TimeUnit.SECONDS);

        singleOkHttpBuilder.writeTimeout(writeTimeout > 0 ? writeTimeout : 10, TimeUnit.SECONDS);

        singleOkHttpBuilder.connectTimeout(connectTimeout > 0 ? connectTimeout : 10, TimeUnit.SECONDS);

        return singleOkHttpBuilder;
    }

}
