package zemmahi.youssef.letsmeet;

/**
 * Created by youssef on 24/02/2016.
 */
public class Preference {
    private String id_=new String();
    private String idLocarion_=new String();
    private String priority_=Constants.DefaultPriority;
    private String userId_=new String();

    public Preference()
    {

    }
    public Preference(String id, Position position)
    {
        idLocarion_=id;
    }
    public Preference(String id,String priority)
    {
        idLocarion_=id;
        priority_=priority;
    }

    public String getIdLocarion() {
        return idLocarion_;
    }

    public void setIdLocation(String id) {
        this.idLocarion_ = id;
    }

    public String getPriority() {
        return priority_;
    }

    public void setPriority(String priority) {
        this.priority_ = priority;
    }

    public void setToHighPriority()
    {
        priority_=Constants.highPriority;
    }
    public void setToMediumPriority()
    {
        priority_=Constants.mediumPriority;
    }
    public void setToLowPriority()
    {
        priority_=Constants.lowPriority;
    }
    public void setToDefaultPriority()
    {
        priority_=Constants.DefaultPriority;
    }

    public String getId() {
        return id_;
    }

    public void setId(String id) {
        this.id_ = id;
    }

    public String getUserId() {
        return userId_;
    }

    public void setUserId(String userId) {
        this.userId_ = userId;
    }
}
