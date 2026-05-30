package com.example.edutrackbackend.auth.jwt;

import com.example.edutrackbackend.auth.service.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    // Utility Class for JWT operations
    private final JwtUtil util;

    // Loads user information from database
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Set current request path
        String path = request.getRequestURI();

        // Read authentication header
        String authHeader = request.getHeader("Authorization");

        /**
         * If no Bearer token is present:
         * Continue the filter chain
         *
         */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Remove "Bearer " prefix and extract raw JWT Token
        String token = authHeader.substring(7);

        try {
            String email;

            /**
             * Extract email from JWT payload
             *
             * This verifies:
             * - JWT format
             * - JWT signature
             * - Token structure
             */
            try {
                email = util.extractEmail(token);
            } catch (JwtException | IllegalArgumentException e) {
                log.warn("[JWT] Invalid access token: {}", e.getMessage());
                filterChain.doFilter(request, response);
                return;
            }

            /**
             * Only authenticate if:
             * 1. Email exists in token
             * 2. No user is already authenticated
             */
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                /**
                 * Validate token:
                 * - Not expired
                 * - Email matches
                 * - Signature is valid
                 */
                if (!util.validateToken(token, email)) {
                    log.warn("[JWT] Invalid token for user={}", email);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"Unauthorized\"}");
                    return;
                }

                /**
                 * Load user from the database
                 *
                 * this ensures:
                 * - User still exists
                 * - Latest roles/authorities are used
                 */
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);


                //Create spring security authentication object
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                        );

                /**
                 * Attach requests details such as:
                 * - IP address
                 * - Session ID
                 */
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                // Stored authenticated user in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (JwtException | IllegalArgumentException e) {

            // Token parsing or validation failed
            log.warn("[JWT] Token processing failed: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Unauthorized\"}");
            return;

        } catch (Exception e) {

            // User lookup or authentication creation failed
            log.warn("[JWT] Authentication lookup failed: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Unauthorized\"}");
            return;
        }

        // Continue Processing status
        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getRequestURI();

        /**
         * Completely skip this filter for:
         * - OPTIONS request
         * - Swagger/OpenAPI endpoints
         * - Root endpoint
         * - Login endpoint
         * - Refresh endpoint
         *
         * These requests never enter doFilterInternal
         */
        return "OPTIONS".equalsIgnoreCase(request.getMethod())
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")
                || path.startsWith("/swagger-ui.html")
                || path.equals("/")
                || path.startsWith("/api/auth/login")
                || path.startsWith("/api/auth/refresh");
    }
}