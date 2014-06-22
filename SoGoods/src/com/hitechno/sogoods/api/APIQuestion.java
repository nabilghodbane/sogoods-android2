package com.hitechno.sogoods.api;

import com.mttam.toollibrary.tools.Tools;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.constant.ConstantsHelper.Questions;
import com.hitechno.sogoods.models.Answer;
import com.hitechno.sogoods.models.Question;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIQuestion
{
  public ArrayList<Question> getAllQuestions(String paramString1, String paramString2)
  {
    ArrayList<Question> localArrayList1 = new ArrayList<Question>();
    Question localQuestion;
    String str2;
    ArrayList<Answer> localArrayList2;
    int j;
    JSONObject localJSONObject3;
	try
    {
   
      String str1 = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/quizz/get_for_user.json", ConstantsHelper.Questions.getQuestionUserToParam(paramString1, paramString2))).getString("quizz");
      JSONObject localJSONObject1 = new JSONObject(str1);
      JSONArray localJSONArray1 = new JSONArray(localJSONObject1.getString("questions"));
      int i = 0;
      if (i >= localJSONArray1.length()) {
        return localArrayList1;
      }
      JSONObject localJSONObject2 = localJSONArray1.getJSONObject(i);
      localQuestion = new Question();
      localQuestion.quesID = localJSONObject2.getInt("id");
      localQuestion.rank = localJSONObject2.getInt("rank");
      localQuestion.question = localJSONObject2.getString("question");
      str2 = localJSONObject2.getString("current_user_answer");
      if (!Tools.isEmpty(str2)) {}
      JSONArray localJSONArray2;
      for (localQuestion.currentUserAnswer = Integer.parseInt(str2);; localQuestion.currentUserAnswer = -1)
      {
        localJSONArray2 = new JSONArray(localJSONObject2.getString("answers"));
        localArrayList2 = new ArrayList<Answer>();
        j = 0;
        if (j < localJSONArray2.length()) {
//TODO:          break label239;
        }
        localQuestion.lstAnswers = localArrayList2;
        localArrayList1.add(localQuestion);
        i++;
        break;
      }
      localJSONObject3 = localJSONArray2.getJSONObject(j);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return localArrayList1;
    }
	//TODO:     label239:

    Answer localAnswer = new Answer();
    try {
		localAnswer.answerId = Integer.parseInt(localJSONObject3.getString("id"));
		
	    localAnswer.answer = localJSONObject3.getString("answer");
	    localAnswer.rank = localJSONObject3.getInt("rank");
	    if (localJSONObject3.has("image"))
	    {
	      String str3 = localJSONObject3.getString("image");
	      if (Tools.isEmpty(str3)) {
//TODO:	        break label429;
	      }
	      JSONObject localJSONObject4 = new JSONObject(str3);
	      if (localJSONObject4.has("160x160")) {
	        localAnswer.imageUrl = localJSONObject4.getString("160x160");
	      }
	      if (localJSONObject4.has("320x320")) {
	        localAnswer.imageUrl = localJSONObject4.getString("320x320");
	      }
	    }
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    if (!Tools.isEmpty(str2))
    {
      int k = Integer.parseInt(str2);
      if (localAnswer.answerId == k) {
        localAnswer.choosed = true;
      }
    }

//TODO:    label429:
    for (localQuestion.isImageQus = true;; localQuestion.isImageQus = false)
    {
      localArrayList2.add(localAnswer);
      j++;
      break;
    }
	return localArrayList1;
  }
  
  public boolean setAnswerForUser(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/quizz/set_answers_for_user.json", ConstantsHelper.Questions.setAnswerForUserToParams(paramString2, paramString3, paramString1));
      try
      {
        boolean bool = new JSONObject(str).getBoolean("success");
        return bool;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return false;
      }
     
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
	return false;
  }
}

