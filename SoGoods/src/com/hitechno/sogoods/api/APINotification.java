package com.hitechno.sogoods.api;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Notification;

public class APINotification
{
  public ArrayList<Notification> getAllNotification(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3)
  {
    ArrayList<Notification> localArrayList = new ArrayList<Notification>();

      int i;
      Notification localNotification;
      JSONObject localJSONObject4;
      try
      {
        JSONObject localJSONObject1 = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_notifications.json", ConstantsHelper.Notification.getNoticationToParams(paramString2, paramString1, paramInt1, paramInt2, "full", paramString3)));
        Log.w("notifs", ""+localJSONObject1.getString("notifications"));
        if (!localJSONObject1.has("notifications")) {

        	return localArrayList;
        }
        JSONArray localJSONArray = new JSONArray(localJSONObject1.getString("notifications"));
        i = 0;
        
        if (i >= localJSONArray.length()) {
          return localArrayList;
        }
        for(i=0;i<localJSONArray.length();i++)
        {
            JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
            localNotification = new Notification();
            localNotification.type = localJSONObject2.getString("type");
//            if ((!localNotification.type.equals("new_follower")) && (!localNotification.type.equals("following_add_picture")) && (!localNotification.type.equals("following_add_product")) && (!localNotification.type.equals("new_comment_on_my_product")) && (!localNotification.type.equals("following_add_comment"))) {
//    continue;
//            }
//            JSONObject localJSONObject5;
//            if ((localJSONObject2.has("profile_picture_square")) && ((localJSONObject2.get("profile_picture_square") instanceof JSONObject)))
//            {
//              localJSONObject5 = localJSONObject2.getJSONObject("profile_picture_square");
//              if (localJSONObject5.has("80x80")) {
//                localNotification.imgProfile = localJSONObject5.getString("80x80");
//              }
//              if (localJSONObject5.has("40x40")) {
//            	  localNotification.imgProfile = localJSONObject5.getString("40x40");
//                }
//               
//            }
//            else
//            {
//              if ((localJSONObject2.has("product_picture_square")) && ((localJSONObject2.get("product_picture_square") instanceof JSONObject)))
//              {
//                localJSONObject4 = localJSONObject2.getJSONObject("product_picture_square");
//                if (localJSONObject4.has("250x250")) {
//                	localNotification.imgConcerned = localJSONObject4.getString("250x250");
//                }
//                if (localJSONObject4.has("125x125")) {
//              	  localNotification.imgConcerned = localJSONObject4.getString("125x125");
//                }
//              }
//              if ((localJSONObject2.has("user_gallery_picture")) && ((localJSONObject2.get("user_gallery_picture") instanceof JSONObject)))
//              {
//                JSONObject localJSONObject3 = localJSONObject2.getJSONObject("user_gallery_picture");
//                if (localJSONObject3.has("100x100")) {
//                  localNotification.imgConcerned = localJSONObject3.getString("100x100");
//                }
//              }
              localNotification.date = localJSONObject2.getString("date");
              if (localJSONObject2.has("iduser")) {
                localNotification.iduser = localJSONObject2.getInt("iduser");
              }
              if (localJSONObject2.has("username")) {
                localNotification.username = localJSONObject2.getString("username");
              }
              if (localJSONObject2.has("idcomment")) {
                localNotification.idcomment = localJSONObject2.getInt("idcomment");
              }
              if (localJSONObject2.has("")) {
                localNotification.nbcomments = localJSONObject2.getInt("nbcomments");
              }
//              Log.w("thenotif", ""+localNotification);
//              Log.w("thejson", ""+localJSONObject2);
//            }
          
            localArrayList.add(localNotification);
        }
        return localArrayList;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return localArrayList;
      }
  }
  
  public ArrayList<Notification> getNotificationSetting(String paramString)
  {
    ArrayList<Notification> localArrayList = new ArrayList<Notification>();
    try
    {

      JSONObject localJSONObject = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_notifications_settings.json", ConstantsHelper.Notification.getNotificationSetting(paramString))).getJSONArray("notifications_settings").getJSONObject(0);
      Notification localNotification = new Notification();
      localNotification.newFollowerNum = localJSONObject.getInt("new_follower");
      localNotification.newCommentOnMyProductNum = localJSONObject.getInt("new_comment_on_my_product");
      localNotification.followingAddProductNum = localJSONObject.getInt("following_add_product");
      localNotification.followingAddCommentNUm = localJSONObject.getInt("following_add_comment");
      localNotification.followingAddPictureNum = localJSONObject.getInt("following_add_picture");
      localArrayList.add(localNotification);
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localArrayList;
  }
  
  public boolean setNotificationSetting(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString)
  {
    try
    {
     
      boolean bool = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/set_notifications_settings.json", ConstantsHelper.Notification.setNotificationSettingToParams(paramString, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5))).getBoolean("success");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    
  }
}

