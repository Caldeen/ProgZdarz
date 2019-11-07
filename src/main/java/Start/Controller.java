package Start;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    private DriversDao driversDao =new DriversDao();
    @FXML
    private TableView DriverTableView;
    public void show(){

        DriverTableView.getColumns().remove(0,DriverTableView.getColumns().size());
        DriverTableView.setPrefWidth(4);
        DriverTableView.getColumns().add(new TableColumn<>("Imię"));
        DriverTableView.getColumns().add(new TableColumn<>("Nazwisko"));
        DriverTableView.getColumns().add(new TableColumn<>("Identyfikator"));
        DriverTableView.getColumns().add(new TableColumn<>("Płaca/h"));
        for (int i = 0; i < 4; i++) {
            adjustTableToNewColumn(i);
        }
        DriverTableView.getVisibleLeafColumn(0)
                .setCellValueFactory(new PropertyValueFactory<Driver,String>("firstName"));
        DriverTableView.getVisibleLeafColumn(1)
                .setCellValueFactory(new PropertyValueFactory<Driver,String>("lastName"));
        DriverTableView.getVisibleLeafColumn(2)
                .setCellValueFactory(new PropertyValueFactory<Driver,Integer>("id"));
        DriverTableView.getVisibleLeafColumn(3)
                .setCellValueFactory(new PropertyValueFactory<Driver, Double>("salary"));
        DriverTableView.setItems(driversDao.getAll());


    }

    private void adjustTableToNewColumn(int index){
        DriverTableView.setPrefWidth(DriverTableView.getPrefWidth()
                +DriverTableView.getVisibleLeafColumn(index).getWidth());
    }
}
