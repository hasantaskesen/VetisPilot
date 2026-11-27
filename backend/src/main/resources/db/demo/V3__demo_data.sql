-- =====================================================================
-- VetisPilot – V3: demo data (the same fictional branch and accounts as the prototype)
--
-- This file lives in db/demo, NOT in db/migration. It is loaded only while
-- spring.flyway.locations contains classpath:db/demo (the default for local development).
-- A real installation leaves db/demo out and starts with an empty database.
--
-- The demo passwords are public on purpose (they are shown on the prototype's login page).
-- Only their BCrypt hashes are stored here, never the passwords themselves.
-- =====================================================================

INSERT INTO sellers (name) VALUES ('Lindenkorb Supermarkt GmbH');

INSERT INTO branches (seller_id, name, street, postal_code, city, opening_hours)
SELECT id, 'Berlin-Spandau', 'Lindenweg 12', '13597', 'Berlin', 'Mo–Sa 08:00–22:00'
FROM sellers WHERE name = 'Lindenkorb Supermarkt GmbH';

INSERT INTO channels (code, name, primary_color, dark_color, light_color) VALUES
    ('HTGO', 'HTGO Market', '#1D4ED8', '#0B2A6F', '#DBE6FF'),
    ('GK',   'GK Market',   '#7C4A21', '#3E2410', '#F3E6D3');

-- four branch employees, as in the prototype (Nadia#2026, Lukas#2026, Mira#2026, Tobias#2026)
INSERT INTO users (email, password_hash, full_name, role, seller_id, branch_id, language, must_change_password)
SELECT u.email, u.hash, u.name, 'BRANCH_EMPLOYEE', b.seller_id, b.id, 'tr', FALSE
FROM branches b
CROSS JOIN (VALUES
    ('nadia.brenner@lindenkorb.example',  '$2a$10$KD1rlEZj/oJ.O2QOyt9BYecFF5elMisji5V.AhcR7auuOZD4uVfyO', 'Nadia Brenner'),
    ('lukas.hartmann@lindenkorb.example', '$2a$10$qkfX9qUn4Yh6iewSbcLUxOBz2SbAasX1CeKiAnPoTGs9Z2sKnWeNG', 'Lukas Hartmann'),
    ('mira.albers@lindenkorb.example',    '$2a$10$EF0Zg35JBdBkwcy5iJWbeu/W2Mrv5d/EW0.twGfWrp/C19BNlMAKC', 'Mira Albers'),
    ('tobias.kraemer@lindenkorb.example', '$2a$10$mzH98GcP8n2fJ5rHnyq9rO34OuvNSL0CYBxfg/RPK8m3UYtLDgOCi', 'Tobias Krämer')
) AS u (email, hash, name)
WHERE b.name = 'Berlin-Spandau';
