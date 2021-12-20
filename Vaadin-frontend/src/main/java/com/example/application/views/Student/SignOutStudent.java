

package com.example.application.views.Student;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;

@PageTitle("Sign Out")
@Route(value = "signOutStudent", layout = StudentLayout.class)

public class SignOutStudent extends VerticalLayout {

    private H1 text;
    private Button leave;

    public SignOutStudent() {
        text = new H1("Do you wish to leave?");
        leave = new Button("Leave");
        add(text,leave);
        leave.addClickListener(e -> {
            // Send the request and get the response
            HashMap<String, Object> response;
            CookieManager cookieManager = new CookieManager();

            ApiRequest  req = new ApiRequest("http://localhost:8080/logout");
            // Add cookie to the request
            req.addCookie(OwnCookieManager.getInstance().getCookie());
            response = req.send();
            getUI().ifPresent(ui ->ui.navigate(""));
        });
    }

}
