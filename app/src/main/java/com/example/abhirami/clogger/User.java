package com.example.abhirami.clogger;

import java.util.ArrayList;

public class User {

    String id;
    String name;
    String type;
    String supervisor;
    String department;
    String contactno;
    ArrayList<String> workerlist=new ArrayList<String>();
    ArrayList<String> complaintslist=new ArrayList<String>();

    public User()
    {}

    public User(String id,String name, String type, String supervisor, String department, String contactno)
    {
        this.id=id;
        this.name=name;
        this.type=type;
        this.supervisor=supervisor;
        this.department=department;
        this.contactno=contactno;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getId() {
        return id;
    }

    public String getContactno() {
        return contactno;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getComplaintslist() {
        return complaintslist;
    }

    public ArrayList<String> getWorkerlist() {
        return workerlist;
    }
}
