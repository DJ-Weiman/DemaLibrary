package com.djw.DemaLibrary.services.impl;

//@Service
//@RequiredArgsConstructor
//public class AuthenticationServiceImpl  {

//    private final AuthenticationManager authManager;
//    private final UserDetailsService userDetailsService;
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    private final Long jwtExpiryMs = 86400000L;
//
//    @Override
//    public UserDetails authenticate(String username, String password) {
//        authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password));
//
//        return userDetailsService.loadUserByUsername(username);
//    }
//
//    @Override
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiryMs))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    @Override
//    public UserDetails validateToken(String token) {
//        String username = extractUsername(token);
//        return userDetailsService.loadUserByUsername(username);
//    }
//
//    private String extractUsername(String token){
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.getSubject();
//    }
//
//
//    private Key getSigningKey(){
//        byte[] keyBytes = secretKey.getBytes();
//
//        System.out.println("SecretKey : " + secretKey);
//
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
