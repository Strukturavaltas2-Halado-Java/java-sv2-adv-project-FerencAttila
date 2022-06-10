create table if not exists
    nest_boxes
(
    id                    bigint auto_increment,
    nest_box_id           varchar(10) unique not null,
    placement_date        date,
    eov_x                 integer            not null,
    eov_y                 integer            not null,
    holder                varchar(255),
    height                decimal,
    orientation           varchar(9),
    nest_box_type         varchar(30),
    placement_reporter    varchar(255),
    expiry_date           date,
    expiry_desc           text,
    expiry_reporter       varchar(255),
    nest_box_condition    varchar(30),
    notes                 text,
    nesting_id            bigint,
    primary key (id)
);