package TwiJava.forJson;


public class JsonTimeLineForm {
   public static String ArrayToObject(String jsonArray){
       if(jsonArray.startsWith("[")){
           jsonArray="{\"items\":" + jsonArray + "}";
           return jsonArray;
       }
       else {
           return jsonArray;
       }
   }
}
