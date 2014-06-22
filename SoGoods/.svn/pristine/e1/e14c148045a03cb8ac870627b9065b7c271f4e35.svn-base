package com.hitechno.sogoods.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SGTools
{
  public static boolean DeleteImage(String paramString)
  {
    File localFile = new File(Environment.getExternalStorageDirectory() + "/.sogoods/image/" + paramString);
    if (localFile.exists()) {
      return localFile.delete();
    }
    return false;
  }
  
  public static boolean DownLoadImage(Context paramContext, String paramString)
  {
    try
    {
      File localFile1 = new File(FindStorageFile(paramContext) + "/.sogoods/image/");
      if (!localFile1.exists()) {
        localFile1.mkdirs();
      }
      File localFile2 = new File(FindStorageFile(paramContext) + "/.sogoods/image/" + paramString);
      InputStream localInputStream = new URL(paramString).openConnection().getInputStream();
      if (!localFile2.exists()) {
        localFile2.getParentFile().mkdirs();
      }
      BufferedInputStream localBufferedInputStream = new BufferedInputStream(localInputStream, 1024);
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
      byte[] arrayOfByte = new byte[1024];
      for (;;)
      {
        int i = localBufferedInputStream.read(arrayOfByte);
        if (i == -1)
        {
          localInputStream.close();
          localFileOutputStream.close();
          return true;
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
    
    }
    catch (Exception localException)
    {
      Log.e("DownLoadImage", "Error: " + localException.toString());
    }
	return false;
  }
  
  private static String FindStorageFile(Context paramContext)
  {
    if (Environment.getExternalStorageDirectory().equals("mounted")) {
      return Environment.getExternalStorageDirectory().toString();
    }
    return paramContext.getCacheDir().toString();
  }
  
  public static Bitmap getImageFromStorage(Context paramContext, String paramString)
  {
    return BitmapFactory.decodeFile(FindStorageFile(paramContext) + "/.sogoods/image/" + paramString);
  }
  
  public static String getTimeFromDate(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
    try
    {
      Date localDate = localSimpleDateFormat.parse(paramString);
      String str = new SimpleDateFormat("hh.mm a", Locale.US).format(localDate);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static boolean isImageEmpty(Context paramContext, String paramString)
  {
    return !new File(FindStorageFile(paramContext) + "/.sogoods/image/" + paramString).exists();
  }
}

