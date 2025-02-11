module cafebite.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens cafebite.demo to javafx.fxml;
    exports cafebite.demo;
    exports cafebite.demo.Order;
    opens cafebite.demo.Order to javafx.fxml;

}