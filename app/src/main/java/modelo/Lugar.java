package modelo;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

public class Lugar implements Parcelable {
    private int id;
    private String nome;
    private String descricao;
    private String latitude;
    private String longitude;

    public Lugar() {

    }

    public Lugar(int id, String nome, String descricao, String latitude, String longitude) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    public Lugar(Parcel n) {
        this.nome = n.readString();
        this.descricao = n.readString();
        this.latitude = n.readString();
        this.longitude = n.readString();
    }

    public static final Parcelable.Creator<Lugar> CREATOR = new Parcelable.Creator<Lugar>() {

        @Override
        public Lugar createFromParcel(Parcel source) {
            return new Lugar(source);
        }

        @Override
        public Lugar[] newArray(int size) {
            return new Lugar[size];
        }
    };
}
