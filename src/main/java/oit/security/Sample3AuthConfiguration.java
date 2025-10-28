package oit.is.team64.nsk4.teamsixfour.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration {
  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // user1/p@ss,user2/p@ss,admin/p@ss

    UserDetails shiro = User.withUsername("shiro")
        .password("{bcrypt}$2y$05$cHbSF9p54xvxo9LGM8zup.ws3bgSSD1Ay96e1xvF.mK4w/IG0e9tK").roles("CUSTOMER").build();

    UserDetails futa = User.withUsername("futa")
        .password("{bcrypt}$2y$05$JBBjYbAvrRfwbcVvxy7TZuAOQZY.kf4FphuiCPWNt44zJ46dkiAF.").roles("CUSTOMER").build();

    UserDetails hayato = User.withUsername("hayato")
        .password("{bcrypt}$2y$05$p5sZGTWa/mG1s6IpBFTMP.BI8zHZSUsVPxC56zHfoA0NdMkd3CHu2").roles("SELLER").build();

    UserDetails admin = User.withUsername("admin")
        .password("{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e").roles("ADMIN").build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(shiro, futa, hayato, admin);
  }

}
