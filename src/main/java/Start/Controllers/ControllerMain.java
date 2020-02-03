package Start.Controllers;

import Start.Dao.ScheduleDao;
import Start.Model.Driver;
import Start.Dao.DriversDao;
import Start.Model.ScheduleRow;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class ControllerMain {
    private ScheduleDao scheduleDao = new ScheduleDao();
    @FXML
    private TableView driverTableView;
    @FXML
    private TableView moneyTableView;
    @FXML
    private TableView scheduleTableView;
    @FXML
    public Text statusText;

    public void initialize() {
        createDriverTableView();
        createScheduleTableView();
        createPayTableView();
        showPay();
        showSchedule();
        showDrivers();
    }

    public void openAddWindow() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddWindow.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Add window");
        ControllerAddDriver controllerAddDriver = loader.getController();
        controllerAddDriver.setControllerMain(this);
        controllerAddDriver.setStage(stage);
        stage.setScene(scene);
        stage.show();

    }

    public void openScheduleWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ScheduleWindow.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Schedule window");
        ControllerSchedule controllerSchedule = loader.getController();
        controllerSchedule.setControllerMain(this);
        controllerSchedule.setStage(stage);
        stage.setScene(scene);
        stage.show();

    }

    public void openDelWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DelWindow.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Deletion window");
        ControllerDelDriver controllerDelDriver = loader.getController();
        controllerDelDriver.setControllerMain(this);
        controllerDelDriver.setStage(stage);
        stage.setScene(scene);
        stage.show();

    }

    public void openUpdateWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateWindow.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Update window");
        ControllerUpdateDriver controllerUpdateDriver = loader.getController();
        controllerUpdateDriver.setControllerMain(this);
        controllerUpdateDriver.setStage(stage);
        stage.setScene(scene);
        stage.show();

    }

    public void showPay() {
        Task<ObservableList<Driver>> task = new Task<ObservableList<Driver>>() {
            @Override
            protected ObservableList<Driver> call() throws Exception {
                DriversDao driversDao1 = new DriversDao();
                return driversDao1.getAll();
            }
        };

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                moneyTableView.getItems().setAll(task.getValue());
            }
        });

        new Thread(task).start();

    }

    public void showSchedule() {
        Task<ObservableList<ScheduleRow>> task = new Task<ObservableList<ScheduleRow>>() {
            @Override
            protected ObservableList<ScheduleRow> call() throws Exception {
                ScheduleDao scheduleDao = new ScheduleDao();
                return scheduleDao.getAll();
            }
        };

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                scheduleTableView.getItems().setAll(task.getValue());
            }
        });

        new Thread(task).start();
    }

    public void showDrivers() {
        Task<ObservableList<Driver>> task = new Task<ObservableList<Driver>>() {
            @Override
            protected void succeeded() {
                super.succeeded();
                updateMessage("Success");
            }

            @Override
            protected void running() {
                super.running();
                updateMessage("Running...");
            }
            @Override
            protected ObservableList<Driver> call() throws Exception {
                DriversDao driversDao1 = new DriversDao();
                return driversDao1.getAll();
            }
        };
        statusText.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                driverTableView.getItems().setAll(task.getValue());
            }
        });


        new Thread(task).start();
    }

    public void addScheduleRow() {
        Task<Void> task=new Task<Void>() {
            @Override
            protected void succeeded() {
                super.succeeded();
                updateMessage("Success!");
            }

            @Override
            protected void running() {
                super.running();
                updateMessage("Running...");
            }

            @Override
            protected void cancelled() {
                super.cancelled();
                updateMessage("Cancelled");
            }

            @Override
            protected void failed() {
                super.failed();
                updateMessage("Failed ");
            }
            @Override
            protected Void call() throws Exception {
                ScheduleDao scheduleDao=new ScheduleDao();
                scheduleDao.add();
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                showSchedule();
            }
        });
        statusText.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }

    public void delScheduleRow() {
        Task<Void> task=new Task<Void>() {
            @Override
            protected void succeeded() {
                super.succeeded();
                updateMessage("Success!");
            }

            @Override
            protected void running() {
                super.running();
                updateMessage("Running...");
            }

            @Override
            protected void cancelled() {
                super.cancelled();
                updateMessage("Cancelled");
            }

            @Override
            protected void failed() {
                super.failed();
                updateMessage("Failed ");
            }
            @Override
            protected Void call() throws Exception {
                ScheduleDao scheduleDao=new ScheduleDao();
                scheduleDao.del(scheduleDao.getMax());
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                showSchedule();
            }
        });
        statusText.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }

    private void createDriverTableView() {
        driverTableView.getColumns().remove(0, driverTableView.getColumns().size());
        driverTableView.getColumns().add(new TableColumn<>("Identyfikator"));
        driverTableView.getColumns().add(new TableColumn<>("Imię"));
        driverTableView.getColumns().add(new TableColumn<>("Nazwisko"));
        driverTableView.getColumns().add(new TableColumn<>("Płaca/h"));
        driverTableView.getColumns().add(new TableColumn<>("Godziny pracy"));

        driverTableView.getVisibleLeafColumn(0)
                .setCellValueFactory(new PropertyValueFactory<Driver, Integer>("id"));
        driverTableView.getVisibleLeafColumn(1)
                .setCellValueFactory(new PropertyValueFactory<Driver, String>("firstName"));
        driverTableView.getVisibleLeafColumn(2)
                .setCellValueFactory(new PropertyValueFactory<Driver, String>("lastName"));
        driverTableView.getVisibleLeafColumn(3)
                .setCellValueFactory(new PropertyValueFactory<Driver, Double>("salary"));
        driverTableView.getVisibleLeafColumn(4)
                .setCellValueFactory(new PropertyValueFactory<Driver, Integer>("hours_worked"));
    }
    private void createPayTableView() {
        moneyTableView.getColumns().remove(0, moneyTableView.getColumns().size());
        moneyTableView.getColumns().add(new TableColumn<>("Imię"));
        moneyTableView.getColumns().add(new TableColumn<>("Nazwisko"));
        moneyTableView.getColumns().add(new TableColumn<>("Identyfikator"));
        moneyTableView.getColumns().add(new TableColumn<>("Wynagrodzenie"));

        moneyTableView.getVisibleLeafColumn(0)
                .setCellValueFactory(new PropertyValueFactory<Driver, String>("firstName"));
        moneyTableView.getVisibleLeafColumn(1)
                .setCellValueFactory(new PropertyValueFactory<Driver, String>("lastName"));
        moneyTableView.getVisibleLeafColumn(2)
                .setCellValueFactory(new PropertyValueFactory<Driver, Integer>("id"));
        moneyTableView.getVisibleLeafColumn(3).setCellValueFactory
                ((Callback<TableColumn.CellDataFeatures<Driver, Integer>, SimpleDoubleProperty>)
                        c -> new SimpleDoubleProperty((c.getValue().getHours_worked() * c.getValue().getSalary())));

    }
    private void createScheduleTableView() {
        scheduleTableView.getVisibleLeafColumn(0)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow, Integer>("id"));
        scheduleTableView.getVisibleLeafColumn(1)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("pon"));
        scheduleTableView.getVisibleLeafColumn(2)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("wt"));
        scheduleTableView.getVisibleLeafColumn(3)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("sr"));
        scheduleTableView.getVisibleLeafColumn(4)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("czw"));
        scheduleTableView.getVisibleLeafColumn(5)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("pt"));
        scheduleTableView.getVisibleLeafColumn(6)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("sob"));
        scheduleTableView.getVisibleLeafColumn(7)
                .setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>("niedz"));

    }
}
