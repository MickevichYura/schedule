package com.github.istin.schedule.backend.servlet;

import com.github.istin.schedule.backend.University;
import com.github.istin.schedule.gson.Faculty;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacultyList extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        final String id = req.getParameter("id");
        final University university = University.values()[Integer.valueOf(id)];
        List<Faculty> faculties = null;
        try {
            faculties = university.getUniversityAdapter().getFacultiesList();
            Collections.sort(faculties);
            resp.getWriter().print(new Gson().toJson(faculties));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        throw new IllegalArgumentException("post is not supported");
    }
}
