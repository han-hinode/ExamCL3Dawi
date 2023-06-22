package com.cibertec.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.exam.models.Producto;
import com.cibertec.exam.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository productoRepo;

	@Override
	public List<Producto> getAllProducts() {
		// TODO Auto-generated method stub
		return productoRepo.findAll();
	}

	public void newProduct(Producto producto) {
		productoRepo.save(producto);
	}

}