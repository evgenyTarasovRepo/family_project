package com.familiproject.dao;

import com.familiproject.entities.Child;
import com.familiproject.entities.Father;
import com.familiproject.entities.Mother;

import java.util.List;

public interface IFamilyDAO {
    void addFamilyViaFather(Father f, Mother m, List<Child> cl);
    void addFamilyViaMother(Mother m, Father f, List<Child> cl);
    void saveFather(Father f);
    void saveMother(Mother m);
    void saveChild(Child c);
    List<Father> getFathers();
    List<Mother> getMothers();
    List<Child> getChildren();
    Father getFatherById(int id);
    Mother getMotherById(int id);
    Child getChildById(int id);
    void removeFather(Father f);
    void removeMother(Mother m);
    void removeChild(Child c);
    void updateFathersFirstName(Father f, String newName);
    void updateFathersLastName(Father f, String newLastName);
    void updateFathersAge(Father f, int newAge);
    void updateMothersFirstName(Mother m, String newName);
    void updateMothersLastName(Mother m, String newLastName);
    void updateMothersAge(Mother m, int newAge);
    void updateChildFirstName(Child c, String newName);
    void updateChildLastName(Child c, String newLastName);
    void updateChildAge(Child c, int newAge);
}