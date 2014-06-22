package com.hitechno.sogoods.api;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class APIPhoto
{
  public boolean deletePhotos(int paramInt, String paramString)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new BasicNameValuePair("apikey", paramString));
      localArrayList.add(new BasicNameValuePair("iduser_gallery", String.valueOf(paramInt)));
      boolean bool = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/user_gallery/delete.json", localArrayList)).getBoolean("success");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  /* Error */
  public ArrayList<com.hitechno.sogoods.models.Photo> loadPhotos(int paramInt, String paramString1, String paramString2)
 {
	return null;
    // Byte code:
    //   0: new 14	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 15	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: new 14	java/util/ArrayList
    //   12: dup
    //   13: invokespecial 15	java/util/ArrayList:<init>	()V
    //   16: pop
    //   17: ldc 64
    //   19: iload_1
    //   20: aload_2
    //   21: invokestatic 70	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   24: invokevirtual 74	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   27: ldc 76
    //   29: bipush 50
    //   31: aload_3
    //   32: invokestatic 82	com/sogoods/sogoods/constant/ConstantsHelper$Photos:photos_profile_paramtoparam	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)Ljava/util/List;
    //   35: invokestatic 46	com/sogoods/sogoods/api/Connectivity:loadContentFromAPI	(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
    //   38: astore 7
    //   40: new 84	org/json/JSONArray
    //   43: dup
    //   44: new 38	org/json/JSONObject
    //   47: dup
    //   48: aload 7
    //   50: invokespecial 49	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   53: ldc 86
    //   55: invokevirtual 90	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   58: invokespecial 91	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   61: astore 8
    //   63: iconst_0
    //   64: istore 9
    //   66: iload 9
    //   68: aload 8
    //   70: invokevirtual 95	org/json/JSONArray:length	()I
    //   73: if_icmplt +6 -> 79
    //   76: aload 4
    //   78: areturn
    //   79: aload 8
    //   81: iload 9
    //   83: invokevirtual 99	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   86: astore 11
    //   88: new 101	com/sogoods/sogoods/models/Photo
    //   91: dup
    //   92: invokespecial 102	com/sogoods/sogoods/models/Photo:<init>	()V
    //   95: astore 12
    //   97: aload 11
    //   99: ldc 104
    //   101: invokevirtual 107	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   104: ifeq +47 -> 151
    //   107: aload 11
    //   109: ldc 104
    //   111: invokevirtual 111	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   114: instanceof 38
    //   117: ifeq +34 -> 151
    //   120: aload 11
    //   122: ldc 104
    //   124: invokevirtual 114	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   127: astore 14
    //   129: aload 14
    //   131: ldc 116
    //   133: invokevirtual 107	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   136: ifeq +15 -> 151
    //   139: aload 12
    //   141: aload 14
    //   143: ldc 116
    //   145: invokevirtual 90	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   148: putfield 120	com/sogoods/sogoods/models/Photo:url	Ljava/lang/String;
    //   151: aload 4
    //   153: aload 12
    //   155: invokevirtual 121	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   158: pop
    //   159: iinc 9 1
    //   162: goto -96 -> 66
    //   165: astore 10
    //   167: aload 10
    //   169: invokevirtual 122	org/json/JSONException:printStackTrace	()V
    //   172: aload 4
    //   174: areturn
    //   175: astore 6
    //   177: aload 6
    //   179: invokevirtual 58	java/lang/Exception:printStackTrace	()V
    //   182: aload 4
    //   184: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	185	0	this	APIPhoto
    //   0	185	1	paramInt	int
    //   0	185	2	paramString1	String
    //   0	185	3	paramString2	String
    //   7	176	4	localArrayList	ArrayList
    //   175	3	6	localException	Exception
    //   38	11	7	str	String
    //   61	19	8	localJSONArray	org.json.JSONArray
    //   64	96	9	i	int
    //   165	3	10	localJSONException	org.json.JSONException
    //   86	35	11	localJSONObject1	JSONObject
    //   95	59	12	localPhoto	com.hitechno.sogoods.models.Photo
    //   127	15	14	localJSONObject2	JSONObject
    // Exception table:
    //   from	to	target	type
    //   40	63	165	org/json/JSONException
    //   66	76	165	org/json/JSONException
    //   79	151	165	org/json/JSONException
    //   151	159	165	org/json/JSONException
    //   9	40	175	java/lang/Exception
    //   40	63	175	java/lang/Exception
    //   66	76	175	java/lang/Exception
    //   79	151	175	java/lang/Exception
    //   151	159	175	java/lang/Exception
    //   167	172	175	java/lang/Exception
  }
}
