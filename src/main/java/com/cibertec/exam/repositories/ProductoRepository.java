package com.cibertec.exam.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.exam.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	//List<Producto> findAll();
}
