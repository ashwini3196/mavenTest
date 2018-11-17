package utility;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class jsonTest {
    public static void main(String[] args){
        JsonObject to=new JsonObject();
        JsonArray finalar= new JsonArray();
        JsonArray jarr= new JsonArray();
        jarr.add("Messaging");
        JsonArray jarr1= new JsonArray();
        jarr1.add("Messaging");
        jarr1.add("Banking");
        System.out.println(jarr);
        System.out.println(jarr1);
        JsonObject currentRecord=new JsonObject();

        JsonObject updatedRecord=new JsonObject();
        currentRecord.add("services_impacted",jarr);
        currentRecord.add("severity",new Gson().fromJson("major",JsonElement.class));
        updatedRecord.add("services_impacted",jarr1);
        updatedRecord.add("severity",new Gson().fromJson("minor",JsonElement.class));

    finalar=computeJsonDiff(currentRecord,updatedRecord);
       /* JsonObject jobj=new JsonObject();
        jobj.add("propName",new Gson().fromJson("ServicesImpacted",JsonElement.class));
        jobj.add("oldValue",jarr);
        jobj.add("newValue",jarr1);
       /* jobj.addProperty("propName","services_impacted");
        jobj.addProperty("oldValue",jarr);
        jobj.addProperty("newValue",jarr1);*/
        /*JsonObject jobj1=new JsonObject();
        jobj1.add("propName",new Gson().fromJson("severity",JsonElement.class));
        jobj1.add("oldValue",new Gson().fromJson("severity",JsonElement.class));
        jobj1.add("newValue",new Gson().fromJson("severity",JsonElement.class));
        finalar.add(jobj);
        finalar.add(jobj1);*/
       JsonObject to0=updateAlarm(to,finalar);
        //to.add("alarm_update",finalar);
        System.out.println(to0.toString());

    }
    public static JsonObject updateAlarm(JsonObject jsonObject, JsonArray updateAlarmValue)
    {
        String alarmUpdate="alarm_update";
        if(jsonObject.has(alarmUpdate))
            jsonObject.remove(alarmUpdate);
        jsonObject.add(alarmUpdate, updateAlarmValue);
              return jsonObject;
    }

    public static JsonArray computeJsonDiff(JsonObject currentRecord, JsonObject updatedRecord){
        JsonArray jsonArray = new JsonArray();
        if( (currentRecord.size() > 0) && (updatedRecord.size() > 0) )
        {
            for( Map.Entry entry : updatedRecord.entrySet()) {
                if ( currentRecord.has(entry.getKey().toString()) ) {

                    String firVal = currentRecord.get(entry.getKey().toString()).toString();
                    String secVal = updatedRecord.get(entry.getKey().toString()).toString();

                    if ( !firVal.equalsIgnoreCase(secVal) ) {
                        JsonObject jsonObj=new JsonObject();
                        jsonObj.add("propName",new Gson().fromJson(entry.getKey().toString(),JsonElement.class));
                        jsonObj.add("oldValue",currentRecord.get(entry.getKey().toString()));
                        jsonObj.add("newValue",updatedRecord.get(entry.getKey().toString()));
                        jsonArray.add(jsonObj);
                    }
                }
            }
        }
        return jsonArray;
    }

}
