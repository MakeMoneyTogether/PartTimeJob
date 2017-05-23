package com.partjob.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Sloriac on 2017/5/23.
 */
@Entity
@Table(name = "tbl_user_info", schema = "parttimejob", catalog = "")
public class TblUserInfo {
    private int uid;
    private String name;
    private String gender;
    private String phone;
    private Double balance;
    private String major;
    private String grade;
    private String direction;
    private Date birthday;
    private String school;
    private String pwd;

    @Id
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 255)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "balance", nullable = true, precision = 0)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "major", nullable = true, length = 255)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "grade", nullable = true, length = 255)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "direction", nullable = true, length = 255)
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "school", nullable = true, length = 255)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "pwd", nullable = true, length = 255)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblUserInfo that = (TblUserInfo) o;

        if (uid != that.uid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (direction != null ? !direction.equals(that.direction) : that.direction != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        return result;
    }
}
