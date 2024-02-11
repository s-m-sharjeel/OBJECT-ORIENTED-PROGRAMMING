package Assignments.Assignment1.datatable;

import java.awt.*;

public class DrawingCanvas extends Canvas {

    Table table;

    public DrawingCanvas(Table table){
        this.table = table;
    }

    public void paint(Graphics g) {

        if(table.isDefault()){
            table.paintDefault(g);
            return;
        }

        table.paint(g);

    }
}