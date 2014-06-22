package com.hitechno.sogoods.models;

import java.util.ArrayList;
import java.util.List;

public class GeneralCateBrand {
	public List<Brand> lstBrand;
	public List<Category> lstCategory;
	
	public Brand brand;
	public Category category;
	
	public GeneralCateBrand() {
		lstBrand = new ArrayList<Brand>();
		lstCategory = new ArrayList<Category>();
		brand = new Brand();
		category = new Category();
	}

	public List<Brand> getLstBrand() {
		return lstBrand;
	}

	public void setLstBrand(List<Brand> lstBrand) {
		this.lstBrand = lstBrand;
	}

	public List<Category> getLstCategory() {
		return lstCategory;
	}

	public void setLstCategory(List<Category> lstCategory) {
		this.lstCategory = lstCategory;
	}
	
	
}
