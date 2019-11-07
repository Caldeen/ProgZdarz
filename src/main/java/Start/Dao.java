package Start;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.List;

public interface Dao<T> {

    void add(T t);
    void del(int id);
    T get(int id);
    ObservableList<T> getAll();

}
