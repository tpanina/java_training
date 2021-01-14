package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetHelper extends HelperBase {

    public ResetHelper(ApplicationManager app) {
        super (app);
    }

    public void login (String username, String password) {
        driver.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public void manageUsers(String username) {
        click(By.linkText("Manage Users"));
        click(By.xpath("//tr/td/a[contains(text(),'" + username + "')]"));
        click(By.xpath("//input[@value='Reset Password']"));
    }

}
