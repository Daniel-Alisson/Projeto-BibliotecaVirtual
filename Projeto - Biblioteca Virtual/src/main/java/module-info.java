module com.sistema.projetobibliotecavirtual {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.sistema.projetobibliotecavirtual to javafx.fxml;
    exports com.sistema.projetobibliotecavirtual;
    exports com.sistema.projetobibliotecavirtual.models;
    opens com.sistema.projetobibliotecavirtual.models to javafx.fxml;
    exports com.sistema.projetobibliotecavirtual.controllers;
    opens com.sistema.projetobibliotecavirtual.controllers to javafx.fxml;
}