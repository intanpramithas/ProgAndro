package com.example.progandroid;

import android.provider.BaseColumns;

public class DataContacts {
    public DataContacts(){}
    public static final class contactsEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAMA = "nama";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMNN_NOHP = "nohp";
    }
}
