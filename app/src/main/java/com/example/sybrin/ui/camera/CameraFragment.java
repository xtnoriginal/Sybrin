package com.example.sybrin.ui.camera;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sybrin.R;
import com.example.sybrin.components.Camera;
import com.example.sybrin.databinding.FragmentCameraBinding;


public class CameraFragment extends Fragment {

    private FragmentCameraBinding binding;
    private TextureView textureView;
    private Camera camera ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CameraViewModel homeViewModel =
                new ViewModelProvider(this).get(CameraViewModel.class);

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        camera = new Camera(root.getContext());

        //Texture view set up
        textureView = (TextureView) root.findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
                camera.open();
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
        });




        //Listener for taking a photo
        Button photoButton = (Button) root.findViewById(R.id.photoButton);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}