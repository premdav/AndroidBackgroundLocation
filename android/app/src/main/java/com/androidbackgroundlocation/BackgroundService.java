package com.androidbackgroundlocation;

import android.app.Service;
import android.os.Handler;
import android.os.Build;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;
import com.facebook.react.HeadlessJsTaskService;


public class BackgroundService extends Service {
    private Handler handler = new Handler();
    private final LocationListener listener = new LocationListener() {
        @Override
          public void onStatusChanged(String provider, int status, Bundle extras) {
          }

        @Override
          public void onProviderEnabled(String provider) {
          }
        @Override
          public void onProviderDisabled(String provider) {
          }
        @Override
            public void onLocationChanged(Location location) {
                 System.out.println("RN: location changed: " + location.toString());
                 System.out.println("RN: " + location.getLatitude() + ", " + location.getLongitude());
                 Intent myIntent = new Intent(getApplicationContext(), HeadlessBackgroundService.class);
                 Bundle bundle = new Bundle();
                 bundle.putFloat("lat", (float)location.getLatitude());
                 bundle.putFloat("lon", (float)location.getLongitude());
                 myIntent.putExtras(bundle);
                 getApplicationContext().startService(myIntent);
                 HeadlessJsTaskService.acquireWakeLockNow(getApplicationContext());
             }
         };

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            System.out.println("RN:In backgroundService.run()");
            Context context = getApplicationContext();
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Start requesting for location
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, listener);
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.handler.post(this.runnableCode); // Starting the interval

        NotificationChannel channel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel(getString(R.string.channel_id), name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), getString(R.string.channel_id))
                .setContentTitle("Service")
                .setContentText("Running...")
                .setContentIntent(contentIntent)
                .setOngoing(true)
                .build();

        int rand = (int)(Math.random() * 10000) + 1000;
        startForeground(rand, notification);

        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
}
