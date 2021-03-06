package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    /**
     * Default constructor.
     */
    //public EncodingFilter() はコンストラクタ
    //フィルタのインスタンスが生成される際に実行される
    public EncodingFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    //「フィルタの処理が不要になったため、フィルタを破棄する」というときの処理を定義
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    //フィルタとしての実行内容を定義
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        //前 :サーブレットが処理を実行する 前 にフィルタの処理が実行
        chain.doFilter(request, response);  //←より
        //後 :サーブレットが処理を実行した 後 にフィルタの処理が実行

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    //フィルタの処理がはじめて実行されるときの処理を定義
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
