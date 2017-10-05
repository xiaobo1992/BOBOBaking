package com.bobo.normalman.bobobaking.idlingResource;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by xiaobozhang on 10/4/17.
 */

public class SimpleIdlingResource implements IdlingResource {

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {

    }

    public void setmIsIdleNow(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
    }
}
