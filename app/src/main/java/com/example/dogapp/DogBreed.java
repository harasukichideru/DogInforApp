package com.example.dogapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

class height implements Parcelable{
    @SerializedName("imperial")
    private String imperial;

    @SerializedName("metric")
    private String metric;

    public height(String imperial, String metric) {
        this.imperial = imperial;
        this.metric = metric;
    }

    protected height(Parcel in) {
        imperial = in.readString();
        metric = in.readString();
    }

    public static final Creator<height> CREATOR = new Creator<height>() {
        @Override
        public height createFromParcel(Parcel in) {
            return new height(in);
        }

        @Override
        public height[] newArray(int size) {
            return new height[size];
        }
    };

    public String getImperial() {
        return imperial;
    }
    public void setImperial(String imperial) {
        this.imperial = imperial;
    }
    public String getMetric() {
        return metric;
    }
    public void setMetric(String metric) {
        this.metric = metric;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imperial);
        parcel.writeString(metric);
    }
}
class weight implements Parcelable{
    @SerializedName("imperial")
    private String imperial;

    @SerializedName("metric")
    private String metric;

    public weight(String imperial, String metric) {
        this.imperial = imperial;
        this.metric = metric;
    }

    protected weight(Parcel in) {
        imperial = in.readString();
        metric = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imperial);
        dest.writeString(metric);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<weight> CREATOR = new Creator<weight>() {
        @Override
        public weight createFromParcel(Parcel in) {
            return new weight(in);
        }

        @Override
        public weight[] newArray(int size) {
            return new weight[size];
        }
    };

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }
    public void setMetric(String metric) {
        this.metric = metric;
    }
}

public class DogBreed implements Parcelable {
    @SerializedName("bred_for")
    private String bred_for;

    private boolean showMenu = false;

    @SerializedName("breed_group")
    private String breed_group;

    @SerializedName("height")
    private height Height;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("life_span")
    private String lifeSpan;

    @SerializedName("origin")
    private String origin;

    @SerializedName("temperament")
    private String temperament;

    @SerializedName("weight")
    private weight Weight;

    @SerializedName("url")
    private String url;

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public static Creator<DogBreed> getCREATOR() {
        return CREATOR;
    }

    public DogBreed(String bred_for, String breed_group, height height,
                    int id, String name, String lifeSpan, String origin,
                    String temperament, weight weight, String url, boolean ShowMenu ) {
        this.bred_for = bred_for;
        this.breed_group = breed_group;
        Height = height;
        this.id = id;
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.temperament = temperament;
        Weight = weight;
        this.url = url;
        this.showMenu = showMenu;
    }

    protected DogBreed(Parcel in) {
        bred_for = in.readString();
        breed_group = in.readString();
        id = in.readInt();
        name = in.readString();
        lifeSpan = in.readString();
        origin = in.readString();
        temperament = in.readString();
        url = in.readString();
    }

    public static final Creator<DogBreed> CREATOR = new Creator<DogBreed>() {
        @Override
        public DogBreed createFromParcel(Parcel in) {
            return new DogBreed(in);
        }

        @Override
        public DogBreed[] newArray(int size) {
            return new DogBreed[size];
        }
    };

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public height getHeight() {
        return Height;
    }

    public void setHeight(height height) {
        Height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public weight getWeight() {
        return Weight;
    }

    public void setWeight(weight weight) {
        Weight = weight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bred_for);
        parcel.writeString(breed_group);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(lifeSpan);
        parcel.writeString(origin);
        parcel.writeString(temperament);
        parcel.writeString(url);
    }

}
