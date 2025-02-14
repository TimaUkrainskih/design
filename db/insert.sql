insert into roles (role_name) values
('Master'),
('Manager'),
('Guest'),
('User');

insert into rules (permission) values
('MANAGE_SYSTEM'),
('MANAGE_USERS'),
('CREATE_ITEM'),
('VIEW_ITEM'),
('EDIT_ITEM'),
('DELETE_ITEM'),
('ADD_COMMENT'),
('UPLOAD_FILE');

insert into role_rules (role_id, rule_id) values
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),

(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),

(3, 3),
(3, 4),
(3, 6),
(3, 7),
(3, 8),

(4, 3),
(4, 4);

insert into users (role_id, full_name, login, password, created_at) values
(1, 'Alina Molla', 'admin@example.com', 'XnaDXCtlF', '1982-08-24 12:47:54'),
(2, 'Xiaoping Alves', 'alves@example.com', 'cX7vJpDhc', '1998-11-09 06:42:06'),
(3, 'John Doe', 'john@example.com', 'JnJXMBo', '2015-11-09 20:40:31');

insert into states (name) values
('New'),
('In Progress'),
('Resolved'),
('Closed');
('Deleted');

insert into categories (name) values
('Technical Support'),
('General Inquiry'),
('Account Issues'),
('Bug Report'),
('Security Concern'),
('Network Problems'),
('Hardware Issues'),
('Software Issues'),
('Service Downtime'),
('Feedback and Suggestions');

insert into items (user_id, category_id, state_id, title, description) values
(3, 1, 1, 'Internet not working', 'The internet connection is down in my office.', '2016-02-04 13:32:46'),
(3, 3, 1, 'General question', 'How can I update my profile details?', '2024-10-16 04:17:38');

insert into comments (item_id, author_id, comment) values
(1, 2, 'We are investigating the issue.'),
(3, 2, 'You can update your profile from the settings page.');

insert into attachs (item_id, author_id, file_path) values
(1, 3, '/uploads/screenshot1.png'),
(1, 3, '/uploads/screenshot2.png');