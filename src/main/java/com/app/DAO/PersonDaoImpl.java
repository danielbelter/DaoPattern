package com.app.DAO;

import com.app.DbConnection;
import com.app.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {
    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void add(Person person) {
        if (connection != null && person != null) {
            try {
                String sql = "INSERT INTO Person (name, surname, age) VALUES (?,?,?)";
                PreparedStatement prep = connection.prepareStatement(sql);
                prep.setString(1, person.getName());
                prep.setString(2, person.getSurname());
                prep.setInt(3, person.getAge());
                prep.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Person person) {
        if (connection != null && person != null) {
            try {
                String sql = "UPDATE Person SET name=?, surname=?, age=? WHERE id=?";
                PreparedStatement prep = connection.prepareStatement(sql);
                prep.setString(1, person.getName());
                prep.setString(2, person.getSurname());
                prep.setInt(3, person.getAge());
                prep.setInt(4, person.getId());
                prep.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        if (connection != null && id != null) {
            try {
                String sql = "DELETE FROM Person WHERE id=?";
                PreparedStatement prep = connection.prepareStatement(sql);
                prep.setInt(1, id);
                prep.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Person> findOneById(Integer id) {
        Optional<Person> personOptional = Optional.empty();

        if (connection != null && id != null) {
            try {
                String sql = "SELECT id, name, surname, age FROM Person WHERE id = ?";
                PreparedStatement prep = connection.prepareStatement(sql);
                prep.setInt(1, id);


                ResultSet resultSet = prep.executeQuery();


                if (resultSet.next()) {
                    personOptional = Optional.of(new Person(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)
                    ));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return personOptional;
    }

    @Override
    public List<Person> findAll() {
        List<Person> people = null;

        if (connection != null) {
            try {
                String sql = "SELECT id, name, surname, age FROM Person";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                people = new ArrayList<>();
                while (resultSet.next()) {
                    people.add(new Person(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return people;
    }
}
