package Model;

public class TypeParcels {
    private Integer id, transport_free,weight;
    private String title, id_type, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver;

    public TypeParcels() {
    }

    public TypeParcels(Integer id, Integer transport_free, Integer weight, String title, String id_type, String name_sender, String phone_sender, String name_receiver, String phone_receiver, String address_receiver) {
        this.id = id;
        this.transport_free = transport_free;
        this.weight = weight;
        this.title = title;
        this.id_type = id_type;
        this.name_sender = name_sender;
        this.phone_sender = phone_sender;
        this.name_receiver = name_receiver;
        this.phone_receiver = phone_receiver;
        this.address_receiver = address_receiver;
    }

    public TypeParcels(Integer transport_free, Integer weight, String title, String id_type, String name_sender, String phone_sender, String name_receiver, String phone_receiver, String address_receiver) {
        this.transport_free = transport_free;
        this.weight = weight;
        this.title = title;
        this.id_type = id_type;
        this.name_sender = name_sender;
        this.phone_sender = phone_sender;
        this.name_receiver = name_receiver;
        this.phone_receiver = phone_receiver;
        this.address_receiver = address_receiver;
    }
}
