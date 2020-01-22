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
            add(new Driver(id,firstName,lastName,salary));
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
        String query="SELECT c FROM Driver c WHERE c.id = :driverID";
        TypedQuery<Driver> typedQuery=entManager.createQuery(query, Driver.class);
        typedQuery.setParameter("driverID",id);
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
    public Integer getMax (){
        EntityManager entManager = factory.createEntityManager();
        String query="SELECT max(id) FROM Driver ";
        TypedQuery<Integer> typedQuery=entManager.createQuery(query, Integer.class);
        Integer result;
        try{
            result = typedQuery.getSingleResult();
            return result;
        }catch (NoResultException e){
            System.out.println("nothing");
        }finally {
            entManager.close();
        }
        return null;
    }

    @Override
    public void update(Driver driver, String[] args) {
        EntityManager entManager=factory.createEntityManager();
        EntityTransaction entityTransaction=null;
        try {
            entityTransaction=entManager.getTransaction();
            entityTransaction.begin();
            Driver driverOld =entManager.find(Driver.class,driver.getId());
            driverOld.setFirstName(driver.getFirstName());
            driverOld.setLastName(driver.getLastName());
            driverOld.setSalary(driver.getSalary());
            driverOld.setHours_worked(driver.getHours_worked());
            entManager.persist(driverOld);
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction!=null){
                entityTransaction.rollback();
            }
        }finally {
            entManager.close();
        }
    }
}
