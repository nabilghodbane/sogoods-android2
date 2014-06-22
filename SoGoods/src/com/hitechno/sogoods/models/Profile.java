package com.hitechno.sogoods.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.hitechno.sogoods.constant.ConstantsHelper;

public class Profile implements Parcelable {

	public int id;
	public int trueId;
	public String updatedAt;

	public String activity;
	public ArrayList<Integer> brandIds;
	public String brandIdsString;
	public String cityId;
	public String cityName;
	public String country_name;
	public String createdAt;
	public String dateOfBirth;
	public int displayRealBirthday;
	public int displayRealName;
	public String email;
	public String firstName;
	public ArrayList<Integer> followIds;
	public String followIdsString;
	public int followed;
	public ArrayList<Integer> followingIds;
	public String followingIdsString;
	public String gender;
	public int idusersProfession;
	public String lastName;
	public int nblike;
	public int numberOfBrands;
	public int numberOfFollowings;
	public int numberOfFollows;
	public int numberOfPhotos;
	public int numberOfProducts;
	public String password;
	public String percent;
	public String phone;
	public ArrayList<Integer> photoIds;
	public String photoIdsString;
	public ArrayList<Integer> productIds;
	public String productIdsString;
	public int profileID;
	public String quiz;
	public String quizzCompletion;
	public int revision;
	public String status;
	public String type;
	public String url;
	public String urlAvatarLarge;
	public String urlAvatarSmall;
	public String urlHome;
	public String urlLarge;
	public int userCreated;
	public int userUpdated;
	public String username;
	public String description;
	public String brands_revision;
	public int following_revision;
	public int followers_revision;
	public int idcountry;
	public String date_created;
	public String date_updated;
	public String brands;
	public String products;
	public String followings;

	public static Profile initFromJson(JSONObject jsonProfile, String typePhone) {
		Profile pro = new Profile();
		try {
			pro.profileID = jsonProfile.getInt("iduser");
			if (jsonProfile.has("gender"))
				pro.gender = jsonProfile.getString("gender");
			if (jsonProfile.has("description"))
				pro.description = jsonProfile.getString("description");
			if (jsonProfile.has("idusers_profession"))
				pro.idusersProfession = jsonProfile
						.getInt("idusers_profession");
			if (jsonProfile.has("brands_revision"))
				pro.brands_revision = jsonProfile.getString("brands_revision");
			if (jsonProfile.has("revision"))
				pro.revision = Integer.parseInt(jsonProfile
						.getString("revision"));
			if (jsonProfile.has("following_revision"))
				pro.following_revision = Integer.parseInt(jsonProfile
						.getString("following_revision"));
			if (jsonProfile.has("followers_revision"))
				pro.followers_revision = Integer.parseInt(jsonProfile
						.getString("followers_revision"));
			pro.username = jsonProfile.getString("username");
			if (jsonProfile.has("firstname"))
				pro.firstName = jsonProfile.getString("firstname");
			if (jsonProfile.has("lastname"))
				pro.lastName = jsonProfile.getString("lastname");
			if (jsonProfile.has("display_real_name"))
				pro.displayRealName = jsonProfile.getInt("display_real_name");
			if (jsonProfile.has("idcountry"))
				pro.idcountry = jsonProfile.getInt("idcountry");
			pro.country_name = jsonProfile.getString("country_name");
			pro.cityName = jsonProfile.getString("city_name");
			if (jsonProfile.has("date_created"))
				pro.date_created = jsonProfile.getString("date_created");
			if (jsonProfile.has("birthdate"))
				pro.dateOfBirth = jsonProfile.getString("birthdate");
			if (jsonProfile.has("display_birthdate"))
				pro.displayRealBirthday = jsonProfile
						.getInt("display_birthdate");
			pro.email = jsonProfile.getString("email");
			if (jsonProfile.has("date_updated"))
				pro.date_updated = jsonProfile.getString("date_updated");
			pro.numberOfBrands = jsonProfile.getInt("nbbrand");
			if (jsonProfile.has("brands"))
				pro.brands = jsonProfile.getString("brands");
			pro.numberOfProducts = jsonProfile.getInt("nblike");
			if (jsonProfile.has("products"))
				pro.products = jsonProfile.getString("products");
			pro.numberOfFollowings = jsonProfile.getInt("nbfollowing");
			if (jsonProfile.has("followings"))
				pro.followings = jsonProfile.getString("followings");
			pro.numberOfFollows = jsonProfile.getInt("nbfollower");
			pro.numberOfPhotos = jsonProfile.getInt("nbphoto");
			if (jsonProfile.has("quizz_completion"))
				pro.quizzCompletion = jsonProfile.getString("quizz_completion");

			if (jsonProfile.has("activity"))
				pro.activity = jsonProfile.getString("activity");

			JSONObject jsonImage;
			if (jsonProfile.has("profile_picture_rectangle")) {
				Object objectRec = jsonProfile.get("profile_picture_rectangle");
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
							pro.urlAvatarLarge = jsonImage.getString("500x125");
						}
					} else if (typePhone.equals("phone_xlarge")) {
						if (jsonImage.has("640x160")) {
							pro.urlAvatarLarge = jsonImage.getString("640x160");
						}
					} else if (typePhone.equals("phone_normal")) {
						if (jsonImage.has("320x80")) {
							pro.urlAvatarLarge = jsonImage.getString("320x80");
						}
					}
				}
			}

			if (jsonProfile.has("profile_picture_square")) {
				Object objectSquare = jsonProfile.get("profile_picture_square");
				if (objectSquare instanceof JSONObject) {
					jsonImage = jsonProfile
							.getJSONObject("profile_picture_square");
					if (typePhone.equals("tablet_large")) {
						if (jsonImage.has("440x440")) {
							pro.urlHome = jsonImage.getString("440x440");
						}
						if (jsonImage.has("90x90")) {
							pro.urlAvatarSmall = jsonImage.getString("90x90");
						}
					} else if (typePhone.equals("tablet_normal")) {
						if (jsonImage.has("250x250")) {
							pro.urlHome = jsonImage.getString("250x250");
						}
						if (jsonImage.has("80x80")) {
							pro.urlAvatarSmall = jsonImage.getString("80x80");
						}
					} else if (typePhone.equals("phone_xlarge")) {
						if (jsonImage.has("220x220")) {
							pro.urlHome = jsonImage.getString("220x220");
						}
						if (jsonImage.has("45x45")) {
							pro.urlAvatarSmall = jsonImage.getString("45x45");
						}
					} else if (typePhone.equals("phone_normal")) {
						if (jsonImage.has("125x125")) {
							pro.urlHome = jsonImage.getString("125x125");
						}
						if (jsonImage.has("40x40")) {
							pro.urlAvatarSmall = jsonImage.getString("40x40");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pro;
	}

	public static Profile initFromJsonSearch(JSONObject object, String typePhone) {
		Profile pro = new Profile();
		try {
			pro.profileID = object.getInt("iduser");
			object.getString("match_rate");
			object.getString("followed");
			pro.country_name = object.getString("country_name");
			object.getString("idcountry");
			pro.username = object.getString("username");
			pro.dateOfBirth = object.getString("birthdate");
			pro.numberOfFollows = object.getInt("nbfollower");
			pro.numberOfFollowings = object.getInt("nbfollowing");
			pro.numberOfProducts = object.getInt("nblike");
			pro.numberOfBrands = object.getInt("nbbrand");
			pro.numberOfPhotos = object.getInt("nbphoto");

			JSONObject img;
			if (object.has("profile_picture_rectangle")) {
				Object object2 = object.get("profile_picture_rectangle");
				if (object2 instanceof JSONObject) {
					img = object.getJSONObject("profile_picture_rectangle");
					if (typePhone.equals("tablet_large")) {
						if (img.has("1000x250")) {
							pro.urlAvatarLarge = img.getString("1000x250");
						}
					} else if (typePhone.equals("tablet_normal")) {
						if (img.has("500x125")) {
							pro.urlAvatarLarge = img.getString("500x125");
						}
					} else if (typePhone.equals("phone_xlarge")) {
						if (img.has("640x160")) {
							pro.urlAvatarLarge = img.getString("640x160");
						}
					} else if (typePhone.equals("phone_normal")) {
						if (img.has("320x80")) {
							pro.urlAvatarLarge = img.getString("320x80");
						}
					}
				}
			}

			if (object.has("profile_picture_square")) {
				Object object2 = object.get("profile_picture_square");
				if (object2 instanceof JSONObject) {
					img = object.getJSONObject("profile_picture_square");
					if (typePhone.equals("tablet_large")) {
						if (img.has("250x250")) {
							pro.urlHome = img.getString("250x250");
						}
						if (img.has("90x90")) {
							pro.urlAvatarSmall = img.getString("90x90");
						}
					} else if (typePhone.equals("tablet_normal")) {
						if (img.has("125x125")) {
							pro.urlHome = img.getString("125x125");
						}
						if (img.has("45x45")) {
							pro.urlAvatarSmall = img.getString("45x45");
						}
					} else if (typePhone.equals("phone_xlarge")) {
						if (img.has("440x440")) {
							pro.urlHome = img.getString("440x440");
						}
						if (img.has("80x80")) {
							pro.urlAvatarSmall = img.getString("80x80");
						}
					} else if (typePhone.equals("phone_normal")) {
						if (img.has("220x220")) {
							pro.urlHome = img.getString("220x220");
						}
						if (img.has("40x40")) {
							pro.urlAvatarSmall = img.getString("40x40");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pro;
	}

	public String getActivity() {
		return activity;
	}

	public String getCityName() {
		return cityName;
	}

	public ContentValues getContentValues() {
		ContentValues localContentValues = new ContentValues();
		followIdsString = stringFromList(followIds);
		followingIdsString = stringFromList(followingIds);
		brandIdsString = stringFromList(brandIds);
		productIdsString = stringFromList(productIds);
		photoIdsString = stringFromList(photoIds);
		localContentValues.put("username", username);
		localContentValues.put("date_of_birth", dateOfBirth);
		localContentValues.put("url", url);
		localContentValues.put("type", type);
		localContentValues.put("number_of_brands",
				Integer.valueOf(numberOfBrands));
		localContentValues.put("brand_ids", brandIdsString);
		localContentValues.put("number_of_products",
				Integer.valueOf(numberOfProducts));
		localContentValues.put("product_ids", productIdsString);
		localContentValues.put("number_of_photos",
				Integer.valueOf(numberOfPhotos));
		localContentValues.put("photo_ids", photoIdsString);
		localContentValues.put("number_of_follows",
				Integer.valueOf(numberOfFollows));
		localContentValues.put("follow_ids", followIdsString);
		localContentValues.put("number_of_followings",
				Integer.valueOf(numberOfFollowings));
		localContentValues.put("following_ids", followingIdsString);
		return localContentValues;
	}

	public ContentValues getContentValuesForSafeUpdate() {
		ContentValues values = new ContentValues();
		followIdsString = stringFromList(followIds);
		followingIdsString = stringFromList(followingIds);
		brandIdsString = stringFromList(brandIds);
		productIdsString = stringFromList(productIds);
		photoIdsString = stringFromList(photoIds);
		values.put(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID, id);
		values.put(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT,
				updatedAt);
		values.put("true_id", Integer.valueOf(id));
		values.put("type", type);
		values.put("number_of_brands", Integer.valueOf(numberOfBrands));
		values.put("brand_ids", brandIdsString);
		values.put("number_of_products", Integer.valueOf(numberOfProducts));
		values.put("product_ids", productIdsString);
		values.put("number_of_photos", Integer.valueOf(numberOfPhotos));
		values.put("photo_ids", photoIdsString);
		values.put("number_of_follows", Integer.valueOf(numberOfFollows));
		values.put("follow_ids", followIdsString);
		values.put("number_of_followings", Integer.valueOf(numberOfFollowings));
		values.put("following_ids", followingIdsString);
		values.put("updated_at", updatedAt);
		return values;
	}

	public Boolean isFollowedBy(int profileId) {
		Boolean isFollowedByProfile = false;
		String[] followingIds = followingIdsString.substring(0,
				followingIdsString.length() - 1).split(",");
		for (int i = 0; i < followingIds.length; i++) {
			if (followingIds[i] != ""
					&& profileId == Integer.parseInt(followingIds[i]))
				isFollowedByProfile = true;
		}

		return isFollowedByProfile;
	}

	public String stringFromList(List<Integer> ids) {
		String idsAsString = "(";
		for (int i = 0; i < ids.size(); i++) {
			Integer idAtIndex = ids.get(i);
			idsAsString = idsAsString.concat(Integer.toString(idAtIndex));
			if (i != ids.size() - 1)
				idsAsString = idsAsString
						.concat(ConstantsHelper.SEPARATOR_FOR_IDS_AS_STRING);
		}
		idsAsString = idsAsString.concat(")");
		return idsAsString;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", trueId=" + trueId + ", updatedAt="
				+ updatedAt + ", activity=" + activity + ", brandIds="
				+ brandIds + ", brandIdsString=" + brandIdsString + ", cityId="
				+ cityId + ", cityName=" + cityName + ", country_name="
				+ country_name + ", createdAt=" + createdAt + ", dateOfBirth="
				+ dateOfBirth + ", displayRealBirthday=" + displayRealBirthday
				+ ", displayRealName=" + displayRealName + ", email=" + email
				+ ", firstName=" + firstName + ", followIds=" + followIds
				+ ", followIdsString=" + followIdsString + ", followed="
				+ followed + ", followingIds=" + followingIds
				+ ", followingIdsString=" + followingIdsString + ", gender="
				+ gender + ", idusersProfession=" + idusersProfession
				+ ", lastName=" + lastName + ", nblike=" + nblike
				+ ", numberOfBrands=" + numberOfBrands
				+ ", numberOfFollowings=" + numberOfFollowings
				+ ", numberOfFollows=" + numberOfFollows + ", numberOfPhotos="
				+ numberOfPhotos + ", numberOfProducts=" + numberOfProducts
				+ ", password=" + password + ", percent=" + percent
				+ ", phone=" + phone + ", photoIds=" + photoIds
				+ ", photoIdsString=" + photoIdsString + ", productIds="
				+ productIds + ", productIdsString=" + productIdsString
				+ ", profileID=" + profileID + ", quiz=" + quiz
				+ ", quizzCompletion=" + quizzCompletion + ", revision="
				+ revision + ", status=" + status + ", type=" + type + ", url="
				+ url + ", urlAvatarLarge=" + urlAvatarLarge
				+ ", urlAvatarSmall=" + urlAvatarSmall + ", urlHome=" + urlHome
				+ ", urlLarge=" + urlLarge + ", userCreated=" + userCreated
				+ ", userUpdated=" + userUpdated + ", username=" + username
				+ ", description=" + description + ", brands_revision="
				+ brands_revision + ", following_revision="
				+ following_revision + ", followers_revision="
				+ followers_revision + ", idcountry=" + idcountry
				+ ", date_created=" + date_created + ", date_updated="
				+ date_updated + ", brands=" + brands + ", products="
				+ products + ", followings=" + followings + "]";
	}

}
