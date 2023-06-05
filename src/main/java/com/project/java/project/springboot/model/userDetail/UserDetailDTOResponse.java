package com.project.java.project.springboot.model.userDetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.java.project.springboot.model.enums.RoleEnum;
import com.project.java.project.springboot.model.pagination.PaginationResponse;
import com.project.java.project.springboot.model.userBookings.UserBookingsEntity;
import com.project.java.project.springboot.model.userBookings.UserBookingsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailDTOResponse {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private Long phoneNumber;

    private RoleEnum userRole;

    private List<UserBookingsResponseDTO> userBookings = new ArrayList<>();

    public UserDetailDTOResponse mapToDTO (UserDetailEntity userDetail) {
        UserDetailDTOResponse userDetailDTOResponse = new UserDetailDTOResponse();
        userDetailDTOResponse.setFirstName(userDetail.getFirstName());
        userDetailDTOResponse.setMiddleName(userDetail.getMiddleName());
        userDetailDTOResponse.setLastName(userDetail.getLastName());
        userDetailDTOResponse.setEmail(userDetail.getEmail());
        userDetailDTOResponse.setPhoneNumber(userDetail.getPhoneNumber());
        userDetailDTOResponse.setUserRole(userDetail.getUserRole());
        List<UserBookingsResponseDTO> userBookingsDTO = convertToUserBookingsResponseDTO(userDetail.getUserBookings());
        userDetailDTOResponse.setUserBookings(userBookingsDTO);
        return userDetailDTOResponse;
    }

    public UserDetailDTOResponse mapToDTOForTraveller (UserDetailEntity userDetail) {
        UserDetailDTOResponse userDetailDTOResponse = new UserDetailDTOResponse();
        List<UserBookingsResponseDTO> userBookingsDTO = convertToUserBookingsResponseDTO(userDetail.getUserBookings());
        userDetailDTOResponse.setUserBookings(userBookingsDTO);
        return userDetailDTOResponse;
    }
//
//    public PaginationResponse<UserDetailDTOResponse> mapToPaginationResponse(List<UserDetailEntity> userDetailList, int currentPage, int pageSize, long totalItems) {
//        PaginationResponse<UserDetailDTOResponse> response = new PaginationResponse<>();
//        List<UserDetailDTOResponse> userDetailDTOList = new ArrayList<>();
//
//        // Convert userDetailList to UserDetailDTOResponse objects
//        for (UserDetailEntity userDetail : userDetailList) {
//            UserDetailDTOResponse userDetailDTO = mapToDTO(userDetail);
//            userDetailDTOList.add(userDetailDTO);
//        }
//
//        response.setData(userDetailDTOList);
//        response.setCurrentPage(currentPage);
//        response.setPageSize(pageSize);
//        response.setTotalItems(totalItems);
//
//        // Calculate the total number of pages
//        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
//        response.setTotalPages(totalPages);
//
//        return response;
//    }


    public UserDetailDTOResponse(UserDetailEntity userDetail) {
        this.setFirstName(userDetail.getFirstName());
        this.setMiddleName(userDetail.getMiddleName());
        this.setLastName(userDetail.getLastName());
        List<UserBookingsResponseDTO> userBookingsDTO = convertToUserBookingsResponseDTO(userDetail.getUserBookings());
        this.setUserBookings(userBookingsDTO);
    }

    private List<UserBookingsResponseDTO> convertToUserBookingsResponseDTO(List<UserBookingsEntity> userBookings) {
        List<UserBookingsResponseDTO> userBookingsDTO = new ArrayList<>();
        for (UserBookingsEntity userBooking : userBookings) {
            UserBookingsResponseDTO userBookingDTO = new UserBookingsResponseDTO(userBooking);
            userBookingsDTO.add(userBookingDTO);
        }
        return userBookingsDTO;
    }
}
