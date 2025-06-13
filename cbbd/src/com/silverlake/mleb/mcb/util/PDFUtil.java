package com.silverlake.mleb.mcb.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
public class PDFUtil
{


	public PDFUtil()
	{

	}


	/*
	public byte[] PDFUtilReceipt(ObMposEmailReceiptRequest payment)throws FileNotFoundException, DocumentException
	{

		System.out.println(PDFUtil.class.getClassLoader().getResource("").getPath());
		   
		Document document=new Document(PageSize.A6);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, bos );
		BaseFont bf = null;
		try {
			
			bf = BaseFont.createFont("vuArial.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		document.open();
		Font font = new Font(bf);
		font.setSize(8);
		font.setStyle(Font.BOLD);


		Font fontx = new Font(bf);
		fontx.setSize(7);
		fontx.setStyle(Font.NORMAL);

		Font footerFont = new Font(bf);
		footerFont.setSize(5);
		footerFont.setStyle(Font.NORMAL);

		Paragraph header1 = new Paragraph("Hong Leong Bank Cambodia Limited (HLBKH)",  font);
		Paragraph header2 = new Paragraph("NgÃ¢n hÃ ng Hong Leong Viá»‡t Nam (HLBVN)",  font);

		header1.setAlignment(Element.ALIGN_CENTER);
		header2.setAlignment(Element.ALIGN_CENTER);
		document.add(header1);
		document.add(header2);
		document.add(new Paragraph(" ",fontx));

		PdfPTable tablex=new PdfPTable(2);
		tablex.setWidthPercentage(100);
		tablex.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		float[] columnWidths = {1f, 1f};
		tablex.setWidths(columnWidths);
		tablex.addCell(new Paragraph("Merchant/Ä�áº¡i lÃ½",fontx));
		tablex.addCell(new Paragraph(": "+payment.getMerchantName(),fontx));
		tablex.addCell(new Paragraph("Merchant ID/MÃ£ Ä‘áº¡i lÃ½",fontx));
		tablex.addCell(new Paragraph(": "+payment.getMerchantID(),fontx));
		tablex.addCell(new Paragraph("Terminal ID/MÃ£ thiáº¿t bá»‹",fontx));
		tablex.addCell(new Paragraph(": "+payment.getTerminalID(),fontx));
		tablex.addCell(new Paragraph("Customer Name/TÃªn khÃ¡ch hÃ ng",fontx));
		tablex.addCell(new Paragraph(": "+payment.getAccountName(),fontx));

		String CURRENCY_DISPLAY_PRICE_FORMAT = "#,##0";
		NumberFormat pFormatter = new DecimalFormat(CURRENCY_DISPLAY_PRICE_FORMAT);
		if(null!=payment.getAccountNumber() && payment.getAccountNumber().length()>2)
		{
			tablex.addCell(new Paragraph("Account No./Sá»‘ tÃ i khoáº£n",fontx));
			tablex.addCell(new Paragraph(": ***"+payment.getAccountNumber().substring(payment.getAccountNumber().length()-7),fontx));
			
			tablex.addCell(new Paragraph("  ",fontx));
			tablex.addCell(new Paragraph(""+payment.getAccountDescription(),fontx));
			tablex.addCell(new Paragraph("  ",fontx));
			tablex.addCell(new Paragraph("  ",fontx));
		}
		document.add(tablex);

		Paragraph headers = new Paragraph("PEx+",font);
		headers.setAlignment(Element.ALIGN_CENTER);

		document.add(headers);
		document.add(new Paragraph(" ",fontx));
		
		PdfPTable tablex3=new PdfPTable(2);

		tablex3.setWidthPercentage(100);
		tablex3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		//tablex.getDefaultCell().setBorder(Rectangle.OUT_LEFT);
		//tablex.getDefaultCell().setBorder(Rectangle.OUT_TOP);
		float[] columnWidths3 = {1f, 1f};

		tablex3.setWidths(columnWidths3);
		tablex3.addCell(new Paragraph("Batch No./Sá»‘ phiÃªn giao dá»‹ch",fontx));
		tablex3.addCell(new Paragraph(": "+payment.getBatchID(),fontx));
		tablex3.addCell(new Paragraph("Trace No./Sá»‘ hiá»‡u tra cá»©u",fontx));
		tablex3.addCell(new Paragraph(": "+payment.getTraceID(),fontx));
		tablex3.addCell(new Paragraph("Date & Time/NgÃ y & Giá»�",fontx));
		tablex3.addCell(new Paragraph(": "+payment.getTransactionDate(),fontx));
		tablex3.addCell(new Paragraph("Ref. No./Sá»‘ tham chiáº¿u",fontx));
		tablex3.addCell(new Paragraph(": "+payment.getAck_no(),fontx));
		//        tablex3.addCell(new Paragraph("APP CODE",fontx));
		//        tablex3.addCell(new Paragraph(": "+payment.getApp_code(),fontx));
		tablex3.addCell(new Paragraph("Total/Tá»•ng cá»™ng",fontx));
		tablex3.addCell(new Paragraph(": VND "+pFormatter.format(Double.parseDouble(payment.getTotalAmount().replaceAll(",", ""))),fontx));

		document.add(tablex3);




		Paragraph footer1 = new Paragraph("I acknowledge receipt of goods and/or services in the amount of the Total shown hereon and agrees to perform the obligations set forth in the Connect by Hong Leong Bank Terms and Conditions.",footerFont);
		footer1.setAlignment(Element.ALIGN_CENTER);


		Paragraph footer2 = new Paragraph("TÃ´i cam káº¿t Ä‘Ã£ nháº­n Ä‘á»§ hÃ ng hÃ³a/dá»‹ch vá»¥ tÆ°Æ¡ng á»©ng vá»›i sá»‘ tiá»�n in trÃªn hÃ³a Ä‘Æ¡n vÃ  chá»‹u trÃ¡ch nhiá»‡m thanh toÃ¡n theo cÃ¡c Ä�iá»�u khoáº£n vÃ  Ä�iá»�u kiá»‡n cá»§a dá»‹ch vá»¥ Connect by Hong Leong Bank.",footerFont);
		footer2.setAlignment(Element.ALIGN_CENTER);


		Paragraph footer3 = new Paragraph("No signature required.",footerFont);
		Paragraph footer4 = new Paragraph("KhÃ´ng yÃªu cáº§u chá»¯ kÃ½.",footerFont);
		footer3.setAlignment(Element.ALIGN_CENTER);
		footer4.setAlignment(Element.ALIGN_CENTER);


		document.add(new Paragraph(" ",fontx));
		document.add(new Paragraph(" ",fontx));
		document.add(new Paragraph(" ",fontx));
		document.add(footer1);
		document.add(new Paragraph(" ",footerFont));
		document.add(footer2);
		document.add(new Paragraph(" ",footerFont));
		document.add(footer3);
		document.add(new Paragraph(" ",footerFont));
		document.add(footer4);
		document.close();


		return bos.toByteArray();



	}*/












	public byte[] PDFUtilSettlementReport(String[][] data)throws FileNotFoundException, DocumentException
	{
		Document document=new Document(PageSize.A4);
		// step 2

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, bos );

		// step 3
		document.open();
		// step 4

		Font font = new Font();
		font.setSize(9);
		font.setStyle(Font.BOLD);


		Font fontx = new Font();
		fontx.setSize(8);
		fontx.setStyle(Font.NORMAL);







		PdfPTable tablex=new PdfPTable(2);

		tablex.setWidthPercentage(60);
		//tablex.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		//tablex.getDefaultCell().setBorder(Rectangle.LEFT);
		//tablex.getDefaultCell().setBorder(Rectangle.OUT_TOP);
		float[] columnWidths = {1f, 1f};

		tablex.setWidths(columnWidths);


		PdfPCell cell = new PdfPCell(new Phrase("Terminal Settlement Report"));
		cell.setColspan(2);
		cell.setBorderWidth((float) 0.7);

		// cell.setBorderWidthBottom(1);
		tablex.addCell(cell);


		for(int i=0;i<12;i++)
		{

			//01
			PdfPCell cellx01 = new PdfPCell(new Phrase(data[i][0],fontx));
			cellx01.setBorder(6);
			cellx01.setBorderWidthLeft((float) 0.7);
			cellx01.setPaddingBottom(5);
			cellx01.setBorderWidthBottom((float) 0.7);
			if (i==11)
			{
				cellx01.setBorderColorBottom(BaseColor.BLACK);
			}
			else
			{
				cellx01.setBorderColorBottom(BaseColor.LIGHT_GRAY);

			}


			if(i==4 || i==5 || i== 7 || i==8 || i==10 || i==11 )
			{
				cellx01.setPaddingLeft(18);
			}
			else
			{
				cellx01.setPaddingLeft(10);
			}













			tablex.addCell(cellx01);
			PdfPCell celly01 = new PdfPCell(new Phrase(data[i][1],fontx));
			celly01.setHorizontalAlignment(Element.ALIGN_RIGHT);
			celly01.setBorder(10);
			celly01.setPaddingRight(10);
			celly01.setPaddingBottom(5);
			celly01.setBorderWidth((float) 0.7); 
			if (i==11)
			{
				celly01.setBorderColorBottom(BaseColor.BLACK);
			}
			else
			{
				celly01.setBorderColorBottom(BaseColor.LIGHT_GRAY);
			}


			celly01.setBorderWidthBottom((float) 0.7);
			tablex.addCell(celly01);


		}    














		document.add(tablex);
		document.close();


		return bos.toByteArray();



	}


//	public static void main(String args[]) throws FileNotFoundException, DocumentException
//	{
//		PDFUtil s = new PDFUtil();
//
//		ObMposEmailReceiptRequest payment = new ObMposEmailReceiptRequest();
//		payment.setMerchantName("Parkson");
//		payment.setTerminalID("M0000005");
//		payment.setMerchantID("00000026");
//		payment.setBatchID("0000001");
//		payment.setTraceID("00000025");
//		payment.setTransactionDate("2014-03-03 12:50:00");
//		payment.setAck_no("R-PEx+-20140402-002");
//		payment.setTotalAmount("1,000.00");
//		ObAccountBean account = new ObAccountBean();
//		payment.setAccountName("Test Account Holder");
//		payment.setAccountNumber("11122223334445");
//		payment.setAccountDescription("YÃªu cáº§u/giao dá»‹ch cá»§a QuÃ½ khÃ¡ch táº¡m thá»�i khÃ´ng thá»±c hiá»‡n Ä‘Æ°á»£c");
//		byte[] dataImage = s.PDFUtilReceipt(payment);
//		FileOutputStream xf = new FileOutputStream("D:/Report.pdf");
//
//		try {
//			xf.write(dataImage);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//	}

}

