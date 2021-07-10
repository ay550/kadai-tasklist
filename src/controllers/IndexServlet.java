package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet1
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class).getResultList();
       // response.getWriter().append(Integer.valueOf(tasks.size()).toString());

        //JPQLの文につけた名前 getAllTasks をcreateNamedQuery メソッドの引数に指定
        //することでデータベースへの問い合わせを実行できる
        //問い合わせ結果を getResultList() メソッドを使ってリスト形式で取得する

        em.close();

        //データベースに保存されたデータはHibernateによって自動で Task クラスのオブジェクトになって
        //このリストの中に格納される

        request.setAttribute("tasks", tasks);

        //フラッシュメッセージがセッションスコープにセットされていたら
        if (request.getSession().getAttribute("flush") != null) {
            //セッションスコープ内のフラッシュメッセージをリクエストスコープに保存し
            //セッションスコープからは削除する
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");

        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);

    }



}
