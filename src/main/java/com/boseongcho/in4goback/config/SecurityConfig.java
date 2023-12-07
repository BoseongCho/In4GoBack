package com.boseongcho.in4goback.config;

import com.boseongcho.in4goback.jwt.JwtAccessDeniedHandler;
import com.boseongcho.in4goback.jwt.JwtAuthenticationEntryPoint;
import com.boseongcho.in4goback.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public SecurityConfig(TokenProvider tokenProvider,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    /* 1. 암호화 처리를 위한 PasswordEncoder를 빈으로 설정 */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    /* 2. 시큐리티 설정을 무시할 정적 리소드 등록 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()
    //(resources 안의 static 폴더 내부의 정적리소스 유형 무시)
    {
        return (web) -> web.ignoring().antMatchers("/imgs/**");
    }

    /* 3. HTTP요청에 대한 권한별 설정(세션 인증 -> 토큰 인증으로 인해 바뀐 부분 존재) */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .exceptionHandling()
                /* 기본 시큐리티 설정에서 JWT 토큰과 관련된 유효성과 권한 체크용 설정 */
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)	// 유효한 자격 증명 없을 시(401)
                .accessDeniedHandler(jwtAccessDeniedHandler)		 // 필요한 권한 없이 접근 시 (403)
                // -> 기존 시큐리티에서 추가된 부분.
                .and()
                .authorizeRequests()  // 다음 리퀘스트에 대한 사용권한 체크
                .antMatchers("/").authenticated()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .antMatchers("/api/v1/dailyWorker/**").permitAll()
                .antMatchers("/api/v1/approval/submit").hasRole("INSA1")
                .antMatchers("/api/v1/approval/").hasAnyRole("DESIGN1", "MARKETING1")
                .antMatchers("/api/v1/marketing/management").hasRole("MARKETING2")

                .anyRequest().permitAll()
                // cors를 위해 preflight 요청 처리용 options 요청 허용
                /* preflight request란?
                요청할 url이 외부 도메인일 경우 웹 브라우저에서 자체 실행되며 options 메소드로 사전 요청을 보내게 된다.
                사전 요청이 안전한지 확인하기 위함(유효한지 서버에 미리 파악할 수 있도록 보내는 수단.)
                * */
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors() /* corss origin resource sharing
                               기존에 있던 동일한 origin을 공유
                               크롬에는 SOP (same origin policy) ex)localhost:8888
                               우리는 서버가 2개이기 때문에 cors걸어줌.
                            */
                .and()
                /* jwt 토큰 방식을 쓰겠다는 설정 */
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }

    /* 4. CORS 설정용 Bean(허용할 origin과 httpMethod 종류와 header 값) */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        //List.of == Array.asList() ->둘 다 배열을 리스트로 변경하는 메소드이지만
        //List.of는 결과값이 불변객체
        //Arrays.asList는 가변객체
//        configuration.setAllowedOrigins(Arrays.asList("http://43.201.169.51:7777")); //이 origin을 허용하겠다/
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3001")); //이 origin을

        http://localhost:3000
        // 허용하겠다/
        // localhost자리에 고정IP 넣기
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3001")); //이 origin을 허용하겠다/
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE")); //이방식들을 가지고 쓸 것이다.
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-type" //여기있는 method는
                // 허용하겠다.
                , "Access-Control-Allow-Headers", "Authorization"
                , "X-Requested-With"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
