package com.muzi.action;

import com.muzi.dao.MemberDao;
import com.muzi.model.Member;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by muzi on 2017/11/9.
 */
public class MemberAction {

    public static void main(String[] args) throws SQLException {
        MemberDao memberDao = new MemberDao();
        /**
         * 查询
         */
        /*List<Member> memberList = memberDao.query();
        for (Member member:
             memberList) {
            System.out.println(member.getUser_name() + ":" + member.getAge() + ":" +member.getEmail());
        }*/
        /**
         * 新增
         */
        Member member = new Member();
        member.setUser_name("wacai");
        member.setSex(0);
        member.setAge(18);
        member.setEmail("muzi@wacai.com");
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        member.setCreate_time(Integer.valueOf(timestamp));
        member.setUpdate_time(Integer.valueOf(timestamp));
        memberDao.addMember(member);
    }
}
