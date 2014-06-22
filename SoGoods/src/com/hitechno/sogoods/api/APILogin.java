package com.hitechno.sogoods.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.util.Debugs;

public class APILogin
{
  public boolean checkEMailExisted(String paramString)
  {
    try
    {
//    	for(int i=0;i<ConstantsHelper.Profiles.checkExistedEmailToParams(paramString).size();i++)
//    	Log.w("bingo",  ""+ConstantsHelper.Profiles.checkExistedEmailToParams(paramString).toString());
    	
    	boolean bool = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.4/profile/email_used.json", ConstantsHelper.Profiles.checkExistedEmailToParams(paramString))).getBoolean("email_used");
        
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public boolean createUser(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt1, String paramString7, String paramString8, String paramString9, int paramInt2, int paramInt3, String paramString10, String paramString11, String paramString12)
  {
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpPost localHttpPost = new HttpPost("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.4/profile/create.json");
    Debugs.show("i", "Connectivity", "calling api : http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.4/profile/create.json");
    try
    {
      File localFile1 = new File(paramString9);
      File localFile2 = new File(paramString10);
      File localFile3 = new File(paramString11);
      MultipartEntity localMultipartEntity = new MultipartEntity();
      FileBody localFileBody1 = new FileBody(localFile1, "image/*");
      FileBody localFileBody2 = new FileBody(localFile2, "image/*");
      FileBody localFileBody3 = new FileBody(localFile3, "image/*");
      localMultipartEntity.addPart("picture", localFileBody1);
      localMultipartEntity.addPart("profile_picture_rectangle", localFileBody2);
      localMultipartEntity.addPart("profile_picture_square", localFileBody3);
      StringBody localStringBody1 = new StringBody(paramString1);
      localMultipartEntity.addPart(new FormBodyPart("firstname", localStringBody1));
      StringBody localStringBody2 = new StringBody(paramString2);
      localMultipartEntity.addPart(new FormBodyPart("lastname", localStringBody2));
      StringBody localStringBody3 = new StringBody(paramString3);
      localMultipartEntity.addPart(new FormBodyPart("birthdate", localStringBody3));
      StringBody localStringBody4 = new StringBody(paramString4);
      FormBodyPart localFormBodyPart1 = new FormBodyPart("gender", localStringBody4);
      localMultipartEntity.addPart(localFormBodyPart1);
      StringBody localStringBody5 = new StringBody(paramString5);
      localMultipartEntity.addPart(new FormBodyPart("email", localStringBody5));
      StringBody localStringBody6 = new StringBody(paramString6);
      FormBodyPart localFormBodyPart2 = new FormBodyPart("password", localStringBody6);
      localMultipartEntity.addPart(localFormBodyPart2);
      FormBodyPart localFormBodyPart3 = new FormBodyPart("idcity", new StringBody(String.valueOf(paramInt1)));
      localMultipartEntity.addPart(localFormBodyPart3);
      StringBody localStringBody7 = new StringBody(paramString7);
      FormBodyPart localFormBodyPart4 = new FormBodyPart("activity", localStringBody7);
      localMultipartEntity.addPart(localFormBodyPart4);
      StringBody localStringBody8 = new StringBody(paramString8);
      FormBodyPart localFormBodyPart5 = new FormBodyPart("username", localStringBody8);
      localMultipartEntity.addPart(localFormBodyPart5);
      localMultipartEntity.addPart(new FormBodyPart("display_real_name", new StringBody(String.valueOf(paramInt2))));
      localMultipartEntity.addPart(new FormBodyPart("display_birthdate", new StringBody(String.valueOf(paramInt3))));
      StringBody localStringBody9 = new StringBody(paramString12);
      FormBodyPart localFormBodyPart6 = new FormBodyPart("language", localStringBody9);
      localMultipartEntity.addPart(localFormBodyPart6);
      localHttpPost.setEntity(localMultipartEntity);
      String str = Connectivity.inputStreamToString(localDefaultHttpClient.execute(localHttpPost).getEntity().getContent());
      Debugs.show("i", "Connectivity", "result: " + str);
      JSONObject localJSONObject = new JSONObject(str);
      boolean bool = localJSONObject.getBoolean("success");
      return bool;
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      localClientProtocolException.printStackTrace();
      return false;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return false;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return false;
    }
    finally
    {
      localDefaultHttpClient.getConnectionManager().shutdown();
    }
  }
  

  
  public String login(String paramString1, String paramString2, String paramString3)
  {
    new ArrayList();
    return Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/login.json", ConstantsHelper.Sessions.PostParamsFromParams(paramString1, paramString2, paramString3, "1"));
  }
  
  public boolean logout(String paramString)
  {
    try
    {
      new ArrayList();
      boolean bool = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/logout.json", ConstantsHelper.Profiles.post_logout_profile(paramString))).getBoolean("success");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public String resetPassword(String paramString)
  {
    try
    {
      new ArrayList();
      String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/reset_password.json", ConstantsHelper.Profiles.resetPasswordToParams(paramString));
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
}
