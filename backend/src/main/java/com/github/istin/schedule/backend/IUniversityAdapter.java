package com.github.istin.schedule.backend;


import com.github.istin.schedule.gson.Department;
import com.github.istin.schedule.gson.Faculty;
import com.github.istin.schedule.gson.Group;
import com.github.istin.schedule.gson.Lecturer;
import com.github.istin.schedule.gson.Lesson;

import java.util.List;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public interface IUniversityAdapter {

    List<Lecturer> getLecturerList() throws Exception;

    List<Lesson> getLecturerScheduleList(String pLid) throws Exception;

    List<Department> getDepartmentList() throws Exception;

    List<Faculty> getFacultiesList() throws Exception;

    List<Group> getGroupsList(String pDid, String pFid, String pC) throws Exception;

    List<Lesson> getGroupScheduleList(String pGid) throws Exception;
}
