package zemmahi.youssef.letsmeet;

/**
 * Created by youssef on 24/02/2016.
 */
public class Groupe {
    private String id_=new String();
    private String name_=null;

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

    public void setGrouoName(String name)
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
}
