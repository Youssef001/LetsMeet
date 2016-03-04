package zemmahi.youssef.letsmeet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssef on 04/03/2016.
 */
public final class Parseur {

    public static List<Utilisateur> ParseToUsersList(String message) throws JSONException {
        JSONArray json=new JSONArray(message);
        List<Utilisateur> userLists= new ArrayList<Utilisateur>();
        for(int i=0;i<json.length();i++)
        {
            Utilisateur user= new Utilisateur();
            user.setId(json.getJSONObject(i).getInt("idutilisateur"));
            user.setCourriel(json.getJSONObject(i).getString("courriel"));
            user.setPhotoEn64(json.getJSONObject(i).getString("photo"));
            if(json.getJSONObject(i).getString("organisateur").contentEquals("1"))
            {
                user.setIsPlanner(true);
            }
            else
            {
                user.setIsPlanner(false);
            }

            userLists.add(user);
        }
    return userLists;

    }

    public static List<Position> ParseToPositionsList(String message) throws JSONException {
        JSONArray json=new JSONArray(message);
        List<Position> positionLists= new ArrayList<Position>();
        for(int i=0;i<json.length();i++)
        {
            Position position= new Position();
            position.setId(json.getJSONObject(i).getInt("idposition"));
            position.setLatitude(json.getJSONObject(i).getDouble("latitude"));
            position.setLongitude(json.getJSONObject(i).getDouble("longitude"));
            position.setRadius(json.getJSONObject(i).getDouble("radius"));
            positionLists.add(position);
        }
        return positionLists;
    }
    public static List<Groupe> ParseToGroupeList(String message) throws JSONException {
        JSONArray json=new JSONArray(message);
        List<Groupe> groupeLists= new ArrayList<Groupe>();
        for(int i=0;i<json.length();i++)
        {
            Groupe groupe= new Groupe();
            groupe.setId(json.getJSONObject(i).getInt("idgroupe"));
            groupe.setGrouoName(json.getJSONObject(i).getString("nom"));
            groupeLists.add(groupe);
        }
        return groupeLists;
    }
}
