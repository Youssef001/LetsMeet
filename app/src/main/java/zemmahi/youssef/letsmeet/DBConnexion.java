package zemmahi.youssef.letsmeet;

import android.app.DownloadManager;
import android.util.Log;

import com.google.android.gms.appdatasearch.GetRecentContextCall;

import java.io.IOException;



/**
 * Created by youssef on 01/03/2016.
 */
public class DBConnexion {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String getRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        DownloadManager.Request request = new DownloadManager.Request.Builder()
                .url(url)
                .build();

        GetRecentContextCall.Response response = client.newCall(request).execute();
        Log.d("response to get",response.toString());
        String temp=response.body().string();
        Log.d("body",temp);
        return temp;
    }

    public String postRequest(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Log.d("response to get",response.toString());
        String temp=response.body().string();
        Log.d("body",temp);
        return temp;
    }
}
