package Assignments.Assignment1.datatable;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Table {

    private int rows;
    private int columns;
    private TitleBar titleBar;
    private Cell[][] cells;
    private final int frameWidth;
    private final int frameLength;
    private final int boundary;
    private final String filePath;
    private boolean defaultTable;

    public Table(String filePath, int frameWidth, int frameLength) {
        this.filePath = filePath;
        this.frameWidth = frameWidth;
        this.frameLength = frameLength;

        if (frameLength > frameWidth)
            this.boundary = frameWidth / 10;
        else this.boundary = frameLength / 10;

        createTable();

    }

    /**
     * creates a table
     */
    private void createTable(){

        String rawData = "";
        String title = "";
        defaultTable = false;

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File does not exist!");
            defaultTable = true;
            createDefaultTable();
            return;
        }

        try {

            Scanner input = new Scanner(file);

            if(!input.hasNext()){
                defaultTable = true;
                createDefaultTable();
                return;
            }

            title += input.nextLine();

            if(!input.hasNext()){
                System.out.println("The file contains only the title!");
                defaultTable = true;
                createDefaultTable();
                titleBar.text = title;
                return;
            }

            rows = 0;
            columns = 0;
            String rowData = "";
            while (input.hasNext()) {
                rowData = input.nextLine();
                String[] rowElements = rowData.split(",");
                if(rowElements.length>columns){
                    columns = rowElements.length;
                }
                rows++;
            }
            input.close();

            input = new Scanner(file);
            input.nextLine();

            while(input.hasNext()){

                rowData = input.nextLine();
                String[] rowElements = rowData.split(",");
                if(rowElements.length<columns){
                    for (int i = 0; i < (columns - rowElements.length); i++)
                        rowData += ",-";
                }
                rawData += rowData + '\n';
            }
            input.close();

        } catch (Exception e) {
            System.out.println("No file found!");
            e.printStackTrace();

        }

        String[][] data = createDataArray(rawData);

        int tableWidth = frameWidth - boundary * 2 - 8;
        int tableLength = frameLength - boundary * 2 - 8;

        int width = tableWidth / (columns);
        int height = tableLength / (rows + 2);

        tableWidth = width * (columns);   //multiplying again to remove int error
        tableLength = height * (rows + 2);  //multiplying again to remove int error

        this.titleBar = new TitleBar(boundary, boundary, tableWidth, height, Color.BLACK, Color.GRAY, Color.WHITE, 4, title);

        this.cells = new Cell[rows][columns];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (j == 0) {
                    cells[j][i] = new Cell(boundary + (width * i), boundary + height, width, height, Color.lightGray, Color.BLUE, Color.BLACK, 4, data[j][i]);
                } else {
                    cells[j][i] = new Cell(boundary + (width * i), boundary + height + (height * j), width, height, Color.gray, Color.BLUE, Color.BLACK, 4, data[j][i]);
                }
            }
        }

    }

    /**
     * creates a 2D array of data separated by commas
     * @param data
     * @return
     */
    private String[][] createDataArray(String data) {

        String[] allData = data.split("[,\n]");

        String[][] finalData = new String[rows][columns];

        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                finalData[i][j] = allData[k];
                k++;
            }
        }

        return finalData;

    }

    /**
     * sets the font to the smallest in the 2D cell array
     * @param g
     */
    private void setToSmallestFontSize(Graphics g) {

        int smallestFontSize = cells[0][0].calculateFontSize(g);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].calculateFontSize(g) < smallestFontSize)
                    smallestFontSize = cells[i][j].calculateFontSize(g);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].fontSize = smallestFontSize;
            }
        }

    }

    /**
     * generates a default table.
     */
    private void createDefaultTable() {

        rows = 15;
        columns = 5;

        int tableWidth = frameWidth - boundary * 2 - 8;
        int tableLength = frameLength - boundary * 2 - 8;

        int width = tableWidth / (columns);
        int height = tableLength / (rows + 2);

        tableWidth = width * (columns);   //multiplying again to remove int error
        tableLength = height * (rows + 1);  //multiplying again to remove int error

        this.titleBar = new TitleBar(boundary, boundary, tableWidth, height, Color.BLACK, Color.GRAY, Color.WHITE, 4, "Data Table");

        this.cells = new Cell[rows][columns];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (j == 0) {
                    cells[j][i] = new Cell(boundary + (width * i), boundary + height, width, height, Color.lightGray, Color.BLUE, Color.BLACK, 4, "Data");
                } else {
                    cells[j][i] = new Cell(boundary + (width * i), boundary + height + (height * j), width, height, Color.GRAY, Color.BLUE, Color.BLACK, 4, "default");
                }
            }
        }

    }

    /**
     * paints a table with cells and a titleBar.
     * @param g
     */
     void paint(Graphics g) {

        titleBar.paint(g);

        setToSmallestFontSize(g);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(i==0)
                    cells[i][j].paintHighlighted(g);
                else cells[i][j].paintNormal(g);
            }
        }

    }

    void paintDefault(Graphics g){

        titleBar.paint(g);

        setToSmallestFontSize(g);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].paint(g);
            }
        }

    }

    /**
     * sets the color of cells except the first row
     * @param color
     */
    private void setCellColor(Color color){

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].cell_color = color;
            }
        }

    }

    /**
     * sets the color of the cells in the first row
     * @param color
     */
    private void setHighlightedCellColor(Color color){
        for (int i = 0; i < columns; i++) {
            cells[0][i].cell_color = color;
        }
    }

    /**
     * sets the color of the border of cells
     * @param color
     */
    private void setCellBorder(Color color){

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].stroke_color = color;
            }
        }

    }

    /**
     * sets the color of the text in the cells
     * @param color
     */
    private void setCellFontColor(Color color){

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].fontColor = color;
            }
        }

    }

    /**
     * sets the color of the titleBar
     * @param color
     */
    private void setTitleBarColor(Color color) {
        titleBar.cell_color = color;
    }

    /**
     * sets the color of the border of the titleBar
     * @param color
     */
    private void setTitleBarBorder(Color color) {
        titleBar.stroke_color = color;
    }

    /**
     * sets the color of the text of the titleBar
     * @param color
     */
    private void setTitleBarFontColor(Color color) {
        titleBar.fontColor = color;
    }

    /**
     * defines all the colors of all the elements
     */
    public void defineColors(){
        System.out.println("Pick the colors for the following elements: ");
        Color color;
        System.out.print("Cells: ");
        color = JColorChooser.showDialog(null, "Cells:", Color.GRAY);
        System.out.println(color.toString());
        setCellColor(color);
        System.out.print("Highlighted Cells: ");
        color = JColorChooser.showDialog(null, "Highlighted Cells:", Color.lightGray);
        System.out.println(color.toString());
        setHighlightedCellColor(color);
        System.out.print("Border of the Cells: ");
        color = JColorChooser.showDialog(null, "Border of the Cells:", Color.BLUE);
        System.out.println(color.toString());
        setCellBorder(color);
        System.out.print("Text: ");
        color = JColorChooser.showDialog(null, "Text:", Color.BLACK);
        System.out.println(color.toString());
        setCellFontColor(color);
        System.out.print("Title Bar: ");
        color = JColorChooser.showDialog(null, "Title Bar:", Color.BLACK);
        System.out.println(color.toString());
        setTitleBarColor(color);
        System.out.print("Border of the Title Bar: ");
        color = JColorChooser.showDialog(null, "Border of the Title Bar:", Color.GRAY);
        System.out.println(color.toString());
        setTitleBarBorder(color);
        System.out.print("Title: ");
        color = JColorChooser.showDialog(null, "Title:", Color.WHITE);
        System.out.println(color.toString());
        setTitleBarFontColor(color);
    }

    /**
     * returns true if default table has to be displayed
     * @return
     */
    public boolean isDefault(){
        return defaultTable;
    }

}
