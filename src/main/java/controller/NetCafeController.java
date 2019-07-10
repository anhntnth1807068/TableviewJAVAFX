package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.NetCafe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class NetCafeController implements Initializable {

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtavatar;

    @FXML
    private JFXTextField txtremainTime;

    @FXML
    private TableView<NetCafe> tableView;

    @FXML
    private TableColumn<NetCafe, String> columnUserName;

    @FXML
    private TableColumn<NetCafe, ImageView> columnavatar;

    @FXML
    private TableColumn<NetCafe, Integer> columnremainTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tableView.setRowFactory(new Callback<TableView<NetCafe>, TableRow<NetCafe>>() {
            @Override
            public TableRow<NetCafe> call(TableView<NetCafe> param) {
                final TableRow<NetCafe> netCafeTableRow = new TableRow<NetCafe>();
                netCafeTableRow.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (!netCafeTableRow.isEmpty()) {
                            NetCafe netCafe = netCafeTableRow.getItem();
                            txtUserName.setText(netCafe.getUserName());
                            txtavatar.setText(netCafe.getAvatar());
                            txtremainTime.setText(netCafe.getRemainTime());
                        }
                    }
                });
                return netCafeTableRow;
            }
        });

        this.columnUserName.setCellValueFactory(new PropertyValueFactory<NetCafe, String>("userName"));
        this.columnavatar.setCellValueFactory(new PropertyValueFactory<NetCafe, ImageView>("avatar"));
        this.columnremainTime.setCellValueFactory(new PropertyValueFactory<NetCafe, Integer>("remainTime"));
        this.tableView.setItems(loadNetCafe());
    }

    private ObservableList<NetCafe> loadNetCafe() {
        ObservableList<NetCafe> netCafes = FXCollections.observableArrayList();
        netCafes.add(new NetCafe());
        netCafes.add(new NetCafe());
        netCafes.add(new NetCafe());
        return netCafes;
    }

    @FXML
    void saveNetCafe(ActionEvent event) {
        NetCafe netCafe = new NetCafe(txtUserName.getText(), txtavatar.getText(), txtremainTime.getText());
        for (int i = 0; i < this.tableView.getItems().size(); i++) {
            NetCafe netCafe1 = this.tableView.getItems().get(i);
            if (netCafe1.getUserName().equals(netCafe.getUserName())) {
                this.tableView.getItems().remove(i);
            }
        }
        tableView.getItems().add(netCafe);
    }

    @FXML
    void doDelete(ActionEvent event) {
        NetCafe netCafe = this.tableView.getSelectionModel().getSelectedItem();
        this.tableView.getItems().remove(netCafe);
    }
}
