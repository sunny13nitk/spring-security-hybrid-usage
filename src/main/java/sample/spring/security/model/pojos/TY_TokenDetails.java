package sample.spring.security.model.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_TokenDetails
{
    private String clientId;
    private String userName;
    private String sapId;
    private String zoneId;
    private String familyName;
    private String firstName;
    private String email;
    private String subAccountId;
    private String grantType;
    private List<String> roles = new ArrayList<String>();
    private List<String> audiences = new ArrayList<String>();
}
