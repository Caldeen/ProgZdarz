package Start;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    DriversDao driversDao =new DriversDao();
    @FXML
    private TableView DriverTableView;
    public void test(ActionEvent event){
        System.out.println("TEST");

    }
    public void show(){
        DriverTableView.getVisibleLeafColumn(0)
                .setCellValueFactory(new PropertyValueFactory<Driver,String>("firstName"));
        DriverTableView.getVisibleLeafColumn(1)
                .setCellValueFactory(new PropertyValueFactory<Driver,String>("lastName"));
        DriverTableView.setItems(driversDao.getAll());

    }


}
