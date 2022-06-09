create table if not exists
    nestboxes
(
    id                    bigint auto_increment,
    nestbox_id            varchar(10) unique not null,
    date_of_placement     date,
    eov_x                 integer            not null,
    eov_y                 integer            not null,
    holder                varchar(255),
    height                decimal,
    orientation           varchar(9),
    nestbox_type          varchar(30),
    reporter_of_placement varchar(255),
    date_of_expiry        date,
    desc_of_expiry        text,
    reporter_of_expiry    varchar(255),
    nestbox_condition     varchar(30),
    notes                 text,
    nesting_id            bigint,
    primary key (id)
);