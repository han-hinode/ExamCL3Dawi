package com.cibertec.exam.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.cibertec.exam.models.Producto;
import com.cibertec.exam.repositories.ProductoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository productoRepo;;

	@Override
	public List<Producto> getAllProducts() {
		// TODO Auto-generated method stub
		return productoRepo.findAll();
	}

	public void newProduct(Producto producto) {
		productoRepo.save(producto);
	}

	public void exportarProducto(String format) throws JRException, IOException {
		// RECUPERAMOS LA LISTA
		List<Producto> listaProductos = productoRepo.findAll();
		// OBTENIENDO RUTA ACTUAL DEL PROYECTO
		File path = new File("");
		String directoryName = path.getAbsolutePath().toString();
		// LLENANDO LA TABLA DE JASPER
		File file = ResourceUtils.getFile("classpath:jasper/ReporteProducto.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaProductos);
		// LLENANDO ALGUN PARAMETRO QUE SE HAYA DEFINIDO EN JASPER
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("examen", "Examen CL3 Dawi");
		//parameters.put("logo", directoryName+"\\src\\main\\resources\\static\\img\\package.png");
		parameters.put("github", "https://github.com/vrakillch/ExamCL3Dawi.git");
		// VISUALIZANDO LA TABLA DE JASPER
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, directoryName+"\\reportes"+"\\Producto.pdf");
		}
		if(format.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, directoryName+"\\reportes"+"\\Producto.html");
		}
	}
}