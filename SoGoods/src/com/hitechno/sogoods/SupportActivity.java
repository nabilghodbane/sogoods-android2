package com.hitechno.sogoods;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.hitechno.sogoods.views.FuturaTextView;

public class SupportActivity
  extends FragmentActivity
{
  private LinearLayout backLayout;
  private FuturaTextView confiTag;
  private FuturaTextView faqTag;
  private FuturaTextView termTag;
  private WebView webview;
  
  private void clearBackGround()
  {
    this.termTag.setBackgroundColor(0);
    this.confiTag.setBackgroundColor(0);
    this.faqTag.setBackgroundColor(0);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(R.layout.activity_support);
    this.termTag = ((FuturaTextView)findViewById(R.id.support_terms_tag));
    this.confiTag = ((FuturaTextView)findViewById(R.id.support_confi_tag));
    this.faqTag = ((FuturaTextView)findViewById(R.id.support_faq_tag));
    this.backLayout = ((LinearLayout)findViewById(R.id.support_back_layout));
    this.webview = ((WebView)findViewById(R.id.support_webview));
    this.webview.loadUrl("file:///android_asset/support/Terms & Conditions.htm");
    this.termTag.setPadding(0, 15, 0, 15);
    this.confiTag.setPadding(0, 15, 0, 15);
    this.faqTag.setPadding(0, 15, 0, 15);
    this.termTag.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SupportActivity.this.clearBackGround();
        SupportActivity.this.termTag.setBackgroundResource(R.drawable.background_white_with_line_gray);
        SupportActivity.this.termTag.setPadding(0, 15, 0, 15);
        SupportActivity.this.webview.loadUrl("file:///android_asset/support/Terms & Conditions.htm");
      }
    });
    this.confiTag.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SupportActivity.this.clearBackGround();
        SupportActivity.this.confiTag.setBackgroundResource(R.drawable.background_white_with_line_gray);
        SupportActivity.this.confiTag.setPadding(0, 15, 0, 15);
        SupportActivity.this.webview.loadUrl("file:///android_asset/support/Confidentiality.htm");
      }
    });
    this.faqTag.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SupportActivity.this.clearBackGround();
        SupportActivity.this.faqTag.setBackgroundResource(R.drawable.background_white_with_line_gray);
        SupportActivity.this.faqTag.setPadding(0, 15, 0, 15);
        SupportActivity.this.webview.loadUrl("file:///android_asset/support/Support - FAQ.htm");
      }
    });
    this.backLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SupportActivity.this.onBackPressed();
        SupportActivity.this.finish();
      }
    });
  }
}

