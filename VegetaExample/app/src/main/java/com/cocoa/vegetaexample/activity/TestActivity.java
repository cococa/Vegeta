package com.cocoa.vegetaexample.activity;

import android.os.Bundle;

import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.R;
import com.cocoa.vegetaexample.bean.Person;
import com.google.gson.Gson;

import java.util.ArrayList;


public class TestActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        Person p = new Person();
        p.setName("name");
        p.setSex("sex");

        Person.DetailsEntity bean = new Person.DetailsEntity();
        bean.setBirthDate("1988");
        bean.setLand("land");
        bean.setNationality("xxxx");

        ArrayList<Person.DetailsEntity> list = new ArrayList<Person.DetailsEntity>();
        list.add(bean);

        p.setDetails(list);

        System.out.print(new Gson().toJson(p));


    }


    @Override
    public void onNetChanged(String status) {

    }
}
