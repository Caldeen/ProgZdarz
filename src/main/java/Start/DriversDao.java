package Start;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

public class DriversDao implements Dao<Driver> {
    private static EntityManagerFactory factory=
            Persistence.createEntityManagerFactory("Zdarzz");
    @Override
    public void add(Driver driver){
        EntityManager entManager=factory.createEntityManager();
        EntityTransaction entityTransaction=null;
        try {
            entityTransaction=entManager.getTransaction();
            entityTransaction.begin();
            entManager.persist(driver);
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction!=null){
                entityTransaction.rollback();
            }
        }finally {
            entManager.close();
        }
    }
    public void add(int id,String firstName,String lastName,double salary){
            Driver driver =new Driver();
            driver.setId(id);
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driver.setSalary(salary);
            add(driver);
    }
    @Override
    public void del(int id){
        EntityManager entManager=factory.createEntityManager();
        EntityTransaction entityTransaction=null;
        try {
            entityTransaction=entManager.getTransaction();
            entityTransaction.begin();
            Driver driver =entManager.find(Driver.class,id);
            entManager.remove(driver);
            entManager.persist(driver);
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction!=null){
                entityTransaction.rollback();
            }
        }finally {
            entManager.close();
        }
    }
    @Override
    public Driver get(int id) {
        EntityManager entManager = factory.createEntityManager();
        String query="SELECT c FROM Driver c WHERE c.id = :custID";
        TypedQuery<Driver> typedQuery=entManager.createQuery(query, Driver.class);
        typedQuery.setParameter("custID",id);
        Driver driver =null;
        try {
            driver =typedQuery.getSingleResult();
            return driver;
        }catch (NoResultException e){
            System.out.println("nothing");
        }finally {
            entManager.close();
        }
        return null;
    }
    @Override
    public ObservableList<Driver> getAll (){
        EntityManager entManager = factory.createEntityManager();
        String query="SELECT c FROM Driver c WHERE c.id IS NOT NULL";
        TypedQuery<Driver> typedQuery=entManager.createQuery(query, Driver.class);
        ObservableList<Driver> drivers;
        try{
            drivers = FXCollections.observableList(typedQuery.getResultList());
            return drivers;
        }catch (NoResultException e){
            System.out.println("nothing");
        }finally {
            entManager.close();
        }
        return null;
    }
}
