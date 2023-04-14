import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    String timezoneParam = request.getParameter("timezone");
    TimeZone timezone = null;
    if (timezoneParam != null) {
        timezone = TimeZone.getTimeZone(timezoneParam.replace(' ', '+'));
    } else {
        timezone = TimeZone.getTimeZone("GMT");
    }
    Calendar calendar = Calendar.getInstance(timezone);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
    dateFormat.setTimeZone(timezone);
    String formattedDate = dateFormat.format(calendar.getTime());

    out.println("<html><head><title>Current Time</title></head><body>");
    out.println("<h1>Current Time:</h1>");
    out.println("<p>" + formattedDate + "</p>");
    out.println("</body></html>");

    out.close();
    }
}