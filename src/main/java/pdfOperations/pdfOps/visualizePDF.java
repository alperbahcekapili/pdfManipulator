package pdfOperations.pdfOps;

import java.io.File;
import java.util.List;
import java.awt.image.BufferedImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
public class visualizePDF {
    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
    
        return new ImageView(wr).getImage();
    }
    public static Image[] saveAsImage(File pdfFile) throws Exception{
        PDDocument document = PDDocument.load(pdfFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        Image[] images = new Image[document.getNumberOfPages()];
        for (int page = 0; page < document.getNumberOfPages(); ++page)
        { 
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

            // suffix in filename will be used as the file format
            ImageIOUtil.writeImage(bim, "denemeresmi" + "-" + (page+1) + ".png", 300);
            document.close();

            images[page] = convertToFxImage(bim);

            return  images;
            
        }
        
        
        return null;
    }
   
}