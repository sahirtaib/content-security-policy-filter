package org.joget;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "ContentSecurityPolicyHeaderFilter", urlPatterns = "/*")
public class ContentSecurityPolicyHeaderFilter implements Filter {

    private String cspValue;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Retrieve the "value" parameter from web.xml or environment variable
        cspValue = filterConfig.getInitParameter("value");
        
        if (cspValue == null) {
            cspValue = "default-src 'self'; script-src 'self';";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // Add the Content Security Policy header based on the configured value
            httpResponse.setHeader("Content-Security-Policy", cspValue);
        }

        // Continue with the next filter or request processing
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}