package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.me.model.LoanItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by huxin on 2018/3/26.
 */

public interface LoanApi {

    @GET("loanapps")
    Observable<List<LoanItem>> getLoanList(@Header("X-AUTH-TOKEN") String token);

}
