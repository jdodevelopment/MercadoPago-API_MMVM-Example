package ar.com.jdodevelopment.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PayerCost implements Parcelable {


    @SerializedName("installments")
    private Integer installments;

    @SerializedName("total_amount")
    private Double totalAmount;

    @SerializedName("installment_amount")
    private Double installmentAmount;

    @SerializedName("recommended_message")
    private String recommendedMessage;

    // TODO: resto de campos


    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public void setRecommendedMessage(String recommendedMessage) {
        this.recommendedMessage = recommendedMessage;
    }


    /**
     * Parcelable
     */

    protected PayerCost(Parcel in) {
        installments = in.readByte() == 0x00 ? null : in.readInt();
        totalAmount = in.readByte() == 0x00 ? null : in.readDouble();
        installmentAmount = in.readByte() == 0x00 ? null : in.readDouble();
        recommendedMessage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (installments == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(installments);
        }
        if (totalAmount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(totalAmount);
        }
        if (installmentAmount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(installmentAmount);
        }
        dest.writeString(recommendedMessage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PayerCost> CREATOR = new Parcelable.Creator<PayerCost>() {
        @Override
        public PayerCost createFromParcel(Parcel in) {
            return new PayerCost(in);
        }

        @Override
        public PayerCost[] newArray(int size) {
            return new PayerCost[size];
        }
    };
}
