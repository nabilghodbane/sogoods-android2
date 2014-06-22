package com.hitechno.sogoods.interfaces;

/**
 * Interface that an {@link Activity} conforms to in order to
 * receive a category id when a user selects a category.
 *
 */
public interface ActivityCallbackInterface {
	/**
	 * Sets the categoryId on BrandsFragment when a user
	 * taps a category.
	 * 
	 * @param categoryId the id of the category selected
	 */
	public void showBrandsForCategoryId(Integer categoryId);
}
