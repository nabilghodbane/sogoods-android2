package com.hitechno.sogoods.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Configuration;

public class APIProfile {

	public String loadContentProfileUser(Context context, int id, String apiKey) {
		Configuration config = Configuration.getInstance(context);
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Profiles
					.post_profileDiary_paramtoparam(String.valueOf(id), 1,
							"full", apiKey, Locale.getDefault().getLanguage(),
							config.typePhone);
			String result = Connectivity.loadContentFromAPI(
					ConstantsHelper.Profiles.API_PROFILE_GET, nameValuePairs);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Profile> loadProfileUser(int id, String apiKey,
			String typePhone) {
		ArrayList<Profile> lstpro = new ArrayList<Profile>();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Profiles
					.post_profileDiary_paramtoparam(String.valueOf(id), 1,
							"full", apiKey, Locale.getDefault().getLanguage(),
							typePhone);
			String result = Connectivity.loadContentFromAPI(
					ConstantsHelper.Profiles.API_PROFILE_GET, nameValuePairs);
			try {
				Profile pro = new Profile();
				JSONObject jsonObject = new JSONObject(result);
				JSONObject jsonProfile = jsonObject.getJSONObject("profil");
				pro.profileID = jsonProfile.getInt("iduser");
				if (jsonProfile.has("gender")) {
					pro.gender = jsonProfile.getString("gender");
				}

				if (jsonProfile.has("display_real_name")) {
					pro.displayRealName = jsonProfile
							.getInt("display_real_name");
				}
				pro.username = jsonProfile.getString("username");
				if (jsonProfile.has("firstname")) {
					pro.firstName = jsonProfile.getString("firstname");
				}
				if (jsonProfile.has("lastname")) {
					pro.lastName = jsonProfile.getString("lastname");
				}
				if (jsonProfile.has("activity")) {
					pro.activity = jsonProfile.getString("activity");
				}
				if (jsonProfile.has("birthdate")) {
					String s = jsonProfile.getString("birthdate");
					if (s.equals("")) {
						pro.dateOfBirth = "1987-10-07";
					} else {
						pro.dateOfBirth = jsonProfile.getString("birthdate");
					}
				} else {
					pro.dateOfBirth = "1987-10-07";
				}

				pro.cityName = jsonProfile.getString("city_name");
				pro.email = jsonProfile.getString("email");
				pro.numberOfFollows = jsonProfile.getInt("nbfollower");
				pro.numberOfFollowings = jsonProfile.getInt("nbfollowing");
				pro.numberOfProducts = jsonProfile.getInt("nblike");
				pro.numberOfBrands = jsonProfile.getInt("nbbrand");
				pro.numberOfPhotos = jsonProfile.getInt("nbphoto");
				if (jsonProfile.has("idusers_profession")) {
					pro.idusersProfession = jsonProfile
							.getInt("idusers_profession");
				}

				JSONObject jsonImage;

				if (jsonProfile.has("profile_picture_rectangle")) {
					Object objectRec = jsonProfile
							.get("profile_picture_rectangle");
					if (objectRec instanceof JSONObject) {
						jsonImage = jsonProfile
								.getJSONObject("profile_picture_rectangle");
						if (typePhone.equals("tablet_large")) {
							if (jsonImage.has("1000x250")) {
								pro.urlAvatarLarge = jsonImage
										.getString("1000x250");
							}
						} else if (typePhone.equals("tablet_normal")) {
							if (jsonImage.has("500x125")) {
								pro.urlAvatarLarge = jsonImage
										.getString("500x125");
							}
						} else if (typePhone.equals("phone_xlarge")) {
							if (jsonImage.has("640x160")) {
								pro.urlAvatarLarge = jsonImage
										.getString("640x160");
							}
						} else if (typePhone.equals("phone_normal")) {
							if (jsonImage.has("320x80")) {
								pro.urlAvatarLarge = jsonImage
										.getString("320x80");
							}
						}
					}
				}
				if (jsonProfile.has("profile_picture_square")) {
					Object objectSquare = jsonProfile
							.get("profile_picture_square");
					if (objectSquare instanceof JSONObject) {
						jsonImage = jsonProfile
								.getJSONObject("profile_picture_square");
						if (typePhone.equals("tablet_large")) {
							if (jsonImage.has("250x250")) {
								pro.urlHome = jsonImage.getString("250x250");
							}

							if (jsonImage.has("90x90")) {
								pro.urlAvatarSmall = jsonImage
										.getString("90x90");
							}
						} else if (typePhone.equals("tablet_normal")) {
							if (jsonImage.has("125x125")) {
								pro.urlHome = jsonImage.getString("125x125");
							}
							if (jsonImage.has("45x45")) {
								pro.urlAvatarSmall = jsonImage
										.getString("45x45");
							}
						} else if (typePhone.equals("phone_xlarge")) {
							if (jsonImage.has("440x440")) {
								pro.urlHome = jsonImage.getString("440x440");
							}
							if (jsonImage.has("80x80")) {
								pro.urlAvatarSmall = jsonImage
										.getString("80x80");
							}
						} else if (typePhone.equals("phone_normal")) {
							if (jsonImage.has("220x220")) {
								pro.urlHome = jsonImage.getString("220x220");
							}

							if (jsonImage.has("40x40")) {
								pro.urlAvatarSmall = jsonImage
										.getString("40x40");
							}
						}
					}
				}
				if (jsonObject.has("display_birthdate")) {
					pro.displayRealBirthday = jsonObject
							.getInt("display_birthdate");
				} else
					pro.displayRealBirthday = 0;

				if (jsonProfile.has("quizz_completion")) {
					pro.quizzCompletion = jsonProfile
							.getString("quizz_completion");
				}
				lstpro.add(pro);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstpro;
	}
	
	
	 public boolean addBrand(int paramInt, String paramString)
	  {
	    new ArrayList();
	    String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/add_brand.json", ConstantsHelper.Brands.addBrandToParams(paramString, paramInt, Locale.getDefault().getLanguage()));
	    try
	    {
	      boolean bool = new JSONObject(str).getBoolean("success");
	      return bool;
	    }
	    catch (JSONException localJSONException)
	    {
	      localJSONException.printStackTrace();
	    }
	    return false;
	  }
	  
	  public boolean checkUserNameExisted(String paramString)
	  {
	    try
	    {
	      new ArrayList();
	      boolean bool = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/username_used.json", ConstantsHelper.Profiles.checkExistedUserToParams(paramString))).getBoolean("username_used");
	      return bool;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return false;
	  }
	  
	  public boolean deleteBrand(int paramInt, String paramString)
	  {
	    new ArrayList();
	    String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/delete_brand.json", ConstantsHelper.Brands.addBrandToParams(paramString, paramInt, Locale.getDefault().getLanguage()));
	    try
	    {
	      boolean bool = new JSONObject(str).getBoolean("success");
	      return bool;
	    }
	    catch (JSONException localJSONException)
	    {
	      localJSONException.printStackTrace();
	    }
	    return false;
	  }
	  
//	  public String loadContentProfileUser(Context paramContext, int paramInt, String paramString)
//	  {
//	    Configuration localConfiguration = Configuration.getInstance(paramContext);
//	    try
//	    {
//	      new ArrayList();
//	      String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get.json", ConstantsHelper.Profiles.post_profileDiary_paramtoparam(String.valueOf(paramInt), 1, "full", paramString, Locale.getDefault().getLanguage(), localConfiguration.typePhone));
//	      return str;
//	    }
//	    catch (Exception localException)
//	    {
//	      localException.printStackTrace();
//	    }
//	    return null;
//	  }
	  
	  /* Error */
	 
	  public ArrayList<Profile> loadFollowing(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2)
	  {
	    ArrayList localArrayList = new ArrayList();
	    new ArrayList();
	    String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_followings.json", ConstantsHelper.Profiles.new_post_followers_following_toparams(paramString1, paramInt1, paramInt2, paramInt3, paramString2));
	    for (;;)
	    {
	      Profile localProfile;
	      JSONObject localJSONObject2;
	      try
	      {
	        JSONArray localJSONArray = new JSONArray(new JSONObject(str).getString("profiles"));
	        int i = 0;
	        if (i >= localJSONArray.length()) {
	          return localArrayList;
	        }
	        JSONObject localJSONObject1 = localJSONArray.getJSONObject(i);
	        localProfile = new Profile();
	        localProfile.profileID = localJSONObject1.getInt("iduser");
	        localProfile.username = localJSONObject1.getString("username");
	        localProfile.cityName = localJSONObject1.getString("country_name");
	        localProfile.followingIdsString = localJSONObject1.getString("nbfollowing");
	        localProfile.followIdsString = localJSONObject1.getString("nbfollower");
	        localProfile.nblike = localJSONObject1.getInt("nblike");
	        localProfile.percent = localJSONObject1.getString("match_rate");
	        localProfile.cityId = localJSONObject1.getString("idcountry");
	        localProfile.followed = localJSONObject1.getInt("followed");
	        if ((localJSONObject1.has("profile_picture_square")) && ((localJSONObject1.get("profile_picture_square") instanceof JSONObject)))
	        {
	          localJSONObject2 = localJSONObject1.getJSONObject("profile_picture_square");
	          if (localJSONObject2.has("250x250"))
	          {
	            localProfile.urlHome = localJSONObject2.getString("250x250");
	            if (!localJSONObject2.has("90x90")) {
//TODO:	              break label374;
	            }
	            localProfile.urlAvatarSmall = localJSONObject2.getString("90x90");
	          }
	        }
	        else
	        {
	          localArrayList.add(localProfile);
	          i++;
	          continue;
	        }
	        if (localJSONObject2.has("125x125"))
	        {
	          localProfile.urlHome = localJSONObject2.getString("125x125");
	          continue;
	        }
	        if (!localJSONObject2.has("440x440")) {
	        	//TODO:	          break label349;
	        }
	      }
	      catch (JSONException localJSONException)
	      {
	        localJSONException.printStackTrace();
	        return localArrayList;
	      }
	    //TODO:	      localProfile.urlHome = localJSONObject2.getString("440x440");
	      continue;
	    }
	  }
	  
	
	  
	  public ArrayList<Profile> onLoadSelectMemberFollow(int paramInt1, int paramInt2, String paramString1, String paramString2)
	  {
	    ArrayList localArrayList = new ArrayList();
	    new ArrayList();
	    String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/list.json", ConstantsHelper.Profiles.post_new_paramsfromparams("suggested", paramString1, Locale.getDefault().getLanguage(), paramInt1, paramInt2, "full", paramString2));
	    label569:
	    for (;;)
	    {
	      Profile localProfile;
	      JSONObject localJSONObject3;
	      JSONObject localJSONObject2;
	      try
	      {
	        JSONArray localJSONArray = new JSONArray(new JSONObject(str).getString("profiles"));
	        int i = 0;
	        if (i >= localJSONArray.length()) {
	          return localArrayList;
	        }
	        JSONObject localJSONObject1 = localJSONArray.getJSONObject(i);
	        localProfile = new Profile();
	        localProfile.profileID = localJSONObject1.getInt("iduser");
	        localProfile.username = localJSONObject1.getString("username");
	        localProfile.followIdsString = localJSONObject1.getString("nbfollower");
	        localProfile.followingIdsString = localJSONObject1.getString("nbfollowing");
	        localProfile.nblike = localJSONObject1.getInt("nblike");
	        localProfile.cityId = localJSONObject1.getString("idcountry");
	        localProfile.percent = localJSONObject1.getString("match_rate");
	        localProfile.cityName = localJSONObject1.getString("country_name");
	        localProfile.numberOfFollows = localJSONObject1.getInt("nbfollower");
	        localProfile.numberOfFollowings = localJSONObject1.getInt("nbfollowing");
	        localProfile.numberOfProducts = localJSONObject1.getInt("nblike");
	        localProfile.numberOfBrands = localJSONObject1.getInt("nbbrand");
	        if (localJSONObject1.has("nbphoto"))
	        {
	          localProfile.numberOfPhotos = localJSONObject1.getInt("nbphoto");
	          if ((localJSONObject1.has("profile_picture_rectangle")) && ((localJSONObject1.get("profile_picture_rectangle") instanceof JSONObject)))
	          {
	            localJSONObject3 = localJSONObject1.getJSONObject("profile_picture_rectangle");
	            if (!paramString2.equals("tablet_large")) {
	            	//TODO:	              break label458;
	            }
	            if (localJSONObject3.has("1000x250")) {
	              localProfile.urlAvatarLarge = localJSONObject3.getString("1000x250");
	            }
	          }
	          if ((localJSONObject1.has("profile_picture_square")) && ((localJSONObject1.get("profile_picture_square") instanceof JSONObject)))
	          {
	            localJSONObject2 = localJSONObject1.getJSONObject("profile_picture_square");
	            if (!paramString2.equals("tablet_large")) {
	              break label569;
	            }
	            if (localJSONObject2.has("250x250")) {
	              localProfile.urlHome = localJSONObject2.getString("250x250");
	            }
	            if (localJSONObject2.has("90x90")) {
	              localProfile.urlAvatarSmall = localJSONObject2.getString("90x90");
	            }
	          }
	          localArrayList.add(localProfile);
	          i++;
	        }
	        else
	        {
	          localProfile.numberOfPhotos = 0;
	          continue;
	        }
	        if (!paramString2.equals("tablet_normal")) {
	        	//TODO:	          break label495;
	        }
	      }
	      catch (JSONException localJSONException)
	      {
	        localJSONException.printStackTrace();
	        return localArrayList;
	      }
	    //TODO:	      label458:
//	      if (localJSONObject3.has("500x125"))
//	      {
//	        localProfile.urlAvatarLarge = localJSONObject3.getString("500x125");
//	        continue;
//	      }
	    }
		return localArrayList;
	  }
	  
	  public boolean updateAvatar(String paramString1, String paramString2, String paramString3, String paramString4)
	  {
	    try
	    {
	      new ArrayList();
	      boolean bool = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/update.json", ConstantsHelper.Profiles.updateAvatar(paramString4, Locale.getDefault().getLanguage(), paramString3, paramString1, paramString2))).getBoolean("success");
	      return bool;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return false;
	  }
	  
	  public String updateProfile(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt1, String paramString6, String paramString7, String paramString8, int paramInt2, int paramInt3, String paramString9, String paramString10, String paramString11)
	  {
	    try
	    {
//	      new ArrayList();
	      String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/update.json", ConstantsHelper.Profiles.updateProfileToParams(paramString11, Locale.getDefault().getLanguage(), paramString1, paramString2, paramString3, paramString4, paramString5, paramInt1, paramString6, paramString7, paramString8, paramInt2, paramInt3, paramString9, paramString10));
	      Log.w("resultat", ""+str);
	      return str;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return null;
	  }
	  
	  public boolean updateStatus(String paramString1, int paramInt, String paramString2)
	  {
	    try
	    {
	      new ArrayList();
	      boolean bool = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/update.json", ConstantsHelper.Profiles.updateStatus(paramString2, Locale.getDefault().getLanguage(), paramInt, paramString1))).getBoolean("success");
	      return bool;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return false;
	  }
	
}
