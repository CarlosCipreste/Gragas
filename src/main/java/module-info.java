module com.gragas.gragas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.controlsfx.controls;


    opens com.gragas.gragas to javafx.fxml;
    opens com.gragas.gragas.metodos to javafx.base;

    exports com.gragas.gragas;
}