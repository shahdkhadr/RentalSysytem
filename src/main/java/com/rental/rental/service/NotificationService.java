package com.rental.rental.service;

import com.rental.rental.dto.NotificationDTO;
import com.rental.rental.model.Customer;
import com.rental.rental.model.Notification;
import com.rental.rental.repository.CustomerRepository;
import com.rental.rental.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO getNotificationById(int id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        return convertToDTO(notification);
    }

    public List<NotificationDTO> getNotificationsByCustomerId(int customerId) {
        return notificationRepository.findByCustomer_CustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = convertToEntity(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }

    public NotificationDTO updateNotification(int id, NotificationDTO notificationDTO) {
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));

        existingNotification.setMessage(notificationDTO.getMessage());
        existingNotification.setRead(notificationDTO.isRead());
        existingNotification.setTimestamp(notificationDTO.getTimestamp());

        Notification updatedNotification = notificationRepository.save(existingNotification);
        return convertToDTO(updatedNotification);
    }

    public void deleteNotification(int id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        notificationRepository.delete(notification);
    }

    private NotificationDTO convertToDTO(Notification notification) {
        return NotificationDTO.builder()
                .notificationId(notification.getNotificationId())
                .message(notification.getMessage())
                .isRead(notification.isRead())
                .timestamp(notification.getTimestamp())
                .customerId(notification.getCustomer().getCustomerId())
                .build();
    }

    private Notification convertToEntity(NotificationDTO notificationDTO) {
        Customer customer = customerRepository.findById(notificationDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + notificationDTO.getCustomerId()));

        return Notification.builder()
                .notificationId(notificationDTO.getNotificationId())
                .message(notificationDTO.getMessage())
                .isRead(notificationDTO.isRead())
                .timestamp(notificationDTO.getTimestamp())
                .customer(customer)
                .build();
    }
}
