package sample.spring.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sap.cloud.security.token.Token;

@Controller
@RequestMapping("/app")
public class UIController
{

    @GetMapping("/showDetails")
    public String showDetails(@AuthenticationPrincipal Token token)
    {
        return "success";
    }

}
