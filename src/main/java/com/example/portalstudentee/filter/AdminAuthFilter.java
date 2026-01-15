package com.example.portalstudentee.filter;


import com.example.portalstudentee.model.User;
import com.example.portalstudentee.model.UserRole;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.management.relation.Role;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/changeStudent",
        "/deleteStudent",
        "/changeCourse",
        "/deleteCourse"
})
public class AdminAuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest request) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null && user.getRole() == UserRole.ADMIN) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/home");
            }
        }
    }
}
