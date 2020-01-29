package Start.Controllers;

import Start.Dao.ScheduleDao;
import Start.Model.Driver;
import Start.Dao.DriversDao;
import Start.Model.ScheduleRow;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class ControllerMain {
    private DriversDao driversDao =new DriversDao();
    private ScheduleDao scheduleDao=new ScheduleDao();
    @FXML
    private TableView DriverTableView;
    @FXML
    private TableView moneyTableView;
    @FXML
    private TableView harmonogramTableView;
    @FXML
    public void initialize(){
        show();
        showWynagrodzenie();
        showHarmonogram();
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
    public void openScheduleWindow() throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/ScheduleWindow.fxml"));
        Pane root=loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Schedule window");
        ControllerSchedule controllerSchedule= loader.getController();
        controllerSchedule.setControllerMain(this);
        controllerSchedule.setStage(stage);
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
         moneyTableView.getVisibleLeafColumn(3).setCellValueFactory
               ((Callback<TableColumn.CellDataFeatures<Driver, Integer>, SimpleDoubleProperty>)
                       c -> new SimpleDoubleProperty((c.getValue().getHours_worked()*c.getValue().getSalary())));
        moneyTableView.setItems(driversDao.getAll());
    }
    public void updateHarmonogram(){
        harmonogramTableView.setItems(scheduleDao.getAll());
    }
    public void showHarmonogram(){
        harmonogramTableView.getVisibleLeafColumn(0)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow,Integer>("id"));
        harmonogramTableView.getVisibleLeafColumn(1)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow,String>("pon"));
        harmonogramTableView.getVisibleLeafColumn(2)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow,String>("wt"));
        harmonogramTableView.getVisibleLeafColumn(3)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow,String>("sr"));
        harmonogramTableView.getVisibleLeafColumn(4)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow,String>("czw"));
        harmonogramTableView.getVisibleLeafColumn(5)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow,String>("pt"));
        harmonogramTableView.getVisibleLeafColumn(6)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow,String>("sob"));
        harmonogramTableView.getVisibleLeafColumn(7)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow,String>("niedz"));

        harmonogramTableView.setItems(scheduleDao.getAll());
    }
    public void addHarmonogramRow(){
        scheduleDao.add();
        updateHarmonogram();
    }
    public void delHarmonogramRow(){
        scheduleDao.del(scheduleDao.getMax());
        updateHarmonogram();
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
