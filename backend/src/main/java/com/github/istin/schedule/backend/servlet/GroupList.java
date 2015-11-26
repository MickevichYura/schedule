package com.github.istin.schedule.backend.servlet;

import com.github.istin.schedule.backend.University;
import com.github.istin.schedule.gson.Faculty;
import com.github.istin.schedule.gson.Group;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupList extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        final String uid = req.getParameter("uid");
        final String did = req.getParameter("did");
        final String fid = req.getParameter("fid");
        final String c = req.getParameter("c");
        final University university = University.values()[Integer.valueOf(uid)];
        List<Group> groups = null;
        try {
            groups = university.getUniversityAdapter().getGroupsList(did, fid, c);
            Collections.sort(groups);
            resp.getWriter().print(new Gson().toJson(groups));
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
