package com.example.sybrin.components;

import android.content.Context;
import android.hardware.camera2.CameraManager;

public class Camera {
    
    private Context context;
    private CameraManager cameraManager;
    public Camera(Context context){
        context = this.context;

        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    public void open() {
    }


    public void takePhoto(){

    }


    public void close(){

    }

}
