package ma.exovate.medaibe.controllers.auth;



import ma.exovate.medaibe.config.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleBasedController {

    private final JwtUtil jwtUtil;

    public RoleBasedController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/admin")
    public String adminEndpoint(@RequestHeader("Authorization") String token) {
        String role = jwtUtil.extractRole(token.replace("Bearer ", ""));
        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }
        return "Welcome, Admin!";
    }

    @GetMapping("/doctor")
    public String doctorEndpoint(@RequestHeader("Authorization") String token) {
        String role = jwtUtil.extractRole(token.replace("Bearer ", ""));
        if (!role.equals("DOCTOR")) {
            throw new RuntimeException("Access denied");
        }
        return "Welcome, Doctor!";
    }

    @GetMapping("/patient")
    public String patientEndpoint(@RequestHeader("Authorization") String token) {
        String role = jwtUtil.extractRole(token.replace("Bearer ", ""));
        if (!role.equals("PATIENT")) {
            throw new RuntimeException("Access denied");
        }
        return "Welcome, Patient!";
    }
}
