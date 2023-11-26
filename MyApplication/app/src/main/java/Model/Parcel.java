package Model;

import com.example.myapplication.MainActivity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Parcel implements Serializable {
    private int parcel_id, id_type, status;
    private String name_sender;
    private String phone_sender;
    private String name_receiver;
    private String phone_receiver;
    private String address_receiver;
    private String decription;
    private double weight;
    private String date_get, date_trans;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Parcel() {
    }

    public Parcel(int parcel_id, int id_type, int status, String name_sender, String phone_sender, String name_receiver, String phone_receiver, String address_receiver, String decription, double weight, String date_get, String date_trans) {
        this.parcel_id = parcel_id;
        this.id_type = id_type;
        this.status = status;
        this.name_sender = name_sender;
        this.phone_sender = phone_sender;
        this.name_receiver = name_receiver;
        this.phone_receiver = phone_receiver;
        this.address_receiver = address_receiver;
        this.decription = decription;
        this.weight = weight;
        this.date_get = date_get;
        this.date_trans = date_trans;
    }

    public int getParcel_id() {
        return parcel_id;
    }

    public void setParcel_id(int parcel_id) {
        this.parcel_id = parcel_id;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault());
        if (status == 2) {
            date_trans = dateFormatter.format(currentDateTime);
        } else {
            date_trans = "1/1/1";
        }
        this.status = status;
    }

    public String getName_sender() {
        return name_sender;
    }

    public void setName_sender(String name_sender) {
        this.name_sender = name_sender;
    }

    public String getPhone_sender() {
        return phone_sender;
    }

    public void setPhone_sender(String phone_sender) {
        this.phone_sender = phone_sender;
    }

    public String getName_receiver() {
        return name_receiver;
    }

    public void setName_receiver(String name_receiver) {
        this.name_receiver = name_receiver;
    }

    public String getPhone_receiver() {
        return phone_receiver;
    }

    public void setPhone_receiver(String phone_receiver) {
        this.phone_receiver = phone_receiver;
    }

    public String getAddress_receiver() {
        return address_receiver;
    }

    public void setAddress_receiver(String address_receiver) {
        this.address_receiver = address_receiver;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDate_get() {
        return date_get;
    }

    public void setDate_get(String date_get) {
        this.date_get = date_get;
    }

    public String getDate_trans() {
        return date_trans;
    }

    public void setDate_trans(String date_trans) {
        this.date_trans = date_trans;
    }

    public void setDate_trans_Date(Date date_trans) {
        this.date_trans = format.format(date_trans);
    }

    public void setDate_get_date(Date date_get) {
        this.date_get = format.format(date_get);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "parcel_id=" + parcel_id +
                ", id_type=" + id_type +
                ", status=" + status +
                ", name_sender='" + name_sender + '\'' +
                ", phone_sender='" + phone_sender + '\'' +
                ", name_receiver='" + name_receiver + '\'' +
                ", phone_receiver='" + phone_receiver + '\'' +
                ", address_receiver='" + address_receiver + '\'' +
                ", decription='" + decription + '\'' +
                ", weight=" + weight +
                ", date_get='" + date_get + '\'' +
                ", date_trans='" + date_trans + '\'' +
                ", format=" + format +
                '}';
    }

    public double getTransport_free() {
        List<TypeParcel> typeParcels = MainActivity.databaseHandler.getAllTypeParcels();
        double multi = 0;
        for (TypeParcel typeParcel : typeParcels) {
            if (typeParcel.getType_id() == id_type) {
                multi = typeParcel.getPack_free();
            }
        }
        return weight * multi;
    }
}
