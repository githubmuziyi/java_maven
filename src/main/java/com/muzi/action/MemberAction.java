package com.muzi.action;

import com.muzi.dao.MemberDao;
import com.muzi.model.Member;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by muzi on 2017/11/9.
 */
public class MemberAction {

    public void add(Member member) throws SQLException {
        MemberDao dao = new MemberDao();
        dao.addMember(member);
    }

    public void edit(Member member) throws SQLException {
        MemberDao dao = new MemberDao();
        dao.updateMember(member);
    }

    public void delete(Integer id) throws SQLException {
        MemberDao dao = new MemberDao();
        dao.delMember(id);
    }

    public List<Member> quert() throws SQLException {
        MemberDao dao = new MemberDao();
        return dao.query();
    }

}
