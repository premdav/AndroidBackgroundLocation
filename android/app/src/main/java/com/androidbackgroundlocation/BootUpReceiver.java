package com.androidbackgroundlocation.bootupreceiver;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.Context;
import com.androidbackgroundlocation.service.LocationService;

public class BootUpReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    context.startService(new Intent(context, LocationService.class));
  }
}