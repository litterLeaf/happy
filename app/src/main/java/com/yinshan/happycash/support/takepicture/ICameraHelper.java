package com.yinshan.happycash.support.takepicture;

import android.hardware.Camera;

/**
 * CameraHelper的统一接口
 * @author admin
 * @date 2017-09-24
 */
public interface ICameraHelper {

    int getNumberOfCameras();

    Camera openCameraFacing(int facing) throws Exception;

    boolean hasCamera(int facing);

    void getCameraInfo(int cameraId, Camera.CameraInfo cameraInfo);
}
