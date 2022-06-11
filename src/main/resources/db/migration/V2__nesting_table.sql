create table if not exists
    nesting
(
    id                    bigint auto_increment primary key,
    box_id           bigint       not null,
    survey_date           date         not null,
    species               varchar(45),
    eggs_number           tinyint,
    nestlings_number      tinyint,
    nestlings_age         varchar(255),
    scrambled_eggs_number tinyint,
    dead_nestlings_number tinyint,
    dead_adults_number    tinyint,
    mortality_desc        text,
    notes                 text,
    observer              varchar(100) not null,
    constraint `fk_nesting_nest_box`
        foreign key (box_id) references nest_boxes (id)
)