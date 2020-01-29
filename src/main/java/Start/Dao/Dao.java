package Start.Dao;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

public interface Dao<T> {

    void add(T t); //C
    void del(int id);//D
    T get(int id);//R
    ObservableList<T> getAll();//R
    void update(T t,String[] args);

}
