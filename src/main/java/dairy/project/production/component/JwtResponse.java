package dairy.project.production.component;

import java.util.Collection;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class JwtResponse {
	private String accessToken;
	private String username;
	private  String name;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String username,String name, Collection<? extends GrantedAuthority> authorities) {
		this.accessToken = accessToken;
		this.username = username;
		this.name =name;
		this.authorities = authorities;
	}




}