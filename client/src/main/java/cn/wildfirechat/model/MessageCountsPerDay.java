/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.wildfirechat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heavyrainlee on 14/12/2017.
 */

public class MessageCountsPerDay implements Parcelable {
    public static class DayCount {
        public String day;
        public int count;

        public DayCount(String day, int count) {
            this.day = day;
            this.count = count;
        }
    }
    public List <DayCount> dayCounts;
    public MessageCountsPerDay() {
        dayCounts = new ArrayList<>();
    }
    public void add(String day, int count) {
        this.dayCounts.add(new DayCount(day, count));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dayCounts.size());
        for (DayCount dayCount:dayCounts) {
            dest.writeString(dayCount.day);
            dest.writeInt(dayCount.count);
        }
    }

    protected MessageCountsPerDay(Parcel in) {
        int size = in.readInt();
        if(dayCounts == null) {
            dayCounts = new ArrayList<>();
        }
        for (int i = 0; i < size; i++) {
            String day = in.readString();
            int count = in.readInt();
            dayCounts.add(new DayCount(day, count));
        }
    }

    public static final Creator<MessageCountsPerDay> CREATOR = new Creator<MessageCountsPerDay>() {
        @Override
        public MessageCountsPerDay createFromParcel(Parcel source) {
            return new MessageCountsPerDay(source);
        }

        @Override
        public MessageCountsPerDay[] newArray(int size) {
            return new MessageCountsPerDay[size];
        }
    };
}
