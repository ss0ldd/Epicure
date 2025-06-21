package ru.itis.epicure.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final Logger userLogger = LoggerFactory.getLogger("ru.itis.epicure.services.UserService");
    private static final Logger mainLogger = LoggerFactory.getLogger("ru.itis.epicure.services.PostService");

    @ExceptionHandler(UserNotFoundException.class)
    public Object handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        userLogger.error("User not found: {}", ex.getMessage());
        
        if (isAjaxRequest(request)) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "User not found");
            response.put("message", ex.getMessage());
            response.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            ModelAndView mav = new ModelAndView("error/404");
            mav.addObject("errorMessage", "Пользователь не найден");
            mav.addObject("errorDetails", ex.getMessage());
            mav.addObject("statusCode", 404);
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
    }

    @ExceptionHandler(PostNotFoundException.class)
    public Object handlePostNotFound(PostNotFoundException ex, WebRequest request) {
        mainLogger.error("Post not found: {}", ex.getMessage());
        
        if (isAjaxRequest(request)) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Post not found");
            response.put("message", ex.getMessage());
            response.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            ModelAndView mav = new ModelAndView("error/404");
            mav.addObject("errorMessage", "Пост не найден");
            mav.addObject("errorDetails", ex.getMessage());
            mav.addObject("statusCode", 404);
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public Object handleRestaurantNotFound(RestaurantNotFoundException ex, WebRequest request) {
        mainLogger.error("Restaurant not found: {}", ex.getMessage());
        
        if (isAjaxRequest(request)) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Restaurant not found");
            response.put("message", ex.getMessage());
            response.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            ModelAndView mav = new ModelAndView("error/404");
            mav.addObject("errorMessage", "Ресторан не найден");
            mav.addObject("errorDetails", ex.getMessage());
            mav.addObject("statusCode", 404);
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Object handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        userLogger.error("Access denied: {}", ex.getMessage());
        
        if (isAjaxRequest(request)) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Access denied");
            response.put("message", "У вас нет прав для выполнения этого действия");
            response.put("status", 403);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        } else {
            ModelAndView mav = new ModelAndView("error/403");
            mav.addObject("errorMessage", "Доступ запрещен");
            mav.addObject("errorDetails", "У вас нет прав для выполнения этого действия");
            mav.addObject("statusCode", 403);
            mav.setStatus(HttpStatus.FORBIDDEN);
            return mav;
        }
    }

    @ExceptionHandler(AuthenticationException.class)
    public Object handleAuthentication(AuthenticationException ex, WebRequest request) {
        userLogger.error("Authentication error: {}", ex.getMessage());
        
        if (isAjaxRequest(request)) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Authentication failed");
            response.put("message", "Неверные учетные данные");
            response.put("status", 401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else {
            ModelAndView mav = new ModelAndView("error/401");
            mav.addObject("errorMessage", "Ошибка аутентификации");
            mav.addObject("errorDetails", "Неверные учетные данные");
            mav.addObject("statusCode", 401);
            mav.setStatus(HttpStatus.UNAUTHORIZED);
            return mav;
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Object handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        mainLogger.error("Illegal argument: {}", ex.getMessage());
        
        if (isAjaxRequest(request)) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Invalid argument");
            response.put("message", ex.getMessage());
            response.put("status", 400);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            ModelAndView mav = new ModelAndView("error/400");
            mav.addObject("errorMessage", "Некорректные данные");
            mav.addObject("errorDetails", ex.getMessage());
            mav.addObject("statusCode", 400);
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
    }

    @ExceptionHandler(Exception.class)
    public Object handleGenericException(Exception ex, WebRequest request) {
        mainLogger.error("Unexpected error: {}", ex.getMessage(), ex);
        
        if (isAjaxRequest(request)) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", "Произошла непредвиденная ошибка");
            response.put("status", 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } else {
            ModelAndView mav = new ModelAndView("error/500");
            mav.addObject("errorMessage", "Внутренняя ошибка сервера");
            mav.addObject("errorDetails", "Произошла непредвиденная ошибка");
            mav.addObject("statusCode", 500);
            mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return mav;
        }
    }

    private boolean isAjaxRequest(WebRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        String accept = request.getHeader("Accept");
        return "XMLHttpRequest".equals(requestedWith) || 
               (accept != null && accept.contains("application/json"));
    }
} 