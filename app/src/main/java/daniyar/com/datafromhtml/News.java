package daniyar.com.datafromhtml;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yernar on 21/09/16.
 */

public class News implements Parcelable {
    private String title;
    private String description;
    private String imageUrl;

    public News(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public News setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public News setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public News setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.imageUrl);
    }

    protected News(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.imageUrl = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
