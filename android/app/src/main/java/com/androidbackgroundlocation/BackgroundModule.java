package com.androidbackgroundlocation;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.ReactActivity;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import android.content.Intent;
import android.content.Context;
import android.widget.Toast;
import android.os.Build;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.content.pm.PackageManager;
import android.content.ComponentName;

public class BackgroundModule extends ReactContextBaseJavaModule {
   private static ReactApplicationContext context;
   public static final String CHANNEL_ID = "BUBBLE";
   private View layout = null;
   private TextView text = null;
   private int screenWidth = 0;
   private int screenHeight = 0;
   private int rootWidth = 0;

   BackgroundModule(ReactApplicationContext context) {
       super(context);
       this.context = context;
   }

   @ReactMethod
   public void init() {
      if (layout == null) {
          System.out.println("RN:In init()");
      }
      else {
          System.out.println("RN:Already initialized");
      }
   }

   @ReactMethod
   public void startService() {
      this.context.startService(new Intent(this.context, BackgroundService.class));
      PackageManager pm = this.context.getPackageManager();
      ComponentName componentName = new ComponentName(this.context, StartAppOnBoot.class);
      pm.setComponentEnabledSetting(
        componentName,
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP);
   }

   @ReactMethod
   public void stopService() {
       this.context.stopService(new Intent(this.context, BackgroundService.class));
      PackageManager pm = this.context.getPackageManager();
      ComponentName componentName = new ComponentName(this.context, StartAppOnBoot.class);
      pm.setComponentEnabledSetting(
        componentName,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP);
   }

   @Override
   public String getName() {
       return "BackgroundModule";
   }
}

