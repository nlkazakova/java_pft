package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void login () {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        click(By.cssSelector("input[value='Войти']"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Войти']"));
    }

    public void goToUsersPage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public UserData selectUser() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table where enabled = 1 and username <> \"administrator\"");
            List<UserData> users = new ArrayList<UserData>();
            while (rs.next()) {
                users.add(new UserData().withId(rs.getInt("id"))
                        .withUsername(rs.getString("username")).withEmail(rs.getString("email")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(users);
            return users.iterator().next();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public void gotoUser(UserData user) {
        click(By.linkText(user.getUsername()));
    }

    public void changeUserPassword() {
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void changePassword(String confirmationLink, UserData user, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("realname"), user.getUsername());
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("button[type='Submit']"));
    }
}
