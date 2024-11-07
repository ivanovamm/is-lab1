package org.example.auth.controllers;

import org.example.auth.ApprovalRequest;
import org.example.auth.services.ApprovalRequestService;
import org.example.auth.ApprovalRequest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import java.util.List;

@Path("/approvals")
public class ApprovalController {

    @Inject
    private ApprovalRequestService approvalRequestService;

    @GET
    public List<ApprovalRequest> getPendingRequests() {
        return approvalRequestService.getPendingRequests();
    }

    @PUT
    @Path("/{id}/approve")
    public String approveRequest(@PathParam("id") Long id) {
        return approvalRequestService.approveRequest(id);
    }
}
