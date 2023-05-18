module com.gragas.gragas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.gragas.gragas to javafx.fxml;
    exports com.gragas.gragas;
}