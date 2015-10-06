package com.example.admin.p0344444444444;

import android.provider.BaseColumns;

public class Names {

    public static final String DEFAULT_SORT = NamesColumns.FNAME + " DESC";
    public static final String TABLE_NAME = "People";
    private String name;
    private long id;
    private String fname;
    private String age;

    public String getName() {

        return name;
    }

    public long getId() {

        return id;
    }

    public String getFname() {

        return fname;
    }

    public String getAge() {

        return age;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setId(long id) {

        this.id = id;
    }

    public void setFname(String fname) {

        this.fname = fname;
    }

    public void setAge(String age) {

        this.age = age;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(fname);
        return builder.toString();
    }

    public class NamesColumns implements BaseColumns {

        public static final String NAME = "name";
        public static final String FNAME = "fname";
        public static final String AGE = "age";
    }
}


