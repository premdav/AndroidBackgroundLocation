package com.androidbackgroundlocation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class StartAppOnBoot extends BroadcastReceiver {
   @Override
   public void onReceive(Context context, Intent intent) {
      System.out.println("RN:In broadcast receiver");
      if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
         context.startForegroundService(new Intent(context, BackgroundService.class));
      }
   }
}
