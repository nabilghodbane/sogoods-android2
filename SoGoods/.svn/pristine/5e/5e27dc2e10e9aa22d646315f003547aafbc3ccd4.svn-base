package com.hitechno.sogoods;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.aviary.android.feather.FeatherActivity;
import com.aviary.android.feather.common.utils.StringUtils;
import com.aviary.android.feather.library.Constants;
import com.aviary.android.feather.library.utils.DecodeUtils;
import com.aviary.android.feather.library.utils.ImageSizes;
import com.mttam.toollibrary.tools.Tools;
import com.hitechno.sogoods.adapters.GGGalleryAdapter;
import com.hitechno.sogoods.adapters.GalleryAdapter;
import com.hitechno.sogoods.api.APIPhoto;
import com.hitechno.sogoods.api.Connectivity;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.GGImage;
import com.hitechno.sogoods.models.Photo;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class ListPhotoActivity
  extends FragmentActivity
{
  private static final int ACTION_AVIARY_EDITOR = 98;
  private String apiKey;
  private LinearLayout backButton;
  private Configuration config;
  private Context context;
  private EditText editSearchbrand;
  private GoogleImageSearchAsync googleImageSearchAsync;
  private GridView gridView;
  private int idProfile;
  private ImageView imageViewCut;
  Boolean loadingMore = Boolean.valueOf(true);
  private String mOutputFilePath;
  int offset = 1;
  private APIPhoto photoApi;
  private ProgressBar progressBar;
  private ArrayList<GGImage> searchImages;
  private RelativeLayout searchLayout;
  Boolean stopLoadingData = Boolean.valueOf(false);
  private boolean type;
  
  private File createFolders()
  {
    File localFile1;
    File localFile2;
    if (Build.VERSION.SDK_INT < 8)
    {
      localFile1 = Environment.getExternalStorageDirectory();
      if (localFile1 != null) {
      	localFile2 = Environment.getExternalStorageDirectory();
      }
      
    }
  //  label32:
    do
    {
//      return localFile2;
      localFile1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//      break;
      localFile2 = new File(localFile1, "aviary");
      if(localFile2!=null)
      	return localFile2;
    } while ((localFile2.exists()) || (localFile2.mkdirs()));
    return Environment.getExternalStorageDirectory();
  }
  
  private InputStream downloadUrl(String paramString)
  {
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      localHttpURLConnection.setReadTimeout(10000);
      localHttpURLConnection.setConnectTimeout(15000);
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.connect();
      InputStream localInputStream = localHttpURLConnection.getInputStream();
      return localInputStream;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }
  private void goToAriaryEditor(Uri paramUri)
  {
    if (!isExternalStorageAvilable())
    {
      Log.w("GalleryActivity", "External Storage not Available");
      return;
    }
    File localFile1 = createFolders();
    File localFile2 = null;
    if (localFile1 != null)
    {
      boolean bool = localFile1.exists();
      localFile2 = null;
      if (bool) {
        localFile2 = new File(localFile1, "aviary_" + System.currentTimeMillis() + ".png");
      }
    }
    if (localFile2 != null)
    {
    	this.mOutputFilePath = localFile2.getAbsolutePath();
      Intent localIntent = new Intent(this, FeatherActivity.class);
      localIntent.setData(paramUri);
      localIntent.putExtra(Constants.EXTRA_OUTPUT, Uri.parse("file://" + this.mOutputFilePath));
    
      localIntent.putExtra( Constants.EXTRA_OUTPUT_FORMAT, Bitmap.CompressFormat.JPEG.name() );
      localIntent.putExtra( Constants.EXTRA_OUTPUT_QUALITY, 90 );
  	final DisplayMetrics metrics = new DisplayMetrics();
  	getWindowManager().getDefaultDisplay().getMetrics( metrics );
  	int max_size = Math.max( metrics.widthPixels, metrics.heightPixels );
  	max_size = (int) ( (float) max_size / 1.2f );
  	localIntent.putExtra( Constants.EXTRA_MAX_IMAGE_SIZE, max_size );

  	localIntent.putExtra( Constants.EXTRA_IN_SAVE_ON_NO_CHANGES, true );
  	
  	localIntent.putExtra( Constants.EXTRA_IN_API_KEY_SECRET, "a919a54d4af91822" );
  	startActivityForResult( localIntent, 1 );  
    }
    Log.w("GalleryActivity", "Fail to create a file");
  }

  
  @SuppressLint("NewApi")
private void init()
  {
    this.backButton = ((LinearLayout)findViewById(R.id.gallery_back_layout));
    this.imageViewCut = ((ImageView)findViewById(R.id.gallery_add_button));
    this.editSearchbrand = ((EditText)findViewById(R.id.search_photo));
    this.searchLayout = ((RelativeLayout)findViewById(R.id.galerry_serach_layout));
    this.gridView = ((GridView)findViewById(R.id.gallery_gridview));
    this.progressBar = ((ProgressBar)findViewById(R.id.gallery_progressbar));
    Bundle localBundle = getIntent().getExtras();
    if ((localBundle != null) && (localBundle.containsKey("type")))
    {
      this.type = localBundle.getBoolean("type");
      if (!this.type) {
    	     this.searchLayout.setVisibility(0);
      }
 
    }
//    for (;;)
//    {
      this.editSearchbrand.setOnKeyListener(new View.OnKeyListener()
      {
        @SuppressLint("NewApi")
		public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if ((paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 84))
          {
            if (ListPhotoActivity.this.googleImageSearchAsync != null) {
              ListPhotoActivity.this.googleImageSearchAsync.cancel(true);
            }
            ListPhotoActivity.this.searchImages.clear();
            ListPhotoActivity.this.googleImageSearchAsync = new ListPhotoActivity.GoogleImageSearchAsync();
            ListPhotoActivity.GoogleImageSearchAsync localGoogleImageSearchAsync = ListPhotoActivity.this.googleImageSearchAsync;
            Executor localExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
            String[] arrayOfString = new String[2];
            arrayOfString[0] = ListPhotoActivity.this.editSearchbrand.getText().toString();
            arrayOfString[1] = ""+ListPhotoActivity.this.offset;
            localGoogleImageSearchAsync.executeOnExecutor(localExecutor, arrayOfString);
            return true;
          }
          return false;
        }
      });
      this.editSearchbrand.setOnEditorActionListener(new TextView.OnEditorActionListener()
      {
        public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if (paramAnonymousInt == 3)
          {
            Tools.hideKeyboard(ListPhotoActivity.this, ListPhotoActivity.this.editSearchbrand);
            if (ListPhotoActivity.this.googleImageSearchAsync != null) {
              ListPhotoActivity.this.googleImageSearchAsync.cancel(true);
            }
            ListPhotoActivity.this.searchImages.clear();
            ListPhotoActivity.this.googleImageSearchAsync = new ListPhotoActivity.GoogleImageSearchAsync();
            ListPhotoActivity.GoogleImageSearchAsync localGoogleImageSearchAsync = ListPhotoActivity.this.googleImageSearchAsync;
            Executor localExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
            String[] arrayOfString = new String[2];
            arrayOfString[0] = ListPhotoActivity.this.editSearchbrand.getText().toString();
            arrayOfString[1] = ""+ListPhotoActivity.this.offset;
            localGoogleImageSearchAsync.executeOnExecutor(localExecutor, arrayOfString);
            return true;
          }
          return false;
        }
      });
      this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (ListPhotoActivity.this.type)
          {
            GGImage localGGImage = (GGImage)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
            Debugs.show("d", "path detail", "path detail =" + localGGImage.link);
            Uri localUri2 = Uri.parse(localGGImage.link);
            ListPhotoActivity.this.goToAriaryEditor(localUri2);
            return;
          }
          Photo localPhoto = (Photo)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
          Debugs.show("d", "path detail", "path detail =" + localPhoto.url);
          Uri localUri1 = Uri.parse(localPhoto.url);
          ListPhotoActivity.this.goToAriaryEditor(localUri1);
        }
      });
      this.gridView.setOnScrollListener(new AbsListView.OnScrollListener()
      {
        public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          if ((paramAnonymousInt1 + paramAnonymousInt2 == paramAnonymousInt3) && (!ListPhotoActivity.this.loadingMore.booleanValue()) && (!ListPhotoActivity.this.stopLoadingData.booleanValue()) && (ListPhotoActivity.this.type))
          {
            ListPhotoActivity localListPhotoActivity = ListPhotoActivity.this;
            localListPhotoActivity.offset = (1 + localListPhotoActivity.offset);
            ListPhotoActivity.GoogleImageSearchMoreAsync localGoogleImageSearchMoreAsync = new ListPhotoActivity.GoogleImageSearchMoreAsync();
            Executor localExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
            String[] arrayOfString = new String[2];
            arrayOfString[0] = ListPhotoActivity.this.editSearchbrand.getText().toString();
            arrayOfString[1] = String.valueOf(ListPhotoActivity.this.offset);
            localGoogleImageSearchMoreAsync.executeOnExecutor(localExecutor, arrayOfString);
          }
        }
        
        public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt) {}
      });
//      return;
//      label193:
      this.searchLayout.setVisibility(8);
      loadPhotosAsync localloadPhotosAsync = new loadPhotosAsync();
      Executor localExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
      Integer[] arrayOfInteger = new Integer[1];
      arrayOfInteger[0] = Integer.valueOf(this.idProfile);
      localloadPhotosAsync.executeOnExecutor(localExecutor, arrayOfInteger);
//    }
  }
  
  private boolean isExternalStorageAvilable()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  private void loadAsync(Uri paramUri)
  {
    Log.i("loadAsync", "loadAsync: " + paramUri);
    Drawable localDrawable = AddProductActivity.productImageView.getDrawable();
    if ((localDrawable != null) && ((localDrawable instanceof BitmapDrawable)) && (((BitmapDrawable)AddProductActivity.productImageView.getDrawable()).getBitmap() != null)) {
      ((BitmapDrawable)AddProductActivity.productImageView.getDrawable()).getBitmap().recycle();
    }
    AddProductActivity.productImageView.setImageDrawable(null);
    new DownloadAsync().execute(new Uri[] { paramUri });
  }
  
  private InputStream retrieveStream(String paramString)
  {
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpGet localHttpGet = new HttpGet(paramString);
    try
    {
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpGet);
      int i = localHttpResponse.getStatusLine().getStatusCode();
      if (i != 200)
      {
        Log.w(getClass().getSimpleName(), "Error " + i + " for URL " + paramString);
        return null;
      }
      HttpEntity localHttpEntity = localHttpResponse.getEntity();
      Log.d("AAA", EntityUtils.toString(localHttpEntity, "UTF-8"));
      InputStream localInputStream = localHttpEntity.getContent();
      return localInputStream;
    }
    catch (IOException localIOException)
    {
      localHttpGet.abort();
      Log.w(getClass().getSimpleName(), "Error for URL " + paramString, localIOException);
    }
    return null;
  }
  
  private void setImageURI(Uri paramUri, Bitmap paramBitmap)
  {
    Log.d("image size: ", "image size: " + paramBitmap.getWidth() + "x" + paramBitmap.getHeight() + " ---" + paramUri.getPath());
    AddProductActivity.productImageView.setImageBitmap(paramBitmap);
    AddProductActivity.LINK_IMAGEPRODUCT = this.mOutputFilePath;
    onBackPressed();
  }
  
  private void updateMedia(String paramString)
  {
    MediaScannerConnection.scanFile(getApplicationContext(), new String[] { paramString }, null, null);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1) {}
//TODO:    
//    switch (paramInt1)
//    {
//    default: 
//      return;
//    }
//    Uri localUri = paramIntent.getData();
//    Bundle localBundle = paramIntent.getExtras();
//    if (localBundle != null) {
//      if (!localBundle.getBoolean("bitmap-changed")) {
//        break label89;
//      }
//    }
//    label89:
//    for (String str = "Aviary OK";; str = "Aviary out Failed")
//    {
//      Log.d("GalleryActivity", str);
//      updateMedia(this.mOutputFilePath);
//      loadAsync(localUri);
//      return;
//    }
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    finish();
    ConstantsHelper.animationPro(this);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(R.layout.activity_gallery);
    this.context = this;
    this.photoApi = new APIPhoto();
    this.searchImages = new ArrayList<GGImage>();
    this.config = Configuration.getInstance(this.context);
    this.idProfile = MySharePreference.getProfileID(this.context);
    this.apiKey = MySharePreference.getApiKey(this.context);
    init();
  }
  
  public void onclickCutButton(View paramView)
  {
    this.editSearchbrand.setText("");
    this.imageViewCut.setVisibility(8);
  }
  
  public void processBackButton(View paramView)
  {
    onBackPressed();
  }
  
  class DownloadAsync
    extends AsyncTask<Uri, Void, Bitmap>
  {
    private Uri mUri;
    
    DownloadAsync() {}
    
    protected Bitmap doInBackground(Uri... paramVarArgs)
    {
      this.mUri = paramVarArgs[0];
      ImageSizes localImageSizes = new ImageSizes();
      return DecodeUtils.decode(ListPhotoActivity.this.context, this.mUri, 200, 200, localImageSizes);
    }
    
    protected void onPostExecute(Bitmap paramBitmap)
    {
      super.onPostExecute(paramBitmap);
      if (paramBitmap != null)
      {
        ListPhotoActivity.this.setImageURI(this.mUri, paramBitmap);
        return;
      }
      Toast.makeText(ListPhotoActivity.this.context, "Failed to load image " + this.mUri, 0).show();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  private class GoogleImageSearchAsync
    extends AsyncTask<String, Void, String>
  {
    private GoogleImageSearchAsync() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      try
      {
        if (isCancelled()) {
          return null;
        }
        String str1 = "https://www.googleapis.com/customsearch/v1?fileType=png%20jpg%20gif&alt=json&searchType=image&imgSize=medium&q=" + paramVarArgs[0] + "&key=AIzaSyAIw6nFiPvZp9Fzug19_eIBAuGwFMZ3U60&cx=007626669963342932099:ipmii45iaw4&start=" + paramVarArgs[1];
        Debugs.show("d", "GooleSearchAPI", str1);
        String str2 = Connectivity.inputStreamToString(new URL(str1).openConnection().getInputStream());
        return str2;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
      if (paramString != null) {}
//TODO:
//      for (;;)
//      {
        try
        {
          JSONArray localJSONArray = new JSONObject(paramString).getJSONArray("items");
          ListPhotoActivity.this.searchImages = GGImage.initArrayFromJson(localJSONArray);
          GGGalleryAdapter localGGGalleryAdapter = new GGGalleryAdapter(ListPhotoActivity.this.context, R.id.gallery_gridview, ListPhotoActivity.this.searchImages);
          ListPhotoActivity.this.gridView.setAdapter(localGGGalleryAdapter);
          ListPhotoActivity.this.gridView.invalidate();
          ListPhotoActivity.this.progressBar.setVisibility(8);
          ListPhotoActivity.this.progressBar.setVisibility(8);
          ListPhotoActivity.this.loadingMore = Boolean.valueOf(false);
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
//          continue;
        }
        if (!isCancelled()) {
          ListPhotoActivity.this.gridView.setAdapter(null);
        }
//      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      ListPhotoActivity.this.progressBar.setVisibility(0);
    }
  }
  
  private class GoogleImageSearchMoreAsync
    extends AsyncTask<String, Void, String>
  {
    private GoogleImageSearchMoreAsync() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      try
      {
        ListPhotoActivity.this.loadingMore = Boolean.valueOf(true);
        String str1 = "https://www.googleapis.com/customsearch/v1?fileType=png%20jpg%20gif&alt=json&searchType=image&imgSize=medium&q=" + paramVarArgs[0] + "&key=AIzaSyAIw6nFiPvZp9Fzug19_eIBAuGwFMZ3U60&cx=007626669963342932099:ipmii45iaw4&start=" + paramVarArgs[1];
        Debugs.show("d", "GooleSearchAPI", str1);
        String str2 = Connectivity.inputStreamToString(new URL(str1).openConnection().getInputStream());
        return str2;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
      if (paramString != null) {}
      //TODO:
//      for (;;)
//      {
      Iterator<GGImage> localIterator;
      GGImage localGGImage;
        try
        {
          int i = ListPhotoActivity.this.gridView.getFirstVisiblePosition();
          localIterator = GGImage.initArrayFromJson(new JSONObject(paramString).getJSONArray("items")).iterator();
          if (localIterator.hasNext()) {
        	  localGGImage = (GGImage)localIterator.next();
              ListPhotoActivity.this.searchImages.add(localGGImage);
          }
          GGGalleryAdapter localGGGalleryAdapter = new GGGalleryAdapter(ListPhotoActivity.this.context, R.id.gallery_gridview, ListPhotoActivity.this.searchImages);
          ListPhotoActivity.this.gridView.setAdapter(localGGGalleryAdapter);
          ListPhotoActivity.this.gridView.invalidate();
          ListPhotoActivity.this.progressBar.setVisibility(8);
          ListPhotoActivity.this.gridView.setSelection(i + 1);
          ListPhotoActivity.this.loadingMore = Boolean.valueOf(false);
          ListPhotoActivity.this.progressBar.setVisibility(8);
          ListPhotoActivity.this.loadingMore = Boolean.valueOf(false);
        
        }
        catch (JSONException localJSONException)
        {
        
          localJSONException.printStackTrace();
 //         continue;
        }
     
//      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  private class loadPhotosAsync
    extends AsyncTask<Integer, Void, ArrayList<Photo>>
  {
    private loadPhotosAsync() {}
    
    protected ArrayList<Photo> doInBackground(Integer... paramVarArgs)
    {
      try
      {
//TODO:
        ArrayList<Photo> localArrayList = photoApi.loadPhotos(paramVarArgs[0].intValue(), ListPhotoActivity.this.apiKey, ListPhotoActivity.this.config.currentLanguage);
        return localArrayList;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(ArrayList<Photo> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      //TODO:
      if ((paramArrayList != null) && (paramArrayList.size() > 0))
      {
//        GalleryAdapter localGalleryAdapter = new GalleryAdapter(ListPhotoActivity.this.context, 2130903134, paramArrayList);
//        ListPhotoActivity.this.gridView.setAdapter(localGalleryAdapter);
        ListPhotoActivity.this.gridView.invalidate();
        ListPhotoActivity.this.progressBar.setVisibility(8);
      }
      else
      {
        ListPhotoActivity.this.progressBar.setVisibility(8);
        ListPhotoActivity.this.loadingMore = Boolean.valueOf(false);
        ListPhotoActivity.this.gridView.setAdapter(null);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      ListPhotoActivity.this.progressBar.setVisibility(0);
    }
  }
}

