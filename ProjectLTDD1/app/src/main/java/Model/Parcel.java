package Model;

import android.widget.Adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parcel {
    private int parcel_id, id_personnel, id_type, status;
    private String title, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver;
    private double transport_free, weight;
    private Date date_get, date_trans;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Parcel() {

    }

    public Parcel(int parcel_id, int id_personnel, int id_type, int status, String title,
                  String name_sender, String phone_sender, String name_receiver, String phone_receiver,
                  String address_receiver, double transport_free, double weight, String date_get, String date_trans) throws ParseException {
        this.parcel_id = parcel_id;
        this.id_personnel = id_personnel;
        this.id_type = id_type;
        this.status = status;
        this.title = title;
        this.name_sender = name_sender;
        this.phone_sender = phone_sender;
        this.name_receiver = name_receiver;
        this.phone_receiver = phone_receiver;
        this.address_receiver = address_receiver;
        this.transport_free = transport_free;
        this.weight = weight;
        this.date_get = format.parse(date_get);
        this.date_trans = format.parse(date_trans);
    }

    public int getParcel_id() {
        return parcel_id;
    }

    public void setParcel_id(int parcel_id) {
        this.parcel_id = parcel_id;
    }

    public int getId_personnel() {
        return id_personnel;
    }

    public void setId_personnel(int id_personnel) {
        this.id_personnel = id_personnel;
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
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public double getTransport_free() {
        return transport_free;
    }

    public void setTransport_free(double transport_free) {
        this.transport_free = transport_free;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDate_get() {
        return format.format(date_get);
    }

    public void setDate_get(Date date_get) {
        this.date_get = date_get;
    }

    public String getDate_trans() {
        return format.format(date_trans);
    }

    public void setDate_trans(Date date_trans) {
        this.date_trans = date_trans;
    }
}
