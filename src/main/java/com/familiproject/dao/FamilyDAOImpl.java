package com.familiproject.dao;

import com.familiproject.entities.Child;
import com.familiproject.entities.Father;
import com.familiproject.entities.Mother;
import com.familiproject.utils.HibernateUtils;
import org.hibernate.SessionFactory;

import java.util.List;

public class FamilyDAOImpl implements IFamilyDAO {
    SessionFactory factory = HibernateUtils.getSessionFactory();
    @Override
    public void addFamilyViaFather(Father f, Mother m, List<Child> cl) {
        f.setWife(m);
        if (m != null) cl.forEach(m::addChild);
        if (f != null) cl.forEach(f::addChild);

        factory.inTransaction(session -> {
            session.persist(f);
        });
    }

    @Override
    public void addFamilyViaMother(Mother m, Father f, List<Child> cl) {
        m.setHusband(f);
        if (f != null) cl.forEach(f::addChild);
        if (m != null) cl.forEach(m::addChild);

        factory.inTransaction(session -> {
            session.persist(m);
        });
    }

    @Override
    public void saveFather(Father f) {
        factory.inTransaction(session -> session.persist(f));
    }
    @Override
    public void saveMother(Mother m) {
        factory.inTransaction(session -> session.persist(m));
    }
    @Override
    public void saveChild(Child c) {
        factory.inTransaction(session -> session.persist(c));
    }

    @Override
    public List<Father> getFathers() {
        return factory.fromTransaction(session -> session.createSelectionQuery("from Father", Father.class).getResultList());
    }

    @Override
    public List<Mother> getMothers() {
        return factory.fromTransaction(session -> session.createSelectionQuery("from Mother", Mother.class).getResultList());
    }

    @Override
    public List<Child> getChildren() {
        return factory.fromTransaction(session -> session.createSelectionQuery("from Child", Child.class).getResultList());
    }

    @Override
    public Father getFatherById(int id) {
        return factory.fromTransaction(session -> session.find(Father.class, id));
    }

    @Override
    public Mother getMotherById(int id) {
        return factory.fromTransaction(session -> session.find(Mother.class, id));
    }

    @Override
    public Child getChildById(int id) {
        return factory.fromTransaction(session -> session.find(Child.class, id));
    }

    @Override
    public void removeFather(Father f) {
        Mother w = f.getWife();
        if (w != null) {
            w.setHusband(null);
            factory.inTransaction(session -> session.merge(w));
        }

        List<Child> childList = f.getChildList();
        if (childList.size() > 0) {
            factory.inTransaction(session -> childList.forEach(child -> {
                child.removeFather();
                session.merge(child);
            }));
        }
        f.setWife(null);
        factory.inTransaction(session -> session.remove(f));
    }

    @Override
    public void removeMother(Mother m) {
        Father f = m.getHusband();
        if (f != null) {
            f.setWife(null);
            factory.inTransaction(session -> session.merge(f));
        }

        List<Child> childList = m.getChildList();
        if (childList.size() > 0) {
            factory.inTransaction(session -> childList.forEach(child -> {
                child.removeMother();
                session.merge(child);
            }));
        }
        m.setHusband(null);
        factory.inTransaction(session -> session.remove(m));
    }

    @Override
    public void removeChild(Child c) {
        Father f = c.getFather();
        Mother m = c.getMother();

        if (f != null){
            f.removeChild(c);
            c.setFather(null);
        }
        if (m != null){
            m.removeChild(c);
            c.setMother(null);
        }

        factory.inTransaction(session -> session.remove(c));
    }

    @Override
    public void updateFathersFirstName(Father f, String newName) {
        f.setFirstName(newName);
        factory.inTransaction(session -> session.merge(f));
    }

    @Override
    public void updateFathersLastName(Father f, String newLastName) {
        f.setLastName(newLastName);
        factory.inTransaction(session -> session.merge(f));
    }

    @Override
    public void updateFathersAge(Father f, int newAge) {
        f.setAge(newAge);
        factory.inTransaction(session -> session.merge(f));
    }

    @Override
    public void updateMothersFirstName(Mother m, String newName) {
        m.setFirstName(newName);
        factory.inTransaction(session -> session.merge(m));
    }

    @Override
    public void updateMothersLastName(Mother m, String newLastName) {
        m.setLastName(newLastName);
        factory.inTransaction(session -> session.merge(m));
    }

    @Override
    public void updateMothersAge(Mother m, int newAge) {
        m.setAge(newAge);
        factory.inTransaction(session -> session.merge(m));
    }

    @Override
    public void updateChildFirstName(Child c, String newName) {
        c.setFirstName(newName);
        factory.inTransaction(session -> session.merge(c));
    }

    @Override
    public void updateChildLastName(Child c, String newLastName) {
        c.setLastName(newLastName);
        factory.inTransaction(session -> session.merge(c));
    }

    @Override
    public void updateChildAge(Child c, int newAge) {
        c.setAge(newAge);
        factory.inTransaction(session -> session.merge(c));
    }
}
