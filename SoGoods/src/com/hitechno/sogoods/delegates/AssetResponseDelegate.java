package com.hitechno.sogoods.delegates;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.hitechno.sogoods.helpers.ConstantsHelper;

/**
 * Saves an asset file (e.g., image) to the application's files directory.
 * 
 */
public class AssetResponseDelegate extends BaseResponseDelegate {

	/**
	 * The directory where the assets will be stored. 
	 */
	private String directory;
	
	/**
	 * The filename of an asset.
	 */
	private String filename;
	
	/**
	 * The <code>Uri</code> for a particular asset. This is used
	 * to update the database with the new location of the asset
	 * (i.e., it is no longer a remote resource, so it is referenced
	 * as an absolute file path).
	 */
	private Uri contentUri;

	private static final String ASSETS_DIRECTORY = "/assets";

	/**
	 * Default constructor.
	 */
	public AssetResponseDelegate() {
		super();
	}

	public String getFilename() {
		return this.filename;
	}

	private void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDirectory() {
		return this.directory;
	}

	/**
	 * Assigns the asset's directory name to the instance variable
	 * <code>directory</code>. If the directory does not exist, it is created.
	 * 
	 * @param directory
	 *            the name of the directory
	 */
	private void setDirectory(String directory) {
		this.directory = directory;

		File modelAssetsDirectory = new File(getAssetsLocation().concat(
				"/" + getDirectory()));
		if (!modelAssetsDirectory.exists()) {
			modelAssetsDirectory.mkdir();
		}
	}

	public Uri getContentUri() {
		return this.contentUri;
	}

	private void setContentUri(Uri uri) {
		this.contentUri = uri;
	}

	/**
	 * Concatenates the application's files directory with a <code>String</code>
	 * constant <code>ASSETS_DIRECTORY</code>. If the directory does not exist,
	 * it is created.
	 * 
	 * @return the absolute path to the assets directory
	 */
	public String getAssetsLocation() {
		String assetsLocation = getContext().getFilesDir().getAbsolutePath()
				.concat(ASSETS_DIRECTORY);

		File assetsDirectory = new File(assetsLocation);
		if (!assetsDirectory.exists()) {
			assetsDirectory.mkdir();
		}
		return assetsLocation;
	}

	/**
	 * Concatenates the application's assets directory with the directory for a
	 * model's assets and the name of the asset file.
	 * 
	 * @return the absolute path to the asset
	 */
	public String getAssetLocation() {
		String location = getAssetsLocation().concat(
				"/" + getDirectory() + "/" + getFilename());
		return location;
	}

	/**
	 * Sets the directory, filename, and {@link ContentUri} for the model. The
	 * model is referenced from the <code>url</code> parameter. For example, the
	 * following url
	 * <p>
	 * <code>
	 * 	"http://www.test.com/advertisements/advertisement_1.png"
	 * </code>
	 * <p>
	 * will be identified with the Advertisement model via "advertisements".
	 * This method will set the appropriate directory and
	 * <code>ContentUri</code>.
	 * 
	 * @param url
	 *            the url for the asset
	 */
	public void setupFilesystemForAssetsAtUrl(String url) {
		String filename = url.substring(url.lastIndexOf("/") + 1, url.length());
		this.setFilename(filename);

		while (iterator.hasNext()) {
			Entry<String, Integer> model = iterator.next();
			if (assetUrlMatchesModel(url, model.getKey())) {
				switch (model.getValue()) {
				case ConstantsHelper.ADVERTISEMENTS_VALUE:
					this.setDirectory(ConstantsHelper.Advertisements.ADVERTISEMENTS_DIRECTORY_NAME);
					this.setContentUri(ConstantsHelper.Advertisements.ADVERTISEMENT_CONTENT_URI);
					break;
				case ConstantsHelper.BRANDS_VALUE:
					this.setDirectory(ConstantsHelper.Brands.BRANDS_DIRECTORY_NAME);
					this.setContentUri(ConstantsHelper.Brands.BRAND_CONTENT_URI);
					break;
				case ConstantsHelper.CATEGORIES_VALUE:
					this.setDirectory(ConstantsHelper.Categories.CATEGORIES_DIRECTORY_NAME);
					this.setContentUri(ConstantsHelper.Categories.CATEGORY_CONTENT_URI);
					break;
				case ConstantsHelper.NEWS_VALUE:
					this.setDirectory(ConstantsHelper.News.NEWS_DIRECTORY_NAME);
					this.setContentUri(ConstantsHelper.News.NEW_CONTENT_URI);
					break;
				case ConstantsHelper.PHOTOS_VALUE:
					this.setDirectory(ConstantsHelper.Photos.PHOTOS_DIRECTORY_NAME);
					this.setContentUri(ConstantsHelper.Photos.PHOTO_CONTENT_URI);
					break;
				case ConstantsHelper.PRODUCTS_VALUE:
					this.setDirectory(ConstantsHelper.Products.PRODUCTS_DIRECTORY_NAME);
					this.setContentUri(ConstantsHelper.Products.PRODUCT_CONTENT_URI);
					break;
				case ConstantsHelper.PROFILES_VALUE:
					this.setDirectory(ConstantsHelper.Profiles.PROFILES_DIRECTORY_NAME);
					this.setContentUri(ConstantsHelper.Profiles.PROFILE_CONTENT_URI);
					break;
				case ConstantsHelper.QUESTIONS_VALUE:
					this.setDirectory(ConstantsHelper.Questions.QUESTIONS_DIRECTORY_NAME);
					this.setContentUri(ConstantsHelper.Questions.QUESTION_CONTENT_URI);
					break;
				default:
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.hitechno.sogoods.delegates.BaseResponseDelegate#handleResponse(java.lang.String, org.apache.http.HttpResponse)
	 */
	@Override
	public Boolean handleResponse(String url, HttpResponse response)
			throws IOException {
		Boolean didHandleResponse = cacheAssetUsingResponse(response, url);
		return didHandleResponse;
	}

	/**
	 * Writes an asset to the filesystem. Also updates the database entry
	 * that corresponds to this asset, so that it no longer contains a
	 * reference to a remote asset (i.e., beings with 'http://...') and
	 * instead references an absolute file path.
	 * 
	 * @param response
	 *            the response from an <code>AssetRequestTask</code>
	 * @param url
	 *            the url of the asset
	 * @return whether the asset was written to the filesystem
	 */
	public Boolean cacheAssetUsingResponse(HttpResponse response, String url) {
		Boolean didCacheData = false;
		InputStream inputStream = null;

		try {
			// inputStream =
			// getContext().getResources().getAssets().open("asset_image_placeholder.png");
			// // For testing
			inputStream = response.getEntity().getContent();

			FileOutputStream outputStream = new FileOutputStream(
					getAssetLocation());
			byte[] bytes = new byte[256];
			int byteCount = 0;
			do {
				byteCount = inputStream.read(bytes);
				if (byteCount >= 0)
					outputStream.write(bytes, 0, byteCount);
			} while (byteCount >= 0);

			inputStream.close();
			outputStream.close();

			didCacheData = true;
		} catch (IOException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
					+ e.getMessage());
		}

		if (didCacheData) {
			ContentValues values = new ContentValues();
			values.put(
					ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_ASSET_URL,
					getAssetLocation());
			int rowId = getContext().getContentResolver().update(
					getContentUri(),
					values,
					ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_ASSET_URL
							+ " LIKE ?", new String[] { '%' + url + '%' });

			if (rowId == 0) {
				didCacheData = false;
			}
		}
		return didCacheData;
	}
}
