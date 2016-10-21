/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.table;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author t.kint
 */
public class Row implements Cloneable {
    private int id;
    private int width;
    private int height;
    private List<Cell> cells = new ArrayList<Cell>();

    public Row(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
    
    /**
     * Ajoute une cellule
     * @param cell 
     */
    public void addCell(Cell cell) {
        this.cells.add(cell);
    }
    
    /**
     * Retourne la cellule cibl�e par l'id fourni
     * @param id 
     * @return Cell
     */
    public Cell getCellById(int id) {
        Cell cell = null;
        int i = 0;
        while (i < this.getCells().size() && cell == null) {
            if (this.cells.get(i).getId() == id) {
                cell = this.cells.get(i);
            }
            i++;
        }
        return cell;
    }
    
    /**
     * Retourne la derni�re cellule
     * @return 
     */
    public Cell getLastCell() {
        return this.cells.get(this.cells.size() - 1);
    }
    
    /**
     * Supprime la cellule cibl�e par l'id fourni
     * @param id 
     */
    public void removeCellById(int id) {
        int i = 0;
        boolean finished = false;
        while (i < this.getCells().size() && !finished) {
            if (this.cells.get(i).getId() == id) {
                this.cells.remove(i);
                finished = true;
            }
            i++;
        }
    }

    @Override
    public String toString() {
        String s = "Row{" + "id=" + id + ", cells={";
        
        for (Cell cell : cells) {
            s += cell.toString();
            if (cell.getId() != cells.get(cells.size() - 1).getId()) {
                s += ", ";
            }
        }
        
        s += "}}";
        return s;
    }
    
    public Row clone() {
        Row row = null;
        try {
            row = (Row) super.clone();
            List<Cell> cells = new ArrayList<Cell>(this.cells.size());
            for (Cell cell : this.cells) {
                cells.add(cell.clone());
            }
            row.cells = cells;
        } catch(CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return row;
    }
}
