\connect auth_db;

INSERT INTO roles (role_name, description)
VALUES
('ROLE_ADMIN', 'System administrator with full access'),
('ROLE_SUPPORT', 'Support staff for handling customer issues'),
('ROLE_CUSTOMER', 'Customer who books flights')
ON CONFLICT (role_name) DO NOTHING;

INSERT INTO privileges (privilege_name, description)
VALUES
('BOOK_FLIGHT', 'Book a flight ticket'),
('CANCEL_BOOKING', 'Cancel an existing booking'),
('VIEW_BOOKING', 'View booking details'),
('MANAGE_USERS', 'Create or manage users'),
('MANAGE_FLIGHTS', 'Create or update flights'),
('PROCESS_REFUND', 'Process payment refunds')
ON CONFLICT (privilege_name) DO NOTHING;

INSERT INTO role_privileges (role_id, privilege_id)
SELECT r.id, p.id
FROM roles r, privileges p
WHERE r.role_name = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;

INSERT INTO role_privileges (role_id, privilege_id)
SELECT r.id, p.id
FROM roles r, privileges p
WHERE r.role_name = 'ROLE_SUPPORT'
AND p.privilege_name IN ('VIEW_BOOKING', 'PROCESS_REFUND')
ON CONFLICT DO NOTHING;

INSERT INTO role_privileges (role_id, privilege_id)
SELECT r.id, p.id
FROM roles r, privileges p
WHERE r.role_name = 'ROLE_CUSTOMER'
AND p.privilege_name IN ('BOOK_FLIGHT', 'CANCEL_BOOKING', 'VIEW_BOOKING')
ON CONFLICT DO NOTHING;

INSERT INTO users (
    username,
    email,
    password,
    status,
    is_account_non_locked,
    is_enabled,
    is_credentials_non_expired
)
VALUES (
    'admin',
    'admin@flight.com',
    '$2a$10$b2czDFU2BDn0weaD5p2PZePwcgRoXrc879kJQnpGZPSB6zeQAJnDS',
    'ACTIVE',
    TRUE,
    TRUE,
    TRUE
)
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.username = 'admin'
AND r.role_name = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;