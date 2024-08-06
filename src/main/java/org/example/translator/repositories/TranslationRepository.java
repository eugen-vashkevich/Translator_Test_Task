package org.example.translator.repositories;

import org.example.translator.models.TranslateRequest;
import org.example.translator.repositories.interfaces.ITranslationRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@Repository
public class TranslationRepository implements ITranslationRepository {
  private final JdbcTemplate jdbcTemplate;

  public TranslationRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(TranslateRequest translationRequest) throws SQLException {
    final var sqlQuery =
        "INSERT INTO translation_requests (ip_address, input_text, translated_text, source_language, target_language) VALUES (?, ?, ?, ?, ?)";

    try (Connection connection =
            Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

      preparedStatement.setString(1, translationRequest.getIpAddress());
      preparedStatement.setString(2, translationRequest.getTextToTranslate());
      preparedStatement.setString(3, translationRequest.getTranslatedText());
      preparedStatement.setString(4, translationRequest.getSourceLang());
      preparedStatement.setString(5, translationRequest.getTargetLang());

      final int rowsAffected = preparedStatement.executeUpdate();

      if (rowsAffected == 0) {
        throw new SQLException("Failed to save translation request.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new SQLException(e.getMessage());
    }
  }
}
