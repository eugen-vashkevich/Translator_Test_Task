package org.example.translator.repositories.interfaces;

import org.example.translator.models.TranslateRequest;
import java.sql.SQLException;

public interface ITranslationRepository {
  void save(TranslateRequest translateRequest) throws SQLException;
}
