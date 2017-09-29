import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class Main {}

  /*  public static void main(String[] args) throws Exception {

        // OAuthにおいて利用する変数宣言
        String consumerkey = "Twitterから発行されたConsumer key";
        String consumerSecret = "Twitterから発行されたConsumer secret";
        String oauthToken = "アクセストークン取得時に取得したoauth_token";
        String oauthTokenSecret = "アクセストークン取得時に取得したoauth_token_secret";
        String method = "POST";
        String urlStr = "https://api.twitter.com/1.1/statuses/update.json";

        // 共通パラメーター
        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("oauth_consumer_key", consumerkey);
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_timestamp", String.valueOf(getUnixTime()));
        params.put("oauth_nonce", String.valueOf(Math.random()));
        params.put("oauth_version", "1.0");
        params.put("oauth_token", oauthToken);

        // ステータス更新API利用時にのみ利用するパラメーター
        String status = "てすと";
        params.put("status", urlEncode(status));

        {
			/*
			 * 署名（oauth_signature）の生成
			 * リクエストトークン取得時と全く同じ処理
			 */
   /*         String paramStr = "";
            for (Entry<String, String> param : params.entrySet()) {
                paramStr += "&" + param.getKey() + "=" + param.getValue();
            }
            paramStr = paramStr.substring(1);

            String text = method + "&" + urlEncode(urlStr) + "&"
                    + urlEncode(paramStr);

            String key = urlEncode(consumerSecret) + "&"
                    + urlEncode(oauthTokenSecret);

            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
                    "HmacSHA1");
            Mac mac = Mac.getInstance(signingKey.getAlgorithm());
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(text.getBytes());
            String signature = new BASE64Encoder().encode(rawHmac);

            params.put("oauth_signature", signature);
        }

		/*
		 * Authorizationヘッダの作成とAPIの呼び出し
		 * リクエストトークン取得、アクセストークン取得と以下の点が異なる。
		 * (1)statusはAuthorizationヘッダーではなくurlに含めるためparamsから削除する
		 * (2)urlにstatusを含める
		 */
        // (1)statusはAuthorizationヘッダーではなくurlに含めるためparamsから削除する
     /*   params.remove("status");

        // Authorizationヘッダの作成
        String paramStr = "";
        for (Entry<String, String> param : params.entrySet()) {
            paramStr += ", " + param.getKey() + "=\""
                    + urlEncode(param.getValue()) + "\"";
        }
        paramStr = paramStr.substring(2);
        String authorizationHeader = "OAuth " + paramStr;

        // APIにアクセス
        // (2)urlにstatusを含める
        URL url = new URL(urlStr + "?status=" + urlEncode(status));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Authorization", authorizationHeader);
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String response;
        while ((response = reader.readLine()) != null) {
            System.out.println(response);
        }
    }

    private static int getUnixTime() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    private static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}*/