package com.example.helloworld.newsapp.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by helloworld on 2017/8/22.
 */

public class Book implements Parcelable{
    public int bookid;
    public String bookName;

    public Book(int bookid, String bookName) {
        this.bookid = bookid;
        this.bookName = bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.bookid);
        dest.writeString(this.bookName);
    }

    protected Book(Parcel in) {
        this.bookid = in.readInt();
        this.bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
