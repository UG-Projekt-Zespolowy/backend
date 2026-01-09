INSERT INTO public.task_manager_project (id, created_at, description, name) VALUES 
('2c78e0a2-e2ab-4da4-a98f-08ff4b754631', '2026-01-09 19:36:17.976727', 'Web-based system for managing tasks and teams using Spring Boot and React', 'Task Management Platform'),
('649ce3a8-27f0-4173-81f1-90b19c6a5fbc', '2026-01-09 19:37:06.260814', 'Online store with product catalog and payments', 'E-commerce Platform'),
('f45b8e0b-edc2-47a9-9601-c550689a8d11', '2026-01-09 19:37:36.447471', 'Internal HR system for employees and payroll', 'HR Management System'),
('d0a660c7-8ca5-48f7-ac6f-1f64ff242c3b', '2026-01-09 19:38:12.075063', 'Platform for online courses and learning progress', 'Learning Management System');

INSERT INTO public.task_manager_epic (id, description, title, project_id) VALUES 
('e278db52-9137-43c4-9188-5cca46b91077', 'Secure login, JWT authentication, and role management', 'User Authentication & Authorization', '2c78e0a2-e2ab-4da4-a98f-08ff4b754631'),
('4719a557-6c4b-4e46-a442-2cf40133139a', 'Enable teams to work together efficiently with shared tasks, comments, and notifications', 'Team Collaboration Features', '2c78e0a2-e2ab-4da4-a98f-08ff4b754631'),
('4611331d-6b6b-4582-8c47-0702331af993', 'Manage products, categories, and inventory in the online store', 'Product Catalog Management', '649ce3a8-27f0-4173-81f1-90b19c6a5fbc'),
('f24a19f3-c3f0-41f7-a636-809467de76d4', 'Manage employee profiles, roles, and departments', 'Employee Management', 'f45b8e0b-edc2-47a9-9601-c550689a8d11'),
('c28aed67-ffd9-4554-9faa-43c834f54ca7', 'Handle salary calculation, deductions, and pay slips', 'Payroll Processing', 'f45b8e0b-edc2-47a9-9601-c550689a8d11'),
('9d3d9234-0565-400c-8685-eb02a7569f3a', 'Create, organize, and manage online courses', 'Course Management', 'd0a660c7-8ca5-48f7-ac6f-1f64ff242c3b');

INSERT INTO public.task_manager_user (id, keycloak_id, name, username) VALUES 
('a9d0f603-9045-4f83-878e-3786713e1b63', '8b1be6af-0ae8-4b65-99a3-f11752093357', 'Pawel Rozanski', 'pawelrozanski@wp.pl'),
('de6314ae-8acd-4c0c-80ca-67928463cbf6', '7f627bbe-b935-4b34-9ae8-9d038e71fa07', 'Magdalena Wisniewska', 'magdawisniewska@gmail.com'),
('3b1c779b-a368-4e5e-941d-319ac5bfebea', 'f49288a7-1011-4ee0-b21b-6b988756a6db', 'Damian Szulc', 'damianszulc@yahooo.com'),
('df7a0043-909b-43b0-bab7-1f884966e43f', 'fecd71dd-b3fb-49d2-8407-d7d31d12f43b', 'Robert Dabrowski', 'robertdabrowski@wp.pl'),
('9c8befda-78d3-42ed-894c-c512a92a2caf', '1d61b220-3988-4bbd-baa0-ee040d937705', 'Paulina Kukulka', 'paulinakukulka@wp.pl'),
('11d06aa9-59ba-46d4-b686-0bc7adf79e0e', '95fbab48-7602-4b2f-b5f3-dfcafe2a0e18', 'Borys Szyc', 'borysszyc@gmail.com'),
('833ff96c-7ad6-4ff9-8f62-20cacbba45d6', '287fbcfd-8986-4fc4-a66a-9b0af30187ac', 'Weronika Malecka', 'weronikamalecka@wp.pl'),
('b9ca8374-8e66-4842-8b6d-63db66552d94', '53422375-d568-4d4d-81a9-6121b32b04b5', 'Pawel Rasz', 'pawelrasz@gmail.com'),
('3eaf8119-2252-46dd-8df8-8aee425f2e76', '354cb1e3-44e8-4db1-9cb3-db1a373cb915', 'Filip Maciejewski', 'filipmaciejewski@wp.pl'),
('b8eedd83-d991-4ed2-90e1-e0b9a6454975', '1fb17c77-0f19-4851-88fa-0cc9ec074ea3', 'Agata Kuc', 'agatakuc@yahooo.com');

INSERT INTO public.task_manager_issue (id, created_at, description, status, story_point, title, assignee_id, epic_id, reporter_id) VALUES 
('0411f485-cf35-4bb7-96c9-25c88d922718', '2026-01-09 19:47:56.390457', 'Create a security filter to validate JWT tokens in the request header', 'TO_DO', 5, 'Implement JWT Filter', '9c8befda-78d3-42ed-894c-c512a92a2caf', 'e278db52-9137-43c4-9188-5cca46b91077', 'a9d0f603-9045-4f83-878e-3786713e1b63'),
('c1ab62ac-02ab-40a1-aa4e-031f8fb16c1a', '2026-01-09 19:50:41.806536', 'Implement role checks to restrict access to certain endpoints based on user roles', 'TO_DO', 6, 'Role-Based Access Control', '833ff96c-7ad6-4ff9-8f62-20cacbba45d6', 'e278db52-9137-43c4-9188-5cca46b91077', 'a9d0f603-9045-4f83-878e-3786713e1b63'),
('7eec4c6b-efb0-45e8-9185-93a964c65ed7', '2026-01-09 19:53:18.126525', 'Allow users to add, edit, and delete comments on tasks', 'TO_DO', 1, 'Implement Task Comments', '833ff96c-7ad6-4ff9-8f62-20cacbba45d6', '4719a557-6c4b-4e46-a442-2cf40133139a', 'a9d0f603-9045-4f83-878e-3786713e1b63'),
('9bc82d3d-85f8-4c39-a396-7bac7996ae91', '2026-01-09 19:54:00.148138', 'Send notifications to team members when tasks are updated or assigned', 'TO_DO', 4, 'Team Notifications', NULL, '4719a557-6c4b-4e46-a442-2cf40133139a', 'a9d0f603-9045-4f83-878e-3786713e1b63'),
('8a515101-b479-4689-aa4d-0a71313f4e46', '2026-01-09 19:57:06.141172', 'Send notifications to team members when tasks are updated or assigned', 'TO_DO', 4, 'Team Notifications', 'b9ca8374-8e66-4842-8b6d-63db66552d94', '4611331d-6b6b-4582-8c47-0702331af993', 'de6314ae-8acd-4c0c-80ca-67928463cbf6'),
('b21a052a-0d55-47d7-b7d1-1865ba0d6e07', '2026-01-09 19:57:33.021265', 'Develop REST endpoints to create, read, update, and delete products', 'TO_DO', 2, 'Create Product CRUD API', 'b9ca8374-8e66-4842-8b6d-63db66552d94', '4611331d-6b6b-4582-8c47-0702331af993', 'de6314ae-8acd-4c0c-80ca-67928463cbf6'),
('4f60bfbc-fbab-4cb7-8290-fb1cecc9b019', '2026-01-09 19:58:27.753526', 'Add, edit, and remove product categories', 'TO_DO', 2, 'Category Management', '3eaf8119-2252-46dd-8df8-8aee425f2e76', '4611331d-6b6b-4582-8c47-0702331af993', 'de6314ae-8acd-4c0c-80ca-67928463cbf6'),
('a7e21176-4c63-48aa-aa1a-66be5b1fe4b7', '2026-01-09 20:01:33.745254', 'Create endpoints to add, view, edit, and delete employee records', 'TO_DO', 3, 'Employee CRUD API', NULL, 'f24a19f3-c3f0-41f7-a636-809467de76d4', '3b1c779b-a368-4e5e-941d-319ac5bfebea'),
('7688cd3f-06ea-409f-bd53-29183ea94c91', '2026-01-09 20:02:03.726126', 'Assign employees to departments and manage department hierarchy', 'TO_DO', 1, 'Department Assignment', 'b8eedd83-d991-4ed2-90e1-e0b9a6454975', 'f24a19f3-c3f0-41f7-a636-809467de76d4', '3b1c779b-a368-4e5e-941d-319ac5bfebea'),
('e2ca9a24-1115-4b13-b8cb-5959e23ff768', '2026-01-09 20:02:57.065253', 'Implement logic to calculate salaries based on working hours and allowances', 'TO_DO', 11, 'Salary Calculation Module', 'b8eedd83-d991-4ed2-90e1-e0b9a6454975', 'c28aed67-ffd9-4554-9faa-43c834f54ca7', '3b1c779b-a368-4e5e-941d-319ac5bfebea'),
('f4fcf749-62e7-4ec1-835d-58548ec20e56', '2026-01-09 20:06:34.726161', 'Allow splitting courses into modules and lessons', 'TO_DO', 3, 'Module & Lesson Management', NULL, '9d3d9234-0565-400c-8685-eb02a7569f3a', 'df7a0043-909b-43b0-bab7-1f884966e43f'),
('b5b9f6a6-2cb2-45c0-9c92-03590a8b9e6a', '2026-01-09 20:07:31.455278', 'Enable uploading videos, documents, and other resources for lessons', 'TO_DO', 4, 'Upload Course Materials', '833ff96c-7ad6-4ff9-8f62-20cacbba45d6', '9d3d9234-0565-400c-8685-eb02a7569f3a', 'df7a0043-909b-43b0-bab7-1f884966e43f'),
('673907fe-94aa-49c2-ba7c-ade468ff0391', '2026-01-09 20:05:53.678338', 'Develop endpoints to create, edit, view, and delete courses', 'DONE', 6, 'Course CRUD API', NULL, '9d3d9234-0565-400c-8685-eb02a7569f3a', 'df7a0043-909b-43b0-bab7-1f884966e43f'),
('f0c8a95d-86c7-477e-ae63-f249930b7687', '2026-01-09 19:49:46.327161', 'Develop a REST endpoint to handle user login and return JWT token upon successful authentication', 'READY_FOR_REVIEW', 2, 'Create Login Endpoint', NULL, 'e278db52-9137-43c4-9188-5cca46b91077', 'a9d0f603-9045-4f83-878e-3786713e1b63');

INSERT INTO public.task_manager_user_project (id, is_owner, joined_at, role, project_id, user_id) VALUES 
('b82876d5-c35a-48c8-aa95-843ab9828b98', true, '2026-01-09 19:36:17.979601', 'PROJECT_MANAGER', '2c78e0a2-e2ab-4da4-a98f-08ff4b754631', 'a9d0f603-9045-4f83-878e-3786713e1b63'),
('5c129a2d-dc21-42e4-a874-804e3f9cc443', true, '2026-01-09 19:37:06.262093', 'PROJECT_MANAGER', '649ce3a8-27f0-4173-81f1-90b19c6a5fbc', 'de6314ae-8acd-4c0c-80ca-67928463cbf6'),
('19651f20-dff3-457e-8272-f09d9b5961fc', true, '2026-01-09 19:37:36.448059', 'PROJECT_MANAGER', 'f45b8e0b-edc2-47a9-9601-c550689a8d11', '3b1c779b-a368-4e5e-941d-319ac5bfebea'),
('2e48dc6d-afd7-4f02-9a68-67bd2fa09e24', true, '2026-01-09 19:38:12.075307', 'PROJECT_MANAGER', 'd0a660c7-8ca5-48f7-ac6f-1f64ff242c3b', 'df7a0043-909b-43b0-bab7-1f884966e43f'),
('45e59a3a-3635-496b-a70e-4c8ba2d5a48e', false, '2026-01-09 19:40:26.870334', 'MEMBER', '2c78e0a2-e2ab-4da4-a98f-08ff4b754631', '9c8befda-78d3-42ed-894c-c512a92a2caf'),
('92e6acd2-9fc0-440d-b6b2-88031265c3c8', false, '2026-01-09 19:44:21.466575', 'MEMBER', '2c78e0a2-e2ab-4da4-a98f-08ff4b754631', '11d06aa9-59ba-46d4-b686-0bc7adf79e0e'),
('ad96fbfd-c815-441d-81c2-43ccfd872cc8', false, '2026-01-09 19:44:27.489665', 'MEMBER', '2c78e0a2-e2ab-4da4-a98f-08ff4b754631', '833ff96c-7ad6-4ff9-8f62-20cacbba45d6'),
('701d0a4c-6849-4a59-92ad-0109e1e482f3', false, '2026-01-09 19:44:45.738462', 'MEMBER', '649ce3a8-27f0-4173-81f1-90b19c6a5fbc', 'b9ca8374-8e66-4842-8b6d-63db66552d94'),
('954b74fd-1535-4151-abb9-012f47cb6be1', false, '2026-01-09 19:44:51.697105', 'MEMBER', '649ce3a8-27f0-4173-81f1-90b19c6a5fbc', '3eaf8119-2252-46dd-8df8-8aee425f2e76'),
('eae0ee21-cf16-40c0-9811-7d64db0062ff', false, '2026-01-09 19:45:02.814247', 'MEMBER', 'f45b8e0b-edc2-47a9-9601-c550689a8d11', '3eaf8119-2252-46dd-8df8-8aee425f2e76'),
('9d57cc81-4301-4b58-b091-e01450d1bab5', false, '2026-01-09 19:45:09.260896', 'MEMBER', 'f45b8e0b-edc2-47a9-9601-c550689a8d11', 'b8eedd83-d991-4ed2-90e1-e0b9a6454975'),
('3b8dc200-2bb7-47f6-9215-390523a7d709', false, '2026-01-09 19:45:25.507595', 'MEMBER', 'd0a660c7-8ca5-48f7-ac6f-1f64ff242c3b', '3b1c779b-a368-4e5e-941d-319ac5bfebea'),
('e3b07124-b6ef-4c4a-9722-e5458eeaa0b2', false, '2026-01-09 19:45:31.116866', 'MEMBER', 'd0a660c7-8ca5-48f7-ac6f-1f64ff242c3b', '11d06aa9-59ba-46d4-b686-0bc7adf79e0e'),
('b1cfc936-8373-4d1d-aafb-c0331faf6f69', false, '2026-01-09 19:45:39.327268', 'MEMBER', 'd0a660c7-8ca5-48f7-ac6f-1f64ff242c3b', '833ff96c-7ad6-4ff9-8f62-20cacbba45d6'),
('1fd315a9-7603-45ad-b6b9-8901773bccc6', false, '2026-01-09 20:08:10.065822', 'MEMBER', '649ce3a8-27f0-4173-81f1-90b19c6a5fbc', 'b8eedd83-d991-4ed2-90e1-e0b9a6454975'),
('555361fd-cf52-41ce-8528-35ab0f3e95c9', false, '2026-01-09 20:08:28.902325', 'MEMBER', '649ce3a8-27f0-4173-81f1-90b19c6a5fbc', '9c8befda-78d3-42ed-894c-c512a92a2caf');