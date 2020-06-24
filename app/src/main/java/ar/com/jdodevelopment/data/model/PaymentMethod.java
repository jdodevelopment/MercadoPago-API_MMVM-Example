package ar.com.jdodevelopment.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PaymentMethod implements Parcelable {


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


    protected PaymentMethod(Parcel in) {
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
    public static final Parcelable.Creator<PaymentMethod> CREATOR = new Parcelable.Creator<PaymentMethod>() {
        @Override
        public PaymentMethod createFromParcel(Parcel in) {
            return new PaymentMethod(in);
        }

        @Override
        public PaymentMethod[] newArray(int size) {
            return new PaymentMethod[size];
        }
    };

}
