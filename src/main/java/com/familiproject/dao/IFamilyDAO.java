package com.familiproject.dao;

import com.familiproject.entities.Child;
import com.familiproject.entities.Father;
import com.familiproject.entities.Mother;

import java.util.List;

public interface IFamilyDAO {
    void saveFamily(Father f, Mother m, List<Child> cl);
    List<Father> getFathers();
    List<Mother> getMothers();
    List<Child> getChildren();

    Father getFatherById(int id);
    Mother getMotherById(int id);
    Child getChildById(int id);
}