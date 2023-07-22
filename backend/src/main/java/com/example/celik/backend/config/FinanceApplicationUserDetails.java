package com.example.celik.backend.config;

import com.example.celik.backend.exception.EmailNotFoundException;
import com.example.celik.backend.model.User;
import com.example.celik.backend.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



/**
 * DATABASE DE KENDİ TABLE IMIZI OLUSTURDUK VE ORADAKİ BİLGİLERİ OKUYUP AUTHENTICATION PROVIDER A VERMEMIZ ICIN KENDI USER DETAILS SERVICE IMIZI YAZMAMIZ GEREKIYOR
 * BUNUN ICIN OLUSTURDUK VE LOADBYUSERNAME METODUNU YAZACAĞIZ
 * */

//Service yaptık ve bu sayede application context te bean i oluşturulmuş oldu. Configteki bean i de sildiğimiz için UserDetailsService i implement eden burası kaldı
//Spring boot otomatik olarak buraya gelir ve UserDetails i alır - tekrardan config içerisinde bean oluşturmamıza gerek yok

@Service
public class FinanceApplicationUserDetails implements UserDetailsService {
    private final UserService userService;

    public FinanceApplicationUserDetails(UserService userService) {
        this.userService = userService;
    }

    //User da UserDetails i implement eder
    //Öncelikle repo dan email ile user i cekti kontrol etti
    // user varsa UserDetails ta olması gereken username password authority gibi alanları aldı
    //authority için SimpleGrantedAuthority kullandı ve user ın role unu ekledi
    //Bilgilerle yeni User oluşturdu - UserDetails oluşturamaz çünkü interface
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userService.findUserByEmail(email);
            List<GrantedAuthority> roles = new ArrayList<>();
            user.getRoles().forEach(e -> roles.add(new SimpleGrantedAuthority(e.getRoleName())));
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),roles);
        }catch (EmailNotFoundException e){
            throw new UsernameNotFoundException("USERNAME IS NOT FOUND - "+e.getMessage());
        }
    }
}
