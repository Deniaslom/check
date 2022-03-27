package servlet;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import services.ProductService;
import services.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/findAllProduct")
public class FindAllProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    private ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));
        resp.setStatus(200);
        writeBody(resp, productService.findAll(size, page));
    }

    private void writeBody(HttpServletResponse response, Object obj) throws IOException {
        @Cleanup PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(obj));
    }
}
