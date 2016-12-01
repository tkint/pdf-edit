/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.view.displayer;

import app.Instance;
import java.io.IOException;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import src.controller.TableController;
import src.model.DocFile;

/**
 *
 * @author tkint
 */
public class ContextMenuDisplayer {

    private static final Instance INSTANCE = Instance.getInstance();

    public static void displayContextMenu(double posX, double posY) {
        if (INSTANCE.hasToolActive()) {
            switch (INSTANCE.getCurrentTool()) {
                case ADDTABLE:
                    displayTableMenu(posX, posY);
                    break;
            }
        }
    }

    private static void displayTableMenu(double posX, double posY) {
        ContextMenu contextMenu = setContextMenu(posX, posY);
        MenuItem validate = new MenuItem("Valider");
        validate.setOnAction((event) -> {
            try {
                DocFile docFile = INSTANCE.getDocFileOpened();
                PDDocument document = docFile.getDocument();
                PDPage page = docFile.getCurrentPage();
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

                docFile.getTempTable().setPosY(docFile.getHeight() - (float) posY);
                docFile.getTempTable().refreshPos();
                
                TableController tc = new TableController();
                tc.printTable(contentStream, docFile.getTempTable());

                contentStream.close();

                TabDisplayer.refreshOpenedTab();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        });

        MenuItem addColumn = new MenuItem("Ajouter colonne");
        addColumn.setOnAction((event) -> {

        });

        MenuItem addRow = new MenuItem("Ajouter ligne");
        addRow.setOnAction((event) -> {

        });

        MenuItem cancel = new MenuItem("Annuler");
        cancel.setOnAction((event) -> {
            TraceDisplayer.clearTrace();
        });

        contextMenu.getItems().addAll(validate, addColumn, addRow, cancel);
        contextMenu.show(INSTANCE.stage);
    }

    private static ContextMenu setContextMenu(double posX, double posY) {
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setAnchorX(posX);
        contextMenu.setAnchorY(posY + 60);

        return contextMenu;
    }
}
