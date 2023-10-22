package Model;

public class Parcel {
    private Integer id,postage;
    private String title,decription;

    public Parcel() {
    }

    public Parcel(Integer id, Integer postage, String title, String decription) {
        this.id = id;
        this.postage = postage;
        this.title = title;
        this.decription = decription;
    }

    public Parcel(Integer postage, String title, String decription) {
        this.postage = postage;
        this.title = title;
        this.decription = decription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
