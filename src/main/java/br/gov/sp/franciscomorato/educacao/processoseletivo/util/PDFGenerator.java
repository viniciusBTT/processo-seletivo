
package br.gov.sp.franciscomorato.educacao.processoseletivo.util;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Subscription;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author thiago
 */
@Component
public class PDFGenerator 
{
    @Value("${pdf.dir}")
    private String pdfDir;
    
    @Value("${pdf.header}")
    private String pdfHeader;
    
    private static final Font COURIER = new Font(
                                        Font.FontFamily.COURIER, 
                                        12, 
                                        Font.BOLD);
    
    
    public ByteArrayInputStream generatePdfReport(Subscription subscription) throws IOException 
    {
        Document document = new Document();
        
        try 
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            
            document.open();
            
            addLogo(document);
            addDocTitle(document, subscription, "INFORMAÇÕES PESSOAIS");
           
            PdfContentByte cb = writer.getDirectContent();
            
            PdfContentByte subsContent = writer.getDirectContent();
           
            subsContent.roundRectangle(480, 702, 90, 35, 5); // coordenadas (x, y, largura, altura)
            subsContent.stroke();
            subsContent.beginText();
            BaseFont bfb = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED);
            subsContent.setFontAndSize(bfb,14);
            subsContent.setTextMatrix(485, 715); // coordenadas (x, y)
            subsContent.showText("#" + subscription.getId());
            subsContent.endText();
            
            // Desenhe um retângulo
            cb.rectangle(20, 570, 565, 100); // coordenadas (x, y, largura, altura)
            cb.stroke();

            // Adicione campos de texto dentro do retângulo
            BaseFont bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED);

            cb.beginText();
            cb.setFontAndSize(bf, 8);
            cb.setTextMatrix(40, 650); // coordenadas (x, y)
            cb.showText("Nome: " + subscription.getCandidate().getName());

            
            cb.setTextMatrix(40, 620); // coordenadas (x, y)
            cb.showText("RG: " + subscription.getCandidate().getRg());
            cb.setTextMatrix(350, 620); // coordenadas (x, y)
            cb.showText("CPF: " + subscription.getCandidate().getCpf());
            cb.setTextMatrix(40, 590); // coordenadas (x, y)
            cb.showText("Data de Nascimento: " + subscription.getCandidate().getBirthDate());
            
            cb.setTextMatrix(350, 590); // coordenadas (x, y)
            cb.showText("Gênero: " + subscription.getCandidate().getGender());
            
            cb.endText();
            
            /*** ADDRESS ***/
            cb.rectangle(20, 510, 565, 50); // coordenadas (x, y, largura, altura)
            cb.stroke();
            
            cb.setTextMatrix(40, 540); // coordenadas (x, y)
            cb.showText("CEP: " + subscription.getCandidate().getRg());
            cb.setTextMatrix(250, 540); // coordenadas (x, y)
            cb.showText("Logradouro: " + subscription.getCandidate().getStreet().getLogradouro() + ", n.º " + subscription.getCandidate().getAddressNumber());
            cb.setTextMatrix(40, 520); // coordenadas (x, y)
            cb.showText("Bairro: " + "Recanto Feliz");
            cb.setTextMatrix(300, 520); // coordenadas (x, y)
            cb.showText("Cidade: " + "Francisco Morato");
            
            /*** CONTACT ***/
            cb.rectangle(20, 450, 565, 50); // coordenadas (x, y, largura, altura)
            cb.stroke();
            
            cb.setTextMatrix(40, 480); // coordenadas (x, y)
            cb.showText("Telefone: " + subscription.getCandidate().getPhone());
            cb.setTextMatrix(40, 460); // coordenadas (x, y)
            cb.showText("E-mail: " + subscription.getCandidate().getEmail());
            
            cb.setTextMatrix(25, 420); // coordenadas (x, y)
            cb.showText("Condição especial para a realização da prova: " 
                    + (subscription.getCandidate().getDiseaseInducedDeficiency() == null ?  "não" : subscription.getCandidate().getDiseaseInducedDeficiency()));
         
            
            cb.setTextMatrix(275, 390); // coordenadas (x, y)
            cb.showText("INSCRIÇÃO");
            
            cb.rectangle(20, 255, 565, 130); // coordenadas (x, y, largura, altura)
            cb.stroke();
            
            cb.setTextMatrix(25, 370); // coordenadas (x, y)
            cb.showText("O Candidato abaixo assinado DECLARA, sob as penas da lei que:");
            
            cb.setTextMatrix(30, 340); // coordenadas (x, y)
            cb.showText("1) Os dados informados neste formulário são verdadeiros;");
            
            cb.setTextMatrix(30, 329); // coordenadas (x, y)
            cb.showText("2) Está ciente de que não tomará posse do cargo se não comprovar os requisitos legais para cada cargo;");


            cb.setTextMatrix(30, 318); // coordenadas (x, y)
            cb.showText("3) Está ciente de que a inexatidão ou irregularidade destas afirmativas, ainda que verificadas posteriormente, ");
            cb.setTextMatrix(30, 308); // coordenadas (x, y)
            cb.showText(" acarretará sua eliminação do processo seletivo, com a anulação de todos os atos praticados, sem prejuízo das");
            cb.setTextMatrix(30, 298); // coordenadas (x, y)
            cb.showText(" demais medidas de ordem administrativa, civil ou criminal.");
            
            
            cb.setTextMatrix(30, 265); // coordenadas (x, y)
            cb.showText("Francisco Morato, ____/____/" + Year.now().getValue());
            cb.setTextMatrix(260, 265); // coordenadas (x, y)
            cb.showText("________________________________________________________________");
            
            
            subsContent.setLineDash(3f, 3f);

            // Desenhe a linha
            subsContent.moveTo(20, 240); // coordenadas de início (x, y)
            subsContent.lineTo(585, 240); // coordenadas de fim (x, y)
            subsContent.stroke();
            
            /*** SUBSCRIPTION ***/
            cb.rectangle(20, 60, 565, 160); // coordenadas (x, y, largura, altura)
            cb.stroke();
            
            
            cb.setTextMatrix(30, 190); // coordenadas (x, y)
            cb.showText("N.º DE INSCRIÇÃO: " + subscription.getId());
            
            cb.setTextMatrix(30, 170); // coordenadas (x, y)
            cb.showText("Nome: " + subscription.getCandidate().getName());
            
            cb.setTextMatrix(30, 160); // coordenadas (x, y)
            cb.showText("RG: " + subscription.getCandidate().getRg());
            
            cb.setTextMatrix(150, 160); // coordenadas (x, y)
            cb.showText("Data de Nascimento: " + subscription.getCandidate().getBirthDate());
            
            cb.setTextMatrix(30, 150); // coordenadas (x, y)
            cb.showText("Inscrito em: " + subscription.modalitiesToString());
            
            cb.setTextMatrix(30, 110); // coordenadas (x, y)
            cb.showText("Condição especial para a realização da prova: " 
                    + (subscription.getCandidate().getDiseaseInducedDeficiency() == null ?  "não" : subscription.getCandidate().getDiseaseInducedDeficiency()));
            
            
            cb.setTextMatrix(30, 80); // coordenadas (x, y)
            cb.showText("___________________________________________________");
            cb.setTextMatrix(100, 72); // coordenadas (x, y)
            cb.showText("Responsável pela inscrição");
            
            cb.setTextMatrix(325, 80); // coordenadas (x, y)
            cb.showText("___________________________________________________");
            cb.setTextMatrix(422, 72); // coordenadas (x, y)
            cb.showText("Candidato");
            
            document.close();
            System.out.println("-------------     PDF gerado      --------------");
            
            return new ByteArrayInputStream(outputStream.toByteArray());
            
        } 
        catch (DocumentException e)
        {
            System.out.println("Erro ao gerar PDF: " + e.getMessage());
            return null;
        }
        
        
    }
    
    
    /**
     * adiciona logo ao documento
     * necessário definir o caminho estático no app.properties
     * @param document 
     */
    private void addLogo(Document document)
    {
        try 
        {
            Image image = Image.getInstance(pdfHeader);
            
            image.scalePercent(20,20);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);
            
        }
        catch (DocumentException | IOException e) 
        {
            System.out.println("Erro ao adicionar cabeçalho: " + e.getMessage());
        }
    }
    
    /**
     * adiciona título ao documento
     * @param document
     * @throws DocumentException 
     */
    private void addDocTitle(Document document, Subscription subscription, String headerText) throws DocumentException
    {
        Paragraph paragraph = new Paragraph();
        leaveEmptyLine(paragraph, 1);
        
        Paragraph title = new Paragraph(headerText, COURIER) ;
        title.setAlignment(Element.ALIGN_CENTER);
        
        paragraph.add(title);
        
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
    }
    
    
    private void createTable(Document document, int noOfColumns, List<String> columnsNames, Subscription subscription) throws DocumentException
    {
        Paragraph paragraph = new Paragraph("Informações");
        
        leaveEmptyLine(paragraph, 3);
        
        document.add(paragraph);
        
        PdfPTable table = new PdfPTable(noOfColumns);
        
        for(int i = 0; i < noOfColumns; i++)
        {
            PdfPCell cell = new PdfPCell(new Phrase(columnsNames.get(i)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            table.addCell(cell);
        }
        
        table.setHeaderRows(1);
        getTableData(table, subscription);
        document.add(table);
        
    }
    
    private void getTableData(PdfPTable table, Subscription subscription)
    {
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        table.addCell(subscription.getCandidate().getName());
        table.addCell(subscription.getCandidate().getRg());
        
    }
    
    
    /**
     * 
     * @param paragraph
     * @param number 
     */
    private static void leaveEmptyLine(Paragraph paragraph, int number)
    {
        for(int i = 0; i < number; i++)
        {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    
    /**
     * 
     * @return 
     */
    private String getPdfNameWithDate() 
    {
        String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
        return pdfDir+"test-pdf"+"-"+localDateString+".pdf";
    }
    
}
