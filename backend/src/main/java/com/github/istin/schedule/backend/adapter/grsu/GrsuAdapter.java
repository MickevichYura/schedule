package com.github.istin.schedule.backend.adapter.grsu;

import com.github.istin.schedule.backend.IUniversityAdapter;
import com.github.istin.schedule.backend.adapter.grsu.domain.DepartmentModel;
import com.github.istin.schedule.backend.adapter.grsu.domain.DepartmentResponse;
import com.github.istin.schedule.backend.adapter.grsu.domain.FacultyModel;
import com.github.istin.schedule.backend.adapter.grsu.domain.FacultyResponse;
import com.github.istin.schedule.backend.adapter.grsu.domain.GroupModel;
import com.github.istin.schedule.backend.adapter.grsu.domain.GroupResponse;
import com.github.istin.schedule.backend.adapter.grsu.domain.LecturerModel;
import com.github.istin.schedule.backend.adapter.grsu.domain.LecturerResponse;
import com.github.istin.schedule.backend.adapter.grsu.domain.LessonModel;
import com.github.istin.schedule.backend.adapter.grsu.domain.LessonsResponse;
import com.github.istin.schedule.backend.utils.HttpUtils;
import com.github.istin.schedule.gson.Department;
import com.github.istin.schedule.gson.Faculty;
import com.github.istin.schedule.gson.Group;
import com.github.istin.schedule.gson.Lecturer;
import com.github.istin.schedule.gson.Lesson;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uladzimir_klyshevich on 10/12/15.
 */
public class GrsuAdapter implements IUniversityAdapter {

    public static final String BASE_URL_API = "http://api.grsu.by/1.x/app1/";
    public static final String LECTURER_LIST_API = BASE_URL_API + "getTeachers?extended=false";
    public static final String DEPARTMENT_LIST_API = BASE_URL_API + "getDepartments";
    public static final String FACULTY_LIST_API = BASE_URL_API + "getFaculties";
    public static final String GROUP_LIST_API = BASE_URL_API + "getGroups?departmentId=%s&facultyId=%s&course=%s";
    public static final String TEACHERS_SCHEDULE_LIST_API = BASE_URL_API + "getTeacherSchedule?teacherId=";
    public static final String GROUP_SCHEDULE_LIST_API = BASE_URL_API + "getGroupSchedule?groupId=";

    @Override
    public List<Lecturer> getLecturerList() throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = HttpUtils.getInputStream(LECTURER_LIST_API);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader, 8192);
            final LecturerResponse lecturerList = new Gson().fromJson(bufferedReader, LecturerResponse.class);
            final List<LecturerModel> lecturers = lecturerList.getLecturers();
            List<Lecturer> list = new ArrayList<>();
            if (lecturers != null) {
                for (LecturerModel lecturerModel : lecturers) {
                    final Lecturer lecturer = new Lecturer();
                    lecturer.setId(lecturerModel.getId());
                    lecturer.setName(lecturerModel.getFullName());
                    list.add(lecturer);
                }
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtils.close(inputStream);
            HttpUtils.close(inputStreamReader);
            HttpUtils.close(bufferedReader);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Lesson> getLecturerScheduleList(String pLid) throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = HttpUtils.getInputStream(TEACHERS_SCHEDULE_LIST_API+pLid);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader, 8192);
            final LessonsResponse scheduleList = new Gson().fromJson(bufferedReader, LessonsResponse.class);
            final List<LessonModel> lessons = scheduleList.getLessons();
            List<Lesson> list = new ArrayList<>();
            if (lessons != null) {
                for (LessonModel lessonModel : lessons) {
                    list.add(lessonModel.doOptimize());
                }
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtils.close(inputStream);
            HttpUtils.close(inputStreamReader);
            HttpUtils.close(bufferedReader);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Department> getDepartmentList() throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = HttpUtils.getInputStream(DEPARTMENT_LIST_API);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader, 8192);
            final DepartmentResponse departmentResponse = new Gson().fromJson(bufferedReader, DepartmentResponse.class);
            final List<DepartmentModel> departments = departmentResponse.getDepartments();
            List<Department> list = new ArrayList<>();
            if (departments != null) {
                for (DepartmentModel departmentModel : departments) {
                    final Department department = new Department();
                    department.setId(departmentModel.getId());
                    department.setName(departmentModel.getTitle());
                    list.add(department);
                }
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtils.close(inputStream);
            HttpUtils.close(inputStreamReader);
            HttpUtils.close(bufferedReader);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Faculty> getFacultiesList() throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = HttpUtils.getInputStream(FACULTY_LIST_API);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader, 8192);
            final FacultyResponse facultyResponse = new Gson().fromJson(bufferedReader, FacultyResponse.class);
            final List<FacultyModel> faculties = facultyResponse.getFaculties();
            List<Faculty> list = new ArrayList<>();
            if (faculties != null) {
                for (FacultyModel facultyModel : faculties) {
                    final Faculty faculty = new Faculty();
                    faculty.setId(facultyModel.getId());
                    faculty.setName(facultyModel.getTitle());
                    list.add(faculty);
                }
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtils.close(inputStream);
            HttpUtils.close(inputStreamReader);
            HttpUtils.close(bufferedReader);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Group> getGroupsList(String pDid, String pFid, String pC) throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = HttpUtils.getInputStream(String.format(GROUP_LIST_API, pDid, pFid, pC));
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader, 8192);
            final GroupResponse groupResponse = new Gson().fromJson(bufferedReader, GroupResponse.class);
            final List<GroupModel> groups = groupResponse.getGroups();
            List<Group> list = new ArrayList<>();
            if (groups != null) {
                for (GroupModel groupModel : groups) {
                    final Group group = new Group();
                    group.setId(groupModel.getId());
                    group.setName(groupModel.getTitle());
                    list.add(group);
                }
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtils.close(inputStream);
            HttpUtils.close(inputStreamReader);
            HttpUtils.close(bufferedReader);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Lesson> getGroupScheduleList(String pGid) throws Exception {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = HttpUtils.getInputStream(GROUP_SCHEDULE_LIST_API+ pGid);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader, 8192);

            final LessonsResponse scheduleList = new Gson().fromJson(bufferedReader, LessonsResponse.class);
            final List<LessonModel> lessons = scheduleList.getLessons();
            List<Lesson> list = new ArrayList<>();
            if (lessons != null) {
                for (LessonModel lessonModel : lessons) {
                    list.add(lessonModel.doOptimize());
                }
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtils.close(inputStream);
            HttpUtils.close(inputStreamReader);
            HttpUtils.close(bufferedReader);
        }

        return new ArrayList<>();
    }
}