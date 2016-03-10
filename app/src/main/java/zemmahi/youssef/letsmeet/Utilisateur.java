package zemmahi.youssef.letsmeet;

import android.graphics.Bitmap;

/**
 * Created by youssef on 24/02/2016.
 */
public class Utilisateur {
    private String id_= new String();
    private String name_ = new String();
    private String courriel_=new String();
    private Bitmap photo_;// = Bitmap.createBitmap(" ");
    private boolean isPlanner_=false;
    private Position position_=null;
    private Boolean checkedBox_;
    private Preference[] userPreferences_=new Preference[3];
    private String photoEn64_ = new String();
    private String positionId_=new String();
    private String groupeId_=new String();
    private String password_=new String();


    public Utilisateur()
   {
       for (int i=0;i<3;i++)
       {
           userPreferences_[i]=null;
       }
   }

    public Utilisateur(String id, String name, String courriel, boolean isPlanner)
    {
        name_ = name;
        id_=id;
        courriel_=courriel;
        isPlanner_=isPlanner;
        for (int i=0;i<3;i++)
        {
            userPreferences_[i]=null;
        }
    }
    public void setName(String name){
        this.name_ = name;
    }
    public String getName() {
        return name_;
    }
    public Bitmap getThumb(){
        return photo_;
    }
    public void setThump(Bitmap thump){
        this.photo_ = thump;
    }
    public Boolean getCheckedBox() {
        return checkedBox_;
    }

    public void setCheckedBox(Boolean checkedBox) {
        this.checkedBox_ = checkedBox;
    }
    public String getId() {
        return id_;
    }

    public void setId(String id) {
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

    public String getPositionId() {
        return positionId_;
    }

    public void setPositionId(String positionId) {
        this.positionId_ = positionId;
    }

    public String getGroupeId() {
        return groupeId_;
    }

    public void setGroupeId(String groupeId) {
        this.groupeId_ = groupeId;
    }

    public String getPassword() {
        return password_;
    }

    public void setPassword(String password) {
        this.password_ = password;
    }

    public boolean IsUserNameValide(){
        /* TO DO */
        // Vérifier si le user name est valide

        return false;
    }
    public boolean IsPasswordValide(){
        /* TO DO */
        // Vérifier si le user name est valide

        return false;
    }

}
