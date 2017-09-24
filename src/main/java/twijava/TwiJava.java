package twijava;

import twijava.core.TwiJavaMainSystem;

import java.io.IOException;
// java lang import



public class TwiJava{ //後で一度このまま試す

    private String ck;
    private String cks;
    private String at;
    private String ats;


    public void setConsumerKey(String consumerKey){
        ck=consumerKey;
    }
    public void setConsumerSecret(String consumerSecret){
        cks=consumerSecret;
    }
    public void setAccessToken(String accessToken){
        at=accessToken;
    }
    public void setAccessTokenSecret(String accessTokenSecret){
        ats=accessTokenSecret;
    }
    private TwiJavaMainSystem twiJavaMainSystem=new TwiJavaMainSystem(ck,cks,at,ats);

    public String tweet(String text) throws IOException{
       return twiJavaMainSystem.postcall(text);
    }


    /*private String getRequest(String url,String method,Map<String,String>timeLineData)throws IOException {
        String fullurl=API_BASE_URL+url;
        Map<String,String>header=createHeader();
        Map<String,String>AuthticationMerged=new TreeMap<>(header);
        AuthticationMerged.putAll(timeLineData);
        String signature = String.join("&",
                method, urlEncode(fullurl), urlEncode(formUrlEncodedContent(AuthticationMerged)));
        header.put("oauth_signature",generateTLsignature(signature));
        String headerString = "OAuth " + header.entrySet().stream()
                .map(e -> String.format("%s=\"%s\"", urlEncode(e.getKey()), urlEncode(e.getValue())))
                .collect(Collectors.joining(", "));

        fullurl += "?" + formUrlEncodedContent(timeLineData);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpUriRequest request;
            request=new HttpGet(fullurl);
            System.out.println(request);
            request.setHeader(HttpHeaders.AUTHORIZATION, headerString);

            return client.execute(request, res -> EntityUtils.toString(res.getEntity(), "UTF-8"));
        }
    }
    private String generateTLsignature(String signature){
        Mac m=null;
        try{
            String sha1SecretKey=String.join("&",CONSUMER_SECRET,ACCESS_TOKEN_SECRET);
            SecretKeySpec secretKeySpec=new SecretKeySpec(sha1SecretKey.getBytes(StandardCharsets.US_ASCII),"HmacSHA1");
            m = Mac.getInstance("HmacSHA1");
            m.init(secretKeySpec);
        }
        catch (Exception e){
        }
        return Base64.getEncoder().encodeToString(m.doFinal(signature.getBytes(StandardCharsets.US_ASCII))); // Convert to Base64
    }*/


}
