# --- !Ups

create table pdb_data_ax (
  id                            uuid not null,
  cod_cliente                   varchar(255),
  nombre_cliente                varchar(255),
  id_descarga                   varchar(255),
  calle                         varchar(255),
  cuidad                        varchar(255),
  cod_postal                    varchar(255),
  provincia                     varchar(255),
  latitude                      float not null,
  longitude                     float not null,
  latitude2                     float not null,
  longitude2                    float not null,
  name                          varchar(255),
  confidence                    varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_data_ax primary key (id)
);

create table pdb_cae (
  id                            uuid not null,
  fid                           varchar(255),
  num                           varchar(255),
  name                          varchar(255),
  enterprise_id                 uuid,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_cae primary key (id)
);

create table pdb_charge_order (
  id                            uuid not null,
  fid                           varchar(255),
  code                          varchar(255),
  order_id                      uuid,
  provider_id                   uuid,
  expiration                    bigint not null,
  terminal_id                   uuid,
  cae_id                        uuid,
  status                        smallint,
  duplicated                    boolean default false not null,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_charge_order primary key (id)
);

create table pdb_charge_order_product (
  id                            uuid not null,
  charge_order_id               uuid not null,
  fid                           varchar(255),
  product_id                    uuid,
  quantity                      float not null,
  price                         float not null,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_charge_order_product primary key (id)
);

create table pdb_client (
  id                            uuid not null,
  fid                           varchar(255),
  name                          varchar(255),
  type                          varchar(255),
  client_code                   varchar(255),
  cif                           varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_client primary key (id)
);

create table pdb_enterprise (
  id                            uuid not null,
  fid                           varchar(255),
  cif                           varchar(255),
  name                          varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_enterprise primary key (id)
);

create table pdb_data_heptan (
  id                            uuid not null,
  base                          varchar(255),
  matricula                     varchar(255),
  fecha                         varchar(255),
  hora                          varchar(255),
  albaran                       varchar(255),
  cod_cliente                   varchar(255),
  id_descarga                   varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_data_heptan primary key (id)
);

create table pdb_location (
  id                            uuid not null,
  fid                           varchar(255),
  name                          varchar(255),
  address                       varchar(255),
  latitude                      float not null,
  longitude                     float not null,
  contact_name                  varchar(255),
  contact_phone                 varchar(255),
  hose_type                     varchar(255),
  connection_type               varchar(255),
  notes                         text,
  client_id                     uuid,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_location primary key (id)
);

create table pdb_lock (
  id                            uuid not null,
  name                          varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_lock primary key (id)
);

create table pdb_data_veosat (
  id                            uuid not null,
  matricula                     varchar(255),
  fecha                         bigint not null,
  latitude                      float not null,
  longitude                     float not null,
  status                        boolean default false not null,
  valid                         boolean default false not null,
  discard                       boolean default false not null,
  ts_processed                  bigint not null,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_data_veosat primary key (id)
);

create table pdb_order (
  id                            uuid not null,
  fid                           varchar(255),
  date                          bigint not null,
  terminal_id                   uuid,
  cae_id                        uuid,
  status                        smallint,
  notes                         text,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_order primary key (id)
);

create table pdb_order_client (
  id                            uuid not null,
  order_id                      uuid not null,
  fid                           varchar(255),
  client_id                     uuid,
  location_id                   uuid,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_order_client primary key (id)
);

create table pdb_order_product (
  id                            uuid not null,
  order_client_id               uuid not null,
  fid                           varchar(255),
  product_id                    uuid,
  quantity                      float not null,
  matched                       boolean default false not null,
  matched_quantity              float not null,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_order_product primary key (id)
);

create table pdb_permission (
  id                            uuid not null,
  role_id                       uuid not null,
  fid                           varchar(255),
  code                          smallint,
  name                          varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_permission primary key (id)
);

create table pdb_planning (
  id                            uuid not null,
  fid                           varchar(255),
  user_id                       uuid not null,
  order_id                      uuid not null,
  notes                         varchar(255),
  status                        smallint,
  ts_start                      bigint not null,
  ts_end                        bigint not null,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint uq_pdb_planning_order_id unique (order_id),
  constraint pk_pdb_planning primary key (id)
);

create table pdb_planning_task (
  id                            uuid not null,
  fid                           varchar(255),
  planning_id                   uuid,
  type                          smallint,
  client_name                   varchar(255),
  location_name                 varchar(255),
  terminal_name                 varchar(255),
  cae_num                       varchar(255),
  cae_name                      varchar(255),
  client_code                   varchar(255),
  charge_order_code             varchar(255),
  hose_type                     varchar(255),
  connection_type               varchar(255),
  product                       varchar(255),
  quantity                      float not null,
  address                       varchar(255),
  latitude                      float not null,
  longitude                     float not null,
  ts_planned                    bigint not null,
  ts_real                       bigint not null,
  status                        smallint,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_planning_task primary key (id)
);

create table pdb_planning_task_incidence (
  id                            uuid not null,
  fid                           varchar(255),
  notes                         text,
  planning_task_id              uuid,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_planning_task_incidence primary key (id)
);

create table pdb_product (
  id                            uuid not null,
  fid                           varchar(255),
  name                          varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_product primary key (id)
);

create table pdb_provider (
  id                            uuid not null,
  fid                           varchar(255),
  name                          varchar(255),
  provider_code                 varchar(255),
  client_code                   varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_provider primary key (id)
);

create table pdb_role (
  id                            uuid not null,
  fid                           varchar(255),
  type                          varchar(255),
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_role primary key (id)
);

create table pdb_sequence (
  name                          varchar(255) not null,
  value                         bigint,
  constraint pk_pdb_sequence primary key (name)
);

create table pdb_terminal (
  id                            uuid not null,
  fid                           varchar(255),
  name                          varchar(255),
  address                       varchar(255),
  latitude                      float not null,
  longitude                     float not null,
  propagate_client_code         boolean default false not null,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_terminal primary key (id)
);

create table pdb_terminal_pdb_provider (
  pdb_terminal_id               uuid not null,
  pdb_provider_id               uuid not null,
  constraint pk_pdb_terminal_pdb_provider primary key (pdb_terminal_id,pdb_provider_id)
);

create table pdb_user (
  id                            uuid not null,
  fid                           varchar(255),
  name                          varchar(255),
  surname                       varchar(255),
  login                         varchar(255),
  password                      varchar(255),
  role_id                       uuid,
  created                       timestamptz not null,
  updated                       timestamptz not null,
  constraint pk_pdb_user primary key (id)
);

create index ix_pdb_data_veosat_matricula on pdb_data_veosat (matricula);
alter table pdb_cae add constraint fk_pdb_cae_enterprise_id foreign key (enterprise_id) references pdb_enterprise (id) on delete restrict on update restrict;
create index ix_pdb_cae_enterprise_id on pdb_cae (enterprise_id);

alter table pdb_charge_order add constraint fk_pdb_charge_order_order_id foreign key (order_id) references pdb_order (id) on delete restrict on update restrict;
create index ix_pdb_charge_order_order_id on pdb_charge_order (order_id);

alter table pdb_charge_order add constraint fk_pdb_charge_order_provider_id foreign key (provider_id) references pdb_provider (id) on delete restrict on update restrict;
create index ix_pdb_charge_order_provider_id on pdb_charge_order (provider_id);

alter table pdb_charge_order add constraint fk_pdb_charge_order_terminal_id foreign key (terminal_id) references pdb_terminal (id) on delete restrict on update restrict;
create index ix_pdb_charge_order_terminal_id on pdb_charge_order (terminal_id);

alter table pdb_charge_order add constraint fk_pdb_charge_order_cae_id foreign key (cae_id) references pdb_cae (id) on delete restrict on update restrict;
create index ix_pdb_charge_order_cae_id on pdb_charge_order (cae_id);

alter table pdb_charge_order_product add constraint fk_pdb_charge_order_product_charge_order_id foreign key (charge_order_id) references pdb_charge_order (id) on delete restrict on update restrict;
create index ix_pdb_charge_order_product_charge_order_id on pdb_charge_order_product (charge_order_id);

alter table pdb_charge_order_product add constraint fk_pdb_charge_order_product_product_id foreign key (product_id) references pdb_product (id) on delete restrict on update restrict;
create index ix_pdb_charge_order_product_product_id on pdb_charge_order_product (product_id);

alter table pdb_location add constraint fk_pdb_location_client_id foreign key (client_id) references pdb_client (id) on delete restrict on update restrict;
create index ix_pdb_location_client_id on pdb_location (client_id);

alter table pdb_order add constraint fk_pdb_order_terminal_id foreign key (terminal_id) references pdb_terminal (id) on delete restrict on update restrict;
create index ix_pdb_order_terminal_id on pdb_order (terminal_id);

alter table pdb_order add constraint fk_pdb_order_cae_id foreign key (cae_id) references pdb_cae (id) on delete restrict on update restrict;
create index ix_pdb_order_cae_id on pdb_order (cae_id);

alter table pdb_order_client add constraint fk_pdb_order_client_order_id foreign key (order_id) references pdb_order (id) on delete restrict on update restrict;
create index ix_pdb_order_client_order_id on pdb_order_client (order_id);

alter table pdb_order_client add constraint fk_pdb_order_client_client_id foreign key (client_id) references pdb_client (id) on delete restrict on update restrict;
create index ix_pdb_order_client_client_id on pdb_order_client (client_id);

alter table pdb_order_client add constraint fk_pdb_order_client_location_id foreign key (location_id) references pdb_location (id) on delete restrict on update restrict;
create index ix_pdb_order_client_location_id on pdb_order_client (location_id);

alter table pdb_order_product add constraint fk_pdb_order_product_order_client_id foreign key (order_client_id) references pdb_order_client (id) on delete restrict on update restrict;
create index ix_pdb_order_product_order_client_id on pdb_order_product (order_client_id);

alter table pdb_order_product add constraint fk_pdb_order_product_product_id foreign key (product_id) references pdb_product (id) on delete restrict on update restrict;
create index ix_pdb_order_product_product_id on pdb_order_product (product_id);

alter table pdb_permission add constraint fk_pdb_permission_role_id foreign key (role_id) references pdb_role (id) on delete restrict on update restrict;
create index ix_pdb_permission_role_id on pdb_permission (role_id);

alter table pdb_planning add constraint fk_pdb_planning_user_id foreign key (user_id) references pdb_user (id) on delete restrict on update restrict;
create index ix_pdb_planning_user_id on pdb_planning (user_id);

alter table pdb_planning add constraint fk_pdb_planning_order_id foreign key (order_id) references pdb_order (id) on delete restrict on update restrict;

alter table pdb_planning_task add constraint fk_pdb_planning_task_planning_id foreign key (planning_id) references pdb_planning (id) on delete restrict on update restrict;
create index ix_pdb_planning_task_planning_id on pdb_planning_task (planning_id);

alter table pdb_planning_task_incidence add constraint fk_pdb_planning_task_incidence_planning_task_id foreign key (planning_task_id) references pdb_planning_task (id) on delete restrict on update restrict;
create index ix_pdb_planning_task_incidence_planning_task_id on pdb_planning_task_incidence (planning_task_id);

alter table pdb_terminal_pdb_provider add constraint fk_pdb_terminal_pdb_provider_pdb_terminal foreign key (pdb_terminal_id) references pdb_terminal (id) on delete restrict on update restrict;
create index ix_pdb_terminal_pdb_provider_pdb_terminal on pdb_terminal_pdb_provider (pdb_terminal_id);

alter table pdb_terminal_pdb_provider add constraint fk_pdb_terminal_pdb_provider_pdb_provider foreign key (pdb_provider_id) references pdb_provider (id) on delete restrict on update restrict;
create index ix_pdb_terminal_pdb_provider_pdb_provider on pdb_terminal_pdb_provider (pdb_provider_id);

alter table pdb_user add constraint fk_pdb_user_role_id foreign key (role_id) references pdb_role (id) on delete restrict on update restrict;
create index ix_pdb_user_role_id on pdb_user (role_id);


# --- !Downs

alter table if exists pdb_cae drop constraint if exists fk_pdb_cae_enterprise_id;
drop index if exists ix_pdb_cae_enterprise_id;

alter table if exists pdb_charge_order drop constraint if exists fk_pdb_charge_order_order_id;
drop index if exists ix_pdb_charge_order_order_id;

alter table if exists pdb_charge_order drop constraint if exists fk_pdb_charge_order_provider_id;
drop index if exists ix_pdb_charge_order_provider_id;

alter table if exists pdb_charge_order drop constraint if exists fk_pdb_charge_order_terminal_id;
drop index if exists ix_pdb_charge_order_terminal_id;

alter table if exists pdb_charge_order drop constraint if exists fk_pdb_charge_order_cae_id;
drop index if exists ix_pdb_charge_order_cae_id;

alter table if exists pdb_charge_order_product drop constraint if exists fk_pdb_charge_order_product_charge_order_id;
drop index if exists ix_pdb_charge_order_product_charge_order_id;

alter table if exists pdb_charge_order_product drop constraint if exists fk_pdb_charge_order_product_product_id;
drop index if exists ix_pdb_charge_order_product_product_id;

alter table if exists pdb_location drop constraint if exists fk_pdb_location_client_id;
drop index if exists ix_pdb_location_client_id;

alter table if exists pdb_order drop constraint if exists fk_pdb_order_terminal_id;
drop index if exists ix_pdb_order_terminal_id;

alter table if exists pdb_order drop constraint if exists fk_pdb_order_cae_id;
drop index if exists ix_pdb_order_cae_id;

alter table if exists pdb_order_client drop constraint if exists fk_pdb_order_client_order_id;
drop index if exists ix_pdb_order_client_order_id;

alter table if exists pdb_order_client drop constraint if exists fk_pdb_order_client_client_id;
drop index if exists ix_pdb_order_client_client_id;

alter table if exists pdb_order_client drop constraint if exists fk_pdb_order_client_location_id;
drop index if exists ix_pdb_order_client_location_id;

alter table if exists pdb_order_product drop constraint if exists fk_pdb_order_product_order_client_id;
drop index if exists ix_pdb_order_product_order_client_id;

alter table if exists pdb_order_product drop constraint if exists fk_pdb_order_product_product_id;
drop index if exists ix_pdb_order_product_product_id;

alter table if exists pdb_permission drop constraint if exists fk_pdb_permission_role_id;
drop index if exists ix_pdb_permission_role_id;

alter table if exists pdb_planning drop constraint if exists fk_pdb_planning_user_id;
drop index if exists ix_pdb_planning_user_id;

alter table if exists pdb_planning drop constraint if exists fk_pdb_planning_order_id;

alter table if exists pdb_planning_task drop constraint if exists fk_pdb_planning_task_planning_id;
drop index if exists ix_pdb_planning_task_planning_id;

alter table if exists pdb_planning_task_incidence drop constraint if exists fk_pdb_planning_task_incidence_planning_task_id;
drop index if exists ix_pdb_planning_task_incidence_planning_task_id;

alter table if exists pdb_terminal_pdb_provider drop constraint if exists fk_pdb_terminal_pdb_provider_pdb_terminal;
drop index if exists ix_pdb_terminal_pdb_provider_pdb_terminal;

alter table if exists pdb_terminal_pdb_provider drop constraint if exists fk_pdb_terminal_pdb_provider_pdb_provider;
drop index if exists ix_pdb_terminal_pdb_provider_pdb_provider;

alter table if exists pdb_user drop constraint if exists fk_pdb_user_role_id;
drop index if exists ix_pdb_user_role_id;

drop table if exists pdb_data_ax cascade;

drop table if exists pdb_cae cascade;

drop table if exists pdb_charge_order cascade;

drop table if exists pdb_charge_order_product cascade;

drop table if exists pdb_client cascade;

drop table if exists pdb_enterprise cascade;

drop table if exists pdb_data_heptan cascade;

drop table if exists pdb_location cascade;

drop table if exists pdb_lock cascade;

drop table if exists pdb_data_veosat cascade;

drop table if exists pdb_order cascade;

drop table if exists pdb_order_client cascade;

drop table if exists pdb_order_product cascade;

drop table if exists pdb_permission cascade;

drop table if exists pdb_planning cascade;

drop table if exists pdb_planning_task cascade;

drop table if exists pdb_planning_task_incidence cascade;

drop table if exists pdb_product cascade;

drop table if exists pdb_provider cascade;

drop table if exists pdb_role cascade;

drop table if exists pdb_sequence cascade;

drop table if exists pdb_terminal cascade;

drop table if exists pdb_terminal_pdb_provider cascade;

drop table if exists pdb_user cascade;

drop index if exists ix_pdb_data_veosat_matricula;
