package com.hitechno.sogoods.delegates;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.interfaces.ResponseDelegateInterface;

/**
 * Provides methods to access {@link Gson}, {@link Context}, and to determine if
 * a <code>url</code> contains a reference to a model class.
 */
public class BaseResponseDelegate implements ResponseDelegateInterface {

	private Gson gson;
	private Context context;

	protected Map<String, Integer> models = ConstantsHelper.MODELS;
	protected Iterator<Entry<String, Integer>> iterator = models.entrySet()
			.iterator();

	/**
	 * Default constructor. Initializes a new instance of {@link Gson}.
	 */
	public BaseResponseDelegate() {
		this.setGson(new Gson());
	}

	protected Gson getGson() {
		return this.gson;
	}

	protected void setGson(Gson gson) {
		this.gson = gson;
	}

	protected Context getContext() {
		return this.context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hitechno.sogoods.interfaces.ResponseDelegateInterface#handleResponse(java.lang.String, org.apache.http.HttpResponse)
	 */
	@Override
	public Boolean handleResponse(String url, HttpResponse response)
			throws IOException {
		return null;
	}

	/**
	 * Performs a regular expression comparison between two <code>String</code>s
	 * to determine if one contains a reference to a model class. The regex will
	 * match the first resource listed after the domain. For example, the
	 * following <code>url</code>:
	 * <p>
	 * <code>
	 * 	"http://www.test.com/advertisements.json"
	 * </code>
	 * <p>
	 * will match with the <code>model</code> <code>"advertisements"</code> and
	 * identify the <code>url</code> with the <code>Advertisement</code> model.
	 * 
	 * Similarly, the following <code>url</code>:
	 * <p>
	 * <code>
	 * "http://www.test.com/products/1/comments.json"
	 * </code>
	 * <p>
	 * will match with the <code>model</code> <code>"comments"</code>, but not
	 * <code>"products"</code>.
	 * 
	 * @param url
	 *            the url for a <code>HttpRequestTask</code> or
	 *            <code>AssetRequestTask</code>
	 * @param model
	 *            the pluralized name of a model
	 * @return whether the <code>url</code> matches the <code>model</code>
	 */
	public Boolean urlMatchesModel(String url, String model) {
		Boolean didMatchModel = false;
		didMatchModel = url.matches("^.*(" + model + ")\\.json.*|^.*(" + model
				+ ")/[0-9]*\\.json.*");
		return true;
	}

	/**
	 * Performs a regular expression comparison between two <code>String</code>s
	 * to determine if one contains a reference to a model class. The regex will
	 * match the model reference <b>anywhere</b> in the <code>String</code>, so
	 * the reference must be unique.
	 * 
	 * @param url the url for an asset
	 * @param model the pluralized name of a model
	 * @return whether the <code>url</code> matches the <code>model</code>
	 */
	public Boolean assetUrlMatchesModel(String url, String model) {
		Boolean didMatchModel = false;
		didMatchModel = url.matches("^.*(" + model + ").*");
		return didMatchModel;
	}
}
