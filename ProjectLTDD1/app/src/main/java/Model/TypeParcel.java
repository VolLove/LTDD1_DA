package Model;

public class TypeParcel {
    private int type_id;
    private String title;
    private double pack_free;

    public TypeParcel() {
    }

    public TypeParcel(int type_id, String title, double pack_free) {
        this.type_id = type_id;
        this.title = title;
        this.pack_free = pack_free;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPack_free() {
        return pack_free;
    }

    public void setPack_free(double pack_free) {
        this.pack_free = pack_free;
    }

    @Override
    public String toString() {
        return title;
    }
}
