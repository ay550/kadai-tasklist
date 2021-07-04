package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        /* Taskのインスタンスを生成 */
        Task t = new Task();

        /* tの各フィールドにデータを代入 */
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());//現在の日時を取得
        t.setCreated_at(currentTime);
        t.setUpdated_at(currentTime);

        String content = "人間失格";
        t.setContent(content);

        /*データベースに保存 */
        em.getTransaction().begin();
        em.persist(t);//データベースに保存
        em.getTransaction().commit();//データの新規登録を確定させる命令

        /* 自動採番されたIDの値を表示 */
        response.getWriter().append(Integer.valueOf(t.getId()).toString());

        em.close();

    }

}
