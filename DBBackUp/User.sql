ALTER TABLE public.t_user
    ADD COLUMN wechat_number character varying(50);

ALTER TABLE public.t_user
    ADD COLUMN alias_name character varying(50);

ALTER TABLE public.t_user
    ADD COLUMN id_card character varying(50);

ALTER TABLE public.t_user
    ADD COLUMN bank_card character varying(50);

ALTER TABLE public.t_user
    ADD COLUMN supper_agent_id integer;