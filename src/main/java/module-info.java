module org.example.lr7_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens org.shop to javafx.fxml;
    exports org.shop;
}