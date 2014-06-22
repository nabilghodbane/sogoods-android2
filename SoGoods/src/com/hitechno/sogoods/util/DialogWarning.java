package com.hitechno.sogoods.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.mttam.toollibrary.tools.Tools;

public class DialogWarning
{
  public void show(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903157, null);
    final PopupWindow localPopupWindow = new PopupWindow(localView, -2, -2, true);
    localPopupWindow.setAnimationStyle(16973826);
    localPopupWindow.setOutsideTouchable(true);
    localPopupWindow.setFocusable(true);
    localPopupWindow.showAtLocation(localView, 17, 0, 0);
    TextView localTextView1 = (TextView)localView.findViewById(2131231199);
    Button localButton = (Button)localView.findViewById(2131231200);
    TextView localTextView2 = (TextView)localView.findViewById(2131231201);
    if (paramBoolean) {
      localButton.setBackgroundResource(2130837669);
    }
    for (;;)
    {
      localTextView1.setText(paramString1);
      if (!Tools.isEmpty(paramString2))
      {
        localTextView2.setVisibility(0);
        localTextView2.setText(paramString2);
      }
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localPopupWindow.dismiss();
        }
      });
      return;
    }
  }
}
