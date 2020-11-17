package com.example.sampleemptyproject;

import android.provider.BaseColumns;

public class UserProfile {
    private UserProfile(){}

    class Users implements BaseColumns{
        public static final String COL_1 = "_ID";
        public static final String COL_2 = "userName";
        public static final String COL_3 = "dateOfBirth";
        public static final String COL_4 = "Gender";
    }
}
