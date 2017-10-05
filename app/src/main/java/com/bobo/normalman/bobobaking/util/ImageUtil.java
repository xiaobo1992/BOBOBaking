package com.bobo.normalman.bobobaking.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bobo.normalman.bobobaking.R;
import com.squareup.picasso.Picasso;

/**
 * Created by xiaobozhang on 10/4/17.
 */

public class ImageUtil {

    public static void loadImage(Context context, ImageView imageView, String url) {
        if(TextUtils.isEmpty(url)) {
            return;
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        try {
            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_restaurant_menu_black_24px)
                    .error(R.drawable.ic_restaurant_menu_black_24px)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
