package parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static parser.ParserJsonFormat.getJson;

class ParserJsonFormatTest {
    @Test
    public void testing() throws JsonProcessingException, IllegalAccessException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        List<String> listNames = new ArrayList<>();
        listNames.add("Product_0");
        listNames.add("Product_1");
        listNames.add("Product_2");

        ProductTest product_1 = new ProductTest(1, listNames, 123.123, true);

        String str = objectMapper.writeValueAsString(product_1);
        String myJson = getJson(product_1);

        assert(myJson.equals(str));
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductTest {

    private int id;
    private List<String> name;
    private Double price;
    private boolean discount;
}