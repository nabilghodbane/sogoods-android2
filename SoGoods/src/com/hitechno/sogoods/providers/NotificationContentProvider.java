package com.hitechno.sogoods.providers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.hitechno.sogoods.SoGoodsApplication;
import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.models.Notification;

public class NotificationContentProvider {
	SoGoodsApplication goodsApplication;
	List<Notification> lstNotifications;

	public NotificationContentProvider() {
		goodsApplication = SoGoodsApplication.getInstance();
		lstNotifications = new ArrayList<Notification>();
	}

	public List<Notification> getAllNotification(String dateStart, int kmax, int offset){
		lstNotifications = new ArrayList<Notification>();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(ConstantsHelper.Notification.API_GET_NOTIFICATION);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Notification.getNoticationToParams(goodsApplication.getApi_key(), dateStart, kmax, offset, "full");

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String result = ConstantsHelper.inputStreamToString(response.getEntity().getContent());
			Log.e("result notifi","result notifi =" + result);
			JSONObject jsonObject = new JSONObject(result);
			if(jsonObject.has("notifications")){
				String notifications = jsonObject.getString("notifications");
				JSONArray jsonArray = new JSONArray(notifications);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonVal = jsonArray.getJSONObject(i);
					Notification notifi = new Notification();
					notifi.type = jsonVal.getString("type");
					notifi.date = jsonVal.getString("date");

					if(jsonVal.has("iduser")){
						notifi.iduser = jsonVal.getInt("iduser");
					}else{
						notifi.iduser=0;
					}
					
					if(jsonVal.has("username")){
						notifi.username = jsonVal.getString("username");	
					}
					else{
						notifi.username="";
					}
					
					if(jsonVal.has("idcomment")){
						notifi.idcomment = jsonVal.getInt("idcomment");		
					}else{
						notifi.idcomment = 0;
					}
				
					if(jsonVal.has("")){
						notifi.nbcomments = jsonVal.getInt("nbcomments");
					}else{
						notifi.nbcomments = 0;
					}
					
					lstNotifications.add(notifi);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		finally{
			httpclient.getConnectionManager().shutdown();
		}
		return lstNotifications;
	}
	
	//  get_notifications_settings
	public List<Notification> getNotificationSetting(){
		lstNotifications = new ArrayList<Notification>();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(ConstantsHelper.Notification.API_GET_NOTIFICATION_SETTING);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Notification.getNotificationSetting(goodsApplication.getApi_key());

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String result = ConstantsHelper.inputStreamToString(response.getEntity().getContent());
			JSONObject jsonObject = new JSONObject(result);
			JSONObject valjson = jsonObject.getJSONObject("notifications_settings");
			Notification notification = new Notification();
			notification.newFollowerNum = valjson.getInt("new_follower");
			notification.newCommentOnMyProductNum = valjson.getInt("new_comment_on_my_product");
			notification.followingAddProductNum = valjson.getInt("following_add_product");
			notification.followingAddCommentNUm = valjson.getInt("following_add_comment");
			notification.followingAddPictureNum = valjson.getInt("following_add_picture");
			
			lstNotifications.add(notification);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		return lstNotifications;
	}
	// set notification setting
	public boolean setNotificationSetting(int newfoll, int newComm, int follPro, int follComm, int follPic){
		lstNotifications = new ArrayList<Notification>();
		boolean succ = false;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(ConstantsHelper.Notification.API_SET_NOTIFICATION);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Notification.setNotificationSettingToParams(goodsApplication.getApi_key(),newfoll, newComm,follPro, follComm, follPic);

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String result = ConstantsHelper.inputStreamToString(response.getEntity().getContent());
			Log.e("result save","result save =" + result);
			JSONObject jsonObject = new JSONObject(result);
			succ = jsonObject.getBoolean("success");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		return succ;
	}
}
