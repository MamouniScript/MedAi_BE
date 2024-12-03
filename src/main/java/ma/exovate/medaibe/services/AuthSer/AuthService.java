package ma.exovate.medaibe.services.AuthSer;

import ma.exovate.medaibe.config.JwtUtil;
import ma.exovate.medaibe.dtos.authDTO.LoginRequest;
import ma.exovate.medaibe.dtos.authDTO.RegistrationRequest;
import ma.exovate.medaibe.entities.authEntity.Role;
import ma.exovate.medaibe.entities.authEntity.User;
import ma.exovate.medaibe.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    public String register(RegistrationRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered.");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        user.setEnabled(false); // Initially set to false
        userRepository.save(user);

        // Generate verification token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        String verificationLink = "http://localhost:8081/api/auth/verify-email?token=" + token;

        // Send verification email
        String emailContent = "<p>Hi " + user.getFirstName() + ",</p>"
                + "<p>Please verify your email by clicking the link below:</p>"
                + "<a href=\"" + verificationLink + "\">Verify Email</a>";
        emailService.sendEmail(user.getEmail(), "Email Verification", emailContent);

        return "User registered successfully. Please verify your email.";
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Email not verified.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    public String verifyEmail(String token) {
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setEnabled(true);
        userRepository.save(user);

        return "Email verified successfully.";
    }
    // Forgot Password - Generate Reset Token
    public String forgotPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with this email does not exist.");
        }

        // Generate reset token
        User user = userOptional.get();
        String resetToken = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        // Send email with reset link
        String resetLink = "http://localhost:3000/reset-password?token=" + resetToken;
        String emailContent = "<p>Hi " + user.getFirstName() + ",</p>"
                + "<p>You requested to reset your password. Click the link below to reset it:</p>"
                + "<a href=\"" + resetLink + "\">Reset Password</a>";
        emailService.sendEmail(user.getEmail(), "Reset Your Password", emailContent);

        return "Password reset link sent to email.";
    }

    // Reset Password - Validate Token and Update Password
    public String resetPassword(String token, String newPassword) {
        // Extract email from token
        String email = jwtUtil.extractEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid token or user does not exist."));

        // Update the password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password reset successfully.";
    }
}
