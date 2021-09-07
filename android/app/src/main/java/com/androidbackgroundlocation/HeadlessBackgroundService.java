package com.androidbackgroundlocation;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import javax.annotation.Nullable;

public class HeadlessBackgroundService extends HeadlessJsTaskService {
  @Nullable
  protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
    Bundle extras = intent.getExtras();
    return new HeadlessJsTaskConfig(
      "Headless",
      extras != null ? Arguments.fromBundle(extras) : null,
      5000,
      true
    );
  }
}