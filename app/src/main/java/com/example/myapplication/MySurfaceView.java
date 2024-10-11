package com.example.myapplication;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private final SurfaceHolder holder;

    public MySurfaceView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Here you can start drawing or animations
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Handle size changes here
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Stop any operations when the surface is destroyed
    }
}
