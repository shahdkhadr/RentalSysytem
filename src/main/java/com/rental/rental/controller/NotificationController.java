package com.rental.rental.controller;

import com.rental.rental.dto.NotificationDTO;
import com.rental.rental.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-rental-system")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/notifications/{customerId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByCustomerId(@PathVariable int customerId) {
        List<NotificationDTO> notifications = notificationService.getNotificationsByCustomerId(customerId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/notification")
    public ResponseEntity<NotificationDTO> getNotificationById(@RequestParam int notificationId) {
        NotificationDTO notification = notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(notification);
    }

    @PostMapping("/notification")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
        return ResponseEntity.ok(createdNotification);
    }

    @PutMapping("/notification")
    public ResponseEntity<NotificationDTO> updateNotification(@RequestParam int notificationId, @RequestBody NotificationDTO notificationDTO) {
        NotificationDTO updatedNotification = notificationService.updateNotification(notificationId, notificationDTO);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/notification")
    public ResponseEntity<String> deleteNotification(@RequestParam int notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok("Notification with ID " + notificationId + " has been deleted successfully.");
    }
}
