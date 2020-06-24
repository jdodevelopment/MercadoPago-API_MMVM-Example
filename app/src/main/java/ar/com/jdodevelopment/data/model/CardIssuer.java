package ar.com.jdodevelopment.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CardIssuer implements Parcelable {


    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    @SerializedName("thumbnail")
    private String thumbnail;


    // TODO: resto de campos


    public String getId() {
        return id;
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


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    /**
     * Parcelable
     */
    protected CardIssuer(Parcel in) {
        id = in.readString();
        name = in.readString();
        thumbnail = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(thumbnail);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CardIssuer> CREATOR = new Parcelable.Creator<CardIssuer>() {
        @Override
        public CardIssuer createFromParcel(Parcel in) {
            return new CardIssuer(in);
        }

        @Override
        public CardIssuer[] newArray(int size) {
            return new CardIssuer[size];
        }
    };
}
