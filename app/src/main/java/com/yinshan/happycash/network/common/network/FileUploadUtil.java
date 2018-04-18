package com.yinshan.happycash.network.common.network;

import android.util.Pair;

import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.utils.FileUtil;
import com.yinshan.happycash.utils.LoggerWrapper;
import com.yinshan.happycash.view.information.view.impl.support.FileUploadType;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by huxin on 2018/4/18.
 */

public class FileUploadUtil {

    public static Observable<Pair<Call<ResponseBody>, Response<ResponseBody>>>
    uploadPhotoFile(FileUploadType type, File file, String token) {
        MediaType mediaType;
        mediaType = MediaType.parse(FileUtil.getMimeType(file));
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(mediaType, file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        //        RequestBody fileType = RequestBody.create(MultipartBody.FORM, FileUploadType.KTP_PHOTO.name().getBytes());

        // finally, execute the request

        final RecordApi service = RxHttpUtils.getInstance().createApi(RecordApi.class);
        final Call<ResponseBody> call = service.uploadPhoto(body, type.name(), token);

        return Observable.create(new Observable.OnSubscribe<Pair<Call<ResponseBody>, Response<ResponseBody>>>() {
            @Override
            public void call(final Subscriber<? super Pair<Call<ResponseBody>, Response<ResponseBody>>> subscriber) {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                                           Response<ResponseBody> response) {
                        LoggerWrapper.d("Upload", "success");
                        if(response.isSuccessful()){
                            subscriber.onNext(new Pair<>(call, response));
                        }else{
                            subscriber.onError(new HttpException(response));

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        LoggerWrapper.d("Upload error:", t.getMessage());
                        subscriber.onError(new UploadFileException(call, t));

                    }
                });


            }
        });

    }


    public static class UploadFileException extends Throwable {
        Call<ResponseBody> call;

        public UploadFileException(Call<ResponseBody> call, Throwable t) {
            super(t);
            this.call = call;
        }

        public Call<ResponseBody> getCall() {
            return call;
        }

    }

}
