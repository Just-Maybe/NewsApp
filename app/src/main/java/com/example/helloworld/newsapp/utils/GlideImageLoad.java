package com.example.helloworld.newsapp.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by helloworld on 2017/8/25.
 */

public class GlideImageLoad extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }
}
