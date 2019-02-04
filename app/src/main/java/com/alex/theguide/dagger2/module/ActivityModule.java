package com.alex.theguide.dagger2.module;

import android.app.Activity;
import android.content.Context;

import com.alex.theguide.dagger2.interfaces.MainActivityScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    ActivityModule(Activity context){
        this.context = context;
    }

    @Named("activity_context")
    @MainActivityScope
    @Provides
    public Context context(){
        return context;
    }
}
