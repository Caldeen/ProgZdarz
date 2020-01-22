package Start.Controllers;

import Start.Driver;
import Start.DriversDao;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class ControllerMain {
    private DriversDao driversDao =new DriversDao();
    @FXML
    private TableView DriverTableView;
    @FXML
    private TableView moneyTableView;
    @FXML
    public void initialize(){
        show();
        showWynagrodzenie();
    }
    public void openAddWindow() throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/AddWindow.fxml"));
        Pane root=loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Add window");
        ControllerAddDriver controllerAddDriver= loader.getController();
        controllerAddDriver.setControllerMain(this);
        controllerAddDriver.setStage(stage);
        stage.setScene(scene);
        stage.show();

    }

    public void openDelWindow() throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/DelWindow.fxml"));
        Pane root=loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Deletion window");
        ControllerDelDriver controllerDelDriver= loader.getController();
        controllerDelDriver.setControllerMain(this);
        controllerDelDriver.setStage(stage);
        stage.setScene(scene);
        stage.show();

    }

    public void openUpdateWindow() throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/UpdateWindow.fxml"));
        Pane root=loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Update window");
        ControllerUpdateDriver controllerUpdateDriver= loader.getController();
        controllerUpdateDriver.setControllerMain(this);
        controllerUpdateDriver.setStage(stage);
        stage.setScene(scene);
        stage.show();

    }
    public void showWynagrodzenie(){
        moneyTableView.getColumns().remove(0,moneyTableView.getColumns().size());
        moneyTableView.getColumns().add(new TableColumn<>("Imię"));
        moneyTableView.getColumns().add(new TableColumn<>("Nazwisko"));
        moneyTableView.getColumns().add(new TableColumn<>("Identyfikator"));
        moneyTableView.getColumns().add(new TableColumn<>("Wynagrodzenie"));

        moneyTableView.getVisibleLeafColumn(0)
                .setCellValueFactory(new PropertyValueFactory<Driver,String>("firstName"));
        moneyTableView.getVisibleLeafColumn(1)
                .setCellValueFactory(new PropertyValueFactory<Driver,String>("lastName"));
        moneyTableView.getVisibleLeafColumn(2)
                .setCellValueFactory(new PropertyValueFactory<Driver,Integer>("id"));
       moneyTableView.getVisibleLeafColumn(3).setCellValueFactory((Callback<TableColumn.CellDataFeatures<Driver, Integer>, SimpleDoubleProperty>) c -> new SimpleDoubleProperty((c.getValue().getHours_worked()*c.getValue().getSalary())));
        moneyTableView.setItems(driversDao.getAll());
    }

    public void show(){
        DriverTableView.getColumns().remove(0,DriverTableView.getColumns().size());
        DriverTableView.getColumns().add(new TableColumn<>("Imię"));
        DriverTableView.getColumns().add(new TableColumn<>("Nazwisko"));
        DriverTableView.getColumns().add(new TableColumn<>("Płaca/h"));
        DriverTableView.getColumns().add(new TableColumn<>("Identyfikator"));
        DriverTableView.getColumns().add(new TableColumn<>("Godziny pracy"));

        DriverTableView.getVisibleLeafColumn(0)
                .setCellValueFactory(new PropertyValueFactory<Driver,String>("firstName"));
        DriverTableView.getVisibleLeafColumn(1)
                .setCellValueFactory(new PropertyValueFactory<Driver,String>("lastName"));
        DriverTableView.getVisibleLeafColumn(2)
                .setCellValueFactory(new PropertyValueFactory<Driver, Double>("salary"));
        DriverTableView.getVisibleLeafColumn(3)
                .setCellValueFactory(new PropertyValueFactory<Driver,Integer>("id"));
        DriverTableView.getVisibleLeafColumn(4)
                .setCellValueFactory(new PropertyValueFactory<Driver,Integer>("hours_worked"));
        DriverTableView.setItems(driversDao.getAll());

    }

}
