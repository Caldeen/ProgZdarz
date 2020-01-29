package Start.Dao;

import Start.Model.Driver;
import Start.Model.ScheduleRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;

public class ScheduleDao implements Dao<ScheduleRow> {

    private static EntityManagerFactory factory=
            Persistence.createEntityManagerFactory("Zdarzz");
    @Override
    public void add(ScheduleRow scheduleRow) {
        EntityManager entManager=factory.createEntityManager();
        EntityTransaction entityTransaction=null;
        try {
            entityTransaction=entManager.getTransaction();
            entityTransaction.begin();
            entManager.persist(scheduleRow);
            entityTransaction.commit();
        }catch (Exception e){
            if(entityTransaction!=null){
                entityTransaction.rollback();
            }
        }finally {
            entManager.close();
        }
    }
    public void add(){
        ScheduleRow scheduleRow=new ScheduleRow();
        add(scheduleRow);
    }
    public Integer getMax (){
        EntityManager entManager = factory.createEntityManager();
        String query="SELECT max(id) FROM ScheduleRow ";
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
    public void del(int id) {
        EntityManager entManager=factory.createEntityManager();
        EntityTransaction entityTransaction=null;
        try {
            entityTransaction=entManager.getTransaction();
            entityTransaction.begin();
            ScheduleRow scheduleRow =entManager.find(ScheduleRow.class,id);
            entManager.remove(scheduleRow);
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
    public ScheduleRow get(int id) {
        EntityManager entManager = factory.createEntityManager();
        String query="SELECT c FROM ScheduleRow c WHERE c.id = :driverID";
        TypedQuery<ScheduleRow> typedQuery=entManager.createQuery(query, ScheduleRow.class);
        typedQuery.setParameter("driverID",id);
        ScheduleRow scheduleRow  =null;
        try {
            scheduleRow=typedQuery.getSingleResult();
            return scheduleRow;
        }catch (NoResultException e){
            System.out.println("nothing");
        }finally {
            entManager.close();
        }
        return null;
    }


    @Override
    public ObservableList<ScheduleRow> getAll() {
        EntityManager entManager = factory.createEntityManager();
        String query="FROM ScheduleRow";
        TypedQuery<ScheduleRow> typedQuery=entManager.createQuery(query, ScheduleRow.class);
        ObservableList<ScheduleRow> rows;
        try{
            rows = FXCollections.observableList(typedQuery.getResultList());
            System.out.println("SIZEE "+rows.size());
            return rows;
        }catch (NoResultException e){
            System.out.println("nothing");
        }finally {
            entManager.close();
        }
        return null;
    }

    @Override
    public void update(ScheduleRow scheduleRow, String[] args) {
        EntityManager entManager=factory.createEntityManager();
        EntityTransaction entityTransaction=null;
        try {
            entityTransaction=entManager.getTransaction();
            entityTransaction.begin();
            ScheduleRow scheduleRow1 =entManager.find(ScheduleRow.class,scheduleRow.getId());
            scheduleRow1.setPon(scheduleRow.getPon());
            scheduleRow1.setWt(scheduleRow.getWt());
            scheduleRow1.setSr(scheduleRow.getSr());
            scheduleRow1.setCzw(scheduleRow.getCzw());
            scheduleRow1.setPt(scheduleRow.getPt());
            scheduleRow1.setSob(scheduleRow.getSob());
            scheduleRow1.setNiedz(scheduleRow.getNiedz());
            entManager.persist(scheduleRow1);
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
