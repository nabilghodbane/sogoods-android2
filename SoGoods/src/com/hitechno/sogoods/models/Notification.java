package com.hitechno.sogoods.models;

public class Notification
{
  public String brand_name;
  public String date;
  public int followingAddCommentNUm;
  public int followingAddPictureNum;
  public int followingAddProductNum;
  public String idbrand;
  public int idcomment;
  public int idproduct;
  public int iduser;
  public String imgConcerned;
  public String imgProfile;
  public int nbcomments;
  public int newCommentOnMyProductNum;
  public int newFollowerNum;
  public String product_name;
  public String type;
  public String username;
@Override
public String toString() {
	return "Notification [brand_name=" + brand_name + ", date=" + date
			+ ", followingAddCommentNUm=" + followingAddCommentNUm
			+ ", followingAddPictureNum=" + followingAddPictureNum
			+ ", followingAddProductNum=" + followingAddProductNum
			+ ", idbrand=" + idbrand + ", idcomment=" + idcomment
			+ ", idproduct=" + idproduct + ", iduser=" + iduser
			+ ", imgConcerned=" + imgConcerned + ", imgProfile=" + imgProfile
			+ ", nbcomments=" + nbcomments + ", newCommentOnMyProductNum="
			+ newCommentOnMyProductNum + ", newFollowerNum=" + newFollowerNum
			+ ", product_name=" + product_name + ", type=" + type
			+ ", username=" + username + "]";
}
  
}

