package com.cibertec.exam.controllers;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.exam.models.Producto;
import com.cibertec.exam.services.ProductoServiceImpl;

@Controller
public class ProductoController {

	@Autowired
	private ProductoServiceImpl productoServi;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/home")
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().stream().count() > 0) {
			List<Producto> products = productoServi.getAllProducts();
			Collections.reverse(products);
			model.addAttribute("products", products);
			return "home";
		}
		return "access_denied";
	}

	@GetMapping("/nuevo")
	public String registro(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {

			Producto objProduct = new Producto();
			objProduct.setIdProducto(0L);
			model.addAttribute("product", objProduct);
			return "registro";
		}

		return "access_denied";
	}

	@PostMapping("/nuevo")
	public String grabar(@RequestParam("idNombre") String idNombre, @RequestParam("idDescripcion") String idDescripcion,
			Model model, RedirectAttributes redirect) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {

			try {
				Producto objTweet = new Producto();

				objTweet.setIdProducto(0L);
				objTweet.setNomProducto(idNombre);
				Date defaultValue = new Date();
				objTweet.setFecProducto(defaultValue);
				objTweet.setDesProducto(idDescripcion);

				productoServi.newProduct(objTweet);

			} catch (Exception e) {
				
				redirect.addAttribute("mensaje", "Ocurrió un error al intentar guardar tweet!");
				e.printStackTrace();
			}
		}
		else {
			return "access_denied";
		}
		return "redirect:/home";
	}
}
