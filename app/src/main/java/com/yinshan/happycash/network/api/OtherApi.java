package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.main.model.HXBean;
import com.yinshan.happycash.view.main.model.YWUser;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

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
 *         ┃　　　┃   兽神保佑
 *         ┃　　　┃   代码无BUG！
 *         ┃　　　┗━━━┓
 *         ┃　　　　　　　┣┓
 *         ┃　　　　　　　┏┛
 *         ┗┓┓┏━┳┓┏┛
 *             ┃┫┫　┃┫┫
 *             ┗┻┛　┗┻┛
 * <p>
 * 文件描述：其他三方的请求
 * 创建人：    admin
 * 创建时间：2018/8/28
 */
public interface OtherApi {

    @GET("{tenantId}/robots/visitor/greetings/app")
    Observable<HXBean> getMessage(@Path("tenantId") String tenantId);

    //从服务器获取用户名密码
    @GET("chat/account/v2")
    Observable<YWUser> getChatUserInfo(@Header("X-AUTH-TOKEN") String token);
}
