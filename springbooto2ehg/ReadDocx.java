import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadDocx {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("f:/javapro/bs/springbooto2ehg/需求.docx");
            XWPFDocument document = new XWPFDocument(fis);
            
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();
                if (!text.isEmpty()) {
                    System.out.println(text);
                }
            }
            
            document.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}