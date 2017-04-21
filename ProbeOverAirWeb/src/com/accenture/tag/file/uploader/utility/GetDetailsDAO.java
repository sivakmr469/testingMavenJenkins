package com.accenture.tag.file.uploader.utility;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
public class GetDetailsDAO {
 
    public String getJsonData() throws  JSONException
    {
        String jsonData="";
 
            JSONObject empDetails;
            JSONObject jSonDataObj;
            JSONObject jSonDataFinalObj;
 
            JSONArray empDetailsArr;
            JSONArray jSonDataFinalArr;
            jSonDataFinalArr =new JSONArray(); 
 
         empDetails = new JSONObject();
         empDetails.put("Code", "1234");
         empDetails.put("Name", "Kumar");
         empDetails.put("Desig", "SO");
         empDetails.put("Pay", "Rs.22221/-");
 
             empDetailsArr =new JSONArray();
         empDetailsArr.put(empDetails);
 
         jSonDataObj=new JSONObject();
         jSonDataObj.put("JsonData", empDetailsArr);
 
         jSonDataFinalArr.put(jSonDataObj);
 
         empDetails = new JSONObject();
         empDetails.put("Code", "1235");
         empDetails.put("Name", "Wilson");
         empDetails.put("Desig", "AO");
         empDetails.put("Pay", "Rs.62222/-");
 
         empDetailsArr =new JSONArray();
         empDetailsArr.put(empDetails);
 
         jSonDataObj=new JSONObject();
         jSonDataObj.put("JsonData", empDetailsArr);
 
         jSonDataFinalArr.put(jSonDataObj);
 
         jSonDataFinalObj=new JSONObject();
 
         jSonDataFinalObj.put("JSonDataFinal", jSonDataFinalArr);
 
         jsonData=jSonDataFinalObj.toString();
 
        System.out.println("Final Json " + jsonData);
 
            return jsonData;
 
        }
 
    }