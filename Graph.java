import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Graph extends Application {
    private final int[] nodeCounts = {10, 50, 100, 500, 1000};
    private final long[] executionTimes = {200, 800, 2000, 10000, 25000}; // Виміряні часи

    @Override
    public void start(Stage stage) {
        stage.setTitle("BFT Protocol Execution Time vs. Number of Nodes");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Nodes");
        yAxis.setLabel("Execution Time (ms)");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("BFT Protocol Execution Time");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Execution Time");

        for (int i = 0; i < nodeCounts.length; i++) {
            series.getData().add(new XYChart.Data<>(nodeCounts[i], executionTimes[i]));
        }

        lineChart.getData().add(series);
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
