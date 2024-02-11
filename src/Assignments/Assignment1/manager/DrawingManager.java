package Assignments.Assignment1.manager;
import Assignments.Assignment1.datatable.*;
import java.util.Scanner;
import javax.swing.*;

public class DrawingManager {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        JFrame frame=new JFrame();

//        System.out.print("File Path: ");
//        String filePath = input.nextLine();
        String filePath = "./src/Assignments/Assignment1/data.txt";

//        System.out.print("Frame Width: ");
//        int frameWidth = input.nextInt();
        int frameWidth = 800;

//        System.out.print("Frame Length: ");
//        int frameLength = input.nextInt();
        int frameLength = 600;

        Table table = new Table(filePath, frameWidth, frameLength);

        if(!table.isDefault()){
            table.defineColors();
        }

        DrawingCanvas canvas=new DrawingCanvas(table);
        frame.add(canvas);
        frame.setSize(frameWidth,frameLength);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

