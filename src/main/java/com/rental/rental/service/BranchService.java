package com.rental.rental.service;

import com.rental.rental.dto.BranchDTO;
import com.rental.rental.dto.DepartmentDTO;
import com.rental.rental.model.*;
import com.rental.rental.repository.BranchRepository;
import com.rental.rental.repository.PaymentRepository;
import com.rental.rental.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<BranchDTO> getAllBranches(){
        return branchRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BranchDTO getBranchById( int branchId ){
        return branchRepository
                .findById(branchId)
                .map(this::convertToDTO)
                .orElseThrow(()->new RuntimeException("Branch not found"));
    }

    public BranchDTO saveBranch(BranchDTO branchDTO){
        Branch branch = convertToEntity(branchDTO);
        Branch savedBranch = branchRepository.save(branch);
        return convertToDTO(savedBranch);
    }

    public BranchDTO updateBranch(int branchId, BranchDTO branchDTO) {
        Optional<Branch> existingBranchOpt = branchRepository.findById(branchId);

        if( existingBranchOpt.isPresent() ){
            Branch existingBranch = existingBranchOpt.get();

            if( branchDTO.getBranchName() != null && !branchDTO.getBranchName().isEmpty() ){
                existingBranch.setBranchName(branchDTO.getBranchName());
            }

            if( branchDTO.getBranchLocation() != null && !branchDTO.getBranchLocation().isEmpty() ){
                existingBranch.setBranchLocation(branchDTO.getBranchLocation());
            }

            if( branchDTO.getContactDetails() != null && !branchDTO.getContactDetails().isEmpty() ){
                existingBranch.setContactDetails(branchDTO.getContactDetails());
            }

            if( branchDTO.getBranchName() != null && !branchDTO.getBranchName().isEmpty() ){
                existingBranch.setBranchName(branchDTO.getBranchName());
            }

            if( branchDTO.getReservationId() != 0 ){
                Optional<Reservation> reservation = reservationRepository.findById(branchDTO.getReservationId());
                if(reservation.isEmpty()){
                    throw new IllegalArgumentException("Reservation not found");
                }
                existingBranch.setReservation(reservation.get());
            }

            if( branchDTO.getPaymentId() != 0 ){
                Optional<Payment> payment = paymentRepository.findById(branchDTO.getPaymentId());
                if(payment.isEmpty()){
                    throw new IllegalArgumentException("Payment not found");
                }
                existingBranch.setPayment(payment.get());
            }

            Branch updatedBranch = branchRepository.save(existingBranch);
            return convertToDTO(updatedBranch);
        }

        return null;
    }

    public void deleteBranch( int branchId ){
        branchRepository.deleteById(branchId);
    }

    private Branch convertToEntity(BranchDTO branchDTO) {

        Optional<Reservation> reservation = reservationRepository.findById(branchDTO.getReservationId());
        if(reservation.isEmpty()){
            throw new IllegalArgumentException("Reservation not found");
        }

        Optional<Payment> payment = paymentRepository.findById(branchDTO.getPaymentId());
        if(payment.isEmpty()){
            throw new IllegalArgumentException("Payment not found");
        }

        return Branch.builder()
                .branchId(branchDTO.getBranchId())
                .branchName(branchDTO.getBranchName())
                .branchLocation(branchDTO.getBranchLocation())
                .contactDetails(branchDTO.getContactDetails())
                .reservation(reservation.get())
                .payment(payment.get())
                .build();
    }

    private BranchDTO convertToDTO(Branch branch) {
        return BranchDTO.builder()
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .branchLocation(branch.getBranchLocation())
                .contactDetails(branch.getContactDetails())
                .reservationId(branch.getReservation().getReservationId())
                .paymentId(branch.getPayment().getPaymentId())
                .build();
    }


}
