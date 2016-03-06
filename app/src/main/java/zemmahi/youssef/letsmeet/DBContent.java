package zemmahi.youssef.letsmeet;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssef on 05/03/2016.
 */
public class DBContent {

    List<Utilisateur> userList_ = new ArrayList<Utilisateur>();
    List<Preference> preferencesList_ = new ArrayList<Preference>();
    List<Groupe> groupsList_ = new ArrayList<Groupe>();
    List<Position> positionsList_ = new ArrayList<Position>();

    public DBContent ()
    {

    }

    public void SynchronizeLocalFromRemoteContent()
    {
        Thread thread1=SynchronizeLocalUsersFromRemoteContent();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2=SynchronizeLocalGroupsFromRemoteContent();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread3 =SynchronizeLocalPreferencesFromRemoteContent();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread4=SynchronizeLocalPositionsFromRemoteContent();
        try {
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }

    public  Thread SynchronizeLocalUsersFromRemoteContent()
    {
        Thread UsersThread = new Thread(new Runnable() {
            public void run() {
                Log.d("mytest", "c mon test a moi");
                DBConnexion con=new DBConnexion();
                try{
                    // TODO set the right url
                    userList_ = Parseur.ParseToUsersList(con.getRequest("http://najibarbaoui.com/najib"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        UsersThread.start();
        return UsersThread;
    }
    public  Thread SynchronizeLocalGroupsFromRemoteContent()
    {
        Thread GroupsThread = new Thread(new Runnable() {
            public void run() {
                Log.d("mytest", "c mon test a moi");
                DBConnexion con=new DBConnexion();
                try{
                    // TODO set the right url
                    groupsList_ = Parseur.ParseToGroupeList(con.getRequest("http://najibarbaoui.com/najib"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        GroupsThread.start();
        return GroupsThread;
    }
    public Thread SynchronizeLocalPreferencesFromRemoteContent()
    {
        Thread PreferencesThread = new Thread(new Runnable() {
            public void run() {
                Log.d("mytest", "c mon test a moi");
                DBConnexion con=new DBConnexion();
                try{
                    // TODO set the right url
                    preferencesList_ = Parseur.ParseToPreferencesList(con.getRequest("http://najibarbaoui.com/najib"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        PreferencesThread.start();
        return PreferencesThread;
    }

    public  Thread SynchronizeLocalPositionsFromRemoteContent()
    {
        Thread PositionsThread= new Thread(new Runnable() {
            public void run() {
                Log.d("mytest", "c mon test a moi");
                DBConnexion con=new DBConnexion();
                try{
                    // TODO set the right url
                    positionsList_ = Parseur.ParseToPositionsList(con.getRequest("http://najibarbaoui.com/najib"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        PositionsThread.start();
        return PositionsThread;
    }

}
