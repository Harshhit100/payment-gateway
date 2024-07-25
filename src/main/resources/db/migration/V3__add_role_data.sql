INSERT INTO public.role (id, "type") VALUES (1, 'USER');
INSERT INTO public.role (id, "type") VALUES (2, 'ADMIN');

SELECT setval('role_seq', max(id)) FROM public.role;