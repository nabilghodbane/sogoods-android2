package com.hitechno.sogoods.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Brand {
	private static final String KEY_ID = "idbrand";
	private static final String KEY_SHOPS = "nbshop";
	private static final String KEY_FANS = "nbfan";
	private static final String KEY_NEWS = "nbnews";
	private static final String KEY_PRODUCTS = "nbproducts";
	private static final String KEY_SHOP_FILTERED = "nbshop_filtered";
	private static final String KEY_PRODUCT_FILTERED = "nbproducts_filtered";
	private int m_ID;
	private int m_Shops;
	private int m_Fans;
	private int m_News;
	private int m_Products;
	private String m_ShopFiltered;
	private String m_ProductsFiltered;
	
	public Brand(int id, int shops, int fans, int news, int products, String shopFiltered, String productsFiltered) {
		m_ID = id;
		m_Shops = shops;
		m_Fans = fans;
		m_News = news;
		m_Products = products;
		m_ShopFiltered = shopFiltered;
		m_ProductsFiltered = productsFiltered;
	}
	
	public Brand(JSONObject json) throws JSONException {
		m_ID = json.getInt(KEY_ID);
		m_Shops = json.getInt(KEY_SHOPS);
		m_Fans = json.getInt(KEY_FANS);
		m_News = json.getInt(KEY_NEWS);
		m_Products = json.getInt(KEY_PRODUCTS);
		m_ShopFiltered = json.getString(KEY_SHOP_FILTERED);
		m_ProductsFiltered = json.getString(KEY_PRODUCT_FILTERED);
	}
	
	public int getID() {
		return m_ID;
	}
	
	public int getShops() {
		return m_Shops;
	}
	
	public int getFans() {
		return m_Fans;
	}
	
	public int getNews() {
		return m_News;
	}
	
	public int getProducts() {
		return m_Products;
	}
	
	public String getShopFiltered() {
		return m_ShopFiltered;
	}
	
	public String getProductsFiltered() {
		return m_ProductsFiltered;
	}
}
