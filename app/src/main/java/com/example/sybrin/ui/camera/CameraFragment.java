package com.example.sybrin.ui.camera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraExtensionCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sybrin.MainActivity;
import com.example.sybrin.MainActivity2;
import com.example.sybrin.R;
import com.example.sybrin.components.Camera;
import com.example.sybrin.databinding.FragmentCameraBinding;


public class CameraFragment extends Fragment {
    private static final String TAG = "CameraFragment";
    private FragmentCameraBinding binding;
    private TextureView textureView;
    private CameraDevice camera ;
    private CameraManager cameraManager;
    private  CameraDevice.StateCallback stateCallback;
    private String cameraID;
    private Size imageDimension;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private  TextureView.SurfaceTextureListener  textureViewListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CameraViewModel homeViewModel =
                new ViewModelProvider(this).get(CameraViewModel.class);

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Context context = root.getContext();


        //get CamerDevice
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);


        stateCallback = new CameraDevice.StateCallback() {
            @Override
            public void onOpened(@NonNull CameraDevice cameraDevice) {
                Log.e(TAG, "opened camera");
                camera = cameraDevice;

            }

            @Override
            public void onDisconnected(@NonNull CameraDevice cameraDevice) {
                cameraDevice.close();
            }

            @Override
            public void onError(@NonNull CameraDevice cameraDevice, int i) {
                cameraDevice.close();
                cameraDevice = null;
            }
        };






        //Texture view set up
        textureView = (TextureView) root.findViewById(R.id.textureView);
        textureViewListener = new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
                openCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

            }
        };

        textureView.setSurfaceTextureListener(textureViewListener);











        //Listener for taking a photo
      /*  Button photoButton = (Button) root.findViewById(R.id.photoButton);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(root.getContext(), MainActivity2.class);
                root.getContext().startActivity(intent);

            }
        });
*/


        return root;
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();

        if (textureView.isAvailable()) {
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(textureViewListener);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




    public void  openCamera(){


        try {
            cameraID = cameraManager.getCameraIdList()[0];
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraID);
            StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            imageDimension =  map.getOutputSizes(SurfaceTexture.class)[0];

            if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
                return;
            }
            cameraManager.openCamera(cameraID, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }finally {

        }


    }
}