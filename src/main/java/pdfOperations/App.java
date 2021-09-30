package pdfOperations;

import pdfOperations.pdfOps.UI.UI;

public class App {
    public static void main(String[] args) {
        Main mainClass = new Main();
        mainClass.start();

        Thread ui = new Thread(new UI());
        ui.start();
        
    }
}
