package com.ngocnv.springtemplate.oauth2;

import com.ngocnv.springtemplate.service.AccountService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerConfig.class);

  @Value("${security.oauth2.resource.id}")
  private String resourceId;

  @Value("${access_token.validity_period}")
  private int accessTokenValiditySeconds;

  @Value("${refresh_token.validity_period}")
  private int refreshTokenValiditySeconds;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private DataSource dataSource;

  @Bean
  public UserDetailsService userDetailsService() {
    return new AccountService();
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .authenticationManager(this.authenticationManager)
        .tokenServices(tokenServices())
        .tokenStore(tokenStore())
        .accessTokenConverter(accessTokenConverter());
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer
        .tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
        .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource)
        .withClient("normal-app")
        .authorizedGrantTypes("authorization_code", "implicit")
        .authorities("ROLE_CLIENT")
        .scopes("read", "write")
        .resourceIds(resourceId)
        .accessTokenValiditySeconds(accessTokenValiditySeconds)
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
        .and()
        .withClient("trusted-app")
        .authorizedGrantTypes("client_credentials", "password", "refresh_token")
        .authorities("ROLE_TRUSTED_CLIENT")
        .scopes("read", "write")
        .resourceIds(resourceId)
        .accessTokenValiditySeconds(accessTokenValiditySeconds)
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
        .secret("$2a$10$wS.6dVTBvoBr25cs5GRXe.Vzj4hEJuU/Yy7cHrZAgx4ToUy.b4VxW") // "secret"
        .and()
        .withClient("register-app")
        .authorizedGrantTypes("client_credentials")
        .authorities("ROLE_REGISTER")
        .scopes("read")
        .resourceIds(resourceId)
        .secret("$2a$10$wS.6dVTBvoBr25cs5GRXe.Vzj4hEJuU/Yy7cHrZAgx4ToUy.b4VxW") // "secret"
        .and()
        .withClient("my-client-with-registered-redirect")
        .authorizedGrantTypes("authorization_code")
        .authorities("ROLE_CLIENT")
        .scopes("read", "trust")
        .resourceIds("oauth2-resource")
        .redirectUris("http://anywhere?key=value");
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Autowired
  private SecretKeyProvider keyProvider;

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    try {
      converter.setSigningKey(keyProvider.getKey());
    } catch (KeyStoreException | NoSuchAlgorithmException | IOException | UnrecoverableKeyException | CertificateException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return converter;
  }

  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    defaultTokenServices.setSupportRefreshToken(true);
    defaultTokenServices.setTokenEnhancer(accessTokenConverter());
    return defaultTokenServices;
  }
}
