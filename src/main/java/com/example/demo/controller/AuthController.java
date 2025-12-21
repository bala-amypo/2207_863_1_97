@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

    User user = userService.findByEmail(request.getEmail());

    if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        return ResponseEntity.status(401).build();
    }

    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getId());
    claims.put("email", user.getEmail());
    claims.put("role", user.getRole().toString()); // ✅ FIX

    String token = jwtUtil.generateToken(claims, user.getEmail());

    AuthResponse response = new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getRole().toString() // ✅ FIX
    );

    return ResponseEntity.ok(response);
}
