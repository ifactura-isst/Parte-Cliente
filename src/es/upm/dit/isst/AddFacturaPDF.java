package es.upm.dit.isst;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.dao.FacturasDAO;
import es.upm.dit.isst.dao.FacturasDAOImpl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

public class AddFacturaPDF extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("archivoPDF");
		BlobKey blobkey = blobKeys.get(0);
		if (blobKeys == null || blobKeys.isEmpty() || blobKeys.get(0) == null) {
			resp.sendError(1200);
		}

		BlobstoreInputStream f = new BlobstoreInputStream(blobkey);

		PdfReader reader = new PdfReader(f);

		String pdfToText = PdfTextExtractor.getTextFromPage(reader, 1);
		pdfToText += PdfTextExtractor.getTextFromPage(reader, 2);
		pdfToText += PdfTextExtractor.getTextFromPage(reader, 3);
		//System.out.println(pdfToText);
		
		char empresa1[] = new char[6];
		pdfToText.getChars((pdfToText.indexOf("área de clientes Mi") + 20),
				(pdfToText.indexOf("área de clientes Mi") + 26), empresa1, 0);
		String empresa = String.valueOf(empresa1);
		System.out.println("Empresa: "+ empresa);

		char startDate1[] = new char[10];
		pdfToText.getChars((pdfToText.indexOf("período facturado") + 18),
				(pdfToText.indexOf("período facturado") + 28), startDate1, 0);
		String startDate = String.valueOf(startDate1);
		System.out.println("startDate: " + startDate);

		char endDate1[] = new char[10];
		pdfToText.getChars((pdfToText.indexOf("período facturado") + 32),
				(pdfToText.indexOf("período facturado") + 42), endDate1, 0);
		String endDate = String.valueOf(endDate1);
		System.out.println("endDate: " + endDate);

		char cuotas1[] = new char[6];
		pdfToText.getChars((pdfToText.indexOf(" GB por ") + 8),
				(pdfToText.indexOf(" GB por ") + 14), cuotas1, 0);
		String cuotas2 = String.valueOf(cuotas1);
		cuotas2 = cuotas2.replace(',', '.');
		Double cuotas = Double.valueOf(cuotas2);
		System.out.println("cuotas: "+cuotas);

		char consumos1[] = new char[7];
		pdfToText.getChars((pdfToText.indexOf("consumos") + 9),
				(pdfToText.indexOf("consumos") + 16), consumos1, 0);
		String consumos2 = String.valueOf(consumos1);
		consumos2 = consumos2.replace(',', '.');
		Double consumos = Double.valueOf(consumos2);
		System.out.println("consumos: "+consumos);

		char importeTotal1[] = new char[6];
		pdfToText.getChars((pdfToText.indexOf(" factura es de ") + 15),
				(pdfToText.indexOf(" factura es de ") + 21), importeTotal1, 0);
		String importeTotal2 = String.valueOf(importeTotal1);
		importeTotal2 = importeTotal2.replace(',', '.');
		Double importeTotal = Double.valueOf(importeTotal2);
		System.out.println("importe total: "+importeTotal);

		char sinImpuestos1[] = new char[6];
		pdfToText.getChars(
				(pdfToText.indexOf("total (antes de impuestos)") - 7),
				(pdfToText.indexOf("total (antes de impuestos)") - 1),
				sinImpuestos1, 0);
		String sinImpuestos2 = String.valueOf(sinImpuestos1);
		sinImpuestos2 = sinImpuestos2.replace(',', '.');
		Double sinImpuestos = Double.valueOf(sinImpuestos2);
		System.out.println("sin impuestos: "+sinImpuestos);

		char datosContratados1[] = new char[3];
		pdfToText.getChars((pdfToText.indexOf(" GB por ") - 3),
				(pdfToText.indexOf(" GB por ")), datosContratados1, 0);
		String datosContratados2 = String.valueOf(datosContratados1);
		datosContratados2 = datosContratados2.replace(',', '.');
		Double datosContratados = Double.valueOf(datosContratados2);
		System.out.println("datos contratados: "+datosContratados);

		char minutosContratados1[] = new char[4];
		pdfToText.getChars((pdfToText.indexOf(" GB por ") - 15),
				(pdfToText.indexOf(" GB por ") - 11), minutosContratados1, 0);
		String minutosContratados2 = String.valueOf(minutosContratados1);
		minutosContratados2 = minutosContratados2.replace(',', '.');
		Double minutosContratados = Double.valueOf(minutosContratados2);
		System.out.println("minutos contratados: "+minutosContratados);

		char municipio1[] = new char[6];
		pdfToText.getChars((pdfToText.indexOf("45223") + 6),
				(pdfToText.indexOf("45223") + 12), municipio1, 0);
		String municipio = String.valueOf(municipio1);
		System.out.println("municipio: " + municipio);

		char provincia1[] = new char[7];
		pdfToText.getChars((pdfToText.indexOf("45223") + 14),
				(pdfToText.indexOf("45223") + 21), provincia1, 0);
		String provincia = String.valueOf(provincia1);
		System.out.println("provincia: "+provincia);
		reader.close();

		FacturasDAO dao = FacturasDAOImpl.getInstance();
		dao.add(empresa, startDate, endDate, cuotas, consumos, sinImpuestos, importeTotal, 
				datosContratados, minutosContratados, municipio, provincia, user);
		
		System.out.println("->Añadida factura. Provincia y municipio: "+provincia+ " "+municipio
				+". Empresa: "+empresa+ " con fecha de facturación: "+startDate+"-"+endDate
				+". Cuotas-consumos: "+cuotas+"-"+consumos+". Importe antes y despuéss de impuestos: "
				+sinImpuestos+"-"+importeTotal+". Datos y minutos contratados: "+datosContratados+"GB y "
				+minutosContratados+"min.");
		
		resp.sendRedirect("/showFactura");

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user == null) {
			resp.sendRedirect("/"); // Si no estoy logueado me devuelve a Inicio
		} else {
			RequestDispatcher view = req
					.getRequestDispatcher("AddFacturaPDF.jsp");
			try {
				view.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}

}
