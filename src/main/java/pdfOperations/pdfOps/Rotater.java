package pdfOperations;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.lang.model.util.ElementScanner14;
import javax.print.attribute.standard.NumberUp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.multipdf.PDFMergerUtility; 
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import java.awt.Rectangle;
public class Rotater extends pdfOps {
    public static  void Rotate(File f, int degree) throws IllegalArgumentException, IOException{//clockwise
        degree= degree%360;
        if(degree < 0)
            throw new IllegalArgumentException("wrong degree");
        PDDocument document = PDDocument.load(f);
        PDPageTree pageTree = document.getDocumentCatalog().getPages();
        int fileSize = pageTree.getCount();
        PDDocument newFile = new PDDocument();
        for(int i = 0;i<fileSize;i++){
            PDPage page = pageTree.get(i);
            newFile.addPage(page);
            PDPageContentStream cs = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.PREPEND, false, false);
            Matrix matrix = Matrix.getRotateInstance(Math.toRadians(degree), 0, 0);
            cs.transform(matrix);
            cs.close();

            PDRectangle cropBox = page.getCropBox();
            Rectangle rectangle = cropBox.transform(matrix).getBounds();
            PDRectangle newBox = new PDRectangle((float)rectangle.getX(), (float)rectangle.getY(), (float)rectangle.getWidth(), (float)rectangle.getHeight());
            page.setCropBox(newBox);
            page.setMediaBox(newBox);
        }

        newFile.save(f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf("\\")) + "\\"+
        f.getName().substring(0,f.getName().length()-4) + 
        "_rotated.pdf" );
    }
}
