package zemmahi.youssef.letsmeet;

import android.util.ArrayMap;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by youssef on 05/03/2016.
 */
public class DBContent {

    Map<String,Utilisateur> userList_ = new HashMap<String,Utilisateur>();
    Map<String,Preference> preferencesList_ = new HashMap<String,Preference>();
    Map<String,Groupe> groupsList_ = new HashMap<String,Groupe>();
    Map<String,Position> positionsList_ = new HashMap<String,Position>();

    public DBContent ()
    {

    }
    // initial synchronisation when we start the application
    public void SynchronizeLocalFromRemoteContent()
    {
        SynchronizeLocalUsersFromRemoteContent();
        SynchronizeLocalGroupsFromRemoteContent();
        SynchronizeLocalPreferencesFromRemoteContent();
        SynchronizeLocalPositionsFromRemoteContent();
        InitialSortOfElements();
    }
    private void InitialSortOfElements()
    {

    }


    public  void SynchronizeLocalUsersFromRemoteContent()
    {
        Thread UsersThread = new Thread(new Runnable() {
            public void run() {
                Log.d("Users test", "c mon test a moi");
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
        try {
            UsersThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void SynchronizeLocalGroupsFromRemoteContent()
    {
        Thread GroupsThread = new Thread(new Runnable() {
            public void run() {
                Log.d("Groups test", "c mon test a moi");
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
        try {
            GroupsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void SynchronizeLocalPreferencesFromRemoteContent()
    {
        Thread PreferencesThread = new Thread(new Runnable() {
            public void run() {
                Log.d("Preferences test ", "c mon test a moi");
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
        try {
            PreferencesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void SynchronizeLocalPositionsFromRemoteContent()
    {
        Thread PositionsThread= new Thread(new Runnable() {
            public void run() {
                Log.d("Positions test", "c mon test a moi");
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
        try {
            PositionsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
