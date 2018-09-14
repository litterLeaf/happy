package com.yinshan.happycash.view.information.view;

import android.util.Pair;

import com.yinshan.happycash.view.information.view.impl.support.FileUploadType;
import com.yinshan.happycash.widget.inter.IBaseView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by huxin on 2018/4/18.
 */

public interface IUploadPhotoView extends IBaseView {

    void uploadPhoto(Pair<Call<ResponseBody>, Response<ResponseBody>> callResponsePair,FileUploadType fileUploadType);
    void uploadFailed(Throwable e,FileUploadType fileUploadType);

}
