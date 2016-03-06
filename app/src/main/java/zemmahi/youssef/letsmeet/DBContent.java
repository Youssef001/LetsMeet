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

    Map<String,Utilisateur> userMap_ = new HashMap<String,Utilisateur>();
    Map<String,Preference> preferencesMap_ = new HashMap<String,Preference>();
    Map<String,Groupe> groupsMap_ = new HashMap<String,Groupe>();
    Map<String,Position> positionsMap_ = new HashMap<String,Position>();

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
        for (Map.Entry<String, Preference> preference : preferencesMap_.entrySet())
        {
            if(userMap_.containsKey(preference.getValue().getUserId()))
            {
                userMap_.get(preference.getValue().getUserId()).addPreferences(preference.getValue());
            }
            else
            {
                Log.d("problem in initial sort","user not found");
            }
        }

        for (Map.Entry<String, Utilisateur> user : userMap_.entrySet())
        {
            if(positionsMap_.containsKey(user.getValue().getPositionId()))
            {
                user.getValue().setPosition(positionsMap_.get(user.getValue().getPositionId()));
            }
            else
            {
                Log.d("problem in initial sort","Position not found in the map");
            }
            if(groupsMap_.containsKey(user.getValue().getGroupeId()))
            {
                groupsMap_.get(user.getValue().getGroupeId()).addUser(user.getValue());
            }
        }

    }


    public  void SynchronizeLocalUsersFromRemoteContent()
    {
        Thread UsersThread = new Thread(new Runnable() {
            public void run() {
                Log.d("Users test", "c mon test a moi");
                DBConnexion con=new DBConnexion();
                try{
                    // TODO set the right url
                    userMap_ = Parseur.ParseToUsersMap(con.getRequest("http://najibarbaoui.com/najib"));
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
                    groupsMap_ = Parseur.ParseToGroupeMap(con.getRequest("http://najibarbaoui.com/najib"));
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
                    preferencesMap_ = Parseur.ParseToPreferencesMap(con.getRequest("http://najibarbaoui.com/najib"));
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
                    positionsMap_ = Parseur.ParseToPositionsMap(con.getRequest("http://najibarbaoui.com/najib"));
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
