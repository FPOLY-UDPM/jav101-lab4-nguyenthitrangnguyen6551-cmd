package controller;

import dao.StudentDAO;
import model.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO dao = new StudentDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        switch (action) {

            case "new":
                req.getRequestDispatcher("/views/student-form.jsp").forward(req,
                        resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Student s = dao.findById(id);
                req.setAttribute("student", s);
                req.getRequestDispatcher("/views/student-form.jsp").forward(req,
                        resp);
                break;
            case "delete":
                dao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("students");
                break;
            default:
                List<Student> list = dao.findAll();
                req.setAttribute("students", list);
                req.getRequestDispatcher("/views/student-list.jsp").forward(req,
                        resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String masv = req.getParameter("masv");
        String hoten = req.getParameter("hoten");
        if ("insert".equals(action)) {
            Student newStudent = new Student(masv, hoten);
            dao.insert(new Student(masv, hoten));
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Student updatedStudent = new Student(id, masv, hoten);
            dao.update(new Student(id, masv, hoten));
        }
        resp.sendRedirect("students");
    }
}
