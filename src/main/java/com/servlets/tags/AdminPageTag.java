package com.servlets.tags;

import com.model.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class AdminPageTag implements Tag {
    private PageContext pageContext;
    private List<User> users;
    private String username;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            drawUserTable(out);
        } catch (IOException e) {
            throw new JspException(e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag tag) {
    }

    @Override
    public Tag getParent() {
        return null;
    }

    @Override
    public int doEndTag() {
        return 0;
    }

    @Override
    public void release() {

    }

    private void drawUserTable(JspWriter out) throws IOException {
        out.print(
                "<table class=\"table table-bordered\">\n" +
                        "    <thead>\n" +
                        "    <tr>\n" +
                        "        <th>Login</th>\n" +
                        "        <th>First Name</th>\n" +
                        "        <th>Last Name</th>\n" +
                        "        <th>Age</th>\n" +
                        "        <th>Role</th>\n" +
                        "        <th colspan=\"2\">Actions</th>\n" +
                        "    </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>");
        for (User user : users) {
            out.print(
                    " <tr>\n" +
                            "<td> " + user.getLogin() + "</td>\n" +
                            "<td> " + user.getFirstName() + "</td>\n" +
                            "<td> " + user.getLastName() + "</td>\n" +
                            "<td> " + Period.between(user.getBirthday().toLocalDate(), LocalDate.now()).getYears() + "</td>\n" +
                            "<td> " + user.getRole().getName() + "</td>\n" +
                            "<td>");
            if (!username.equals(user.getLogin())) {
                out.print("<a href=\"/admin/delete-user?deleteLogin=" + user.getLogin() + "\" onclick=\"return confirm('Are you sure you want to delete user?')\">delete    </a>");
            }
            out.print("</td>\n" +
                    "<td>" +
                    "<a href=\"/admin/edit-user?editLogin=" + user.getLogin() + "\">     edit</a>" +
                    "</td>\n" +
                    " </tr>");
        }
        out.print("    </tbody>\n" +
                "</table>");
    }
}
