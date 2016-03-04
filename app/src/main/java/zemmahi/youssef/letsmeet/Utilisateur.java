package zemmahi.youssef.letsmeet;

/**
 * Created by youssef on 24/02/2016.
 */
public class Utilisateur {
    private Integer id_=0;
    private String courriel_=new String();
    private boolean isPlanner_=false;
    private Position position_=null;
    private Preference[] userPreferences_=new Preference[3];
    private String photoEn64_ = new String();

   public Utilisateur()
   {
       for (int i=0;i<3;i++)
       {
           userPreferences_[i]=null;
       }
   }

    public Utilisateur(int id, String courriel, boolean isPlanner)
    {
        id_=id;
        courriel_=courriel;
        isPlanner_=isPlanner;
        for (int i=0;i<3;i++)
        {
            userPreferences_[i]=null;
        }
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public String getCourriel() {
        return courriel_;
    }

    public void setCourriel(String courriel) {
        this.courriel_ = courriel;
    }

    public boolean isPlanner() {
        return isPlanner_;
    }

    public void setIsPlanner(boolean isPlanner) {
        this.isPlanner_ = isPlanner;
    }

    public Position getPosition() {
        return position_;
    }

    public void setPosition(Position position) {
        this.position_ = position;
    }

    public Preference[] getUserPreferences() {
        return userPreferences_;
    }

    public void setUserPreferences(Preference[] userPreferences) {
        this.userPreferences_ = userPreferences;
    }

    public boolean addPreferences(Preference preference)
    {
        for (int i=0;i<3;i++)
        {
            if(userPreferences_[i]==null)
            {
                userPreferences_[i]=preference;
                return true;
            }
        }
        return  false;
    }
// indice a recevoir de 0 a 2
    public boolean switchPreferencesPriorities(int x, int y )
    {
        if (userPreferences_[x]==null || userPreferences_[y]==null)
        {
            // probleme si ca entre ici
            return false;
        }

        String temp = userPreferences_[x].getPriority();
        userPreferences_[x].setPriority(userPreferences_[y].getPriority());
        userPreferences_[y].setPriority(temp);

        return true;
    }

    public String getPhotoEn64() {
        return photoEn64_;
    }

    public void setPhotoEn64(String photoEn64) {
        this.photoEn64_ = photoEn64;
    }
}
