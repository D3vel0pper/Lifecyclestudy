package t4ka.com.lifecyclestudy.commons;

/**
 * Created by taka-dhu on 2015/10/26.
 */

public class DBDatas {
    private String id = "0";
    private String name = "";
    private String comment = "";

    public String getId() {
        //return id.toString();
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
