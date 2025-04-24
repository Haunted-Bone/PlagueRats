module edu.redwoods.cis18.plaguerats {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens edu.redwoods.cis18.plaguerats to javafx.fxml;
    exports edu.redwoods.cis18.plaguerats;
}