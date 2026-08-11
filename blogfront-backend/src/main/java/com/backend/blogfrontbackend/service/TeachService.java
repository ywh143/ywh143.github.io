package com.backend.blogfrontbackend.service;


import com.backend.blogfrontbackend.entity.teacher.Teach;

import java.util.List;

public interface TeachService {
    List<Teach> findTeachByTeacherId(int id);

    List<Teach> findTeachByCourseId(int id);

    List<Teach> findAllTeach();

    boolean insertTeach(Teach teach);

    boolean deleteteach(int tid,int cid);

    boolean updateTeach(Teach teach);
}
