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
    // initial synchronisation when we start the application
    public void SynchronizeLocalFromRemoteContent()
    {
        SynchronizeLocalUsersFromRemoteContent();
        SynchronizeLocalGroupsFromRemoteContent();
        SynchronizeLocalPreferencesFromRemoteContent();
        SynchronizeLocalPositionsFromRemoteContent();
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
