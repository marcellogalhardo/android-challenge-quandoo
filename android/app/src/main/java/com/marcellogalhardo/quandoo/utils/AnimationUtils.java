package com.marcellogalhardo.quandoo.utils;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

public class AnimationUtils {

    @SuppressWarnings("NewApi")
    public static void startCircularRevealAnimation(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            int x = view.getRight();
            int y = view.getBottom();

            int startRadius = 0;
            int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
            anim.start();
        }
    }

}
