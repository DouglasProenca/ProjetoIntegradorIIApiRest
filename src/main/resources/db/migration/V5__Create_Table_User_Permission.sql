CREATE TABLE rc_user_permission (
 id_user int not null,
 id_permission int not null,
)

alter table rc_user_permission add constraint fk_user_permission_user foreign key (id_user) references rc_user(id)
alter table rc_user_permission add constraint fk_user_permission_permission foreign key (id_permission) references rc_permission(id)