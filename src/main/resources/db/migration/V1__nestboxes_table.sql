create table if not exists
    nest_boxes
(
    id                    bigint auto_increment,
    nest_box_id           varchar(10)   unique      not null,
    placement_date        date                      not null,
    eov_x                 integer                   not null,
    eov_y                 integer                   not null,
    holder                varchar(100),
    height                decimal,
    orientation           varchar(9),
    nest_box_type         varchar(30),
    placement_reporter    varchar(100)              not null,
    expiry_date           date,
    expiry_desc           text,
    expiry_reporter       varchar(100),
    nest_box_condition    varchar(30)               not null,
    notes                 text,
    primary key (id)
);