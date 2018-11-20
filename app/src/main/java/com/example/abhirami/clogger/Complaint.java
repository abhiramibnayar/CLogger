package com.example.abhirami.clogger;

class Complaint {


    public String department;
    public String location;
    public String additionalinfo;
    public String clientid;
    public String status;
    public String message;
    public String supervisorid;
    public String workerid;
    public String complaintid;


    public Complaint()
    {

    }

    public Complaint(String dept,String loc, String addinf,String clientid)
    {
        this.department=dept;
        this.location=loc;
        this.additionalinfo=addinf;
        this.clientid=clientid;
        this.status="waiting for supervisor";
    }

    public String getDepartment() {
        return department;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public String getClientid() {
        return clientid;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getSupervisorid() {
        return supervisorid;
    }

    public String getWorkerid() {
        return workerid;
    }

    public String getComplaintid() {
        return complaintid;
    }
}
