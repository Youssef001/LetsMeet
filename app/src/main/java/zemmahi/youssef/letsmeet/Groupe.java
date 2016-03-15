package zemmahi.youssef.letsmeet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youssef on 24/02/2016.
 */
public class Groupe {
    private String id_=new String();
    private String name_=null;
    private Map<String,Utilisateur> usersMap_ = new HashMap<String,Utilisateur>();

    public Groupe()
    {
        name_="sans";
    }


    public Groupe(String nom)
    {
        name_=nom;
    }

    public String getGroupName()
    {
        return name_;
    }

    public void setGroupName(String name)
    {
        name_=name;
    }

    public String getId()
    {
        return id_;
    }

    public void setId(String id)
    {
        id_=id;
    }

    public Map<String, Utilisateur> getUsersMap() {
        return usersMap_;
    }

    public void setUsersMap(Map<String, Utilisateur> usersMap) {
        this.usersMap_ = usersMap;
    }

    public void deleteUser(String id)
    {
        usersMap_.remove(id);
    }
    public boolean addUser(Utilisateur user)
    {
        if(!usersMap_.containsKey(user.getId()))
        {
            usersMap_.put(user.getId(),user);
            return true;
        }
        return false;
    }
}
