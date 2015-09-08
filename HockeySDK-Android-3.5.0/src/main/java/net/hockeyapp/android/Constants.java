package net.hockeyapp.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import java.io.File;
import java.security.MessageDigest;

/**
 * <h3>Description</h3>
 * 
 * Various constants and meta information loaded from the context.
 * 
 * <h3>License</h3>
 * 
 * <pre>
 * Copyright (c) 2009 nullwire aps
 * Copyright (c) 2011-2014 Bit Stadium GmbH
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * </pre>
 *
 * @author Mads Kristiansen
 * @author Glen Humphrey
 * @author Evan Charlton
 * @author Peter Hewitt
 * @author Thomas Dohmke
 **/
public class Constants {
  /**
   * Path where crash logs and temporary files are stored.
   */
  public static String FILES_PATH = null;

  /**
   * The app's version code.
   */
  public static String APP_VERSION = null;
  
  /**
   * The app's version name.
   */
  public static String APP_VERSION_NAME = null;

  /**
   * The app's package name.
   */
  public static String APP_PACKAGE = null;
  
  /**
   * The device's OS version.
   */
  public static String ANDROID_VERSION  = null;

  /**
   * The device's model name.
   */
  public static String PHONE_MODEL = null;

  /**
   * The device's model manufacturer name.
   */
  public static String PHONE_MANUFACTURER = null;

  /**
   * Unique identifier for crash reports. 
   */
  public static String CRASH_IDENTIFIER = null;

  /**
   * Tag for internal logging statements.
   */
  public static final String TAG = "HockeyApp";
  
  /**
   * HockeyApp API URL.
   */
  public static final String BASE_URL = "https://sdk.hockeyapp.net/";
  
  /**
   * Name of this SDK.
   */
  public static final String SDK_NAME = "HockeySDK";
  
  /**
   * Version of this SDK.
   */
  public static final String SDK_VERSION = "3.5.0";

  /**
   * Initializes constants from the given context. The context is used to set 
   * the package name, version code, and the files path.
   *
   * @param context The context to use. Usually your Activity object.
   */
  public static void loadFromContext(Context context) {
    Constants.ANDROID_VERSION = android.os.Build.VERSION.RELEASE;
    Constants.PHONE_MODEL = android.os.Build.MODEL;
    Constants.PHONE_MANUFACTURER = android.os.Build.MANUFACTURER;

    loadFilesPath(context);
    loadPackageData(context);
    loadCrashIdentifier(context);
  }
  
  /**
   * Returns a file representing the folder in which screenshots are stored.
   *
   * @return A file representing the screenshot folder.
   */
  public static File getHockeyAppStorageDir() {
    File externalStorage = Environment.getExternalStorageDirectory();

    File dir = new File(externalStorage.getAbsolutePath() + "/" + Constants.TAG);
    dir.mkdirs();
    return dir;
  }

  /**
   * Helper method to set the files path. If an exception occurs, the files 
   * path will be null!
   * 
   * @param context The context to use. Usually your Activity object.
   */
  private static void loadFilesPath(Context context) {
    if (context != null) {
      try {
        File file = context.getFilesDir();

        // The file shouldn't be null, but apparently it still can happen, see
        // http://code.google.com/p/android/issues/detail?id=8886
        if (file != null) {
          Constants.FILES_PATH = file.getAbsolutePath();
        }
      }
      catch (Exception e) {
        Log.e(TAG, "Exception thrown when accessing the files dir:");
        e.printStackTrace();
      }
    }
  }

  /**
   * Helper method to set the package name and version code. If an exception 
   * occurs, these values will be null! 
   * 
   * @param context The context to use. Usually your Activity object.
   */
  private static void loadPackageData(Context context) {
    if (context != null) {
      try {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        Constants.APP_PACKAGE = packageInfo.packageName;
        Constants.APP_VERSION = "" + packageInfo.versionCode;
        Constants.APP_VERSION_NAME = packageInfo.versionName;
        
        int buildNumber = loadBuildNumber(context, packageManager);
        if ((buildNumber != 0) && (buildNumber > packageInfo.versionCode)) {
          Constants.APP_VERSION = "" + buildNumber;
        }
      } 
      catch (Exception e) {
        Log.e(TAG, "Exception thrown when accessing the package info:");
        e.printStackTrace();
      }
    }
  }

  /**
   * Helper method to load the build number from the AndroidManifest. 
   * 
   * @param context the context to use. Usually your Activity object.
   * @param packageManager an instance of PackageManager
   */
  private static int loadBuildNumber(Context context, PackageManager packageManager) {
    try {
      ApplicationInfo appInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
      Bundle metaData = appInfo.metaData;
      if (metaData != null) {
        return metaData.getInt("buildNumber", 0);
      }
    } 
    catch (Exception e) {
      Log.e(TAG, "Exception thrown when accessing the application info:");
      e.printStackTrace();
    }
    
    return 0;
  }

  /**
   * Helper method to load the crash identifier. 
   * 
   * @param context the context to use. Usually your Activity object.
   */
  private static void loadCrashIdentifier(Context context) {
    String deviceIdentifier = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    if ((Constants.APP_PACKAGE != null) && (deviceIdentifier != null)) {
      String combined = Constants.APP_PACKAGE + ":" + deviceIdentifier + ":" + createSalt(context);
      try {
          MessageDigest digest = MessageDigest.getInstance("SHA-1");
          byte[] bytes = combined.getBytes("UTF-8");
          digest.update(bytes, 0, bytes.length);
          bytes = digest.digest();

          Constants.CRASH_IDENTIFIER = bytesToHex(bytes);
      }
      catch (Throwable e) {
      }
    }
  }

  /**
   * Helper method to create a salt for the crash identifier. 
   * 
   * @param context the context to use. Usually your Activity object.
   */
  private static String createSalt(Context context) {
    String fingerprint = "HA" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.PRODUCT.length() % 10);

    String serial = "";
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
      try {
        serial = android.os.Build.class.getField("SERIAL").get(null).toString();
      }
      catch (Throwable t) {
      }
    }
    
    return fingerprint + ":" + serial;
  }

  /**
   * Helper method to convert a byte array to the hex string. 
   * Based on http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
   *
   * @param bytes a byte array
   */
  private static String bytesToHex(byte[] bytes) {
    final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    char[] hex = new char[bytes.length * 2];
    for (int index = 0; index < bytes.length; index++) {
      int value = bytes[index] & 0xFF;
      hex[index * 2] = HEX_ARRAY[value >>> 4];
      hex[index * 2 + 1] = HEX_ARRAY[value & 0x0F];
    }
    String result = new String(hex);
    return result.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");  
  }
}
