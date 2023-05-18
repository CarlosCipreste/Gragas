module com.gragas.gragas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens com.gragas.gragas to javafx.fxml;
    exports com.gragas.gragas;
}