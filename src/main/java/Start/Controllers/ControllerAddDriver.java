package Start.Controllers;

import Start.Dao.DriversDao;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerAddDriver {
    private ControllerMain controllerMain;
    private Stage stage;

    @FXML
    Text outputText;
    @FXML
    TextField nameTextField;
    @FXML
    TextField surnameTextField;
    @FXML
    TextField salaryTextField;

    public void addButtonAction() {
        Task<Void> task=new Task<Void>() {
            @Override
            protected void succeeded() {
                super.succeeded();
                controllerMain.showDrivers();
                controllerMain.showPay();
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
            protected Void call() throws InterruptedException {
                DriversDao driversDao = new DriversDao();
                int id = driversDao.getMax()+1;
                Thread.sleep(3000);
                driversDao.add(id,nameTextField.getText(),
                        surnameTextField.getText(),Double.parseDouble(salaryTextField.getText()));
                return null;
            }
        };
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
