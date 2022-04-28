/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.daniel.sga.domain.Persona;
import com.daniel.sga.service.PersonaServiceRemote;
import com.sun.enterprise.security.ee.auth.login.ProgrammaticLogin;
import java.util.List;
import javax.naming.*;

/**
 *
 * @author adseglocdom
 */
public class ClientePersonaService {
    
    public static void main(String[] args) {
        
        System.out.println("Iniciando llamada el EJB desde el cliente");
        
        String authFile = "login.conf";
        System.setProperty("java.security.auth.login.config", authFile);
        
        ProgrammaticLogin programmaticLogin = new ProgrammaticLogin();
        programmaticLogin.login("admin", "admin".toCharArray());
        
        try {
            Context jdni = new InitialContext();
            PersonaServiceRemote personaService = (PersonaServiceRemote) jdni.lookup("java:global/sga-jee-web-ws-security/PersonaServiceImpl!com.daniel.sga.service.PersonaServiceRemote");
            
            List<Persona> personas = personaService.listarPersonas();
            
            for (Persona persona : personas) {
                System.out.println(persona);
            }
            
        } catch (NamingException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
