package com.hitechno.sogoods.interfaces;

/**
 * Interface that an {@link ArrayAdapter} implements to treat
 * different models similarly when presented in a grid as an
 * icon and title.
 * 
 * @see com.hitechno.sogoods.adapters.CategoriesAdapter#setModels(java.util.ArrayList).
 *
 */
public interface GridItemModelInterface{
	public int getId();
	public String getTitle();
	public String getUrl();
}
