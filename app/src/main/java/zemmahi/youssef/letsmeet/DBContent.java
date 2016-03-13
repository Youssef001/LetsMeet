package zemmahi.youssef.letsmeet;

import android.util.ArrayMap;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by youssef on 05/03/2016.
 */
public class DBContent {

    // la map contient les utilisateurs qui appartiennent au groupe auquel appartient l'utilisateur
    private Map<String,Utilisateur> userMap_ = new HashMap<String,Utilisateur>();
    private Map<String,Preference> preferencesMap_ = new HashMap<String,Preference>();
    private Map<String,Groupe> groupsMap_ = new HashMap<String,Groupe>();
    private Map<String,Position> positionsMap_ = new HashMap<String,Position>();
    private String actualGroupId_= new String();
    private String actualUserId_=new String();
    // seul attribut commun permettant de verifier la reponse a la requette http a l'interieur d'un thread
    private boolean flagForResponses = false;

    // instance du singleton
    private static DBContent instance_ = null;

    private DBContent ()
    {

    }

    public static DBContent getInstance()
    {
        if(instance_==null)
        {
            instance_ = new DBContent();
        }
        return instance_;
    }
    public static void destroyInstance()
    {
        instance_=null;
    }

    private void InitialSyncOfElements()
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


    public  void GetUsersFromGroup(String idGroupe)
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

    public boolean CreerNouvelUtilisateur(final String UserName, final String email, final String password)
    {
        flagForResponses=false;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                // todo password enregistre localement est dangereux, voir solution alternative
                Utilisateur NUtilisateur = new Utilisateur(UserName, email, password, actualGroupId_);
                try {
                    // todo l<url a mettre et le format de la reponse pour verifier si loperatin est bien effectuer
                    // reponse true ou false du cote serveur
                    String reponsePost = DBConnexion.postRequest("URL", Parseur.ParseUserToJsonFormat(NUtilisateur));
                    // todo verifier le format de la reponse pour savoir si les changement ont bien ete effectue
                    if(reponsePost.contentEquals(""))
                    {
                        flagForResponses=true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flagForResponses;

    }
    public boolean authentification(final String username, final String password)
    {;
        flagForResponses=false;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    //todo right url
                    String reponsePost= DBConnexion.postRequest("url",Parseur.ParseAuthentificationInfoToJsonFormat(username, password));
                   // todo response format true or false
                    if(reponsePost.contentEquals(""))
                    {
                        flagForResponses=true;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flagForResponses;
    }

    void mettreAjourPositionsMembresDuGroupe()
    {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                // todo right url
                String url = "";
                try {
                    url+="?groupe_idgroupe=" + URLEncoder.encode(actualGroupId_,"UTF-8");
                    Map<String, Position> newPositions= new HashMap<String, Position>();

                    try {
                        newPositions = Parseur.ParseToPositionsMap(DBConnexion.getRequest(url));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for(Map.Entry<String, Utilisateur> entry : userMap_.entrySet())
                    {
                        if(newPositions.containsKey(entry.getValue().getPositionId()))
                        {
                            entry.getValue().setPosition(newPositions.get(entry.getValue().getPositionId()));
                        }
                        else
                        {
                            Log.d("Probelem","when updating users position");
                        }
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void getAllGroupsInformations()
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

    public  void GetUsersPositions(String IdGroupe)
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
    public Map<String, Utilisateur> getUserMap() {
        return userMap_;
    }

    public void setUserMap_(Map<String, Utilisateur> userMap) {
        this.userMap_ = userMap;
    }

    public Map<String, Preference> getPreferencesMap() {
        return preferencesMap_;
    }

    public void setPreferencesMap(Map<String, Preference> preferencesMap) {
        this.preferencesMap_ = preferencesMap;
    }

    public Map<String, Groupe> getGroupsMap() {
        return groupsMap_;
    }

    public void setGroupsMap(Map<String, Groupe> groupsMap) {
        this.groupsMap_ = groupsMap;
    }

    public Map<String, Position> getPositionsMap() {
        return positionsMap_;
    }

    public void setPositionsMap(Map<String, Position> positionsMap) {
        this.positionsMap_ = positionsMap;
    }

    public String getActualGroupId() {
        return actualGroupId_;
    }

    public void setActualGroupId(String actualGroupId) {
        this.actualGroupId_ = actualGroupId;
    }

    public String getActualUserId() {
        return actualUserId_;
    }

    public void setActualUserId(String actualUserId_) {
        this.actualUserId_ = actualUserId_;
    }
}
