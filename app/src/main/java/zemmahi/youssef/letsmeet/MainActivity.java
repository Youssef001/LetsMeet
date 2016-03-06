package zemmahi.youssef.letsmeet;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void test()
    {
        DBConnexion con=new DBConnexion();
        try {
            con.getRequest("http://najibarbaoui.com/najib/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addListenerOnButton() {

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Thread t=new Thread(new Runnable() {
                    public void run() {
                        Log.d("mytest","c mon test a moi");
                        DBConnexion con=new DBConnexion();
                        try{
                            Map<String,Utilisateur> userList = Parseur.ParseToUsersMap(con.getRequest("http://najibarbaoui.com/najib"));
                            userList.size();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
                t.start();

                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
