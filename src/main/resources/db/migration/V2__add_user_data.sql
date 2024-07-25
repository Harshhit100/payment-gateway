INSERT INTO public.user (id, first_name, last_name, username, email, "password")
VALUES (1, 'Loukik', 'Kalbande', 'louki', 'louki@ka.com', '$2a$10$MMOkMuO8zVcXl8YH2GrZSOYf/9zeC/sznGHRVzAq0T8.tzet7QJWq');
INSERT INTO public.user (id, first_name, last_name, username, email, "password")
VALUES (2, 'Aditya', 'verma', 'adi', 'adi@verma.com', '$2a$10$I5hOscIFqw73AU2/my0H0.vHAjI/rxXGcI49PB/jl8krTcM7VqkCy');
INSERT INTO public.user (id, first_name, last_name, username, email, "password")
VALUES (3, 'raman', 'Kumar', 'ram', 'ram@kum.com', '$2a$10$0grDMvQ7mSRLDAS6zuGOp.0ycwhgAzyE2FgLHzCV8KaXXP2TtGJ/W');

SELECT setval('user_seq', max(id)) FROM public.user;