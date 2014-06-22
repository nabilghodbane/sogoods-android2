package com.hitechno.sogoods;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.hitechno.sogoods.models.Answer;
import java.util.ArrayList;

public class QuestionTextAdapter
  extends ArrayAdapter<Answer>
{
  private ArrayList<Answer> answers;
  private Context context;
  private int resource;
  
  public QuestionTextAdapter(Context paramContext, int paramInt, ArrayList<Answer> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.context = paramContext;
    this.resource = paramInt;
    this.answers = paramArrayList;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.resource, paramViewGroup, false);
    }
    TextView localTextView = (TextView)paramView.findViewById(R.id.item_quizz_text);
    Answer localAnswer = (Answer)this.answers.get(paramInt);
    localTextView.setText(localAnswer.answer);
//TODO:    if (localAnswer.choosed)
//    {
//      localTextView.setBackgroundColor(this.context.getResources().getColor(2131099783));
//      return paramView;
//    }
//    localTextView.setBackgroundResource(2130837724);
    return paramView;
  }
}

