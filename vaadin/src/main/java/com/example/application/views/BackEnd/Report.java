package com.example.application.views.BackEnd;

enum ReportType {
    MEDICAL_CERTIFICATE,
    LEAVE,
    STUDY_GROUP_CHANGE
}


public class Report {
    private Student issuer;
    private Secretary approver;
    String content;
    ReportType type;

    public Report(String content, ReportType type)
    {
        this.content = content;
        this.type = type;
    }

    public String getContent()
    {
        return this.content;
    }

    public String getType()
    {
        if(this.type == ReportType.MEDICAL_CERTIFICATE)
        {
            return "MEDICAL CERTIFICATE";
        }
        else if(this.type == ReportType.STUDY_GROUP_CHANGE)
        {
            return  "STUDY GROUP CHANGE";
        }
        else if(this.type == ReportType.LEAVE)
        {
            return "LEAVE";
        }

        return "UNKNOWN";
    }


    public void showReport()
    {
        if(this.type == ReportType.MEDICAL_CERTIFICATE)
        {
            System.out.println("MEDICAL CERTIFICATE: ");
        }
        else if(this.type == ReportType.LEAVE)
        {
            System.out.println("LEAVE: ");
        }
        else if(this.type == ReportType.STUDY_GROUP_CHANGE)
        {
            System.out.println("STUDY GROUP CHANGE : ");
        }

        System.out.println("\n" +  this.content);

    }


    public Student getIssuer() {
        return issuer;
    }


    public Secretary getApprover() {
        return approver;
    }
}
