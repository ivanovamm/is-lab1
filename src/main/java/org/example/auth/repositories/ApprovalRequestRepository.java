package org.example.auth.repositories;

import org.example.auth.ApprovalRequest;

import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApprovalRequestRepository {

    @Inject
    private DataSource dataSource;

    // Найти заявку по ID
    public Optional<ApprovalRequest> findById(Long id) {
        String query = "SELECT * FROM approval_requests WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ApprovalRequest request = new ApprovalRequest();
                    request.setId(resultSet.getLong("id"));
                    request.setApproved(resultSet.getBoolean("is_approved"));
                    request.setId(resultSet.getLong("user_id"));
                    return Optional.of(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public void save(ApprovalRequest request) {
        String query = "INSERT INTO approval_requests (user_id, approved) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, request.getUser().getId());
            statement.setBoolean(2, request.isApproved());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ApprovalRequest> findAllPending() {
        List<ApprovalRequest> requests = new ArrayList<>();
        String query = "SELECT * FROM approval_requests WHERE approved = false";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ApprovalRequest request = new ApprovalRequest();
                request.setId(resultSet.getLong("id"));
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public void delete(ApprovalRequest request) {
        String query = "DELETE FROM approval_requests WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, request.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
