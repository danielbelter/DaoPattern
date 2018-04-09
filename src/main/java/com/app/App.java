package com.app;

import com.app.DAO.PersonDao;
import com.app.DAO.PersonDaoImpl;

public class App {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDaoImpl();
        personDao.add(Person.builder().name("Jan").surname("Kowalski").age(20).build());
        personDao.add(Person.builder().name("Kacper").surname("Mmmm").age(22).build());
        personDao.add(Person.builder().name("Marek").surname("Kkk").age(40).build());


        // personDao.update(new Person(1, "Marek", "Kowalski", 21));
        // personDao.delete(1);
    }
}
