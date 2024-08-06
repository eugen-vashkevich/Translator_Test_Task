CREATE TABLE translation_requests
(
    id              SERIAL PRIMARY KEY,
    ip_address      VARCHAR(255),
    input_text      VARCHAR(255),
    translated_text VARCHAR(255),
    source_language VARCHAR(10),
    target_language VARCHAR(10)
);