module com.example.currencychartfxmaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.desktop;
    requires java.sql;
    requires com.google.gson;
    requires mongo.java.driver;
    requires org.slf4j;
    requires net.sf.jasperreports.core;

    opens com.example.currencychartfxmaven to javafx.fxml;
    exports com.example.currencychartfxmaven;
}