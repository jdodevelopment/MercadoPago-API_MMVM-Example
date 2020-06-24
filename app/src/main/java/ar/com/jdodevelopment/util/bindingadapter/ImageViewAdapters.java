package ar.com.jdodevelopment.util.bindingadapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class ImageViewAdapters {


    @BindingAdapter({"imageUrl"})
    public static void imageLoader(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


    @BindingAdapter({"imageUrl", "placeholder"})
    public static void imageLoader(ImageView imageView, String imageUrl, Drawable placeHolder) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(placeHolder)
                .into(imageView);

    }



}
