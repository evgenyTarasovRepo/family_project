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
    public void saveFamily(Father f, Mother m, List<Child> cl) {
        f.setWife(m);
        cl.forEach(f::addChild);
        cl.forEach(m::addChild);

        factory.inTransaction(session -> {
            session.persist(f);
        });
    }

    @Override
    public List<Father> getFathers() {
        return factory.fromTransaction(session -> session.createSelectionQuery("from Father", Father.class).list());
    }

    @Override
    public List<Mother> getMothers() {
        return factory.fromTransaction(session -> session.createSelectionQuery("from Mother", Mother.class).list());
    }

    @Override
    public List<Child> getChildren() {
        return factory.fromTransaction(session -> session.createSelectionQuery("from Child", Child.class).list());
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


}
