package servlet;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import model.DiscountCard;
import services.DiscountCartService;
import services.impl.DiscountCartServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cart")
public class DiscountCartServlet extends HttpServlet {
    private final static String CART_ID = "id";
    private final static String CART_DISCOUNT = "discount";
    private final static int HTTP_STATUS_CREATED = 201;
    private final static int HTTP_STATUS_OK = 200;

    private DiscountCartService cartService = new DiscountCartServiceImpl();
    private ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter(CART_ID);
        DiscountCard discountCard = cartService.getDiscountCardByNumber(Integer.parseInt(id));
        writeBody(response, discountCard);
        response.setStatus(HTTP_STATUS_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DiscountCard cardRequest = cartSetParam(request);
        DiscountCard cardSave = cartService.save(cardRequest);
        response.setStatus(HTTP_STATUS_CREATED);
        writeBody(response, cardSave);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DiscountCard cardRequest = cartSetParam(request);
        cartService.update(cardRequest.getNumber(), cardRequest);
        response.setStatus(HTTP_STATUS_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        cartService.delete(Integer.parseInt(request.getParameter(CART_ID)));
        response.setStatus(HTTP_STATUS_OK);
    }

    private void writeBody(HttpServletResponse response, Object obj) throws IOException {
        @Cleanup PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(obj));
    }

    private DiscountCard cartSetParam(HttpServletRequest request) {
        DiscountCard discountCard = new DiscountCard();
        if (request.getParameter(CART_ID) != null)
            discountCard.setNumber(Integer.valueOf(request.getParameter(CART_ID)));
        discountCard.setDiscount(Double.parseDouble(request.getParameter(CART_DISCOUNT)));
        return discountCard;
    }
}
