package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPujasTable {

    private TableLayout tableLayout;
    private Context context;
    private String[] header;
    private ArrayList<String[]> data;
    private TableRow row;
    private TextView txtCell;
    private int indexC;
    private int indexR;
    private boolean multicolor = false;
    private int firstColor, secondColor;

    public AdapterPujasTable(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addHeader(String[] header){
        this.header = header;
        createHeader();
    }

    public void addData(ArrayList<String[]> data){
        this.data = data;
        createDataTable();
    }

    private void newRow(){
        row = new TableRow(context);
        row.setWeightSum(10);
    }

    private void newCell(){
        txtCell = new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(25);
    }

    private void createHeader(){
        indexC=0;
        newRow();
        while (indexC<header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            row.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(row);
    }

    private void createDataTable(){
        String info;
        //for(indexR=1;indexR<=header.length;indexR++){
        for(indexR=1;indexR<=data.size();indexR++){
            newRow();
            for(indexC=0;indexC<header.length;indexC++){
                newCell();
                String[] columns=data.get(indexR-1);
                info=(indexC<columns.length)?columns[indexC]:"";
                txtCell.setText(info);
                row.addView(txtCell, newTableRowParams());
            }
            tableLayout.addView(row);
        }
    }

    public void backGroundHeader(int color){
        indexC=0;
        while (indexC<header.length){
            txtCell=getCell(0,indexC++);
            txtCell.setBackgroundColor(color);
        }
    }

    public void backGroundData(int firstColor, int secondColor){
        for(indexR=1;indexR<=header.length;indexR++){
            multicolor=!multicolor;
            for(indexC=0;indexC<header.length;indexC++){
                txtCell=getCell(indexR,indexC);
                txtCell.setBackgroundColor(multicolor?firstColor:secondColor);
            }
            tableLayout.addView(row);
        }
        this.firstColor=firstColor;
        this.secondColor=secondColor;
    }

    public void reColoring(){
        indexC=0;
        multicolor=!multicolor;
        while (indexC<header.length){
            txtCell=getCell(data.size()-1,indexC++);
            txtCell.setBackgroundColor(multicolor?firstColor:secondColor);
        }
        tableLayout.addView(row);
    }

    private TableRow getRow(int index){
        return (TableRow) tableLayout.getChildAt(index);
    }

    private TextView getCell(int rowIndex, int columnIndex){
        row=getRow(rowIndex);
        return (TextView) row.getChildAt(columnIndex);
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }
}
