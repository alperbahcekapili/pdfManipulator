package pdfOperations.pdfOps;




import java.io.File;
import org.apache.pdfbox.pdmodel.PDPage;
import java.io.IOException;
import java.util.Scanner;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.multipdf.PDFMergerUtility; 
import org.apache.pdfbox.io.MemoryUsageSetting;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class componentAdder {//junk class

    public static void doIt() throws IOException{
        File file = new File("C:\\Users\\Lenovo\\Pictures\\islevsel\\white.pdf");
        PDDocument doc = PDDocument.load(file);
        PDPage page = doc.getPage(0);
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\Lenovo\\Documents\\havelsanstaj\\kimlikon.PNG", doc);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page,true,true,true);
        contentStream.drawImage(pdImage, 70, 150);
        
        pdImage = PDImageXObject.createFromFile("C:\\Users\\Lenovo\\Documents\\havelsanstaj\\kimlikarka.PNG", doc);
        contentStream.drawImage(pdImage, 70, 500);
        contentStream.close();
        doc.save("C:\\Users\\Lenovo\\Documents\\havelsanstaj\\kimlikFotokopi.pdf");
        doc.close();
    }
}   