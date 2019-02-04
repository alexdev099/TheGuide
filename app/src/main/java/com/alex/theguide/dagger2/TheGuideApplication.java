package com.alex.theguide.dagger2;

import android.app.Activity;
import android.app.Application;

import com.alex.theguide.dagger2.component.ApplicationComponent;
import com.alex.theguide.dagger2.component.DaggerApplicationComponent;
import com.alex.theguide.dagger2.module.ContextModule;

public class TheGuideApplication extends Application {
    private ApplicationComponent theGuideApplicationComponent;

    public static TheGuideApplication get(Activity activity){
        return (TheGuideApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        theGuideApplicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this)).build();
    }

    public ApplicationComponent getTheGuideApplicationComponent() {
        return theGuideApplicationComponent;
    }
}
