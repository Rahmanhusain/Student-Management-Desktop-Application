//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.management.schoolmanagement.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    SimpleLongProperty sno,ID;
    SimpleLongProperty TOATALFESS, FEEPAID, FEEDUE;
    SimpleStringProperty NAME;
    SimpleStringProperty CLASS;
    SimpleStringProperty FATHER;
    SimpleStringProperty SCHOOL;
    SimpleStringProperty FEEPLAN, BATCH;
    SimpleStringProperty Status;


    public Student(long sno,long ID, String N, String Class, String FATHER,  String school, long TOATALFESS, long FEEPAID, long FEEDUE,String FEEPLAN, String BATCH,  String status) {
        this.sno=new SimpleLongProperty(sno);
        this.ID = new SimpleLongProperty(ID);
        this.NAME = new SimpleStringProperty(N);
        this.CLASS = new SimpleStringProperty(Class);
        this.SCHOOL = new SimpleStringProperty(school);
        this.FATHER = new SimpleStringProperty(FATHER);
        this.FEEPLAN = new SimpleStringProperty(FEEPLAN);
        this.TOATALFESS = new SimpleLongProperty(TOATALFESS);
        this.FEEPAID = new SimpleLongProperty(FEEPAID);
        this.FEEDUE = new SimpleLongProperty(FEEDUE);
        this.BATCH = new SimpleStringProperty(BATCH);
        this.Status = new SimpleStringProperty(status);
    }

    public long getID() {
        return ID.get();
    }



    public void setID(long ID) {
        this.ID.set(ID);
    }

    public long getTOATALFESS() {
        return TOATALFESS.get();
    }



    public void setTOATALFESS(long TOATALFESS) {
        this.TOATALFESS.set(TOATALFESS);
    }

    public long getFEEPAID() {
        return FEEPAID.get();
    }



    public void setFEEPAID(long FEEPAID) {
        this.FEEPAID.set(FEEPAID);
    }

    public long getFEEDUE() {
        return FEEDUE.get();
    }



    public void setFEEDUE(long FEEDUE) {
        this.FEEDUE.set(FEEDUE);
    }

    public String getNAME() {
        return NAME.get();
    }



    public void setNAME(String NAME) {
        this.NAME.set(NAME);
    }

    public String getCLASS() {
        return CLASS.get();
    }



    public void setCLASS(String CLASS) {
        this.CLASS.set(CLASS);
    }

    public String getFATHER() {
        return FATHER.get();
    }



    public void setFATHER(String FATHER) {
        this.FATHER.set(FATHER);
    }

    public String getSCHOOL() {
        return SCHOOL.get();
    }



    public void setSCHOOL(String SCHOOL) {
        this.SCHOOL.set(SCHOOL);
    }

    public String getFEEPLAN() {
        return FEEPLAN.get();
    }


    public void setFEEPLAN(String FEEPLAN) {
        this.FEEPLAN.set(FEEPLAN);
    }

    public String getBATCH() {
        return BATCH.get();
    }



    public void setBATCH(String BATCH) {
        this.BATCH.set(BATCH);
    }

    public String getStatus() {
        return Status.get();
    }


    public void setStatus(String status) {
        this.Status.set(status);
    }

    public long getSno() {
        return sno.get();
    }


    public void setSno(long sno) {
        this.sno.set(sno);
    }
}