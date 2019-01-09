package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdminTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testChangeUserPassword() throws IOException {
        app.admin().login();
        app.admin().goToUsersPage();
        UserData selectedUser = app.admin().selectUser();
        app.admin().gotoUser(selectedUser);
        app.admin().changeUserPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, selectedUser.getEmail());
        System.out.println(confirmationLink);
        String newPassword = "password";
        app.admin().changePassword(confirmationLink, selectedUser, newPassword);
        assertTrue(app.newSession().login(selectedUser.getUsername(), newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
