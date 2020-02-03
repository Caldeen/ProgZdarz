package Start.Controllers;

import Start.Dao.DriversDao;
import Start.Dao.ScheduleDao;
import Start.Model.Driver;
import Start.Model.ScheduleRow;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerSchedule {
    private ControllerMain controllerMain;
    private Stage stage;
    private ScheduleDao scheduleDao = new ScheduleDao();
    @FXML
    Button UpdateButton;
    @FXML
    Text outputText;
    @FXML
    CheckBox ponCheckBox;
    @FXML
    CheckBox wtCheckBox;
    @FXML
    CheckBox srCheckBox;
    @FXML
    CheckBox czwCheckBox;
    @FXML
    CheckBox ptCheckBox;
    @FXML
    CheckBox sobCheckBox;
    @FXML
    CheckBox niedzCheckBox;
    @FXML
    Text driverText;
    @FXML
    TextField idTextField;

    @FXML
    TextField idWierszaTextField;
    DriversDao driversDao = new DriversDao();
    ObservableList<ScheduleRow> scheduleRows;
    ObservableList<Driver> drivers;
    ScheduleRow scheduleRow;
    Driver driver;
    int scheduleId;
    int id;

    public void initialize() {
        Task<ObservableList<ScheduleRow>> task = new Task<ObservableList<ScheduleRow>>() {
            @Override
            protected ObservableList<ScheduleRow> call() throws Exception {
                return new ScheduleDao().getAll();
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                scheduleRows = task.getValue();
            }
        });
        Task<ObservableList<Driver>> task1 = new Task<ObservableList<Driver>>() {
            @Override
            protected ObservableList<Driver> call() throws Exception {
                return new DriversDao().getAll();
            }
        };
        task1.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                drivers=task1.getValue();
            }
        });
        new Thread(task).start();
        new Thread(task1).start();
    }

    public void updateButtonAction() {

        String text = driverText.getText();
        if (ponCheckBox.isSelected())
            scheduleRow.setPon(text);
        if (wtCheckBox.isSelected())
            scheduleRow.setWt(text);
        if (srCheckBox.isSelected())
            scheduleRow.setSr(text);
        if (czwCheckBox.isSelected())
            scheduleRow.setCzw(text);
        if (ptCheckBox.isSelected())
            scheduleRow.setPt(text);
        if (sobCheckBox.isSelected())
            scheduleRow.setSob(text);
        if (niedzCheckBox.isSelected())
            scheduleRow.setNiedz(text);

        Task<ObservableList<ScheduleRow>> task=new Task<ObservableList<ScheduleRow>>() {
            @Override
            protected void succeeded() {
                super.succeeded();
                controllerMain.showDrivers();
                controllerMain.showPay();
                controllerMain.showSchedule();
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
            protected ObservableList<ScheduleRow> call() throws Exception {
                new ScheduleDao().update(scheduleRow,null);
                return null;
            }
        };
        controllerMain.statusText.textProperty().bind(task.messageProperty());
        new Thread(task).start();
        stage.close();
    }

    public void getDriverScheduleRowSet() {
        getScheduleRow();
        getDriver();
        if(driver!=null){
            driverText.setText(driver.getFirstName() + " " + driver.getLastName());
            driverText.setVisible(true);
            if(scheduleRow!=null){
                outputText.setVisible(false);
                UpdateButton.setVisible(true);
            }
        }
    }
    public void getScheduleRow() {
        if (!idWierszaTextField.getText().equals("")) {
            try {
                scheduleId = Integer.parseInt(idWierszaTextField.getText());
            } catch (NumberFormatException e) {
                outputText.setVisible(true);
                outputText.setText("Podaj poprawny id harmonogramu");
                outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                return;
            }
            ScheduleRow scheduleRow = null;
            if (scheduleRows.size() != 0) {
                for (ScheduleRow scheduleRow1 : scheduleRows) {
                    if (scheduleRow1.getId() == scheduleId){
                        scheduleRow = scheduleRow1;
                        this.scheduleRow=scheduleRow;
                    }
                }
                if (scheduleRow == null) {
                    outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    outputText.setText("Wypelnij poprawnymi danymi");
                    outputText.setVisible(true);

                }
            }
        }
    }

    public void getDriver() {
        if (!idTextField.getText().equals("")) {
            try {
                id = Integer.parseInt(idTextField.getText());
            } catch (NumberFormatException e) {
                outputText.setVisible(true);
                outputText.setText("Podaj poprawny id");
                outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                return;
            }
            Driver tempDriver = null;
            if (drivers.size() != 0) {
                for (Driver temp : drivers) {
                    if (temp.getId() == id){
                        tempDriver = temp;
                        driver=tempDriver;
                    }
                }
                if (tempDriver == null) {
                    outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    outputText.setText("Wypelnij poprawnymi danymi");
                    outputText.setVisible(true);
                }
            }
        }
    }

    public void setControllerMain(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
