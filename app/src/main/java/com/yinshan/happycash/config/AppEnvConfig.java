package com.yinshan.happycash.config;

/**
 * Created by huxin on 2018/3/2.
 */

public enum AppEnvConfig {

    /**
     * 本地环境
     */
    LOCAL(
            AppNetConfig.LOCAL_NET_CONFIG,
            "本地环境",
            "LOCAL",
            "https://www.happycash.id"
    ),

    /**
     * 测试环境
     *   "https://www.rupiahplus.com/api/"
     */
    TEST(
            AppNetConfig.TEST_NET_CONFIG,
            "测试环境",
            "TEST",
            "https://www.happycash.id/api/"
    ),

    /**
     * 正式环境
     */
    RELEASE(
            AppNetConfig.RELEASE_NET_CONFIG,
            "正式环境",
            "RELEASE",
            "https://www.happycash.id/api/"
    );


    private int index;
    private String name;
    private String tag;
    private String apiUrl;

    AppEnvConfig(int index, String name, String tag, String apiUrl){
        this.index = index;
        this.name = name;
        this.tag = tag;
        this.apiUrl = apiUrl;
    }

    public int getIndex() {

        return index;
    }

    public String getName() {

        return name;
    }

    public String getTag(){
        return tag;
    }

    public String getApiUrl() {

        return apiUrl;
    }


    public static AppEnvConfig typeOf(String appEnvName){
        return valueOf(appEnvName);
    }

    public boolean isRelease(){
        if(getTag().equals("RELEASE"))
            return true;
        return false;
    }

    public static AppEnvConfig indexOf(int index){
        AppEnvConfig envConfig = RELEASE;
        if (RELEASE.getIndex() == index) {
            envConfig = RELEASE;
        } else if (TEST.getIndex() == index) {
            envConfig = TEST;
        } else if (LOCAL.getIndex() == index) {
            envConfig = LOCAL;
        }
        return envConfig;
    }
}
