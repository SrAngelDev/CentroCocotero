package srangeldev.centrococotero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import srangeldev.centrococotero.producto.models.Producto;
import srangeldev.centrococotero.producto.services.ProductoService;

@Controller
public class HomeController {

    ProductoService productoService;

    @Autowired
    public HomeController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "q", required = false) String query,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Producto> productosPage;

        if (query != null && !query.isEmpty()) {
            productosPage = productoService.findByNombreActive(query, pageable);
            model.addAttribute("query", query);
        } else {
            productosPage = productoService.findAllActive(pageable);
        }

        model.addAttribute("productosPage", productosPage);
        return "app/producto/lista";
    }
}
