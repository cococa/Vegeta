package com.cocoa.vegetaexample.bean;

import java.util.List;

/**
 * Created by cocoa on 2015/10/21.14:49
 * email:385811416@qq.com
 */
public class Person {


    /**
     * sex : value
     * name : value
     * details : [{"nationality":"value","birthDate":"value","Land":"value"}]
     */
    private String sex;
    private String name;
    private List<DetailsEntity> details;

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(List<DetailsEntity> details) {
        this.details = details;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public List<DetailsEntity> getDetails() {
        return details;
    }

    public static class DetailsEntity {
        /**
         * nationality : value
         * birthDate : value
         * Land : value
         */
        private String nationality;
        private String birthDate;
        private String Land;

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public void setLand(String Land) {
            this.Land = Land;
        }

        public String getNationality() {
            return nationality;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String getLand() {
            return Land;
        }
    }
}




