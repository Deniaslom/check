package servlet;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import model.Product;
import services.ProductService;
import services.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private final static String PRODUCT_ID = "id";
    private final static String PRODUCT_NAME = "name";
    private final static String PRODUCT_PRICE = "price";
    private final static String PRODUCT_DISCOUNT = "isdiscount";
    private final static int HTTP_STATUS_CREATED = 201;
    private final static int HTTP_STATUS_OK = 200;

    private ProductService productService = new ProductServiceImpl();
    private ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter(PRODUCT_ID);
        Product productById = productService.getProductById(Integer.parseInt(id));
        response.setStatus(HTTP_STATUS_OK);
        writeBody(response, productById);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product productRequest = productSetParam(request);
        Product productSave = productService.save(productRequest);
        response.setStatus(HTTP_STATUS_CREATED);
        writeBody(response, productSave);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productSetParam(request);
        response.setStatus(HTTP_STATUS_OK);
        productService.update(product.getId(), product);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HTTP_STATUS_OK);
        productService.delete(Integer.parseInt(request.getParameter(PRODUCT_ID)));
    }


    private void writeBody(HttpServletResponse response, Object obj) throws IOException {
        @Cleanup PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(obj));
    }

    private Product productSetParam(HttpServletRequest request) {
        Product product = new Product();
        if (request.getParameter(PRODUCT_ID) != null)
            product.setId(Integer.valueOf(request.getParameter(PRODUCT_ID)));
        product.setName(request.getParameter(PRODUCT_NAME));
        product.setPrice(new BigDecimal(Integer.parseInt(request.getParameter(PRODUCT_PRICE))));
        product.setDiscount(Boolean.parseBoolean(request.getParameter(PRODUCT_DISCOUNT)));
        return product;
    }
}