package org.example.auth.services;

import org.example.auth.ApprovalRequest;
import org.example.models.User;
import org.example.auth.repositories.ApprovalRequestRepository;
import org.example.repositories.UserRepository;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ApprovalRequestService {

    @Inject
    private ApprovalRequestRepository approvalRequestRepository;

    @Inject
    private UserRepository userRepository;


    public List<ApprovalRequest> getPendingRequests() {
        return approvalRequestRepository.findAllPending();
    }


    public String approveRequest(Long requestId) {
        Optional<ApprovalRequest> requestOpt = approvalRequestRepository.findById(requestId);

        if (requestOpt.isEmpty()) {
            return "Заявка не найдена.";
        }

        ApprovalRequest request = requestOpt.get();
        User user = request.getUser();

        if (user != null) {

            user.setRole(User.ROLE_ADMIN);
            userRepository.save(user);


            request.setApproved(true);
            approvalRequestRepository.save(request);

            return "Заявка одобрена. Пользователь " + user.getUsername() + " стал администратором.";
        } else {
            return "Ошибка: пользователь для этой заявки не найден.";
        }
    }

    public String rejectRequest(Long requestId) {
        Optional<ApprovalRequest> requestOpt = approvalRequestRepository.findById(requestId);

        if (requestOpt.isEmpty()) {
            return "Заявка не найдена.";
        }

        approvalRequestRepository.delete(requestOpt.get());
        return "Заявка отклонена.";
    }
}
