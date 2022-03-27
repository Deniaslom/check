package filter;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ApplicationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json");
        chain.doFilter(request, response);
    }
}
