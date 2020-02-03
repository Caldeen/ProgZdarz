package Start.Controllers;

import Start.Model.Driver;
import Start.Dao.DriversDao;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Iterator;

public class ControllerDelDriver {
    private ControllerMain controllerMain;
    private Stage stage;

    @FXML
    Button DelButton;
    @FXML
    Text outputText;
    @FXML
    TextField nameTextField;
    @FXML
    TextField surnameTextField;
    @FXML
    TextField salaryTextField;
    @FXML
    TextField idTextField;
    @FXML
    ProgressIndicator delProgress;
    ObservableList<Driver> drivers;
    int id;
    private DriversDao driversDao = new DriversDao();
    public void initialize(){
        Task<ObservableList<Driver>> task= new Task<ObservableList<Driver>>(){
            @Override
            protected ObservableList<Driver> call() throws Exception {
                DriversDao driversDao=new DriversDao();
                return  driversDao.getAll();
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                drivers=task.getValue();
            }
        });
        new Thread(task).start();
    }
    public void getDriver(){
        if(!idTextField.getText().equals("")) {
            try {
                id = Integer.parseInt(idTextField.getText());
            }catch (NumberFormatException e){
                outputText.setVisible(true);
                outputText.setText("Podaj poprawny id");
                outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                return;
            }
            Driver tempDriver = null;
            if (drivers.size() != 0) {
                for (Driver temp : drivers) {
                    if (temp.getId() == id)
                        tempDriver = temp;
                }
                if (tempDriver != null) {
                    outputText.setVisible(false);
                    nameTextField.setText(tempDriver.getFirstName());
                    nameTextField.setVisible(true);

                    surnameTextField.setText(tempDriver.getLastName());
                    surnameTextField.setVisible(true);

                    salaryTextField.setText(String.valueOf(tempDriver.getSalary()));
                    salaryTextField.setVisible(true);
                    DelButton.setVisible(true);
                } else {
                    nameTextField.setVisible(false);
                    surnameTextField.setVisible(false);
                    salaryTextField.setVisible(false);
                    outputText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                    outputText.setText("Wypelnij poprawnymi danymi");
                    outputText.setVisible(true);
                }
            }else{
                nameTextField.setVisible(false);
                surnameTextField.setVisible(false);
                salaryTextField.setVisible(false);
            }
        }
    }
    public void delButtonAction() {
        Task<Void> task=new Task<Void>() {
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
                new DriversDao().del(id);
                return null;
            }
        };
        task.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                delProgress.setVisible(true);
            }
        });
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                controllerMain.showDrivers();
                controllerMain.showPay();
            }
        });
        controllerMain.statusText.textProperty().bind(task.messageProperty());
        new Thread(task).start();
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setControllerMain(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
    }
}
