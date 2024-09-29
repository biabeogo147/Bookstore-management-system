module vn.hust.bookstore {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires fontawesomefx;
    requires jakarta.persistence;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens vn.hust.bookstore.controller to javafx.fxml;
    opens vn.hust.bookstore.entity to org.hibernate.orm.core;

    exports vn.hust.bookstore;
    exports vn.hust.bookstore.entity;
    exports vn.hust.bookstore.controller;
}