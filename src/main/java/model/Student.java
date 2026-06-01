package model;

public class Student {
    private int id;
    private String masv;
    private String hoten;

    public Student() {
    }

    public Student(String masv, String hoten) {
    this.masv = masv;
    this.hoten = hoten;
}
public Student(int id, String masv, String hoten) {
    this.id = id;
    this.masv = masv;
    this.hoten = hoten;
}

public int getId() {
        return id;
    }
public String getMasv() { return masv; }
public String getHoten() { return hoten; }
}
