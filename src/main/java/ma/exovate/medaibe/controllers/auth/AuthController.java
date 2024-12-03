package ma.exovate.medaibe.controllers.auth;

import ma.exovate.medaibe.dtos.authDTO.ForgotPasswordRequest;
import ma.exovate.medaibe.dtos.authDTO.LoginRequest;
import ma.exovate.medaibe.dtos.authDTO.RegistrationRequest;
import ma.exovate.medaibe.services.AuthSer.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        String response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        String response = authService.verifyEmail(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        String response = authService.forgotPassword(request.getEmail());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");

        // Add logic to validate the token and reset the password
        authService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully.");
    }

}
