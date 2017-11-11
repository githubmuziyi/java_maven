package com.muzi.dao;

import com.muzi.db.DBUtil;
import com.muzi.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muzi on 2017/11/9.
 */
public class MemberDao {

    /**
     * 增
     * @param member
     * @throws SQLException
     */
    public void addMember(Member member) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String insertSql =
                "insert into imooc.member" +
                "(user_name, sex, age, email, create_time, update_time)" +
                "values" +
                "(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
        preparedStatement.setString(1, member.getUser_name());
        preparedStatement.setInt(2, member.getSex());
        preparedStatement.setInt(3, member.getAge());
        preparedStatement.setString(4, member.getEmail());
        preparedStatement.setInt(5, member.getCreate_time());
        preparedStatement.setInt(6, member.getUpdate_time());
        preparedStatement.execute();
    }

    public void updateMember(Member member) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String updateSql =
                " update imooc.member" +
                        " set user_name=?, sex=?, age=?, email=?, create_time=?, update_time=?" +
                        " WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(updateSql);
        preparedStatement.setString(1, member.getUser_name());
        preparedStatement.setInt(2, member.getSex());
        preparedStatement.setInt(3, member.getAge());
        preparedStatement.setString(4, member.getEmail());
        preparedStatement.setInt(5, member.getCreate_time());
        preparedStatement.setInt(6, member.getUpdate_time());
        preparedStatement.setInt(7, member.getUpdate_time());
        preparedStatement.execute();
    }

    public void delMember(Integer id) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String delSql = "delete from imooc.member where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(delSql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Member> query() throws SQLException {
        Connection conn = DBUtil.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from imooc.member WHERE id > 0");
        List<Member> memberList = new ArrayList<Member>();
        Member member = null;
        while (resultSet.next()) {
            member = new Member();
            member.setUser_name(resultSet.getString("user_name"));
            member.setAge(resultSet.getInt("age"));
            member.setEmail(resultSet.getString("email"));
            memberList.add(member);
        }
        return memberList;
    }

    public Member getMember() {
        return null;
    }
}
