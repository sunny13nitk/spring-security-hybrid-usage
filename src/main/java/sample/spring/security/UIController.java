package sample.spring.security;

import static com.sap.cloud.security.config.Service.XSUAA;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sap.cloud.security.token.AccessToken;
import com.sap.cloud.security.token.Token;
import com.sap.cloud.security.token.TokenClaims;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/app")
@Slf4j
public class UIController
{

    // To get In UserInfo Autowired Bean

    @GetMapping("/")
    public String showHome()
    {
        return "home";
    }

    @GetMapping("/token")
    @PreAuthorize("hasAuthority('Admin')")
    public String showDetails(@AuthenticationPrincipal Token token, Model model)
    {
        boolean validToken = false;
        if (token != null)
        {
            validToken = true;
            Map<String, String> result = new HashMap<>();
            result.put("client id", token.getClientId());
            result.put("User Name", token.getClaimAsString(TokenClaims.USER_NAME));
            result.put("Global SAP user id", token.getClaimAsString(TokenClaims.SAP_GLOBAL_USER_ID));
            result.put("audiences", token.getClaimAsStringList(TokenClaims.AUDIENCE).toString());
            result.put("zone id", token.getZoneId());
            result.put("family name", token.getClaimAsString(TokenClaims.FAMILY_NAME));
            result.put("given name", token.getClaimAsString(TokenClaims.GIVEN_NAME));
            result.put("email", token.getClaimAsString(TokenClaims.EMAIL));

            if (XSUAA.equals(token.getService()))
            {
                result.put("(Xsuaa) subaccount id", ((AccessToken) token).getSubaccountId());
                result.put("(Xsuaa) scopes", String.valueOf(token.getClaimAsStringList(TokenClaims.XSUAA.SCOPES)));
                result.put("grant type", token.getClaimAsString(TokenClaims.XSUAA.GRANT_TYPE));
            }

            model.addAttribute("tokenDetails", result);
        }
        model.addAttribute("validToken", validToken);

        return "tokenDetails";
    }

}
